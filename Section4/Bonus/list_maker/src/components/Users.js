import React, {Component} from "react";

export default class Users extends Component{


    render(){
        return(
            <tr>
                <td>
                    {this.props.name}
                </td>
                <td>
                    {this.props.age}
                </td>
                <td>
                    <button onClick={() => this.props.delete(this.props.name)}>Delete User</button>
                </td>
            </tr>
        )
    }
}