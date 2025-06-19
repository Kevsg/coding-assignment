import { FormControl, Grid, MenuItem, Select, Typography } from "@mui/material";
import Button from "@mui/material/Button";
import Stack from "@mui/material/Stack";
import { DateTimePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import useMediaQuery from "@mui/material/useMediaQuery";

export default function SearchBar({
  onStartDateChange,
  onEndDateChange,
  onSearch,
  searchResult,
}) {
  const matches = useMediaQuery("(min-width:1536px)");
  return (
    <Grid
      container
      direction="row"
      spacing={2}
      columns={12}
      sx={{
        justifyContent: "space-between",
        alignItems: "center",
        width: "100%",
        flexWrap: "wrap",
      }}
    >
      <Grid
        size={{ xs: 12, sm: 12, md: 12, lg: 12, xl: "auto" }}
        sx={{
          justifyContent: "flex-start",
          alignItems: "flex-start",
        }}
      >
        <Typography variant="h5" textAlign={"left"}>
          Search
        </Typography>
        <Typography variant="body1" textAlign={"left"}>
          Search {searchResult.length > 1 ? "results" : "result"} : <b>{searchResult}</b>
        </Typography>
      </Grid>

      <Grid
        container
        size={"grow"}
        direction="row"
        sx={{
          justifyContent: matches ? "flex-end" : "flex-start",
          alignItems: "baseline",
          width: matches ? "auto" : "100%",
          textAlign: "left",
        }}
      >
        <Stack
          direction="row"
          spacing={2}
          sx={{
            justifyContent: "flex-start",
            alignItems: "baseline",
            width: matches ? "auto" : "100%",
          }}
        >
          <Typography
            variant="h6"
            gutterBottom
            sx={{ width: matches ? "fit-content" : "80px" }}
          >
            Period
          </Typography>
          <FormControl sx={{ width: matches ? "auto" : "100%" }}>
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={"Transmission"}
              onChange={() => {}}
              inputProps={{ readOnly: true }}
              sx={{ width: matches ? "auto" : "100%" }}
            >
              <MenuItem value={"Transmission"}>Trasmission</MenuItem>
            </Select>
          </FormControl>
        </Stack>

        <Stack
          direction="row"
          spacing={2}
          sx={{
            justifyContent: "flex-start",
            alignItems: "baseline",
            width: matches ? "auto" : "100%",
          }}
        >
          <Typography
            variant="h6"
            gutterBottom
            sx={{ width: matches ? "fit-content" : "80px" }}
          >
            Status
          </Typography>
          <FormControl sx={{ width: matches ? "auto" : "100%" }}>
            <Select
              labelId="demo-simple-select-label"
              id="demo-simple-select"
              value={"Waiting"}
              onChange={() => {}}
              inputProps={{ readOnly: true }}
              sx={{ width: matches ? "auto" : "100%" }}
            >
              <MenuItem value={"Waiting"}>Waiting</MenuItem>
            </Select>
          </FormControl>
        </Stack>

        <Stack
          direction="row"
          spacing={2}
          sx={{
            justifyContent: "flex-start",
            alignItems: "baseline",
            width: matches ? "auto" : "100%",
          }}
        >
          <Typography
            variant="h6"
            gutterBottom
            sx={{ width: matches ? "fit-content" : "80px" }}
          >
            From
          </Typography>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DateTimePicker
              onChange={(val) => onStartDateChange(val)}
              sx={{ width: matches ? "auto" : "100%" }}
            />
          </LocalizationProvider>
        </Stack>

        <Stack
          direction="row"
          spacing={2}
          sx={{
            justifyContent: "flex-start",
            alignItems: "baseline",
            width: matches ? "auto" : "100%",
          }}
        >
          <Typography
            variant="h6"
            gutterBottom
            sx={{ width: matches ? "fit-content" : "80px" }}
          >
            To
          </Typography>
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DateTimePicker
              sx={{ width: matches ? "auto" : "100%" }}
              onChange={(val) => onEndDateChange(val)}
            />
          </LocalizationProvider>
        </Stack>

        <Stack
          direction="row"
          spacing={2}
          sx={{
            width: matches ? "auto" : "100%",
            marginTop: matches ? "0px" : "16px",
          }}
        >
          <Button
            variant="contained"
            onClick={() => onSearch()}
            sx={{
              borderRadius: matches ? "20px" : "0px",
              width: matches ? "auto" : "100%",
            }}
          >
            Search
          </Button>
        </Stack>
      </Grid>
    </Grid>
  );
}
