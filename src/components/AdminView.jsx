import { Box, Button, Container, Typography } from "@mui/material";
import { useCallback, useContext, useEffect, useState } from "react";
import { config } from "../config";
import Context from "../store/context";
import CarForm from "./CarForm";
import Navbar from "./Navbar";
import List from "./UI/List";

const SERVER_ADDRESS = config.serverAddress;
function createCarRow(
    id,
    lokalizacja,
    model,
    nr_rejestracyjny,
    data_produkcji,
    data_rejestracji,
    data_przegladu,
    przebieg,
    usun,
    edytuj
) {
    return {
        id,
        lokalizacja,
        model,
        nr_rejestracyjny,
        data_produkcji,
        data_rejestracji,
        data_przegladu,
        przebieg,
        usun,
        edytuj,
    };
}

function createRentPlaceRow(id, miasto, adres) {
    return { id, miasto, adres };
}

function createModelRow(
    idModelu,
    marka,
    modelSamochodu,
    cenaWyp,
    spalanie,
    miejsca,
    drzwi,
    pojemnoscZbiornika,
    typPaliwa,
    pojemnoscSilnika,
    moc,
    rodzajKaroserii
) {
    return {
        idModelu,
        marka,
        modelSamochodu,
        cenaWyp,
        spalanie,
        miejsca,
        drzwi,
        pojemnoscZbiornika,
        typPaliwa,
        pojemnoscSilnika,
        moc,
        rodzajKaroserii,
    };
}

function isLaterOrTheSame(firstDate, secondDate) {
    let first = Date.parse(firstDate);
    let second = Date.parse(secondDate);
    return first <= second;
}

const AdminView = () => {
    const [currFormData, setCurrFormData] = useState({
        rental: undefined,
        carModel: undefined,
        prodDate: undefined,
        regDate: undefined,
        checkDate: undefined,
        regNum: undefined,
        totalKm: undefined,
    });

    const [isAddFormOpen, setIsAddFormOpen] = useState(false);

    const [rentPlaces, setRentPlaces] = useState(["Wczytywanie..."]);

    const [models, setModels] = useState(["Wczytywanie..."]);

    //contains data for adding car [0]licensePlate, [1]productionDate, [2]registrationDate, [3]carReviewDate, [4]carMileage

    const ctx = useContext(Context);

    //contains all cars
    const [cars, setCars] = useState([createCarRow("Wczytywanie...")]);

    const getCarById = useCallback(
        (id) => {
            let car = null;
            for (const tmp of cars) {
                if (tmp.id === id) {
                    car = tmp;
                }
            }
            return car;
        },
        [cars]
    );

    const getModelByName = useCallback(
        (name) => {
            let model = null;
            for (const tmp of models) {
                if (tmp.modelSamochodu === name) {
                    model = tmp;
                }
            }
            return model;
        },
        [models]
    );

    const getRentalByLoc = useCallback(
        (name) => {
            let rental = null;
            for (const tmp of rentPlaces) {
                if (name.includes(tmp.adres) && name.includes(tmp.miasto)) {
                    rental = tmp;
                }
            }
            return rental;
        },
        [rentPlaces]
    );

    const [edit, setEdit] = useState({ isEdit: false, id: null });

    const getCars = useCallback(() => {
        const xhttp = new XMLHttpRequest();
        let json;
        let obj;
        xhttp.onreadystatechange = function () {
            let rows;
            if (this.readyState === 4 && this.status === 200) {
                json = xhttp.responseText;

                obj = JSON.parse(json);

                rows = obj.answer.map((ans) => {
                    return createCarRow(
                        ans.id,
                        ans.idWypozyczalni.miasto +
                            " " +
                            ans.idWypozyczalni.adres,
                        ans.idModelu.modelSamochodu,
                        ans.nrRejestracyjny,
                        ans.dataProdukcji,
                        ans.dataRejestracji,
                        ans.dataPrzegladu,
                        ans.przebieg
                    );
                });

                setCars(rows);
            }
            if (this.readyState === 4 && this.status === 400) {
                rows = [createCarRow("Brak uprawnień.")];
                setCars(rows);
                console.log("No access.");
            }
        };
        xhttp.open(
            "GET",
            `http://localhost:8080/data/samochody?authToken=${ctx.authToken.authToken}`,
            true,
            null,
            null
        );
        xhttp.send();
    }, [ctx]);

    const deleteCar = useCallback(
        async (id) => {
            let carToDeleteId = id;
            console.log("Car deleted: " + id);
            let car = getCarById(carToDeleteId);
            if (car !== null) {
                console.log(car);
                const resp = await fetch(
                    `${SERVER_ADDRESS}/data/samochody/${car.id}?authToken=${ctx.authToken.authToken}`,
                    { method: "DELETE" }
                );
                if (!resp.ok) {
                    ctx.showErrorAlert(
                        "Usunięcie samochodu nie było pomyślne."
                    );
                    console.log(await resp.json());
                } else {
                    ctx.showSuccessAlert(
                        "Pomyślnie usunięto samochód z bazy danych."
                    );
                }
            }
            getCars();
        },
        [getCarById, ctx, getCars]
    );

    const editCar = useCallback(
        (id) => {
            console.log("Car edited: " + id);
            setEdit({ isEdit: true, id: id });

            let car = getCarById(id);
            setCurrFormData({
                carModel: getModelByName(car.model).idModelu,
                checkDate: car.data_przegladu,
                prodDate: car.data_produkcji,
                regDate: car.data_rejestracji,
                regNum: car.nr_rejestracyjny,
                rental: getRentalByLoc(car.lokalizacja).id,
                totalKm: car.przebieg,
            });
            setIsAddFormOpen(true);
        },
        [setIsAddFormOpen, getCarById, getModelByName, getRentalByLoc]
    );

    const modifyCar = useCallback(
        async (carData) => {
            ctx.setIsLoading(true);
            if (edit.isEdit) {
                if (isLaterOrTheSame(carData.prodDate, carData.regDate)) {
                    if (isLaterOrTheSame(carData.regDate, carData.checkDate)) {
                        let payload = {
                            id: edit.id,
                            idWypozyczalni: rentPlaces.find(
                                (rental) => rental.id === carData.rental
                            ),
                            idModelu: models.find(
                                (model) => model.idModelu === carData.carModel
                            ),
                            nrRejestracyjny: carData.regNum,
                            dataProdukcji: carData.prodDate,
                            dataRejestracji: carData.regDate,
                            dataPrzegladu: carData.checkDate,
                            przebieg: carData.totalKm,
                        };

                        console.log(payload);
                        const reqAddress = `${SERVER_ADDRESS}/data/samochody/${edit.id}?authToken=${ctx.authToken.authToken}`;
                        const resp = await fetch(reqAddress, {
                            method: "PUT",
                            body: JSON.stringify(payload),
                            headers: {
                                "Content-Type": "application/json",
                            },
                        });
                        if (resp.ok) {
                            ctx.showSuccessAlert(
                                "Pomyślnie zaktualizowano samochód"
                            );
                            console.log(await resp.json());
                        } else {
                            let jsonResp = await resp.json();
                            ctx.showErrorAlert(
                                "Nie można było edytować samochodu: ",
                                +jsonResp.message
                            );
                            console.log(jsonResp);
                        }
                    } else {
                        ctx.showErrorAlert(
                            "Nie dodano, data przeglądu nie może być wcześniejsza niż data rejestracji."
                        );
                    }
                } else {
                    ctx.showErrorAlert(
                        "Nie dodano, data rejestracji nie może być wcześniejsza niż data produkcji."
                    );
                }
            } else {
                let rentPlace = null;
                for (const place of rentPlaces) {
                    if (place.id === carData.rental) {
                        rentPlace = place;
                    }
                }
                if (rentPlace == null) {
                    ctx.showErrorAlert(
                        "Nie dodano, nieprawidłowa wypożyczalnia."
                    );
                } else {
                    let carModel = null;
                    for (const model of models) {
                        if (model.idModelu === carData.carModel) {
                            carModel = model;
                        }
                    }
                    if (carModel == null) {
                        ctx.showErrorAlert("Nie dodano, nieprawidłowy model.");
                    } else {
                        if (
                            isLaterOrTheSame(carData.prodDate, carData.regDate)
                        ) {
                            if (
                                isLaterOrTheSame(
                                    carData.regDate,
                                    carData.checkDate
                                )
                            ) {
                                let car = {
                                    idSamochodu: edit.id,
                                    idWypozyczalni: rentPlace,
                                    idModelu: carModel,
                                    nrRejestracyjny: carData.regNum,
                                    dataProdukcji: carData.prodDate,
                                    dataRejestracji: carData.regDate,
                                    dataPrzegladu: carData.checkDate,
                                    przebieg: carData.totalKm,
                                };
                                let carJSON = JSON.stringify(car, null, 1);
                                console.log(carJSON);
                                const xhttp = new XMLHttpRequest();
                                xhttp.onreadystatechange = function () {
                                    if (
                                        this.readyState === 4 &&
                                        this.status === 200
                                    ) {
                                        let jsonResponse = xhttp.responseText;
                                        console.log(jsonResponse);
                                        ctx.showSuccessAlert(
                                            "Dodano pomyślnie."
                                        );

                                        getCars();
                                    } else if (
                                        this.readyState === 4 &&
                                        this.status === 400
                                    ) {
                                        let jsonResponse = xhttp.responseText;
                                        let response = JSON.parse(jsonResponse);
                                        if (
                                            response.answer ===
                                            "Entity with the same license plate exists."
                                        ) {
                                            ctx.showErrorAlert(
                                                "Nie dodano, samochód o podanym numerze rejestracyjnym już istnieje."
                                            );
                                        } else {
                                            ctx.showErrorAlert("Nie dodano");
                                        }
                                        console.log(jsonResponse);
                                    } else if (this.readyState === 4) {
                                        ctx.showErrorAlert("Nie dodano");

                                        let jsonResponse = xhttp.responseText;
                                        console.log(jsonResponse);
                                    }
                                };
                                xhttp.open(
                                    "POST",
                                    `http://localhost:8080/data/samochody?authToken=${ctx.authToken.authToken}`,
                                    true,
                                    null,
                                    null
                                );
                                xhttp.setRequestHeader(
                                    "Content-Type",
                                    "application/json;charset=UTF-8"
                                );
                                xhttp.send(carJSON);
                            } else {
                                ctx.showErrorAlert(
                                    "Nie dodano, data przeglądu nie może być wcześniejsza niż data rejestracji."
                                );
                            }
                        } else {
                            ctx.showErrorAlert(
                                "Nie dodano, data rejestracji nie może być wcześniejsza niż data produkcji."
                            );
                        }
                    }
                }
            }
            setIsAddFormOpen(false);
        },
        [
            getCars,
            models,
            rentPlaces,
            edit.isEdit,
            edit.id,
            ctx.authToken.authToken,
            ctx.showErrorAlert,
            ctx.showSuccessAlert,
            ctx.setIsLoading,
        ]
    );

    const toggleForm = () => {
        setIsAddFormOpen(!isAddFormOpen);
        setEdit({ isEdit: false, id: null });
    };

    const getRentPlaces = useCallback(() => {
        const xhttp = new XMLHttpRequest();
        let json;
        let obj;
        xhttp.onreadystatechange = function () {
            let rows;
            if (this.readyState === 4 && this.status === 200) {
                json = xhttp.responseText;
                obj = JSON.parse(json);
                rows = obj.answer.map((ans) => {
                    return createRentPlaceRow(ans.id, ans.miasto, ans.adres);
                });
                setRentPlaces(rows);
            } else if (this.readyState === 4 && this.status === 400) {
                rows = [createCarRow("Brak uprawnień.")];
                setRentPlaces(rows);
                console.log("No access.");
            }
        };
        xhttp.open(
            "GET",
            `http://localhost:8080/data/wypozyczalnie?authToken=${ctx.authToken.authToken}`,
            true,
            null,
            null
        );
        xhttp.send();
    }, [ctx.authToken]);

    const getModels = useCallback(() => {
        const xhttp = new XMLHttpRequest();
        let json;
        let obj;
        xhttp.onreadystatechange = function () {
            let rows;
            if (this.readyState === 4 && this.status === 200) {
                json = xhttp.responseText;
                obj = JSON.parse(json);
                rows = obj.answer.map((ans) => {
                    return createModelRow(
                        ans.idModelu,
                        ans.marka,
                        ans.modelSamochodu,
                        ans.cenaWyp,
                        ans.spalanie,
                        ans.miejsca,
                        ans.drzwi,
                        ans.pojemnoscZbiornika,
                        ans.typPaliwa,
                        ans.pojemnoscSilnika,
                        ans.moc,
                        ans.rodzajKaroserii
                    );
                });
                setModels(rows);
            }
            if (this.readyState === 4 && this.status === 400) {
                rows = [createCarRow("Brak uprawnień.")];
                setRentPlaces(rows);
                console.log("No access.");
            }
        };
        xhttp.open(
            "GET",
            `http://localhost:8080/data/modele?authToken=${ctx.authToken.authToken}`,
            true,
            null,
            null
        );
        xhttp.send();
    }, [ctx.authToken]);

    useEffect(() => {
        //All onLoad actions are taken, for example we fetch all rentals and car models from servers.
        if (!ctx.isLoggedIn) return;
        ctx.setIsLoading(true);
        getCars();
        getRentPlaces();
        getModels();
        ctx.setIsLoading(false);
    }, [ctx, getCars, getRentPlaces, getModels]);
    return (
        <>
            <Navbar />
            <CarForm
                isOpen={isAddFormOpen}
                onClose={toggleForm}
                onSubmit={modifyCar}
                rentPlaces={rentPlaces}
                models={models}
                selectedRental={currFormData.rental}
                selectedModel={currFormData.carModel}
                selectedProdDate={currFormData.prodDate}
                selectedRegDate={currFormData.regDate}
                selectedCheckDate={currFormData.checkDate}
                selectedRegNum={currFormData.regNum}
                totalKm={currFormData.totalKm}
                edit={edit.isEdit}
            />
            <Container sx={{ padding: "10px" }}>
                <Box
                    sx={{
                        display: "flex",
                        justifyContent: "space-between",
                        alignItems: "center",
                    }}
                >
                    <Typography variant="h4" sx={{ margin: "10px" }}>
                        Lista samochodów
                    </Typography>
                    <Button
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                        onClick={toggleForm}
                    >
                        Dodaj samochód
                    </Button>
                </Box>
                <List
                    showButtons={true}
                    rows={cars}
                    onDelete={deleteCar}
                    onEdit={editCar}
                    headers={[
                        "Id",
                        "Lokalizacja",
                        "Model",
                        "Nr rejestracyjny",
                        "Data produkcji",
                        "Data rejestracji",
                        "Data przeglądu",
                        "Przebieg",
                    ]}
                />
            </Container>
        </>
    );
};

export default AdminView;
