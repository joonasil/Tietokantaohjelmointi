import React, { Component } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

import AppBarCustom from './components/AppBarCustom.js';
import TabBarCustom2 from './components/TabBarCustom2.js';
import TabBarCustom from './components/TabBarCustom.js';


const fetchRelation = (obj, name) => {
  return fetch('http://localhost:8080/api/v1/' + name)
  .then(res => res.json())
    .catch(console.log)
}

class App extends Component {

  state = {
    relations: ['asiakas', 'tyokohde', 'tyosopimus'],
    activeRelation: [],
    customers: [],
    workTarget: [],
    contract: [],
  }

  


  componentDidMount() {
    this.setState({activeRelation: fetchRelation(this, "asiakas")});
    console.log(fetchRelation(this, "asiakas"));
  }

  render() {

    return (
      <div className="App">

        <AppBarCustom/>

        <TabBarCustom2 tableNames={this.state.relations}/>

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
    </div>
    );
  }
}
export default App;