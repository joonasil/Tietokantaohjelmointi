import React, {useRef, useEffect} from 'react';
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
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';

import AppBarCustom from './components/AppBarCustom.js';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles (theme => ({

    textFields: {
        margin: '5px 10px 5px 10px',
    },
    textBox: {
        margin: "10px 10px 10px 0px",
    },
}));

function App() {

	const classes = useStyles();

    const [tableName, setTableName] = React.useState("asiakas");
    const [activeTable, setActiveTable] = React.useState([]);
    const [htmlTable, setHtmlTable] = React.useState([]);
    const [htmlTableHead, setHtmlTableHead] = React.useState([]);
    const [relations, setRelations] = React.useState(['asiakas', 'tyokohde', 'tyosopimus', 'tyosuoritus', 'tyosuorituksentuntityo', 'lasku', 'tarvikeluettelo', 'tarvike', 'tuntityo']);
    const [insertFields, setInsertFields] = React.useState([]);
    const [deleteFieldValue, setDeleteFieldValue] = React.useState([]);
    const [searchFieldValue, setSearchFieldValue] = React.useState([]);
    const [insertFieldValue, setInsertFieldValue] = React.useState({});
    const [additionalForm, setAdditionalForm] = React.useState([]);

    const fetchTable = (newTableName, newMetadataTableName) => {
        let newTable = {};
        if (/*!relations.includes(newMetadataTableName)*/ newMetadataTableName == undefined || newMetadataTableName == null) {
            newMetadataTableName = "metadata/" + newTableName;
        }
        if (newTableName !== "" && newTableName !== null) {
            console.log('http://localhost:8080/api/v1/' + newTableName);
            console.log('http://localhost:8080/api/v1/' + newMetadataTableName);
            fetch('http://localhost:8080/api/v1/' + newTableName)
                .then(res => res.json())
                .then((data) => {(newTable.table = data )})
                .then(fetch('http://localhost:8080/api/v1/' + newMetadataTableName)
                .then(res => res.json())
                .then((data) => {(newTable.metadata = data )})
                .then(() => {
                    setActiveTable(newTable)
                })
                .then(() => {
                    formHtmlTable(newTable)
                })).catch(console.log);
    }}

    const formHtmlTable = (newTable) => {
        console.log("Rendeöidään taulu")
        console.log(newTable)
        if (newTable.metadata[0].table_name == "asiakas") {
            newTable.table = sortJSONByKey(newTable.table);
        }
        setHtmlTable([]);
        if (newTable.table != null && newTable.metadata != null) {
            let table = newTable.table;
            let metadata = newTable.metadata;
            let tableSize = Object.keys(table).length;
            let columnCount = Object.keys(metadata).length
            let html = [];
            let textFields = [];
            for (let i = 0; i < columnCount; i++) {
                let item = metadata[i];
                // Sarakkeiden nimet
                html.push(<TableCell key={tableName + "_" + item.column_name + "_" + i + Math.random()}>{item.column_name}</TableCell>)
                // Input kentät
                textFields.push(
                    <TextField className={classes.textFields} key={tableName + "_" + item.column_name + Math.random()} label={item.column_name} variant="outlined" 
                        onChange={(e) => {updateInsertFieldValue(e, metadata[i].column_name)}}
                    />
                )
            }
            setHtmlTableHead(html);
            setInsertFields(textFields);
            
            html = [];
            for (let i = 0; i < tableSize; i++) {
                let item = table[i];
                
                let cells = []
                Object.keys(item).forEach(function(key) {
                    if (item[key] != null) {
                        cells.push(<TableCell key={tableName + "_" + item[key] + "_" + i + Math.random()}>{item[key]}</TableCell>);
                    } else {
                        cells.push(<TableCell key={tableName + "_" + item[key] + "_" + i + Math.random()}>NULL</TableCell>);
                    }
                });
                html.push(<TableRow key={i}>{cells}</TableRow>);
            }
            setHtmlTable(html);
            updateAdditionalForm();
        }
    }
    // This id only for customer table
    function sortJSONByKey(array){
        var sortedArray = [];
        // Push each JSON Object entry in array by [key, value]
        for(var i in array)
        {
            sortedArray.push({asiakasID : [array[i].asiakasID], nimi : [array[i].nimi], osoite : [array[i].osoite]});
        }
        // Run native sort function and returns sorted array.
        return sortedArray;
    }

    // UI HANDLERIT
    const updateDeleteFieldValue = (event) => {
        setDeleteFieldValue(event.target.value);
    }
    const updateSearchFieldValue = (event) => {
        setSearchFieldValue(event.target.value);
    }
    const updateInsertFieldValue = (event, label) => {
        let state = insertFieldValue;
        state[[label]] = event.target.value;
        setInsertFieldValue(state);
    }
    // ADDITIONAL FORM
    const updateAdditionalForm = () => {
        let formArray = [];
        if (tableName == "lasku") {
            formArray.push( 
                <Paper key="invoiceButtonPaper" className={classes.textFields}  className={classes.textFields} elevation={2}>
                    <Typography key="heading" className={classes.textFields} >Hallitse Laskuja</Typography>
                    <Button key="all" className={classes.textFields}  variant="contained" color="default" onClick={(e) => fetchTable("lasku")}>Näytä kaikki laskut</Button>
                    <Button key="overdue" className={classes.textFields}  variant="contained" color="default" onClick={(e) => fetchTable("lasku/eraantyneet", "metadata/lasku")}>Näytä erääntyneet laskut</Button>
                    <Button key="generates"className={classes.textFields}  variant="contained" color="default" onClick={generateOverdueInvoices}>Luo muistutuslaskut erääntyneistä</Button>
                </Paper>
            )
        }
        setAdditionalForm(formArray);
    }
    const generateOverdueInvoices = () => {
        const requestOptions = { method: 'POST', headers: { 'Content-Type': 'application/json' } };
        fetch("http://localhost:8080/api/v1/lasku/eraantyneet/muistuta", requestOptions).then((response) => { return response.json()}).then((result) => { console.log(result)}).catch(console.log);
        fetchTable("lasku");
    }

    // DELETE HANDLER
    const handleDeleteClick = () => {
        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' }
        };
        fetch("http://localhost:8080/api/v1/" + tableName + "/" + deleteFieldValue, requestOptions).then((response) => {
          return response.json();
        }).then((result) => {
            fetchTable(tableName)
        })
        .catch(console.log);
    }
    // SEARCH HANDLER
    const handleSearchClick = () => {
        if (searchFieldValue != null && searchFieldValue != "") {
            console.log("http://localhost:8080/api/v1/" + tableName + "/" + searchFieldValue)
            fetch("http://localhost:8080/api/v1/" + tableName + "/" + searchFieldValue).then((response) => {
            return response.json();
            }).then((result) => {
                let newTable = {};
                newTable.table = [result];
                newTable.metadata = activeTable.metadata;
                formHtmlTable(newTable)
            });    
        }
        fetchTable(tableName);
    }
    // INSERT HANDLER
    const handleInsertClick = () => {
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(insertFieldValue)
              };
            console.log("http://localhost:8080/api/v1/" + tableName + "/", requestOptions)
            fetch("http://localhost:8080/api/v1/" + tableName + "/", requestOptions).then((response) => {
            return response.json();
            }).then((result) => {
                console.log(result);
            }).catch(console.log);
        fetchTable(tableName);
    }

    const handleTabChange = (event, newValue) => {
        console.log(newValue);
        setInsertFieldValue([]);
        setTableName(newValue);
      };

    useEffect(() => {
        console.log("Hello world")
        console.log(tableName)
        fetchTable(tableName)
	}, [tableName]);

	return (
		<div className="App">

        <AppBarCustom/>
        <AppBar position="static">
              <Tabs value={tableName} onChange={handleTabChange}>
                  <Tab label={relations[0]} value={relations[0]}/>
                  <Tab label={relations[1]} value={relations[1]}/>
                  <Tab label={relations[2]} value={relations[2]}/>
                  <Tab label={relations[3]} value={relations[3]}/>
                  <Tab label={relations[4]} value={relations[4]}/>
                  <Tab label={relations[5]} value={relations[5]}/>
                  <Tab label={relations[6]} value={relations[6]}/>
                  <Tab label={relations[7]} value={relations[7]}/>
                  <Tab label={relations[8]} value={relations[8]}/>
                  <Tab label={relations[9]} value={relations[9]}/>
              </Tabs>
          </AppBar>

        <Paper className={classes.textFields} elevation={2}>
          <Typography className={classes.textFields}>Hae avaimella</Typography>
          <TextField  className={classes.textFields} id="outlined-basic" label="Key" variant="outlined" value={searchFieldValue} onChange={updateSearchFieldValue}/>
          <Button className={classes.textFields} variant="contained" color="primary" onClick={handleSearchClick}>Hae</Button>
        </Paper>

        <Paper className={classes.textFields} elevation={2}>
          <Typography className={classes.textFields} >Lisää entiteetti</Typography>
          <form noValidate autoComplete="off">
              {insertFields}
        </form>
          <Button className={classes.textFields}  variant="contained" color="primary" onClick={handleInsertClick}>Lisää</Button>
        </Paper>

        <Paper className={classes.textFields}  className={classes.textFields} elevation={2}>
          <Typography className={classes.textFields} >poista entiteetti</Typography>
          <form noValidate autoComplete="off">
            <TextField className={classes.textFields}  id="outlined-basic" label="ID" variant="outlined" value={deleteFieldValue} onChange={updateDeleteFieldValue}/>
          </form>
          <Button className={classes.textFields}  variant="contained" color="secondary" onClick={handleDeleteClick}>Poista</Button>
        </Paper>
        
        {additionalForm}

        {/* TABLE */ }
        <TableContainer component={Paper}>
        <Table aria-label="simple table" size="small">
            <TableHead>
                <TableRow>
                    {htmlTableHead}
                </TableRow>
            </TableHead>
            <TableBody>
                {htmlTable}
            </TableBody>
        </Table>
      </TableContainer>
    </div>
    );
}

export default App;
