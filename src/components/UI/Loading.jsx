import { Backdrop, CircularProgress } from "@mui/material";
import React from "react";
const Loading = (props) => {
    return (
        <Backdrop
            sx={{ color: "#fff", zIndex: (theme) => theme.zIndex.drawer + 1 }}
            open={props.isOpen}
        >
            <CircularProgress />
        </Backdrop>
    );
};

export default Loading;
