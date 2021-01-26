import React from 'react';
import { withRouter } from "react-router-dom";
function Header(props) {
    const goToRegister = (e) => {
        props.updateTitle('Register')
        props.history.push('/register');
    }
    const goToLogin = (e) => {
        props.updateTitle('Login')
        props.history.push('/login');
    }
    const logout=(e)=>{
        localStorage.clear();
       // props.updateTitle('Login')
        props.history.push('/login');
        
    }
   {/* return(
        <nav className="navbar navbar-dark bg-primary">
        <div className="row col-12 d-flex justify-content-left text-white">
        <button 
                type="submit" 
                className="btn btn-primary"
                onClick={goToLogin}
            >
                Login
            </button>
            <button 
                type="submit" 
                className="btn btn-primary"
                onClick={goToRegister}
            >
                Register
            </button>
        </div>
    </nav>
   )*/}
    return(
        <div className=" logout row col-12 d-flex justify-content-left text-white">
            {localStorage.getItem("username")}
        {localStorage.getItem("username")?  <button 
                className="btn  logoutBtn"
                onClick={logout}
            >
                logout
            </button>:null}
            </div>
    )
}
export default withRouter (Header);