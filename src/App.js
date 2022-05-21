import { Alert, createTheme, Snackbar, ThemeProvider } from "@mui/material";
import { useContext } from "react";
import { Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import AdminView from "./components/AdminView";
import Loading from "./components/UI/Loading";
import AdminLogin from "./pages/AdminLogin";
import Home from "./pages/Home";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Context from "./store/context";
export const theme = createTheme();

theme.palette.primary.main = "#000";

function App() {
    let ctx = useContext(Context);
    return (
        <ThemeProvider theme={theme}>
            <Snackbar open={ctx.error.isError} autoHideDuration={6000}>
                <Alert severity="error" sx={{ width: "100%" }}>
                    {ctx.error.message}
                </Alert>
            </Snackbar>
            <Snackbar open={ctx.success.isSuccess} autoHideDuration={6000}>
                <Alert severity="success" sx={{ width: "100%" }}>
                    {ctx.success.message}
                </Alert>
            </Snackbar>
            <Loading isOpen={ctx.isLoading} />
            <Routes>
                <Route path="/" element={<Home />} />
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route
                    path="/admin"
                    element={
                        !ctx.isLoggedIn ? (
                            <Navigate to="/admin/login" replace />
                        ) : (
                            <AdminView />
                        )
                    }
                />
                <Route path="/admin/login" element={<AdminLogin />} />
            </Routes>
        </ThemeProvider>
    );
}

export default App;
