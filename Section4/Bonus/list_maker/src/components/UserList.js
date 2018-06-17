import React, { Component } from 'react';
import UserComponent from './Users';
import Form from './Form';

export default class UserList extends Component{

    allUsers= (users) => {
        return users.map( el => <UserComponent key={el.name} name={el.name} age={el.age} delete={this.props.delete}/>);
    }

    render(){
        return(
            <div>
            <Form add={this.props.add} />
            <table>
            <tbody>
                <tr>
                    <td>Name</td>
                    <td>Age </td>
                    <td>Delete</td>
                </tr>
                {this.allUsers(this.props.users)}
            </tbody>
            </table>
            </div>
        )
    }
}