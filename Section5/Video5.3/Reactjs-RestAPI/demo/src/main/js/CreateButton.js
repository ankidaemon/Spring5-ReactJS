import React, { Component } from 'react';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class CreateButton extends Component {
	render() {
	      return (
	    		<div>
	    		    <a href='#' onClick={() => this.props.changeOperation('create')}> Add User</a>
	    		</div>
	      );
	    }
}

export default CreateButton;