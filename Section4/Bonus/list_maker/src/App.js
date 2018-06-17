import React, { Component } from 'react';
import UserList from './components/UserList';

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      users: [
        {
          name: 'Ankit',
          age: 29
        },{
          name: 'ditto',
          age: 28
        }
      ]
    }
  }

  delete = (name) => {
      const newUsers = this.state.users.filter(u=> (u.name!==name));
      this.setState({users:newUsers});
  }

  add = (name,age) => {
      const newUsers = [...this.state.users,{name:name,age:age}]
      this.setState({users:newUsers});
  }

  render() {
    return (
      <UserList users={this.state.users} delete={this.delete} add={this.add}/>
    );
  }
}

export default App;
