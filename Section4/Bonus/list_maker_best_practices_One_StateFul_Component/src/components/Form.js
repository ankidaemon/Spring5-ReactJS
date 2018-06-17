import React from 'react';

 const Form = (props) => {
   
        return(
                <form onSubmit={props.submit}>
                <input type="text" name="name" placeholder="Enter UserName" value={props.name} onChange={props.handleChange}/>
                <input type="text" name="age" placeholder="Enter age" value={props.age} onChange={props.handleChange}/>
                <button>Add User</button>
                </form>
        )
}

export default Form;