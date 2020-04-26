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
import TabBarCustom2 from './components/TabBarCustom2.js';
import TabBarCustom from './components/TabBarCustom.js';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles (theme => ({
    canvas: {
        zIndex: 1, 
        position: "fixed",
        borderStyle: 'solid',
        borderWidth: '2px',
        borderColor: 'black',
        margin: '10px',
    },
    box: {
        margin: '10px',
    },
    textBox: {
        margin: "10px 10px 10px 0px",
    },
}));

function App() {

	const classes = useStyles();

    const [tableName, setTableName] = React.useState("asiakas");
    const [activeTable, setActiveTable] = React.useState(() => {
        let newTable = {};
        fetch('http://localhost:8080/api/v1/' + tableName)
            .then(res => res.json())
            .then((data) => { 
                (newTable.table = data ); 
            })
            .then(fetch('http://localhost:8080/api/v1/metadata/' + tableName)
            .then(res => res.json())
            .then((data) => { 
                (newTable.metadata = data )
            })
            .catch(console.log))
            return newTable;
    });
    const [update, setUpdate] = React.useState(true);
    const [htmlTable, setHtmlTable] = React.useState([]);
    const [htmlTableHead, setHtmlTableHead] = React.useState([]);
    const [relations, setRelations] = React.useState(['asiakas', 'tyokohde', 'tyosopimus', 'tyosuoritus', 'tyosuorituksentuntityo', 'lasku', 'tarvikeluettelo', 'tarvike', 'tuntityo']);

    const canvasRef = useRef(null);

    const updatePage = () => {
        setUpdate(true);
    }

    const fetchTable = (tableName) => {
        let newTable = {};
        console.log('http://localhost:8080/api/v1/' + tableName);
        fetch('http://localhost:8080/api/v1/' + tableName)
            .then(res => res.json())
            .then((data) => { 
                (newTable.table = data ); 
            })
            .then(fetch('http://localhost:8080/api/v1/metadata/' + tableName)
            .then(res => res.json())
            .then((data) => { 
                (newTable.metadata = data )})
            .then(setActiveTable(newTable))
            .then(setTimeout(function(){
                formHtmlTable();
            }, 800))
                .catch(console.log))
        
    }

    const formHtmlTable = () => {
        let table = activeTable.table;
        let metadata = activeTable.metadata;
        let tableSize = Object.keys(table).length;
        let columnCount = Object.keys(metadata).length
        console.log(columnCount + " " + tableSize);
        let html = [];
        for (let i = 0; i < columnCount; i++) {
            let item = metadata[i];
            html.push(<TableCell key={item.column_name + "_" + i}>{item.column_name}</TableCell>)}
        setHtmlTableHead(html);
        html = [];
        
        for (let i = 0; i < tableSize; i++) {
            let item = table[i];
            
            let cells = []
            Object.keys(item).forEach(function(key) {
                if (item != null)
                cells.push(<TableCell key={item[key] + "_" + i}>{item[key]}</TableCell>);
            });
            html.push(<TableRow key={i}>{cells}</TableRow>);
        }
        setHtmlTable(html);
    }

    useEffect(() => {
        fetchTable(tableName)
        console.log("active table: ") 
        console.log(activeTable)
        setUpdate(false)
        
	}, [tableName]);

	return (
		<div className="App">

        <AppBarCustom/>
        <AppBar position="static">
              <Tabs  aria-label="simple tabs example">
                  <Tab label={relations[0]} key={0} onClick={() => {setTableName(relations[0])}}/>
                  <Tab label={relations[1]} key={1} onClick={() => {setTableName(relations[1])}}/>
                  <Tab label={relations[2]} key={2} onClick={() => {setTableName(relations[2])}}/>
                  <Tab label={relations[3]} key={3} onClick={() => {setTableName(relations[3])}}/>
                  <Tab label={relations[4]} key={4} onClick={() => {setTableName(relations[4])}}/>
                  <Tab label={relations[5]} key={5} onClick={() => {setTableName(relations[5])}}/>
                  <Tab label={relations[6]} key={6} onClick={() => {setTableName(relations[6])}}/>
                  <Tab label={relations[7]} key={7} onClick={() => {setTableName(relations[7])}}/>
                  <Tab label={relations[8]} key={8} onClick={() => {setTableName(relations[8])}}/>
                  <Tab label={relations[9]} key={9} onClick={() => {setTableName(relations[9])}}/>
              </Tabs>
          </AppBar>

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
