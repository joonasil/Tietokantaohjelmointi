import React from 'react';
import PropTypes from 'prop-types';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Tabs from '@material-ui/core/Tabs';
import Tab from '@material-ui/core/Tab';
import Typography from '@material-ui/core/Typography';
import Box from '@material-ui/core/Box';



class AppBarCustom extends React.Component {


  

  render() {
      return (
        <div>
          <AppBar position="static">
              <Tabs  aria-label="simple tabs example">
                {this.props.tableNames.map((tab) =>
                  <Tab label={tab} value={tab} key={tab}></Tab>
                )}
              </Tabs>
          </AppBar>
        </div>
      )
  }
}
export default AppBarCustom;