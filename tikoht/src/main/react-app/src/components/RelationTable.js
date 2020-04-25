import React from 'react';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import TableBody from '@material-ui/core/TableBody';

class RelationTable extends React.Component {
  constructor(props) {
    super(props);
    let content = props.data;
    this.state = {data: content};
    console.log("constructor");
    console.log(content);
  }

  render() {

      let tableRows = this.state.data.map((row) => {
        return (
        <TableRow key={row.asiakasID}>
          <TableCell>{row.asiakasID}</TableCell>
          <TableCell>{row.nimi}</TableCell>
          <TableCell>{row.osoite}</TableCell>
        </TableRow>)
      })
      return <TableBody>{tableRows}</TableBody>;
    }
  }
  export default RelationTable;