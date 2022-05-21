import {
    Box,
    Button,
    Dialog,
    DialogContent,
    DialogContentText,
    DialogTitle,
    FormControl,
    InputLabel,
    MenuItem,
    Select,
    TextField,
} from "@mui/material";
import moment from "moment";
import React, { useEffect, useState } from "react";

const CarForm = (props) => {
    const [selectedRental, setSelectedRental] = useState("");
    const [selectedModel, setSelectedModel] = useState("");
    const [selectedRegNum, setSelectedRegNum] = useState("");
    const [selectedProdDate, setSelectedProdDate] = useState(
        moment().format("YYYY-MM-DD")
    );
    const [selectedRegDate, setSelectedRegDate] = useState(
        moment().format("YYYY-MM-DD")
    );
    const [selectedCheckDate, setSelectedCheckDate] = useState(
        moment().format("YYYY-MM-DD")
    );
    const [totalKm, setTotalKm] = useState("");

    useEffect(() => {
        if (!props.edit) return;
        if (props.selectedRental !== undefined)
            setSelectedRental(props.selectedRental);
        if (props.selectedModel !== undefined)
            setSelectedModel(props.selectedModel);
        if (props.selectedRegNum !== undefined)
            setSelectedRegNum(props.selectedRegNum);
        if (props.selectedProdDate !== undefined)
            setSelectedProdDate(props.selectedProdDate);
        if (props.selectedRegDate !== undefined)
            setSelectedRegDate(props.selectedRegDate);
        if (props.selectedCheckDate !== undefined)
            setSelectedCheckDate(props.selectedCheckDate);
        if (props.totalKm !== undefined) setTotalKm(props.totalKm);
    }, [
        props.selectedModel,
        props.selectedRental,
        props,
        props.selectedProdDate,
        props.selectedRegDate,
        props.totalKm,
    ]);

    const handleLicensePlateChange = (event) => {
        setSelectedRegNum(event.target.value);
    };

    const handleProductionDateChange = (event) => {
        setSelectedProdDate(event.target.value);
    };

    const handleRegistrationDateChange = (event) => {
        setSelectedRegDate(event.target.value);
    };

    const handleCarReviewDateChange = (event) => {
        setSelectedCheckDate(event.target.value);
    };

    const handleCarMileageChange = (event) => {
        setTotalKm(event.target.value);
    };

    const handleRentalChange = (event) => {
        setSelectedRental(event.target.value);
    };

    const handleModelChange = (event) => {
        setSelectedModel(event.target.value);
    };

    const submitHandler = (event) => {
        event.preventDefault();
        let data = {
            rental: selectedRental,
            carModel: selectedModel,
            prodDate: selectedProdDate,
            regDate: selectedRegDate,
            checkDate: selectedCheckDate,
            regNum: selectedRegNum,
            totalKm: totalKm,
        };

        props.onSubmit(data);
    };

    return (
        <Dialog open={props.isOpen} onClose={props.onClose}>
            <DialogTitle>Dodaj samochód</DialogTitle>
            <DialogContent>
                <DialogContentText>
                    Aby wprowadzić zmiany danych wymagane jest wprowadzenie
                    informacji o tym pojeździe.
                </DialogContentText>
                <Box component="form" onSubmit={submitHandler}>
                    <FormControl fullWidth sx={{ margin: "10px 0px" }} required>
                        <InputLabel id="rental-label">Wypożyczalnia</InputLabel>
                        <Select
                            labelId="rental-label"
                            id="rental-select"
                            value={selectedRental}
                            label="Wypożyczalnia"
                            onChange={handleRentalChange}
                        >
                            {Object.values(props.rentPlaces).map((place) => (
                                <MenuItem value={place.id} key={place.id}>
                                    {place.miasto + ", " + place.adres}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>

                    <FormControl fullWidth sx={{ margin: "10px 0px" }} required>
                        <InputLabel id="model-label">
                            Model samochodu
                        </InputLabel>
                        <Select
                            labelId="model-label"
                            id="model-select"
                            value={selectedModel}
                            label="Model samochodu"
                            onChange={handleModelChange}
                        >
                            {Object.values(props.models).map((model) => (
                                <MenuItem
                                    value={model.idModelu}
                                    key={model.idModelu}
                                >
                                    {model.marka + " " + model.modelSamochodu}
                                </MenuItem>
                            ))}
                        </Select>
                    </FormControl>
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        label="Numer rejestracyjny"
                        type="text"
                        id="nr-rejestracyjny"
                        value={selectedRegNum}
                        onChange={handleLicensePlateChange}
                    />
                    <TextField
                        sx={{ margin: "10px 0px" }}
                        required
                        fullWidth
                        label="Data produkcji"
                        type="date"
                        id="production-date"
                        value={selectedProdDate}
                        onChange={handleProductionDateChange}
                    />
                    <TextField
                        sx={{ margin: "10px 0px" }}
                        required
                        fullWidth
                        label="Data rejestracji"
                        type="date"
                        id="register-date"
                        value={selectedRegDate}
                        onChange={handleRegistrationDateChange}
                    />

                    <TextField
                        sx={{ margin: "10px 0px" }}
                        required
                        fullWidth
                        label="Data przeglądu"
                        type="date"
                        id="check-date"
                        value={selectedCheckDate}
                        onChange={handleCarReviewDateChange}
                    />

                    <TextField
                        sx={{ margin: "10px 0px" }}
                        required
                        fullWidth
                        label="Przebieg"
                        type="number"
                        id="total-km"
                        value={totalKm}
                        onChange={handleCarMileageChange}
                    />
                    <Button type="submit" variant="contained">
                        {props.edit ? "Edytuj" : "Dodaj"}
                    </Button>
                    <Button onClick={props.onClose}>Anuluj</Button>
                </Box>
            </DialogContent>
        </Dialog>
    );
};

export default CarForm;
