import React,{Component} from 'react';

export default class Form extends Component{

    constructor(props){
        super(props);
        this.state = {
            name: '',
            age: ''
        }
    }

    handleChange = (event) => {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        this.setState({
            [name]:value
        });
    }

    submit = (event) =>{
       
        this.props.add(this.state.name,this.state.age)
        this.setState({
            name:'',
            age:''
        })
        event.preventDefault();
    }

    render(){
        return(
                <form onSubmit={this.submit}>
                <input type="text" name="name" placeholder="Enter UserName" value={this.state.name} onChange={this.handleChange}/>
                <input type="text" name="age" placeholder="Enter age" value={this.state.age} onChange={this.handleChange}/>
                <button>Add User</button>
                </form>
        )
    }
}