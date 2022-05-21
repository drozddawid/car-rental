import {
    AppBar,
    Box,
    Button,
    IconButton,
    MenuItem,
    Toolbar,
    Typography,
} from "@mui/material";
import React, { useContext } from "react";
import { Link as ReactLink } from "react-router-dom";
import Context from "../store/context";

const Navbar = (props) => {
    const logoutHandler = () => {
        ctx.logout();
    };

    const ctx = useContext(Context);
    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <IconButton
                        size="large"
                        edge="start"
                        color="inherit"
                        aria-label="menu"
                        sx={{ mr: 2 }}
                    >
                        <MenuItem />
                    </IconButton>
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1 }}
                    >
                        Wypozyżyczalnia samochodów
                    </Typography>
                    {!ctx.isLoggedIn ? (
                        <Button
                            color="primary"
                            to="/login"
                            component={ReactLink}
                        >
                            Zaloguj się
                        </Button>
                    ) : (
                        <Button
                            color="primary"
                            onClick={logoutHandler}
                            variant="contained"
                        >
                            Wyloguj się
                        </Button>
                    )}
                </Toolbar>
            </AppBar>
        </Box>
    );
};

export default Navbar;
