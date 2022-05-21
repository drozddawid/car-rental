import { Alert, Container } from "@mui/material";
import React from "react";
const NotLoggedIn = () => {
    return (
        <Container sx={{ padding: "10px" }}>
            <Alert severity="error" variant="filled">
                Wymagane zalogowanie
            </Alert>
        </Container>
    );
};

export default NotLoggedIn;
