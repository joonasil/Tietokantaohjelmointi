import React, { Component } from 'react';

class App extends Component {
  state = {
    customers: []
  }

  componentDidMount() {
    fetch('http://localhost:8080/api/v1/asiakas/withcors')
    .then(res => res.json())
    .then((data) => console.log(data))
    .catch(console.log);
  }

  render() {

    return (
      <div >ASd</div>
    );
  }
}



export default App;