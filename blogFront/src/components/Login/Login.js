import React, { useState} from 'react';
import axios from 'axios';
import {API_BASE_URL} from '../../constants/api';
import { withRouter } from "react-router-dom";
import '../Login/Login.css';

function Login(props) {
    const [state , setState] = useState({
        email : "",
        password : ""
    })
    
    const handleChange = (e) => {
        const {id , value} = e.target   
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }

    const handleSubmitClick = (e) => {
        e.preventDefault();
     
        const payload={
            "email":state.email,
            "password":state.password,
        }
        axios.post(API_BASE_URL+'login?username='+payload.email +"&password="+payload.password)
            .then(function (response) {
                console.log("here"+response.status)
                if(response.status === 200){
                   localStorage.setItem("email",payload.email);
                   localStorage.setItem("password",payload.password)
                    redirectToPosts();
                   
                }
                else if(response.data.code === 204){
                    props.showError("Username and password do not match");
                }
                else{
                    props.showError("Username does not exists");
                }
            })
            .catch(function (error) {
                console.log(error);
            });
    }
    const redirectToPosts = () => {
        props.history.push('/posts');
        props.updateTitle('Post');
    }
    return(
        <div className="card" >
                <div className="card-header">
                <p className="card-text">Login</p>
                </div>
                <div className="card-body">
        <div className="col-12 col-lg-12  ">
            <form>
                <div className="form-group text-left">
                <label htmlFor="exampleInputEmail1" className="txt">Email address</label>
                <input type="email" 
                       className="form-control" 
                       id="email" 
                       aria-describedby="emailHelp" 
                       placeholder="Enter email" 
                       value={state.email}
                       onChange={handleChange}
                />

                </div>
                <div className="form-group text-left">
                <label htmlFor="exampleInputPassword1 "className="txt">Password</label>
                <input type="password" 
                       className="form-control" 
                       id="password" 
                       placeholder="Password"
                       value={state.password}
                       onChange={handleChange} 
                />
                </div>
                {props.showError}
                <button 
                    type="submit" 
                    className="btn btn-primary"
                    onClick={handleSubmitClick}
                >Login</button>
            </form>
            <div className="alert alert-success mt-2" style={{display: state.successMessage ? 'block' : 'none' }} role="alert">
                {state.successMessage}
            </div>
            <div className="registerMessage">
                <p>Vous n'avez pas encore un compte? <span></span>
                <a className="loginText" href="/register">  Register</a> </p>
            </div>
        </div>
    </div>
    </div>
    )
}

export default withRouter(Login) ;