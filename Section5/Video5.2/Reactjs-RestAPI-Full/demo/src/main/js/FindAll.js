import React, { Component } from 'react';
import CreateButton from './CreateButton';
import UpdateButton from './UpdateButton';
import DeleteButton from './DeleteButton';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class FindAll extends Component {

	constructor(props) {
		super(props);
		this.state = {
			users: [],
			error:''
		};
	}
	componentDidMount() {
		client({ path: '/findAll' })
		.then(
				response => {
		            console.log(response);
		            console.log(response.entity);
		            this.setState( {users: response.entity} );
		        }
			);
	}
			
    render() {
		const errorStyle = {
			color: 'red'
		};
				
      return (
		<div>
			{    this.props.error !== undefined ?
    	         <h2 style={errorStyle}>{this.props.error}</h2> : null
    	    }

    		  <div>
       		  <UserListComponent users={this.state.users} changeOperation={this.props.changeOperation}/>
    		  <br/>
       		  <CreateButton changeOperation={this.props.changeOperation} />
       		  </div>
		</div>
      );
    }
}

export class UserListComponent extends React.Component{
	render() {
		var users = this.props.users.map(user =>
			<UserComponent key={user.id} user={user} changeOperation={this.props.changeOperation}/>
		);
		return (
			<table>
				<tbody>
					<tr>
						<th>User Name</th>
						<th>Phone</th>
						<th>Update</th>
						<th>Delete</th>
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
				<td><UpdateButton id={this.props.user.id} changeOperation={this.props.changeOperation}/></td>
				<td><DeleteButton id={this.props.user.id} changeOperation={this.props.changeOperation}/></td>
			</tr>
		)
	}
}

export default FindAll;
