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
      ],
      name: '',
      age: ''
    }
  }

  delete = (name) => {
      const newUsers = this.state.users.filter(u=> (u.name!==name));
      this.setState({users:newUsers});
  }

  add = () => {
      const newUsers = [...this.state.users,{name:this.state.name,age:this.state.age}]
      this.setState({users:newUsers});
  }

  handleChange = (event) => {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    this.setState({
        [name]:value
    });
  }

  submit = (event) =>{
    this.add()
    this.setState({
      name:'',
      age:''
    })
    event.preventDefault();
  }

  render() {
    return (
      <UserList users={this.state.users} delete={this.delete} add={this.add} submit={this.submit} handleChange={this.handleChange} name={this.state.name} age={this.state.age}/>
    );
  }
}

export default App;
