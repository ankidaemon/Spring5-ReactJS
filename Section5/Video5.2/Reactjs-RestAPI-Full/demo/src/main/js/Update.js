import React, { Component } from 'react';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class Update extends Component {

	constructor(props) {
		super(props);
		this.state = {
				  id:this.props.id,
				  userName: '',
				  phone: '',
				  resultStatus: ''
				};

		this.handleChange = this.handleChange.bind(this);
		this.submit = this.submit.bind(this);
	}

	componentDidMount() {
		client({ path: '/'+ this.state.id })
		.then(
				response => {
		            console.log(response);
		            console.log(response.entity);
		            this.setState( {
						userName: response.entity.userName,
						phone: response.entity.phone
					} );
		        }
			);
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
				id: this.state.id,
		        userName: this.state.userName,
		        phone: this.state.phone		        
		    };
		
		client({
			method: 'PUT',
		    path: '/update',
		    headers: {
		        'Content-Type': 'application/json'
		    },
		    entity : JSON.parse(JSON.stringify(form_data))
		
		})
		.then(
				response => {
		            console.log(response.status);
		            this.setState( {resultStatus: response.status.code} );
		            if(this.state.resultStatus == "200" || this.state.resultStatus == "204"){
		            	this.props.changeOperation('find');
		            }else{
						this.props.changeOperation('find','','Error Updating User with id '+this.state.id);
					}
		        }
			);
		
		event.preventDefault();
		
	}
		
    render() {
      return (
    		  <div>  	        
	    		  <fieldset>
				  <legend>Please enter details to update</legend>
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

export default Update;
