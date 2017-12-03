import React, { Component } from 'react';

class CreateButton extends Component {
	render() {
	      return (
	    		<div>
	    		    <a href='#' onClick={() => this.props.changeOperation('create',null)}> Add User</a>
	    		</div>
	      );
	    }
}

export default CreateButton;