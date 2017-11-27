import React, { Component } from 'react';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class FindAll extends Component {

	constructor(props) {
		super(props);
		this.state = {users: []};
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
      return (
            <UserListComponent users={this.state.users}/>
      );
    }
}

class UserListComponent extends React.Component{
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

class UserComponent extends React.Component{
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
