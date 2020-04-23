import React, { Component } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';


class App extends Component {
  state = {
    customers: []
  }
  componentDidMount() {
    fetch('http://localhost:8080/api/v1/asiakas')
    .then(res => res.json())
    .then((data) => {
      this.setState({ customers: data })
      console.log(this.state.customers)
    })
    .catch(console.log)
  }
  render() {

    return (
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
              <TableCell component="th" scope="row">
                {customer.asiakasID}
              </TableCell>
              <TableCell align="right">{customer.nimi}</TableCell>
              <TableCell align="right">{customer.osoite}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
    );
  }
}
export default App;