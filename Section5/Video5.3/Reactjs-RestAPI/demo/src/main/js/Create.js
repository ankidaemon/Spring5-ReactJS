import React, { Component } from 'react';
import CreateButton from './CreateButton';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class Create extends Component {

	constructor(props) {
		super(props);
		this.state = {
				  userName: '',
				  phone: '',
				  resultStatus: ''
				};

		this.handleChange = this.handleChange.bind(this);
		this.submit = this.submit.bind(this);
	}
	
	handleChange(event) {
		const target = event.target;
		const value = target.value;
		const name = target.name;

		this.setState({
		  [name]: value
		});
	}
	
	submit(event){
		var form_data={
		        userName: this.state.userName,
		        phone: this.state.phone		        
		    };
		
		client({
			method: 'POST',
		    path: '/create',
		    headers: {
		        'Content-Type': 'application/json'
		    },
		    entity : JSON.parse(JSON.stringify(form_data))
		
		})
		.then(
				response => {
		            console.log(response.status);
		            this.setState( {resultStatus: response.status.code} );
		            if(this.state.resultStatus == "201"){
		            	this.setState({
		          		  userName: '',
		          		  phone:''
		          		});
		            }
		        }
			);
		
		event.preventDefault();
		
	}
		
    render() {
      return (
    		  <div>
    		    {
    	            this.state.resultStatus == "201" ?
    	                <h2>User Added Successfully</h2>
    	            : null
    	        }    	        
	    		  <fieldset>
				  <legend>Please enter your details</legend>
				  <form onSubmit={this.submit}>
					<table>
						<tbody>
						<tr>
							<td>Enter UserName</td>
							<td><input	name="userName" type="text" value={this.state.userName} onChange={this.handleChange} /></td></tr>
						<tr>
							<td>Enter Phone</td>
							<td><input name="phone" value={this.state.phone} onChange={this.handleChange} /></td></tr>
						<tr>
							<td><button name="submit" onSubmit={this.submit}>Submit</button></td>
							</tr>
						</tbody>
					</table>
				  </form>
				  </fieldset>
				  
				  <br/>			    	 
	    	        <a href='#' onClick={() => this.props.changeOperation('find')}> Find All Users </a>
			  </div>
      );
    }
}

export default Create;
