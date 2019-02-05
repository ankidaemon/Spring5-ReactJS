import React, { Component } from 'react';

class UpdateButton extends Component {
	render() {
	      return (
	    		<div>
	    		    <a href='#' onClick={() => this.props.changeOperation('update',this.props.id)}> Update User</a>
	    		</div>
	      );
	    }
}

export default UpdateButton;