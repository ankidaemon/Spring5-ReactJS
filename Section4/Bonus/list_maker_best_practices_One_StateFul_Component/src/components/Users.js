import React from "react";

const Users = (props) => {
        return(
            <tr>
                <td>
                    {props.name}
                </td>
                <td>
                    {props.age}
                </td>
                <td>
                    <button onClick={() => props.delete(props.name)}>Delete User</button>
                </td>
            </tr>
        )
}

export default  Users;