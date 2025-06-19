import * as React from "react";
import Box from "@mui/material/Box";
import Collapse from "@mui/material/Collapse";
import IconButton from "@mui/material/IconButton";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import KeyboardArrowUpIcon from "@mui/icons-material/KeyboardArrowUp";
import CollapseDetail from "./CollapseDetail";
import type { Order } from "../service/getOrderService";
import useMediaQuery from "@mui/material/useMediaQuery";
import Typography from "@mui/material/Typography";
import dayjs from "dayjs";

function transformExternalId(externalId: string): string {
  const parts = externalId.split("-");
  if (parts.length !== 3) {
    return externalId;
  }
  const [first, middle, last] = parts;
  const transformedMiddle = "X".repeat(middle.length - 1) + middle.slice(-1);
  return `${first}-${transformedMiddle}-${last}`;
}

function Row(props: { row: Order }) {
  const { row } = props;
  const [open, setOpen] = React.useState(false);
  const matches = useMediaQuery("(min-width:900px)");
  const tableMetadata = [
    { showOnSmallScreen: true, field: "accountId", align: "left" },
    { showOnSmallScreen: true, field: "operation", align: "left" },
    { showOnSmallScreen: true, field: "symbol", align: "left" },
    { showOnSmallScreen: false, field: "description", align: "left" },
    { showOnSmallScreen: false, field: "quantity", align: "right" },
    { showOnSmallScreen: false, field: "filledQuantity", align: "right" },
    { showOnSmallScreen: false, field: "price", align: "right" },
    { showOnSmallScreen: true, field: "status", align: "right" },
    { showOnSmallScreen: false, field: "createdAt", align: "left" },
    { showOnSmallScreen: false, field: "expiresAt", align: "left" },
    { showOnSmallScreen: false, field: "orderId", align: "right" },
    { showOnSmallScreen: false, field: "externalId", align: "right" },
  ];
  const rowToShow = {
    accountId: row.accountId,
    operation: row.operation,
    symbol: row.symbol,
    description: row.description,
    quantity: row.quantity,
    filledQuantity: row.filledQuantity,
    price: row.price.toFixed(2),
    status: row.status,
    createdAt: dayjs(row.createdAt).format("YYYY/MM/DD HH:mm:ss"),
    expiresAt: dayjs(row.expiresAt).format("YYYY/MM/DD HH:mm:ss"),
    orderId: row.orderId,
    externalId: transformExternalId(row.externalId),
  };
  return (
    <React.Fragment>
      <TableRow
        key={row.accountId}
        sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
      >
        {matches && (
          <TableCell>
            <IconButton
              aria-label="expand row"
              size="small"
              onClick={() => setOpen(!open)}
            >
              {open ? <KeyboardArrowUpIcon /> : <KeyboardArrowDownIcon />}
            </IconButton>
          </TableCell>
        )}
        {tableMetadata.map((cell, index) =>
          cell.showOnSmallScreen || matches ? (
            <TableCell
              key={index}
              align={cell.align}
              sx={{ color: cell.field === "accountId" ? "#1976d2" : "inherit" }}
            >
              <Typography
                variant="body1"
                sx={{
                  fontWeight: cell.field === "accountId" ? "bold" : "normal",
                }}
              >
                {rowToShow[cell.field]}
              </Typography>
            </TableCell>
          ) : null
        )}
      </TableRow>
      {matches && (
        <TableRow>
          <TableCell style={{ paddingBottom: 0, paddingTop: 0 }} colSpan={13}>
            <Collapse in={open} timeout="auto" unmountOnExit>
              <Box sx={{ margin: 2 }}>
                <CollapseDetail />
              </Box>
            </Collapse>
          </TableCell>
        </TableRow>
      )}
    </React.Fragment>
  );
}

export default function CollapsibleTable({ data }: { data: Order[] }) {
  const matches = useMediaQuery("(min-width:900px)");
  const headerMetadata = [
    { showOnSmallScreen: false, label: "", align: "right" },
    { showOnSmallScreen: true, label: "Account", align: "left" },
    { showOnSmallScreen: true, label: "Operation", align: "left" },
    { showOnSmallScreen: true, label: "Symbol", align: "left" },
    { showOnSmallScreen: false, label: "Description", align: "left" },
    { showOnSmallScreen: false, label: "Qty.", align: "right" },
    { showOnSmallScreen: false, label: "Filled Qty.", align: "right" },
    { showOnSmallScreen: false, label: "Price", align: "right" },
    { showOnSmallScreen: true, label: "Status", align: "right" },
    { showOnSmallScreen: false, label: "Date", align: "left" },
    { showOnSmallScreen: false, label: "Expiration", align: "left" },
    { showOnSmallScreen: false, label: "No. Ref.", align: "right" },
    { showOnSmallScreen: false, label: "Ext. Ref.", align: "right" },
  ];
  return (
    <TableContainer component={Paper}>
      <Table aria-label="collapsible table">
        <TableHead>
          <TableRow>
            {headerMetadata.map((header, index) =>
              header.showOnSmallScreen || matches ? (
                <TableCell key={index} align={header.align}>
                  <Typography variant="subtitle1">{header.label}</Typography>
                </TableCell>
              ) : null
            )}
          </TableRow>
        </TableHead>
        <TableBody>
          {data.map((d) => (
            <Row key={d.orderId} row={d} />
          ))}
        </TableBody>
      </Table>
    </TableContainer>
  );
}
