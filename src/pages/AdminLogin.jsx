import { LockOutlined } from "@mui/icons-material";
import {
    Avatar,
    Button,
    Container,
    CssBaseline,
    TextField,
    Typography,
} from "@mui/material";
import { Box, ThemeProvider } from "@mui/system";
import { useContext } from "react";
import { theme } from "../App";
import Context from "../store/context";

const AdminLogin = () => {
    const ctx = useContext(Context);

    const onSubmit = (event) => {
        event.preventDefault(true);
        ctx.login(
            {
                email: event.target[0].value,
                password: event.target[2].value,
            },
            1
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
                        Admin panel
                    </Typography>
                </Box>

                <Box
                    component="form"
                    noValidate
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
                </Box>
            </Container>
        </ThemeProvider>
    );
};

export default AdminLogin;
