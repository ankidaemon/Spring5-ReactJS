import React, { Component } from 'react';
import Home from './components/Home';
import About from './components/About';
import Signup from './components/Signup';
import {
  BrowserRouter as Router,
  Route,
  NavLink
} from 'react-router-dom'

class App extends Component {
  render() {
  
    const Routing = () => (
      <Router>
        <div>
          <ul className="mainmenu">
            <li><NavLink exact to="/">Home</NavLink></li>
            <li><NavLink to="/about">About Us</NavLink></li>
            <li><NavLink to="/signup">Sign Up</NavLink></li>
          </ul>
    
          <Route exact path="/" component={Home}/>
          <Route path="/about" component={About}/>
          <Route path="/signup" component={Signup}/>
        </div>
      </Router>
    )
   
    return (
      <Routing/>
    );
  }
}

export default App;
