import Box from "@mui/material/Box";
import Grid from "@mui/material/Grid";
import { Button, Stack, Typography } from "@mui/material";
import KeyboardArrowDownIcon from "@mui/icons-material/KeyboardArrowDown";
import LaunchIcon from "@mui/icons-material/Launch";

export default function CollapseDetail() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={2}>
        <Grid size={13}>
          <Stack
            direction="row"
            spacing={2}
            sx={{
              justifyContent: "space-between",
              alignItems: "center",
            }}
          >
            <Stack
              direction="row"
              spacing={2}
              sx={{
                justifyContent: "space-between",
                alignItems: "baseline",
              }}
            >
              <Typography variant="h6" gutterBottom>
                FIRST-NAME LAST-NAME (10103ZA - US Margin)
              </Typography>
              <Button variant="outlined" endIcon={<LaunchIcon />}>
                Full Review Details
              </Button>
            </Stack>

            <Stack direction="row" spacing={2}>
              <Button variant="contained" sx={{ borderRadius: "20px" }}>
                Accept
              </Button>
              <Button
                variant="outlined"
                color="error"
                endIcon={<KeyboardArrowDownIcon />}
                sx={{ borderRadius: "20px" }}
              >
                Reject
              </Button>
            </Stack>
          </Stack>
        </Grid>
        <Grid size={12}>
          <Grid container spacing={2}>
            <Grid size={3}>
              <Typography variant="subtitle1">
                Net Amount: <b>1,152.95 USD</b>
              </Typography>
            </Grid>
            <Grid size={3}>
              <Typography variant="subtitle1">
                Price: <b>135.00</b>
              </Typography>
            </Grid>
            <Grid size={3}>
              <Typography variant="subtitle1">
                Exchange Rate: <b>1.3357</b>
              </Typography>
            </Grid>
            <Grid size={3}>
              <Typography variant="subtitle1">
                O/S Limit: <b>140.0</b>
              </Typography>
            </Grid>
          </Grid>
          <Grid container spacing={2}>
            <Grid size={3}>
              <Typography variant="subtitle1">
                Reference Number: <b>1234567890</b>
              </Typography>
            </Grid>
            <Grid size={3}>
              <Typography variant="subtitle1">
                Date/Time: <b>2023/01/04 03:05:43</b>
              </Typography>
            </Grid>
            <Grid size={3}>
              <Typography variant="subtitle1">
                Telephone: <b>000-000-0000</b>
              </Typography>
            </Grid>
            <Grid size={3}>
              <Typography variant="subtitle1">
                User ID: <b>12344321</b>
              </Typography>
            </Grid>
          </Grid>
        </Grid>

        <Grid size={13} sx={{ backgroundColor: "#f5f5f5", padding: "16px" }}>
          <Typography variant="h5">Warning(s)</Typography>
          <Typography variant="body1" sx={{padding: "8px 16px 0px"}} gutterBottom>
              
              <li>
                To trade this security in this account, a currency conversion
                will be made at the current rate.
              </li>
              <li>A similar order has already been submitted.</li>
              <li>
                Your transaction will be processed the following business day.
              </li>
              <li>
                It is not possible to calculate the buying power of this order.
              </li>
              <li>
                A cancellation will not be possible during business hours on
                market orders. You can call a representative for more
                information.
              </li>
          </Typography>
        </Grid>
      </Grid>
    </Box>
  );
}
