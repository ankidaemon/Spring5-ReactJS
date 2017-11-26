import React, { Component } from 'react';

class App extends Component {

	constructor(props) {
		super(props);
		this.state = {users: []};
	}
	componentDidMount() {
		fetch({method: 'GET', path: '/data-rest/users'}).then(response => {
			this.setState({users: response.entity._embedded.users});
		});
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
			<UserComponent key={user._links.self.href} user={user}/>
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

export default App;
