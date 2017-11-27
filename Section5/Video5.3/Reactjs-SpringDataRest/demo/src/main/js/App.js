import React, { Component } from 'react';

var rest, mime, errorCode, client;
rest = require('rest'),
mime = require('rest/interceptor/mime');
errorCode = require('rest/interceptor/errorCode');
 
client = rest.wrap(mime).wrap(errorCode);

class App extends Component {
	
	getInitialState: function(){
        return {
            currentOperation: 'read',
            id: null
        };
    },
 
    changeOperation: function(newOperation, id){
        this.setState({currentOperation: newOperation});
            if(id !== undefined){
            this.setState({id: id});
        }
    },
 
    render: function(){
 
        var OperationComponent =
            <FindAll
            changeOperation={this.changeOperation} />;
 
        switch(this.state.currentOperation){
            case 'read':
                break;
            case 'readOne':
                OperationComponent = <ReadOneProductComponent id={this.state.id} changeOperation={this.changeOperation}/>;
                break;
            case 'create':
                OperationComponent = <CreateProductComponent changeOperation={this.changeOperation}/>;
                break;
            case 'update':
                OperationComponent = <UpdateProductComponent id={this.state.id} changeOperation={this.changeOperation}/>;
                break;
            case 'delete':
                OperationComponent = <DeleteProductComponent id={this.state.id} changeOperation={this.changeOperation}/>;
                break;
            default:
                break;
        }
 
        return OperationComponent;
    }