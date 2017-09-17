import React, { Component } from 'react';
import {
  BrowserRouter as Router,
  Route,
  Link
} from 'react-router-dom'

class App extends Component {
  render() {

    const HomePage = () => (
      <div>
        <h2>Home</h2>
      </div>
    )
    
    const About = () => (
      <div>
        <h2>About Us</h2>
      </div>
    )

    const Routing = () => (
      <Router>
        <div>
          <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/about">About Us</Link></li>
          </ul>
    
          <hr/>
    
          <Route exact path="/" component={HomePage}/>
          <Route path="/about" component={About}/>
        </div>
      </Router>
    )
   
    return (
      <Routing/>
    );
  }
}

export default App;
