import React, {useState} from 'react';
import { withRouter } from "react-router-dom";
import axios from 'axios';
import {API_BASE_URL} from '../../constants/api';
import '../Login/Login.css';


function RegisterForm(props) {
    const [state , setState] = useState({
        username : "",
        email:"",
        password : ""
    })
    const handleChange = (e) => {
        const {id , value} = e.target   
        setState(prevState => ({
            ...prevState,
            [id] : value
        }))
    }
    
    const redirectToLogin = () => {
        props.updateTitle('Login')
        props.history.push('/login'); 
    }
    const handleSubmitClick = (e) => {
        e.preventDefault();
        if( state.password !=="" && state.username !=="") {
            sendDetailsToServer()    
        } else {
            props.showError('Identifiants invalides!');
        }
    }
    const sendDetailsToServer = () => {
        if(state.username.length && state.password.length) {
            //props.showError(null);
            const payload={
                "username":state.username,
                "email":state.email,
                "password":state.password
            }
            axios.post(API_BASE_URL+'user', payload,{headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin':'*',
                'Authorization': null,
            }})
                .then(function (response) {
                    if(response.status === 200){
                        setState(prevState => ({
                            ...prevState,
                            'successMessage' : 'Registration successful. Redirecting to home page..'
                        }))
                        redirectToLogin();
                        props.showError(null)
                    } else{
                        props.showError("Some error ocurred");
                    }
                })
                .catch(function (error) {
                    console.log(error);
                });    
        } else {
            props.showError('Please enter valid username and password')    
        }
        
    }

  return ( 
    <div className="card">
            <div className="card-header">
            <p className="card-text">Register</p>
            </div>
            <div className="card-body">
    <div className="col-12 col-lg-12  ">      
      <div className="col-12 col-lg-10  mt-2 hv-center">
          <form>
                <div className="form-group text-left">
                <label htmlFor="exampleInputEmail1" class="txt">Username</label>
                <input type="email" 
                       className="form-control" 
                       id="username" 
                       placeholder="Enter username"
                       value={state.username}
                       onChange={handleChange}

                />
                <label htmlFor="exampleInputEmail1" class="txt">Email address</label>
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
                    <label htmlFor="exampleInputPassword1" class="txt">Password</label>
                    <input type="password" 
                        className="form-control" 
                        id="password" 
                        placeholder="Password"
                        value={state.password}
                        onChange={handleChange}

                    />
                </div>
                <button 
                    type="submit" 
                    className="btn btn-primary"
                    onClick={handleSubmitClick}
                >
                    Register
                </button>
            </form>
      </div>
 </div>
 </div>
 </div>
  )
} 
export default withRouter(RegisterForm);

