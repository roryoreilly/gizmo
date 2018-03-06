const React = require('react');
import UpdateDialog from './update-dialog.js';

export default class User extends React.Component {

	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete() {
		this.props.onDelete(this.props.user);
	}

	render() {
		return (
			<tr>
				<td>{this.props.user.entity.firstName}</td>
				<td>{this.props.user.entity.balance}</td>
				<td>{this.props.user.entity.account}</td>
				<td>
					<UpdateDialog user={this.props.user}
								  attributes={this.props.attributes}
								  onUpdate={this.props.onUpdate}/>
				</td>
				<td>
					<button onClick={this.handleDelete}>Delete</button>
				</td>
			</tr>
		)
	}
}