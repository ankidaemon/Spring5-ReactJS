import React, { Component } from 'react';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');

client = rest.wrap(mime).wrap(errorCode);

class DeleteButton extends Component {
	constructor(props) {
		super(props);
		this.submit = this.submit.bind(this);
	}

	submit(event){
		client({
			method: 'DELETE',
		    path: '/delete/'+this.props.id		
		})
		.then(
				response => {
		            console.log(response.status);
		            this.setState( {resultStatus: response.status.code} );
		            if(this.state.resultStatus == "200" || this.state.resultStatus == "204"){
		            	this.props.changeOperation('find');
		            }else{
						this.props.changeOperation('find','','Error deleting User with id '+this.state.id);
					}
		        }
			);
		event.preventDefault();
	}

		render() {
	      return (
	    		<div>
	    		    <a href='#' onClick={this.submit}> Delete User</a>
	    		</div>
	      );
	    }
}

export default DeleteButton;