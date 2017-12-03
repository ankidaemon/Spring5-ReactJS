import React, { Component } from 'react';
import FindAll from './FindAll';
import Create from './create';

class App extends Component {
	
	constructor(props) {
		super(props);
		this.state = {
				currentOperation: 'find',
	            id: null
		};
		
		this.changeOperation = this.changeOperation.bind(this);
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
            case 'find':
            	OperationComponent = <FindAll changeOperation={this.changeOperation} />;
                break;
            case 'create':
                OperationComponent = <Create changeOperation={this.changeOperation}/>;
                break;
            default:
                break;
        }
 
        return OperationComponent;
    }
}

export default App;