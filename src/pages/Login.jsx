import { LockOutlined } from "@mui/icons-material";
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
const Login = () => {
    const ctx = useContext(Context);

    const onSubmit = (event) => {
        event.preventDefault(true);
        ctx.login(
            {
                email: event.target[0].value,
                password: event.target[2].value,
            },
            0
        );
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
                        <LockOutlined />
                    </Avatar>
                    <Typography component="h1" variant="h4" alignSelf="center">
                        Logowanie
                    </Typography>
                </Box>

                <Box
                    component="form"
                    validate
                    sx={{ mt: 1 }}
                    onSubmit={onSubmit}
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
                    <TextField
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Hasło"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                    />
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        sx={{ mt: 3, mb: 2 }}
                    >
                        Zaloguj się
                    </Button>
                    <Grid container justifyContent="right">
                        <Grid item>
                            <Link
                                to="/register"
                                variant="body2"
                                component={ReactLink}
                            >
                                {"Rejestracja"}
                            </Link>
                        </Grid>
                    </Grid>
                </Box>
            </Container>
        </ThemeProvider>
    );
};

export default Login;
