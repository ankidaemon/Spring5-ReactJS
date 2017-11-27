import React, { Component } from 'react';
import FindAll from './FindAll';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class App extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
				currentOperation: 'read',
	            id: null
		};
	}
	 
    changeOperation(newOperation, id){
        this.setState({currentOperation: newOperation});
            if(id !== undefined){
            this.setState({id: id});
        }
    }
 
    render(){
 
        var OperationComponent = <FindAll changeOperation={this.changeOperation} />;
 
        switch(this.state.currentOperation){
            case 'read':
            	OperationComponent = <FindAll changeOperation={this.changeOperation} />;
                break;
            case 'readOne':
                OperationComponent = <FindOne id={this.state.id} changeOperation={this.changeOperation}/>;
                break;
            case 'create':
                OperationComponent = <Create changeOperation={this.changeOperation}/>;
                break;
            case 'update':
                OperationComponent = <Update id={this.state.id} changeOperation={this.changeOperation}/>;
                break;
            case 'delete':
                OperationComponent = <Delete id={this.state.id} changeOperation={this.changeOperation}/>;
                break;
            default:
                break;
        }
 
        return OperationComponent;
    }
}

export default App;