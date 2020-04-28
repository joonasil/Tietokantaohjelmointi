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
import InputLabel from '@material-ui/core/InputLabel';
import FormHelperText from '@material-ui/core/FormHelperText';
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import NativeSelect from '@material-ui/core/NativeSelect';


import AppBarCustom from './components/AppBarCustom.js';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles (theme => ({
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
      },
      selectEmpty: {
        marginTop: theme.spacing(2),
      },
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
    const currentDateIso = (new Date).toISOString().substring(0,10);
    const sopimustyyppiEnum = ["urakka", "tunti"];
    const yksikkoEnum = ['kpl', 'kg', 'm', 'cm', 'g', 'l', 'kela'];
    const sopimusTilaEnum = ['luonnos', 'tarjous', 'hyvaksytty'];


    const fetchTable = async (newTableName, newMetadataTableName) => {
        let newTable = {};
        if (/*!relations.includes(newMetadataTableName)*/ newMetadataTableName == undefined || newMetadataTableName == null) {
            newMetadataTableName = "metadata/" + newTableName;
        }
        if (newTableName !== "" && newTableName !== null) {
            //console.log('http://localhost:8080/api/v1/' + newTableName);
            //console.log('http://localhost:8080/api/v1/' + newMetadataTableName);
            await fetch('http://localhost:8080/api/v1/' + newTableName)
                .then(res => res.json())
                .then((data) => {(newTable.table = data )}).catch(console.log);

            await fetch('http://localhost:8080/api/v1/' + newMetadataTableName)
                .then(res => res.json())
                .then((data) => {(newTable.metadata = data )}).catch(console.log);
            
            await setActiveTable(newTable);
            await formHtmlTable(newTable);
            extractAttributeNames(newTable.table);
            //console.log(newTable.table);
            //console.log(newTable.metadata);
    }}

    const extractAttributeNames = (tableJson) => {
        let keys = [];
        for (let key in tableJson[0]) {
            keys.push(key.toLowerCase());
        }
        return keys;
    }

    const formHtmlTable = async (newTable) => {
        //console.log("Rendeöidään taulu")
        //console.log(newTable)
        /*if (newTable.metadata[0].table_name == "asiakas") {
            newTable.table = sortJSONByKey(newTable.table);
        }
        */
        setHtmlTable([]);
        if (newTable.table != null) {
            let table = newTable.table;
            let metadata = newTable.metadata; // NOT USEED ANYMORE
            let attributeNames = extractAttributeNames(table);
            let tableSize = Object.keys(table).length;
            let html = [];
            let textFields = [];
            for (let i = 0; i < attributeNames.length; i++) {
                // Sarakkeiden nimet
                html.push(<TableCell key={tableName + "_" + attributeNames[i] + "_" + i + Math.random()}>{attributeNames[i]}</TableCell>);
                // Input kentät
                // PVM input
                if (attributeNames[i].endsWith("pvm") || attributeNames[i].endsWith("paiva")) {
                    textFields.push(
                        <TextField key={tableName + "_" + attributeNames[i] + Math.random()} label={attributeNames[i]} type="date" InputLabelProps={{ shrink: true, }}
                            onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}}
                        />)
                }
                // Selectorit
                else if (attributeNames[i] == "sopimuksentila" || attributeNames[i] == "yksikko" || attributeNames[i] == "tyyppi") {
                    let selectorValues = [];
                    console.log(attributeNames[i]);
                    if (attributeNames[i] == ("sopimuksentila")) {
                        selectorValues = JSON.parse(JSON.stringify(sopimusTilaEnum))}
                    if (attributeNames[i] == ("yksikko")) {
                        selectorValues = JSON.parse(JSON.stringify(yksikkoEnum))}
                    if (attributeNames[i] == ("tyyppi")) {
                        selectorValues = JSON.parse(JSON.stringify(sopimustyyppiEnum))}
                    console.log(selectorValues)
                    let options = [];
                    for (let i = 0; i < selectorValues.length; i++) {
                        options.push(<option >{selectorValues[i]}</option>)
                    }
                    textFields.push(
                        <FormControl className={classes.formControl} variant="outlined">
                            <InputLabel htmlFor="outlined-age-native-simple">{attributeNames[i]}</InputLabel>
                                <Select native onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}} label={attributeNames[i]} inputProps={{ name: 'age', id: 'outlined-age-native-simple', }}>
                                    {options}                 
                                </Select>
                        </FormControl>)
                }
                else {
                    textFields.push(
                        <TextField className={classes.textFields} key={tableName + "_" + attributeNames[i] + Math.random()} label={attributeNames[i]} variant="outlined" 
                            onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}}
                        />
                )}
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
    // This is only for customer table
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
        console.log(state);
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
    const generateOverdueInvoices = async () => {
        const requestOptions = { method: 'POST', headers: { 'Content-Type': 'application/json' } };
        await fetch("http://localhost:8080/api/v1/lasku/eraantyneet/muistuta", requestOptions).then((response) => { return response.json()}).then((result) => { console.log(result)}).catch(console.log);
        fetchTable("lasku");
    }

    // DELETE HANDLER
    const handleDeleteClick = async () => {
        const requestOptions = {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' }
        };
        await fetch("http://localhost:8080/api/v1/" + tableName + "/" + deleteFieldValue, requestOptions)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(console.log);
        fetchTable(tableName);
    }
    // SEARCH HANDLER
    const handleSearchClick = async () => {
        if (searchFieldValue != null && searchFieldValue != "") {
            console.log("http://localhost:8080/api/v1/" + tableName + "/" + searchFieldValue)
            await fetch("http://localhost:8080/api/v1/" + tableName + "/" + searchFieldValue).then((response) => {
            return response.json();
            }).then((result) => {
                if (result.status == 200) {
                    let newTable = {};
                    newTable.table = [result];
                    newTable.metadata = activeTable.metadata;
                    formHtmlTable(newTable)
                }
            }).catch(console.log);
        }
        else {
            fetchTable(tableName);
        }
    }
    // INSERT HANDLER
    const handleInsertClick = async () => {
        console.log(insertFieldValue);
        console.log(JSON.stringify(insertFieldValue))
        var arrayToString = JSON.stringify(Object.assign({}, insertFieldValue));  // convert array to string
        var stringToJsonObject = JSON.parse(arrayToString);  // convert string to json object
        console.log(stringToJsonObject);
        
        const requestOptions = {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(stringToJsonObject)
            };
        console.log("http://localhost:8080/api/v1/" + tableName + "/", requestOptions)
        await fetch("http://localhost:8080/api/v1/" + tableName + "/", requestOptions)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(console.log);
    fetchTable(tableName);
    }
    // EDIT HANDLER
    const handleEditClick = async () => {
        // Search for id
        let keys = [];
        let key = "";
        for (let key in insertFieldValue) keys.push(key);
        for (let keyName in keys) {
            if (keys[keyName].endsWith("id")) {
                key = keys[keyName];
            }
        }
        const requestOptions = {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(insertFieldValue)};
        console.log("http://localhost:8080/api/v1/" + tableName + "/" + insertFieldValue[key] + "/", requestOptions)
        await fetch("http://localhost:8080/api/v1/" + tableName + "/" + insertFieldValue[key] + "/", requestOptions)
            .then(response => response.json())
            .then(result => console.log(result))
            .catch(console.log);
        fetchTable(tableName);
    }
    // REFORMAT DATES IN INSEERT FIELD VALUES
    const reformatDates = () => {
        let keys = [];
        for (let key in insertFieldValue) keys.push(key);
        for (let keyName in keys) {
            if (keys[keyName].endsWith("pvm")) {
                insertFieldValue[[keyName]] = new Date(insertFieldValue[[keyName]]);
            }
        }
    }


    // HANDLE TABS
    const handleTabChange = (event, newValue) => {
        setSearchFieldValue([]);
        setDeleteFieldValue([]);
        setInsertFieldValue([]);
        setTableName(newValue);
    };

    useEffect(() => {
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
          <Typography className={classes.textFields} >Lisää tai muokkaa entiteettiä</Typography>
          <form noValidate autoComplete="off">
              {insertFields}
        </form>
          <Button className={classes.textFields}  variant="contained" color="primary" onClick={handleInsertClick}>Lisää</Button>
          <Button className={classes.textFields}  variant="contained" color="primary" onClick={handleEditClick}>Muokkaa</Button>
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
