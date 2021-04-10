import React from "react";
import ReactDOM from "react-dom";

class CreateDialog extends React.Component {

	constructor(props) {
		super(props);
		this.handleSubmit = this.handleSubmit.bind(this);
	}

	handleSubmit(e) {
		e.preventDefault(); // stops the event from bubbling further up the hierarchy
		const newCake = {};
		this.props.attributes.forEach(attribute => {
			newCake[attribute] = ReactDOM.findDOMNode(this.refs[attribute]).value.trim();
		});
		this.props.onCreate(newCake);

		// clear out the dialog's inputs
		this.props.attributes.forEach(attribute => {
			ReactDOM.findDOMNode(this.refs[attribute]).value = '';
		});

		// Navigate away from the dialog to hide it.
		window.location = "#";
	}

	render() {
		const inputs = this.props.attributes.map(attribute => {
			return (
				<p key={attribute}>
					<input type="text" placeholder={attribute} ref={attribute} className="field"/>
				</p>
			)
		});

		return (
			<div>
				<a href="#addNewCake">Create</a>

				<div id="addNewCake" className="modalDialog">
					<div>
						<a href="#" title="Close" className="close">X</a>

						<h2>Add New Cake</h2>

						<form>
							{inputs}
							<button onClick={this.handleSubmit}>Create</button>
						</form>
					</div>
				</div>
			</div>
		)
	}
}

export default CreateDialog;