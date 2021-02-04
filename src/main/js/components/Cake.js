import React from "react";
import ReactDOM from "react-dom";

class Cake extends React.Component {
	constructor(props) {
		super(props);
		this.handleDelete = this.handleDelete.bind(this);
	}

	handleDelete(e) {
		e.preventDefault();
		this.props.onDelete(this.props.cake);
	}

	render() {
        return (
			<tr>
				<td>{this.props.cake.title}</td>
				<td>{this.props.cake.description}</td>
				<td><img alt="Image" src={this.props.cake.image} height="50" width="50"/></td>
				<td><button onClick={this.handleDelete}>DELETE</button></td>
			</tr>
        );
	}
}

export default Cake;


