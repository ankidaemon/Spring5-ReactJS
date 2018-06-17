import React from 'react';
import UserComponent from './Users';
import Form from './Form';

const UserList = (props) => {

   const allUsers= (users) => {
        return users.map( el => <UserComponent key={el.name} name={el.name} age={el.age} delete={props.delete}/>);
    }
        return(
            <div>
            <Form add={props.add} submit={props.submit} handleChange={props.handleChange} name={props.name} age={props.age}/>
            <table>
            <tbody>
                <tr>
                    <td>Name</td>
                    <td>Age </td>
                    <td>Delete</td>
                </tr>
                {allUsers(props.users)}
            </tbody>
            </table>
            </div>
        )
}

export default UserList;