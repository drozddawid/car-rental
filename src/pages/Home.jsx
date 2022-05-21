import { useContext } from "react";
import { Navigate } from "react-router-dom";
import Navbar from "../components/Navbar";
import UserView from "../components/UserView";
import Context from "../store/context";
const Home = () => {
    const ctx = useContext(Context);
    return (
        <>
            <Navbar isLoggedIn={true} />
            {!ctx.isLoggedIn && <Navigate to="/login" replace />}
            {ctx.isLoggedIn && ctx.authToken.permissionLevel === 0 && (
                <UserView />
            )}
            {ctx.isLoggedIn && ctx.authToken.permissionLevel === 1 && (
                <Navigate to="/admin" replace />
            )}
        </>
    );
};

export default Home;
