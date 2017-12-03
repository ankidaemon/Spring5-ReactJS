import React, { Component } from 'react';
import CreateButton from './CreateButton';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
 
client = rest.wrap(mime);

class FindAll extends Component {

	constructor(props) {
		super(props);
		this.state = {users: [],resultStatus: ''};
	}
	componentDidMount() {
		client({ path: '/findAll' })
		.then(
				response => {
		            console.log(response);
		            this.setState( {resultStatus: response.status.code} );
		            if(this.state.resultStatus == "200"){
		            	this.setState( {users: response.entity} );
		            }		            
		        }
			);
	}
	
    render() {
      return (
    		  <div>
    		  {
  	            this.state.resultStatus == "403" ?
  	                <h2 className="error">You are not authorized.</h2>
  	            : null
  	          }
       		  <UserListComponent users={this.state.users}/>
    		  <br/>
       		  <CreateButton changeOperation={this.props.changeOperation} />
       		  </div>
      );
    }
}

export class UserListComponent extends React.Component{
	render() {
		var users = this.props.users.map(user =>
			<UserComponent key={user.id} user={user}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>User Name</th>
						<th>Phone</th>
					</tr>
					{users}
				</tbody>
			</table>
		)
	}
}

export class UserComponent extends React.Component{
	render() {
		return (
			<tr>
				<td>{this.props.user.userName}</td>
				<td>{this.props.user.phone}</td>
			</tr>
		)
	}
}

export default FindAll;
