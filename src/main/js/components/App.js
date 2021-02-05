import React from "react";
import ReactDOM from "react-dom";
import CakeList from "./CakeList";
import CreateDialog from "./CreateDialog";

const CAKE_SERVICE_API = 'http://localhost:8080/api/cakes'
const CAKES_PROFILE_API = 'http://localhost:8080/api/profile/cakes'
class App extends React.Component {
    constructor(props) {
    	super(props);
    	this.state = {cakes: [], isFetching: false, attributes: []};
    	this.onDelete = this.onDelete.bind(this);
    	this.onCreate = this.onCreate.bind(this);
    }

    onDelete(cake) {
        fetch(cake._links.self.href, {method: 'DELETE'})
            .then(res => {
                console.log('Deleted '+ res);
                this.fetchCakes();
            })
            .catch(e => {
                console.log(e);
            });
    }

    fetchCakesWithFetchAPI = () => {
        console.log('Fetching Cakes');
        this.setState({...this.state, isFetching: true});
        fetch(CAKE_SERVICE_API)
            .then(response => response.json())
            .then(result => {
                this.setState({cakes: result._embedded.cakes, isFetching: false})
            })
            .catch(e => {
                    console.log(e);
                    this.setState({...this.state, isFetching: false});
            });
    };

    fetchCakes = this.fetchCakesWithFetchAPI

    fetchCakeAttributes() {
        fetch(CAKES_PROFILE_API)
        .then(response => response.json())
        .then(result => {
            const descriptor = result.alps.descriptor[0].descriptor;
            const attrs = descriptor.map(field => field.name);
            console.log('Attibutes retrieved '+ attrs);
            this.setState({attributes: attrs});
         })
         .catch(e => {
            console.log(e);
            this.setState({...this.state, isFetching: false});
         });
    }

	componentDidMount() {
		this.fetchCakes();
		this.fetchCakeAttributes();
	}

	onCreate(newCake) {
	    const reqBody = {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                    },
                body: JSON.stringify(newCake)
        };
        fetch(CAKE_SERVICE_API, reqBody)
          .then(response => response.json())
          .then(result => {
            console.log('Posted a new Cake '+ result); // JSON data parsed by `data.json()` call
            this.fetchCakes();
         });
	}

    render() {
        const cakes = (
            <CakeList cakes={this.state.cakes} onDelete={this.onDelete} />
        );

        return (
            <div className="flex-container">
                <div> Welcome To Cake Manager App</div>
                {cakes}
                <CreateDialog attributes={this.state.attributes} onCreate={this.onCreate}/>
            </div>
        )
    };
}
export default App;