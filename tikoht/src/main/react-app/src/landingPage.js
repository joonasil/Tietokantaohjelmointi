import React, {useEffect} from 'react';
import './css/styles.css';
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
import FormControl from '@material-ui/core/FormControl';
import Select from '@material-ui/core/Select';
import Typography from '@material-ui/core/Typography';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormLabel from '@material-ui/core/FormLabel';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import AddIcon from '@material-ui/icons/Add';
import IconButton from '@material-ui/core/IconButton';
import SearchIcon from '@material-ui/icons/Search';
import CloudUploadIcon from '@material-ui/icons/CloudUpload';
import BuildIcon from '@material-ui/icons/Build';

import AppBarCustom from './components/AppBarCustom.js';
// For prettier pdf

/* HIENOMMAT PÄIVÄN VALITSIJAT
import 'date-fns';
import DateFnsUtils from '@date-io/date-fns';
import {
  MuiPickersUtilsProvider,
  KeyboardTimePicker,
  KeyboardDatePicker,
  DatePicker,
} from '@material-ui/pickers';
*/

const useStyles = makeStyles (theme => ({
    app: {
        color: "#2C3539",
    },
    formControl: { margin: theme.spacing(1), minWidth: 120, },
    selectEmpty: { marginTop: theme.spacing(2), },
    textFields: { margin: '5px 10px 5px 10px', },
    textFieldsShort: { 
        width: "100px", 
        margin: '5px 10px 5px 10px', 
    },
    textBox: { margin: "10px 10px 10px 0px", },
    productContainer: { borderStyle : "solid", borderWidth : "1.2px", borderColor : "lightgrey", borderRadius : "3px", margin : "5px"},
    productTextFields: { color: "grey", display: "inline-block", },
    deleteButton : { 
        position: "left", 
        margin: "10px 10px 10px 10px",
        position: "relative",
        top: "35%",
        transform: "translateY(-80%)",
    },
    deleteButtonProduct : { 
        position: "left", 
        margin: "10px 10px 10px 10px",
        position: "relative",
        top: "35%",
        transform: "translateY(-30%)",
    },
    root: {
        ...theme.typography.button,
        backgroundColor: theme.palette.background.paper,
        padding: theme.spacing(1),
        fontWeight: "Bold",
        color : "#51504E",
        fontSize: "1.5rem",
      },
    datePicker: {
        borderStyle : "solid", borderWidth : "1.2px", borderColor : "lightgrey", borderRadius : "3px",
        padding: "5px 5px",
    },
}));

function App() {

	const classes = useStyles();

    const [tableName, setTableName] = React.useState("asiakas");
    const [activeTable, setActiveTable] = React.useState([]);
    const [htmlTable, setHtmlTable] = React.useState([]);
    const [htmlTableHead, setHtmlTableHead] = React.useState([]);
    const [insertFields, setInsertFields] = React.useState([]);
    const [deleteFieldValue, setDeleteFieldValue] = React.useState([]);
    const [searchFieldValue, setSearchFieldValue] = React.useState([]);
    const [insertFieldValue, setInsertFieldValue] = React.useState({});
    const [additionalForm, setAdditionalForm] = React.useState([]);
    const [inputTypeRadioValue, setInputTypeRadioValue] = React.useState("lisaa");
    const [dateSelectorValues, setDateSelectorValues] = React.useState({});

    // IVOICE
    const [invoiceData, setInvoiceData] = React.useState([]);
    const [invoiceProducts, setInvoiceProducts] = React.useState([]);
    const [invoiceServices, setInvoiceServices] = React.useState([]);
    const [invoiceTotals, setInvoiceTotals] = React.useState({});

    
    const currentDateIso = (new Date()).toISOString().substring(0,10);
    const sopimustyyppiEnum = ["urakka", "tunti"];
    const yksikkoEnum = ['kpl', 'kg', 'm', 'cm', 'g', 'l', 'kela'];
    const sopimusTilaEnum = ['luonnos', 'tarjous', 'hyvaksytty'];
    const relations = ['asiakas', 'tyokohde', 'tyosopimus', 'tyosuoritus', 'tyosuorituksentuntityo', 'lasku', 'tarvikeluettelo', 'tarvike', 'tuntityo'];
    const numericAttributeEnding = ['id', 'hinta', 'osamaksu', 'summa', 'prosentti', 'lkm', 'kulut', 'edeltavalasku', 'alv'];
    const keyAttributes = {'asiakas' : 'asiakasid', "tyokohde" : 'kohdeid', "tyosopimus" : 'sopimusid', "tyosuoritus" : 'suoritusid', "lasku" : 'laskuid', "tarvike" : 'tarvikeid'}

    // Hooks for R1
    const [quoteId, setQuoteId] = React.useState("");
    const [quoteProductsValue, setQuoteProductsValue] = React.useState([]);
    const [quoteServicesValue, setQuoteServicesValue] = React.useState([]);
    const [quoteTotal, setQuoteTotal] = React.useState({total : 0, taxfreeTotal : 0, taxAmount : 0, workAmount : 0});
    
    // Hooks for R2
    const [selectedInvoice, setSelectedInvoice] = React.useState();
    const [showInvoice, setShowInvoice] = React.useState(false);


    const fetchTable = async (newTableName, newMetadataTableName) => {
        let newTable = {};
        /*if (/*!relations.includes(newMetadataTableName)*//* newMetadataTableName === "" || newMetadataTableName === null) {
            newMetadataTableName = "metadata/" + newTableName;
        }*/
        if (newTableName !== "" && newTableName !== null) {
            //console.log('http://localhost:8080/api/v1/' + newTableName);
            //console.log('http://localhost:8080/api/v1/' + newMetadataTableName);
            await fetch('http://localhost:8080/api/v1/' + newTableName)
                .then(res => res.json())
                .then((data) => {(newTable.table = data )}).catch(console.log);

            /*await fetch('http://localhost:8080/api/v1/' + newMetadataTableName)
                .then(res => res.json())
                .then((data) => {(newTable.metadata = data )}).catch(console.log);
            */
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
            //let metadata = newTable.metadata; // NOT USEED ANYMORE
            let attributeNames = extractAttributeNames(table);
            let tableSize = Object.keys(table).length;
            let html = [];
            let textFields = [];
            for (let i = 0; i < attributeNames.length; i++) {
                // Sarakkeiden nimet
                html.push(<TableCell key={tableName + "_" + attributeNames[i] + "_" + i + Math.random()} style={{fontWeight : "bold"}}>{attributeNames[i] === keyAttributes[tableName] ? (attributeNames[i] + " (AVAIN)"): attributeNames[i]}</TableCell>);
                // Input kentät
                // PVM input
                if (attributeNames[i].endsWith("pvm") || attributeNames[i].endsWith("paiva")) {
                    textFields.push(
                        <TextField key={tableName + "_" + attributeNames[i] + Math.random()} className={classes.datePicker} label={attributeNames[i]} type="date" InputLabelProps={{ shrink: true, }}
                            onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}}
                        />)
                    /*textFields.push(
                        <MuiPickersUtilsProvider key={"muiPicker_" + attributeNames[i]} utils={DateFnsUtils}>
                            <DatePicker key={"datePicker_" + attributeNames[i]} style={{width : "223px"}} disableToolbar inputVariant="outlined" variant="inline" format="yyyy-MM-dd" margin="normal" label={attributeNames[i]} InputAdornmentProps={{ position: "start" }}
                                onChange={date => {handleDateChange(date, attributeNames[i])}} 
                                value={dateSelectorValues[attributeNames[i]]}
                                />
                                <DatePicker
                                    variant="inline"
                                    label="Basic example"
                                    value={dateSelectorValues[attributeNames[i]]}
                                    onChange={setDateSelectorValues}
                                />
                            
                        </MuiPickersUtilsProvider>)*/
                }
                // Selectorit
                else if (attributeNames[i] === "sopimuksentila" || attributeNames[i] === "yksikko" || attributeNames[i] === "tyyppi") {
                    let selectorValues = [];
                    console.log(attributeNames[i]);
                    if (attributeNames[i] === ("sopimuksentila")) {
                        selectorValues = JSON.parse(JSON.stringify(sopimusTilaEnum))}
                    if (attributeNames[i] === ("yksikko")) {
                        selectorValues = JSON.parse(JSON.stringify(yksikkoEnum))}
                    if (attributeNames[i] === ("tyyppi")) {
                        selectorValues = JSON.parse(JSON.stringify(sopimustyyppiEnum))}
                    console.log(selectorValues)
                    let options = [];
                    for (let i = 0; i < selectorValues.length; i++) {
                        options.push(<option key={"select_option_" + selectorValues[i]}>{selectorValues[i]}</option>)
                    }
                    textFields.push(
                        <FormControl value={selectorValues[0]} className={classes.formControl} variant="outlined" key={"formControl_select_" + attributeNames[i]}>
                            <InputLabel htmlFor="outlined-age-native-simple" key={"formControl_inputLavbel_" + attributeNames[i]}>{attributeNames[i]}</InputLabel>
                                <Select key={"select_" + attributeNames[i]} native onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}} label={attributeNames[i]}>
                                    {options}                 
                                </Select>
                        </FormControl>)
                    updateInsertFieldValue(null, attributeNames[i], selectorValues[0]);
                }
                // Numeroiden rajoittamiset
                // Testaa jos attribuutin nimestä löytyy numeerinen pääte (numericAttributeEndings)
                else if (containsSubString(numericAttributeEnding, attributeNames[i])){
                    textFields.push(
                        <TextField type="number" variant="outlined" className={classes.textFields} key={tableName + "_" + attributeNames[i] + Math.random()} 
                            label={attributeNames[i]}
                            style={{display: (inputTypeRadioValue === "lisaa" && attributeNames[i] === keyAttributes[tableName]) ? 'none' : "inline-block"}}
                            helperText={attributeNames[i] === keyAttributes[tableName] ? ("AVAIN ATTRIBUUTTI"): ""}
                            //disabled={inputTypeRadioValue == "lisaa" && keyAttributes.includes(attributeNames[i]) && !keylessRelations.includes(tableName)}  
                            onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}}
                        />
                )}
                // Kaikki loput attribuutit
                else {
                    textFields.push(
                        <TextField className={classes.textFields} key={tableName + "_" + attributeNames[i] + Math.random()} label={attributeNames[i]} variant="outlined" 
                            onChange={(e) => {updateInsertFieldValue(e, attributeNames[i])}}
                        />
                )}
            }
            if (tableName === "tyosopimus") {
                html.push(<TableCell key={tableName + "_alvMaara_" + Math.random()}>ALV_määrä</TableCell>);
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
                if (tableName === "tyosopimus") {
                    cells.push(<TableCell key={tableName + "_alvMaara_" + Math.random()}>{Number((item.sopimuksenSumma * 0.24).toFixed(2))}</TableCell>)
                }
                html.push(<TableRow key={i}>{cells}</TableRow>);
            }
            setHtmlTable(html);
            updateAdditionalForm();
        }
    }
    // Check if given string contains any substring given in array
    const containsSubString = (array, string) => {
        for (let i = 0; i < array.length; i++) {
            if (string.includes(array[i])) {
                return true;
            }
        }
        return false;
    }

    // UI HANDLERIT
    const updateDeleteFieldValue = (event) => {
        setDeleteFieldValue(event.target.value);
    }
    const updateSearchFieldValue = (event) => {
        setSearchFieldValue(event.target.value);
    }
    const updateInsertFieldValue = (event, label, newValue) => {
        let state = insertFieldValue;
        if (event != null) {
            state[[label]] = event.target.value;
        }
        else if (newValue != null && newValue != "") {
            state[[label]] = newValue;
        }
        console.log(state);
        setInsertFieldValue(state);
    }
    const handleInputTypeChange = async (event) => {
        console.log(event.target.value);
        await setInputTypeRadioValue(event.target.value);
        formHtmlTable(activeTable);
        
    }
    const handleDateChange = async (date, attribute) => {
        let dates = dateSelectorValues;
        dates[attribute] = date;
        console.log(dates)
        console.log(date.toISOString())

        if (Object.prototype.toString.call(date) === "[object Date]") {
            if (isNaN(date.getTime())) {
              console.log("not valid")
            } else {
                console.log("valid date")
                let state = insertFieldValue;
                state[[attribute]] = date.toISOString().substring(0,10);
                console.log(state);
                setInsertFieldValue(state);
            }
          }
    }
    // ADDITIONAL FORM
    const updateAdditionalForm = () => {
        let formArray = [];
        if (tableName === "lasku") {
            formArray.push( 
                <Paper key="invoiceButtonPaper" className={classes.textFields} elevation={2}>
                    <Typography key="heading" className={classes.root} >Hallitse Laskuja</Typography>
                    <Button key="all" className={classes.textFields}  variant="contained" color="default" onClick={(e) => fetchTable("lasku")}>Näytä kaikki laskut</Button>
                    <Button key="overdue" className={classes.textFields}  variant="contained" color="default" onClick={(e) => fetchTable("lasku/eraantyneet" /*, "metadata/lasku" */)}>Näytä erääntyneet laskut</Button>
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
        if (searchFieldValue !== null && searchFieldValue !== "") {
            console.log("http://localhost:8080/api/v1/" + tableName + "/" + searchFieldValue)
            await fetch("http://localhost:8080/api/v1/" + tableName + "/" + searchFieldValue).then((response) => {
            return response.json();
            }).then((result) => {
                if (result.status === 200) {
                    let newTable = {};
                    newTable.table = [result];
                    /*newTable.metadata = activeTable.metadata;*/
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

    // HANDLE TABS
    const handleTabChange = (event, newValue) => {
        setSelectedInvoice("");
        setSearchFieldValue([]);
        setDeleteFieldValue([]);
        setInsertFieldValue([]);
        setTableName(newValue);
        if (newValue !== "tyosopimus" || newValue !== "lasku") {
            setShowInvoice(false);
        }
        if (newValue !== "tyokohde" || newValue !== "tyosopimus" || newValue !== "lasku" || newValue !== "tarvike") {
            setQuoteId("");
            setQuoteProductsValue([]);
            setQuoteServicesValue([]);
            setQuoteTotal({total : 0, taxfreeTotal : 0, taxAmount : 0, workAmount : 0});
        }
    };

    // FOR QUOTE GENERATE
    const updateQuoteProductsValue = async(event, label ,id) => {
        let state = quoteProductsValue;
        let object = state[id]
        object[label] = (event.target.value) !== null ? (event.target.value) : 0;
        if (label === "tarvikeid") {
            object.tuote = await fetchProduct(event.target.value);
        }
        setQuoteProductsValue(state);
        updateQuoteTotal();
        formHtmlTable(activeTable);
    }
    const updateQuoteServicesValue = async(event, label ,id) => {
        let state = quoteServicesValue;
        let object = state[id]
        object[label] = (event.target.value) !== null ? event.target.value : 0;
        console.log(state[id]);
        if (label === "tyontyyppi") {
            object.tyo = await fetchService(event.target.value);
        }
        setQuoteServicesValue(state);
        updateQuoteTotal();
        formHtmlTable(activeTable);
    }
    const addProductRow = () => {
        let tmpArray = quoteProductsValue;
        let length = tmpArray.length;
        let json = { id : length, tarvikeid : "", lkm : "", aleprosentti : "", tuote : { nimi : "", hinta : "", alv : "" } };
        tmpArray.push(json);
        console.log(tmpArray)
        setQuoteProductsValue(tmpArray);
        fetchTable(tableName);
    }
    const addServiceRow = async () => {
        let tmpArray = quoteServicesValue;
        let length = tmpArray.length;
        let tyoJson = await fetchService("asennustyo")
        console.log(tyoJson)
        let json = { id : length, tyontyyppi : "asennustyo", lkm : "", tyo : tyoJson , aleprosentti : ""};
        tmpArray.push(json);
        console.log(tmpArray);
        setQuoteServicesValue(tmpArray);
        updateQuoteTotal();
        fetchTable(tableName);
    }
    const handleProductDeleteClick = (id) => {
        let tmpArray = quoteProductsValue;
        if (id >= 0) { tmpArray.splice(id, 1)}
        console.log("poiston jälkeen:")
        console.log(tmpArray)
        setQuoteProductsValue(reindexIdInJsonArray(tmpArray));
        updateQuoteTotal(quoteProductsValue);
        fetchTable(tableName);
    }
    const handleServiceDeleteClick = (id) => {
        let tmpArray = quoteServicesValue;
        if (id >= 0) { tmpArray.splice(id, 1)}
        console.log("poiston jälkeen:")
        console.log(tmpArray)
        setQuoteServicesValue(reindexIdInJsonArray(tmpArray));
        updateQuoteTotal();
        fetchTable(tableName);
    }
    const reindexIdInJsonArray = (jsonArray) => {
        if (jsonArray.length > 0) {
            for (let i = 0; i < jsonArray.length; i++) {
                let item = jsonArray[i];
                if (item.hasOwnProperty("id")) {
                    item.id = i;
                }
            }
        }
        console.log(jsonArray);
        return jsonArray;
    }
    const fetchProduct = async (id) => {
        let fetchResult = {};
        await fetch('http://localhost:8080/api/v1/tarvike/' + id)
                .then(res => res.json())
                .then(data => {fetchResult = data}).catch(console.log);
        if (fetchResult !== null && fetchResult.hasOwnProperty("tarvikeID")) { return fetchResult; }
        else { 
            alert("Antamaasi tarvikeid:tä ei ole tietokannassa.") 
            return ({ nimi : "", hinta : "", alv : "" });
        }
    }
    const fetchService = async (name) => {
        let fetchResult = {};
        await fetch('http://localhost:8080/api/v1/tuntityo/' + name)
                .then(res => res.json())
                .then((data) => {(fetchResult = data )}).catch(console.log);
        if (fetchResult !== null && fetchResult.hasOwnProperty("tyonTyyppi")) { return fetchResult; }
        else { console.log("työtä ei ole kannassa") }
    }
    const updateQuoteTotal = () => {
        let quote = {total : 0, taxfreeTotal : 0, taxAmount : 0, workAmount : 0};
        let items = quoteProductsValue;
        let item = null;
        for (let i = 0; i < items.length; i++) {
            item = items[i];
            console.log(item)
            if (item.hasOwnProperty("tuote") && item.tuote.hasOwnProperty("tarvikeID") && item.tuote !== null) {
                if (item.aleprosentti === "" || item.aleprosentti.isNaN) {item.aleprosentti = 0}
                console.log(item.aleprosentti);
                let factor = item.lkm * (1 - (item.aleprosentti / 100));
                quote.total += Number((factor * (item.tuote.myyntihinta)).toFixed(2));
                quote.taxAmount += Number((factor * (item.tuote.myyntihinta * (item.tuote.alv / 100))).toFixed(2));
                quote.taxfreeTotal += Number((factor * ((item.tuote.myyntihinta - (item.tuote.myyntihinta * (item.tuote.alv / 100))))).toFixed(2));
            }
        }
        items = quoteServicesValue;
        for (let i = 0; i < items.length; i++) {
            item = items[i];
            if (item.hasOwnProperty("tyo") && item.tyo !== null) {
                if (item.aleprosentti === "" || item.aleprosentti.isNaN) {item.aleprosentti = 0}
                console.log("summataanTuotteita")
                console.log(item)
                let factor  = item.lkm * (1 - (item.aleprosentti / 100));
                quote.total += Number((factor * item.tyo.hinta).toFixed(2));
                quote.workAmount += Number((factor * item.tyo.hinta).toFixed(2));
                quote.taxAmount += Number((factor * item.tyo.hinta * (item.tyo.alv / 100)).toFixed(2));
                quote.taxfreeTotal += Number((factor * (item.tyo.hinta - (item.tyo.hinta * (item.tyo.alv / 100)))).toFixed(2));
            }
        }
        console.log(quote)
        setQuoteTotal(quote);
    }
    const formQuoteToContract = async () => {
        if (quoteId !== null && quoteId !== "") {
            let contract = {kohdeid: quoteId, 
                            tyyppi : "urakka", 
                            osamaksu : "1", 
                            pvm : currentDateIso, 
                            sopimuksentila : "tarjous", 
                            selite : ("Urakkatarjous kohteesta " + quoteId),
                            tyonhinta : quoteTotal.workAmount,
                            tarvikkeidenhinta : (quoteTotal.total - quoteTotal.workAmount),
                            sopimuksensumma : (quoteTotal.total + quoteTotal.workAmount)
                        };
            const requestOptions = {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(contract)
                };
            console.log("http://localhost:8080/api/v1/tyosopimus/", requestOptions)
            await fetch("http://localhost:8080/api/v1/tyosopimus/", requestOptions)
                .then(response => response.json())
                .then(result => console.log(result))
                .catch(console.log);
            fetchTable(tableName);
        }
        else {
            window.alert("Virhe hinta-arviota luodessa. Hinta-arviolla annettava kohteen ID, joka numeerista tyyppiä.")
        }
    }
    const formPrintableInvoice = async (sopimusid) => {
        await fetch("http://localhost:8080/api/v1/tyosopimus/" + sopimusid + "/lasku")
                .then(response => response.json())
                .then(result => {
                    console.log(result)
                    if (result.hasOwnProperty("asiakas")) {
                        result.sopimusid = sopimusid;
                        setInvoiceData(result);
                        calculatePrintableInvoice(result);
                        setShowInvoice(true);
                    }
                    else {
                        alert("Annettua sopimustunnusta ei ole tietokannassa");
                        setShowInvoice(false);
                    }
                })
                .catch(console.log);
    }
    const calculatePrintableInvoice = async (fetchData) => {
        console.log(invoiceData);
        let invoiceProducts = [];
        let invoiceServices = [];
        let sopimus = (fetchData === null) ? invoiceData : fetchData;
        console.log(sopimus)
        let invoiceTotals = {taxfree : 0, taxAmount : 0, total : 0,  workAmountWithoutTax : 0};
        // Tarvikkeesta HALUTAAN
        // nimi, kplHinta, ale%, aleHinta alv%, hintaAlvilla, kpl, yksikko, summaAlv0, summaAlvilla, alvinSumma
        console.log(sopimus);
        for (let i = 0; i < sopimus.tarvikkeet.length; i++) {
            let tarvike = sopimus.tarvikkeet[i];
            let tarviketieto = {};
            let tuoterivi = {};
            for (let j = 0; j < sopimus.tarviketiedot.length; j++) {
                if (sopimus.tarviketiedot[j].tarvikeID == tarvike.tarvikeID) {
                    tarviketieto = sopimus.tarviketiedot[j]
                }
            }
            tuoterivi.nimi = tarviketieto.nimi;
            tuoterivi.kplhinta = tarviketieto.myyntihinta;
            tuoterivi.aleprosentti = tarvike.aleProsentti;
            tuoterivi.aleHinta = Number((tarviketieto.myyntihinta * (1 - (tarvike.aleProsentti / 100))).toFixed(2));
            tuoterivi.alvprosentti = tarviketieto.alv;
            tuoterivi.kplhintaAlv = Number((tuoterivi.aleHinta * (tarviketieto.alv / 100)).toFixed(2));
            tuoterivi.lkm = tarvike.lkm
            tuoterivi.yksikko = tarviketieto.yksikko
            tuoterivi.rivisumma = Number((tuoterivi.aleHinta * tarvike.lkm).toFixed(2));
            invoiceTotals.taxfree += tuoterivi.rivisumma;
            tuoterivi.rivisummaAlv = Number((tuoterivi.rivisumma * (1 + tarviketieto.alv / 100)).toFixed(2));
            invoiceTotals.total += tuoterivi.rivisummaAlv;
            tuoterivi.riviAlvSumma = Number((tuoterivi.rivisummaAlv - tuoterivi.rivisumma).toFixed(2));
            invoiceTotals.taxAmount += tuoterivi.riviAlvSumma;
            invoiceProducts.push(tuoterivi);
        }
        console.log(invoiceProducts);
        setInvoiceProducts(invoiceProducts);
        // SERVICET
        for (let i = 0; i < sopimus.tyot.length; i++) {
            let tyo = sopimus.tyot[i];
            let tyotieto = {};
            let tyorivi = {};
            for (let j = 0; j < sopimus.tyotyypit.length; j++) {
                if (sopimus.tyotyypit[j].tyonTyyppi == tyo.tyonTyyppi) {
                    tyotieto = sopimus.tyotyypit[j]
                }
            }
            tyorivi.nimi = tyotieto.tyonTyyppi;
            tyorivi.kplhinta = tyotieto.hinta;
            tyorivi.aleprosentti = tyo.aleProsentti;
            tyorivi.aleHinta = Number((tyotieto.hinta * (1 - (tyo.aleProsentti / 100))).toFixed(2));
            tyorivi.alvprosentti = tyotieto.alv;
            tyorivi.kplhintaAlv = Number((tyorivi.aleHinta * (tyotieto.alv / 100)).toFixed(2));
            tyorivi.lkm = tyo.tuntiLkm;
            tyorivi.yksikko = ("tuntia");
            tyorivi.rivisumma = Number((tyorivi.aleHinta * tyorivi.lkm).toFixed(2));
            invoiceTotals.workAmountWithoutTax += tyorivi.rivisumma;
            invoiceTotals.taxfree += tyorivi.rivisumma;
            tyorivi.rivisummaAlv = Number((tyorivi.rivisumma * (1 + tyotieto.alv / 100)).toFixed(2));
            invoiceTotals.total += tyorivi.rivisumma;
            tyorivi.riviAlvSumma = Number((tyorivi.rivisummaAlv - tyorivi.rivisumma).toFixed(2));
            invoiceTotals.taxAmount += tyorivi.riviAlvSumma;
            invoiceServices.push(tyorivi);
        }
        console.log(invoiceServices);
        setInvoiceServices(invoiceServices);
        setInvoiceTotals(invoiceTotals);
    }
    
    useEffect(() => {
        fetchTable(tableName)
	}, [tableName, quoteProductsValue, quoteServicesValue, inputTypeRadioValue]);

	return (
		<div className={classes.app}>

        <AppBar position="static">
              <Tabs value={tableName} onChange={handleTabChange}>
                  <Tab label={"Asiakkaat"} value={relations[0]}/><Tab label={"Työkohteet"} value={relations[1]}/><Tab label={"Työsopimukset"} value={relations[2]}/><Tab label={"Työsuoritukset"} value={relations[3]}/><Tab label={"Työsuoritusten tuntityöt"} value={relations[4]}/><Tab label={"Laskut"} value={relations[5]}/><Tab label={"Tarvikeluettelot"} value={relations[6]}/><Tab label={"Tarvikkeet"} value={relations[7]}/><Tab label={"Tuntityöt"} value={relations[8]}/>
              </Tabs>
          </AppBar>

        <Paper className={classes.textFields} elevation={2}>
            <Typography className={classes.root}>Hae avaimella</Typography>
            <TextField type="number" className={classes.textFields} id="searchByKey" label="Avain" variant="outlined" value={searchFieldValue} onChange={updateSearchFieldValue}/>
            <Button className={classes.textFields} variant="contained" color="primary" onClick={handleSearchClick} startIcon={<SearchIcon />}>Hae</Button>
        </Paper>

        {/* TABLE */ },
        <Paper className={classes.textFields} elevation={2}>
            <Typography className={classes.root}>Relaation tila:</Typography>
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
        </Paper>

        <Paper className={classes.textFields} elevation={2}>
            <Typography className={classes.root}>Lisää tai muokkaa entiteettiä</Typography>
            <Typography className={classes.textFields}>Entiteetin avain luodaan tietokantaan lisätessä automaattisesti.</Typography>
            <RadioGroup className={classes.textFields} aria-label="inputType" name="inputType" value={inputTypeRadioValue} onChange={handleInputTypeChange}>
                <FormControlLabel value="lisaa" control={<Radio />} label="Lisää entiteetti"/>
                <FormControlLabel value="muokkaa" control={<Radio />} label="Muokkaa entiteettiä" />
            </RadioGroup>
          <form noValidate autoComplete="off">
              {insertFields}
            </form>
          <Button className={classes.textFields}  variant="contained" color="primary" onClick={handleInsertClick} disabled= {inputTypeRadioValue === "muokkaa"} startIcon={<AddIcon />}>Lisää</Button>
    <Button className={classes.textFields}  variant="contained" color="primary" onClick={handleEditClick} disabled= {inputTypeRadioValue === "lisaa"} startIcon={<EditIcon/>}>Muokkaa</Button>
        </Paper>
        <Paper className={classes.textFields} elevation={2}>
            <Typography className={classes.root} >poista entiteetti</Typography>
            <TextField type="number" className={classes.textFields}  id="deleteByKey" label="Avain" variant="outlined" value={deleteFieldValue} onChange={updateDeleteFieldValue}/>
            <Button className={classes.textFields}  variant="contained" color="secondary" onClick={handleDeleteClick} startIcon={<DeleteIcon />}>Poista</Button>
        </Paper>
        
        {additionalForm}

        {/* R1 Hinta-arvio */}
        <Paper className={classes.textFields} elevation={2}
            style={{ display : ((tableName === "tyosopimus" || tableName === "lasku" || tableName === "tyokohde" || tableName === "tarvike") ? "block" : "none")}}>
          <Typography className={classes.root} >Laske hinta-arvio kohteelle</Typography>
          <form noValidate autoComplete="off"></form>
            <TextField type="number" className={classes.textFields} key="quoteIdTextField" label="kohdeid" variant="outlined" value={quoteId} onChange={(event) => {setQuoteId(event.target.value)}}></TextField>
            <Button className={classes.textFields} variant="contained" color="primary" onClick={addProductRow} startIcon={<AddIcon />}>Lisää tuote</Button>
            <Button className={classes.textFields} variant="contained" color="primary" onClick={addServiceRow} startIcon={<AddIcon />}>Lisää palvelu</Button>
            <Button className={classes.textFields} variant="outlined" color="primary" onClick={formQuoteToContract} startIcon={<CloudUploadIcon />}>Mudosta urakka-tarjous</Button>
            
            {/* Hinta-arvion tuotteet */}
            {quoteProductsValue.map((item, index) =>
                        <div key={item.id} className={classes.productContainer}>
                            <Button className={classes.deleteButton}  variant="contained" color="secondary" onClick={() => handleProductDeleteClick(item.id)}><DeleteIcon /></Button>
                            <TextField type="number" className={classes.textFieldsShort} key={"quoteProducts_tarvikeid_" + item.id} label="tarvikeid" variant="outlined" 
                                onChange={ async (e) => {
                                    updateQuoteProductsValue(e, "tarvikeid", item.id)
                                    .then(console.log(quoteProductsValue[item.id].tuote.nimi))
                                    }}></TextField>
                            <TextField type="number" className={classes.textFieldsShort} key={"quoteProducts_lkm" + item.id} label="lkm" variant="outlined" onChange={(e) => {updateQuoteProductsValue(e, "lkm", item.id)}}></TextField>
                            <TextField type="number" className={classes.textFieldsShort} key={"quoteProducts_aleprosentti" + item.id} label="ale%" variant="outlined" onChange={(e) => {updateQuoteProductsValue(e, "aleprosentti", item.id)}}></TextField>
                            
                            <div key={item.id + "_text"} className={classes.productTextFields}>
                                <Typography className={classes.textFields}>{"Tuotteen nimi: " + (quoteProductsValue[item.id].tuote.nimi)}</Typography>
                                <Typography className={classes.textFields}>{"Tuotteen yksikköhinta: " + (quoteProductsValue[item.id].tuote.myyntihinta) + " €"}</Typography>
                                <Typography className={classes.textFields}>{"Tuotteen ALV: " + (quoteProductsValue[item.id].tuote.alv) + " %"}</Typography>
                            </div>
                                
                        </div>
                )
            }
            {/* Hinta-arvion työt */}
            {quoteServicesValue.map((item, index) =>
                        <div key={item.id} className={classes.productContainer}>
                            <Button className={classes.deleteButtonProduct}  variant="contained" color="secondary" onClick={() => handleServiceDeleteClick(item.id)}><DeleteIcon /></Button>
                            <FormControl className={classes.formControl} variant="outlined" key={"ServiceName_select_" + item.id}>
                                <InputLabel htmlFor="outlined-age-native-simple" key={"quote_inputLabel_" + item.id}>Työn tyyppi</InputLabel>
                                    <Select key={"select_" + item.id} native onChange={(e) => {updateQuoteServicesValue(e, "tyontyyppi", item.id)}} label="Työn tyyppi">
                                        <option key={"select_asennustyo_" + item.id}>asennustyo</option>
                                        <option key={"select_suunnittelu_" + item.id}>suunnittelu</option>
                                        <option key={"select_aputyo_" + item.id}>aputyo</option>
                                        <option key={"select_matkakorvaus_" + item.id}>matkakorvaus</option>                 
                                    </Select>
                            </FormControl>
                            <TextField type="number" className={classes.textFieldsShort} key={"quoteServices_lkm" + item.id} label="lkm" variant="outlined" onChange={(e) => {updateQuoteServicesValue(e, "lkm", item.id)}}></TextField>
                            <TextField type="number" className={classes.textFieldsShort} key={"quoteServices_aleprosentti" + item.id} label="ale%" variant="outlined" onChange={(e) => {updateQuoteServicesValue(e, "aleprosentti", item.id)}}></TextField>
                            
                            <div key={item.id + "_service_text"} className={classes.productTextFields}>
                                <Typography className={classes.textFields}>{"Työn tuntihinta: " + (quoteServicesValue[item.id].tyo.hinta) + " €"}</Typography>
                                <Typography className={classes.textFields}>{"Tuotteen ALV: " + (quoteServicesValue[item.id].tyo.alv) + "%"}</Typography>
                            </div>
                                
                        </div>
                    
                )
            }
            <Typography className={classes.textFields}>{"Kokonaissumma (sis. ALV): " + quoteTotal.total + " €"}</Typography>
            <Typography className={classes.textFields}>{"Kokonaissumma (ilman ALV): " + quoteTotal.taxfreeTotal + " €"}</Typography>
            <Typography className={classes.textFields}>{"Arvonlisäveron määrä: " + quoteTotal.taxAmount + " €"}</Typography>
            <Typography className={classes.textFields}>{"Kotivähennyskelpoinen osuus: " + quoteTotal.workAmount + " €"}</Typography>
        </Paper>




        
        <Paper className={classes.textFields} elevation={2}
            style={{ display : ((tableName === "tyosopimus" || tableName === "lasku") ? "block" : "none")}}
        >
            <Typography className={classes.root} >Muodosta lasku sopimuksesta</Typography>
            <TextField type="number" className={classes.textFieldsShort} value={selectedInvoice} key={"laskunvalinta"} label="sopimusId" variant="outlined" 
                onChange={(event) => {setSelectedInvoice(event.target.value)}}></TextField>
            <Button className={classes.textFields} variant="outlined" color="primary"
                onClick={(event) => {
                    formPrintableInvoice(selectedInvoice)
                    console.log(selectedInvoice)
                }}
                    >
                    {"Lasku sopimuksesta nro. " + (selectedInvoice != null ? selectedInvoice : "")}
            </Button>
        
            {/* R2 Hinta-arvio */}
            <div id="invoicepreview"
                style={{ display : ((showInvoice) ? "block" : "none")}}
            >
                    <header>
                        <h1 className="company">TMI SÄHKÖTÄRSKY</h1>
                        <div className="sender">
                            <div className="name">Pekka Tärsky</div>
                            <div className="address">Varastokuja 3 B</div>
                            <div className="city">33720 Tampere</div>
                            <div className="phone">Puhelin: +358 12 345 6789</div>
                            <div className="iban">IBAN: FI49 5000 9420 0287 30</div>
                            <div className="bic">BIC/SWIFT: OKOYHIHH</div>
                            <div className="companyId">Y-tunnus: 1234567-8</div>
                        </div>
                        <div className="extra">
                            <div className="invoice">LASKU</div>
                            <div className="date">{currentDateIso}</div>
                            <div className="reference">#sopimus-{invoiceData.sopimusid}:{"1234567-8"}</div>
                        </div>
                    </header>
                    <article>
                        <div className="info">
                            <div className="recipient">
                                <div className="company">{"Yksityisasiakas"}</div>
                                <div className="name">{invoiceData.asiakas}</div>
                                <div className="address">{invoiceData.asiakasosoite}</div>
                                <div className="city">Tampere, 33100</div>
                                <div className="phone">+358 12 345 6789</div>
                                <div className="companyId"></div>
                            </div>
                        </div>
                        <table className="services">
                            <thead>
                                <tr>
                                    <th>Tuote</th>
                                    <th>€ (alv 0%) </th>
                                    <th>Ale %</th>
                                    <th>Ale€ (alv 0%)</th>
                                    <th>ALV %</th>
                                    <th>Lkm</th>
                                    <th>Yksikkö</th>
                                    <th>Summa (alv 0%)</th>
                                    <th>Rivi Alv</th>
                                    <th>Summa (sis. alv)</th>
                                </tr>
                            </thead>
                            <tbody>
                            {invoiceProducts.map((product, i) => 
                                <tr key={"product_" + i}>
                                    <th>{product.nimi}</th>
                                    <th>{product.kplhinta}</th>
                                    <th>{product.aleprosentti}</th>
                                    <th>{product.aleHinta}</th>
                                    <th>{product.alvprosentti}</th>
                                    <th>{product.lkm}</th>
                                    <th>{product.yksikko}</th>
                                    <th>{product.rivisumma}</th>
                                    <th>{product.riviAlvSumma}</th>
                                    <th>{product.rivisummaAlv}</th>
                                </tr>
                            )}
                            </tbody>
                        </table>
                        <br style={{height: "3em"}}></br>
                        <table className="services">
                            <thead>
                                <tr>
                                    <th>Työ</th>
                                    <th>€ (alv 0%) </th>
                                    <th>Ale %</th>
                                    <th>Ale€ (alv 0%)</th>
                                    <th>ALV %</th>
                                    <th>Lkm</th>
                                    <th>Yksikkö</th>
                                    <th>Summa (alv 0%)</th>
                                    <th>Rivi Alv</th>
                                    <th>Summa (sis. alv)</th>
                                </tr>
                            </thead>
                            <tbody>
                            {invoiceServices.map((service, i) =>
                                <tr key={"service_" + i}>
                                    <td>{service.nimi}</td>
                                    <td>{service.kplhinta}</td>
                                    <td>{service.aleprosentti}</td>
                                    <td>{service.aleHinta}</td>
                                    <td>{service.alvprosentti}</td>
                                    <td>{service.lkm}</td>
                                    <td>{service.yksikko}</td>
                                    <td>{service.rivisumma}</td>
                                    <td>{service.rivisummaAlv}</td>
                                    <td>{service.riviAlvSumma}</td>
                                </tr>
                            )}
                            </tbody>
                        </table>
                        <br style={{height: "3em"}}></br>
                            {/* TOTALS*/}
                        <h2>Lasku Yhteensä:</h2>
                        <table className="services" className="totals">
                            <tbody>
                                <tr><td>Laskun alv 0% summa: {invoiceTotals.taxfree} €</td></tr>
                                <tr><td>Laskun alv määrä: {invoiceTotals.taxAmount} €</td></tr>
                                <tr><td>Laskun työn määrä (Kotivähennyskelpoista): {invoiceTotals.workAmountWithoutTax} €</td></tr>
                                <tr><td>Laskun kokonaissumma: {invoiceTotals.total} €</td></tr>
                            </tbody>
                        </table>
                    </article>
                    <footer>
                        <div className="companyDetails"></div>
                    </footer>
            </div>
        </Paper>
    </div>
    
    );
}

export default App;
