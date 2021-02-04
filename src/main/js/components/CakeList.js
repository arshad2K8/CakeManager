import React from "react";
import ReactDOM from "react-dom";
import Cake from "./Cake";

class CakeList extends React.Component {

    constructor(props) {
        super(props);
    }

	render() {
		const cakes = this.props.cakes.map(cake =>
			<Cake key={cake.image} cake={cake} onDelete={this.props.onDelete} />
		);
		return (
			<div>
				<table>
					<tbody>
						<tr>
							<th>Title</th>
							<th>Description</th>
							<th>Image</th>
						</tr>
						{cakes}
					</tbody>
				</table>
			</div>
		)
    }
}

export default CakeList;