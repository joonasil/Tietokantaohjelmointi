import React, { Component } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';

import AppBarCustom from './components/AppBarCustom.js';
import TabBarCustom2 from './components/TabBarCustom2.js';
import TabBarCustom from './components/TabBarCustom.js';
import Typography from '@material-ui/core/Typography';


class App extends Component {

  state = {
    relations: ['asiakas', 'tyokohde', 'tyosopimus'],
    customers: [],
    workTarget: [],
    contract: [],
  }

  componentDidMount() {
    fetch('http://localhost:8080/api/v1/asiakas')
    .then(res => res.json())
    .then((data) => {
      this.setState({ customers: data })
      console.log(this.state.customers)
    })
    .catch(console.log)

    fetch('http://localhost:8080/api/v1/tyokohde')
    .then(res => res.json())
    .then((data) => {
      this.setState({ workTarget: data })
      console.log(this.state.workTarget)
    })
    .catch(console.log)
    
    fetch('http://localhost:8080/api/v1/tyosopimus')
    .then(res => res.json())
    .then((data) => {
      this.setState({ contract: data })
      console.log(this.state.contract)
    })
    .catch(console.log)
  }

  render() {

    return (
      <div className="App">

        <AppBarCustom/>
        <TabBarCustom2 tableNames={this.state.relations}/>

        <Paper elevation={3}>
          <Typography>Hae avaimella</Typography>
          <TextField id="outlined-basic" label="Key" variant="outlined" />
          <Button variant="contained" color="primary">Hae</Button>
        </Paper>

        <Paper elevation={3}>
          <Typography>Lisää entiteetti</Typography>
          <form noValidate autoComplete="off">
            <TextField id="outlined-basic" label="ID" variant="outlined" />
            <TextField id="outlined-basic" label="Nimi" variant="outlined" />
            <TextField id="outlined-basic" label="Osoite" variant="outlined" />
        </form>
          <Button variant="contained" color="primary">Lisää</Button>
        </Paper>

        <Paper elevation={3}>
          <Typography>poista entiteetti</Typography>
          <form noValidate autoComplete="off">
            <TextField id="outlined-basic" label="ID" variant="outlined" />
          </form>
          <Button variant="contained" color="secondary">Poista</Button>
        </Paper>
        
        <TableContainer component={Paper}>
        <Table aria-label="simple table" size="small">
          <TableHead>
            <TableRow>
              <TableCell>AsiakasID</TableCell>
              <TableCell align="right">nimi</TableCell>
              <TableCell align="right">osoite</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.customers.map((customer) => (
              <TableRow key={customer.asiakasID}>
                <TableCell component="th" scope="row">{customer.asiakasID}</TableCell>
                <TableCell align="right">{customer.nimi}</TableCell>
                <TableCell align="right">{customer.osoite}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
    );
  }
}
export default App;