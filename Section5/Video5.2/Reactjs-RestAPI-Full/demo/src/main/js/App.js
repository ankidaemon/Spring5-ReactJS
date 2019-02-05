import React, { Component } from 'react';
import FindAll from './FindAll';
import Create from './create';
import Update from './Update';

class App extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
				currentOperation: 'find',
	            id: null,
	            error:null
		};
		this.key = 0;
		this.changeOperation = this.changeOperation.bind(this);
	}
	
	
	 
    changeOperation(newOperation, id,error){
        this.setState({currentOperation: newOperation});
        if(id !== undefined){
          this.setState({id: id});
        }
        if(error !== undefined){
            this.setState({error: error});
        }
    }
 
    render(){
 
        var OperationComponent = <FindAll changeOperation={this.changeOperation} />;
 
        switch(this.state.currentOperation){
            case 'find':
            	OperationComponent = <FindAll key={this.key++} changeOperation={this.changeOperation} error={this.state.error}/>;
                break;
            case 'create':
                OperationComponent = <Create changeOperation={this.changeOperation}/>;
                break;
            case 'update':
                OperationComponent = <Update changeOperation={this.changeOperation} id={this.state.id}/>;
                break;
            default:
                break;
        }
 
        return OperationComponent;
    }
}

export default App;