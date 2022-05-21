import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { config } from "../config";

const Context = React.createContext({
    isLoggedIn: false,
    authToken: null,
    isLoading: false,
    setIsLoading: (isLoading) => {},
    login: (loginData, permissionLevel) => {},
    register: (registerData) => {},
    logout: async () => {},
    error: { isError: false, message: null },
    success: { isError: false, message: null },
    showErrorAlert: (message) => {},
    showSuccessAlert: (message) => {},
});

const SERVER_ADDRESS = config.serverAddress;

export const ContextProvider = (props) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [authToken, setAuthtoken] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState({ isError: false, message: null });
    const [success, setSuccess] = useState({ isSuccess: false, message: null });

    const showErrorAlert = (message) => {
        setError({ isError: true, message: message });
        setTimeout(() => {
            setError({ isError: false, message: null });
        }, 6000);
    };

    const showSuccessAlert = (message) => {
        setSuccess({ isSuccess: true, message: message });
        setTimeout(() => {
            setSuccess({ isSuccess: false, message: null });
        }, 6000);
    };

    const navigate = useNavigate();

    const register = async (data) => {
        try {
            setIsLoading(false);
            const response = await fetch(`${SERVER_ADDRESS}/auth/register`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    id: data.email,
                    userGroupId: 0,
                    passwordHash: data.password,
                }),
            });
            const resp = await response.json();
            if (!response.ok) {
                showErrorAlert(resp.message);
            } else {
                navigate("/login");
            }
            console.log(resp);
        } catch (e) {
            showErrorAlert("Nie można było uzyskać połączenia z serwerem.");
        }
        setIsLoading(false);
        showSuccessAlert(
            "Pomyślnie zarejestrowano, proszę się teraz zalogować."
        );
    };

    const logout = async () => {
        console.log("Starting logout...");
        const requestString = new URLSearchParams({
            token: authToken.authToken,
        });
        const url = `${SERVER_ADDRESS}/auth/revoke`;
        try {
            setIsLoading(true);
            const response = await fetch(`${url}?${requestString.toString()}`, {
                method: "DELETE",
            });
            const resp = await response.json();
            if (!response.ok) {
                showErrorAlert(resp.message);
            } else {
                navigate("/login");
                setAuthtoken(null);
                setIsLoggedIn(false);
                showSuccessAlert("Pomyślnie wylogowano.");
            }
            console.log(resp);
        } catch (e) {
            showErrorAlert("Nie można było uzyskać połączenia z serwerem.");
        }
        setIsLoading(false);
    };

    const login = async (data, permissionLevel) => {
        console.log(data);
        const requestString = new URLSearchParams(data);
        const url =
            permissionLevel === 0
                ? `${SERVER_ADDRESS}/auth/login`
                : `${SERVER_ADDRESS}/auth/admin`;
        console.log(`${url}?${requestString.toString()}`);
        try {
            setIsLoading(true);
            const response = await fetch(`${url}?${requestString.toString()}`);
            const resp = await response.json();
            if (!response.ok) {
                showErrorAlert(resp.message);
            } else {
                setAuthtoken(resp.token);
                setIsLoggedIn(true);
                navigate("/");
                console.log(resp);
                showSuccessAlert("Pomyślnie zalogowano.");
                setIsLoading(false);
            }
        } catch (e) {
            showErrorAlert("Nie można było uzyskać połączenia z serwerem.");
        }
        setIsLoading(false);
    };

    return (
        <Context.Provider
            value={{
                isLoading,
                setIsLoading,
                authToken,
                isLoggedIn,
                login,
                register,
                logout,
                error,
                success,
                showErrorAlert,
                showSuccessAlert,
            }}
        >
            {props.children}
        </Context.Provider>
    );
};

export default Context;
