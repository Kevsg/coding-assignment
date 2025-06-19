import "./App.css";
import { useState } from "react";
import { Stack } from "@mui/material";
import CollapsibleTable from "./components/CollapsibleTable";
import { getOrder, mockOrders } from "./service/getOrderService";
import SearchBar from "./components/SearchBar";

function App() {
  const [startDate, setStartDate] = useState(undefined);
  const [endDate, setEndDate] = useState(undefined);
  const [orders, setOrders] = useState(mockOrders);

  return (
    <Stack
      direction="column"
      spacing={4}
      sx={{
        justifyContent: "flex-start",
        alignItems: "center",
      }}
    >
      <SearchBar
        onStartDateChange={setStartDate}
        onEndDateChange={setEndDate}
        onSearch={() => setOrders(getOrder(startDate, endDate))}
        searchResult={orders.length.toString()}
      />
      <CollapsibleTable data={orders} />
    </Stack>
  );
}

export default App;
