import { Container, Typography } from "@mui/material";
import React, { useCallback, useContext, useEffect, useState } from "react";
import Context from "../store/context";
import List from "./UI/List";

function createCarRow(
    idSam,
    status,
    miasto,
    marka,
    model,
    dataProdukcji,
    cena,
    spalanie,
    lMiejsc,
    lDrzwi,
    pojemnoscZbiornika,
    typPaliwa,
    pojemnoscSilnika,
    moc,
    rodzajKaroserii
) {
    return {
        idSam,
        status,
        miasto,
        marka,
        model,
        dataProdukcji,
        cena,
        spalanie,
        lMiejsc,
        lDrzwi,
        pojemnoscZbiornika,
        typPaliwa,
        pojemnoscSilnika,
        moc,
        rodzajKaroserii,
    };
}

const UserView = () => {
    const ctx = useContext(Context);

    const [cars, setCars] = useState([
        createCarRow(
            "Wczytywanie...",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
        ),
    ]);

    const getCars = useCallback(() => {
        const xhttp = new XMLHttpRequest();
        let json;
        let obj;
        xhttp.onreadystatechange = function () {
            let rows;
            if (this.readyState === 4 && this.status === 200) {
                json = xhttp.responseText;
                console.log(json);
                obj = JSON.parse(json);

                rows = obj.answer.map((ans) => {
                    return createCarRow(
                        ans.idSam,
                        ans.status,
                        ans.miasto,
                        ans.marka,
                        ans.model,
                        ans.dataProdukcji,
                        ans.cena,
                        ans.spalanie,
                        ans.lmiejsc,
                        ans.ldrzwi,
                        ans.pojemnoscZbiornika,
                        ans.typPaliwa,
                        ans.pojemnoscSilnika,
                        ans.moc,
                        ans.rodzajKaroserii
                    );
                });
                setCars(rows);
                console.log(rows);
            }
            if (this.readyState === 4 && this.status === 400) {
                rows = [createCarRow("Brak uprawnień.")];
                setCars(rows);
                console.log("No access.");
            }
        };

        xhttp.open(
            "GET",
            `http://localhost:8080/data/dostepnesamochody?authToken=${ctx.authToken.authToken}`,
            true,
            null,
            null
        );
        xhttp.send();
    }, [ctx.authToken, setCars]);

    useEffect(() => {
        //All onLoad actions are taken, for example we fetch all rentals and car models from servers.
        if (!ctx.isLoggedIn) {
            console.log("User not logged in.");
            return;
        }
        ctx.setIsLoading(true);
        getCars();
        ctx.setIsLoading(false);
    }, [ctx, getCars]);
    return (
        <Container sx={{ padding: "10px" }}>
            <Typography variant="h4" sx={{ margin: "10px" }}>
                Dostępne samochody
            </Typography>
            <List
                showButtons={false}
                rows={cars}
                headers={[
                    "ID",
                    "Dostępność",
                    "Miasto",
                    "Marka",
                    "Model",
                    "Data produkcji",
                    "Cena (za dzień)",
                    "Spalanie[L/100km]",
                    "L. Miejsc",
                    "L. Drzwi",
                    "Pojemnosc zbiornika",
                    "Rodzaj paliwa",
                    "Pojemnosc silnika",
                    "Moc[KM]",
                    "Karoseria",
                ]}
            />
        </Container>
    );
};

export default UserView;
