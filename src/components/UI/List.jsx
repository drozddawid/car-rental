import {
    Button,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
} from "@mui/material";
import React from "react";

const List = (props) => {
    const onDelete = (e) => {
        props.onDelete(e);
    };
    const onEdit = (e) => {
        props.onEdit(e);
    };

    const renderButtons = (row) => {
        if(props.showButtons) {
            return (
                <>
                <TableCell align="right">
                    <Button
                        variant="outlined"
                        sx={{mt: 3, mb: 2}}
                        onClick={onEdit.bind(null, row.id)}
                    >
                        Edytuj
                    </Button>
                </TableCell>
                <TableCell align="right">
                    <Button
                        variant="outlined"
                        color="error"
                        sx={{mt: 3, mb: 2}}
                        onClick={onDelete.bind(null, row.id)}
                    >
                        Usuń
                    </Button>
                </TableCell>
                </>
            );
        }else{return null;}
    }

    return (
        <TableContainer sx={{minWidth: 1000}} component={Paper}>
            <Table sx={{minWidth: 650}} aria-label="simple table">
                <TableHead>
                    <TableRow>
                        {props.headers.map((header) => (
                            <TableCell
                                align={
                                    props.headers.indexOf(header) === 0
                                        ? "left"
                                        : "right"
                                }
                                key={header}
                            >
                                {header}
                            </TableCell>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {props.rows.map((row) => (
                        <TableRow key={row.name}>
                            {Object.values(row).map((cell) => (
                                <TableCell variant={"body"} align="right">
                                    {cell}
                                </TableCell>
                            ))}
                            {renderButtons(row)}
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default List;
