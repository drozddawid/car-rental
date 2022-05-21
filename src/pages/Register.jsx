import { AccountCircle } from "@mui/icons-material";
import {
    Avatar,
    Button,
    Container,
    CssBaseline,
    Grid,
    Link,
    TextField,
    Typography,
} from "@mui/material";
import { Box, ThemeProvider } from "@mui/system";
import { useContext } from "react";
import { Link as ReactLink } from "react-router-dom";
import { theme } from "../App";
import Context from "../store/context";

const Register = () => {
    const ctx = useContext(Context);

    const handleSubmit = (event) => {
        event.preventDefault(true);
        ctx.register({
            email: event.target[0].value,
            password: event.target[2].value,
        });
    };

    return (
        <ThemeProvider theme={theme}>
            <Container
                component="main"
                maxWidth="xs"
                sx={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center",
                    height: "100vh",
                }}
            >
                <CssBaseline />
                <Box
                    sx={{
                        display: "flex",
                        alignItems: "right",
                        textAlign: "center",
                    }}
                >
                    <Avatar sx={{ m: 1, bgcolor: "primary.main" }}>
                        <AccountCircle />
                    </Avatar>
                    <Typography component="h1" variant="h4" alignSelf="center">
                        Rejestracja
                    </Typography>
                </Box>

                <Box
                    component="form"
                    noValidate
                    sx={{ mt: 1 }}
                    onSubmit={handleSubmit}
                >
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        id="email"
                        label="Email"
                        name="email"
                        autoComplete="email"
                        autoFocus
                    />
                    <Grid container spacing={2}>
                        <Grid item xs={6}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                label="Hasło"
                                type="password"
                                id="password"
                                autoComplete="current-password"
                            />
                        </Grid>
                        <Grid item xs={6}>
                            <TextField
                                margin="normal"
                                required
                                fullWidth
                                label="Powtorz hasło"
                                type="password"
                                autoComplete="current-password"
                            />
                        </Grid>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Zarejestruj się
                    </Button>
                    <Grid container justifyContent="right">
                        <Grid item>
                            <Link
                                to="/login"
                                variant="body2"
                                component={ReactLink}
                            >
                                {"Logowanie"}
                            </Link>
                        </Grid>
                    </Grid>
                </Box>
            </Container>
        </ThemeProvider>
    );
};

export default Register;
