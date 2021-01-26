import React, {useState} from 'react';
import './App.css';
import Header from './components/Header/Header';
import RegisterForm from './components/Register/RegisterForm';
import Login from '../src/components/Login/Login';
import Post from '../src/components/Post/Post'

import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {
  const [title, updateTitle] = useState(null);
  const [errorMessage, updateErrorMessage] = useState(null);
  return (
    <Router>
    <div className="App">
        <div className=" d-flex align-items-center flex-column">
          <Switch>
          <Route path="/" exact={true}>
          <Login showError={updateErrorMessage} updateTitle={updateTitle}/>
            </Route>
            <Route path="/register">
              <RegisterForm showError={updateErrorMessage} updateTitle={updateTitle}/>
            </Route>
            <Route path="/login">
              <Login showError={updateErrorMessage} updateTitle={updateTitle}/>
            </Route>
            <Route path="/posts">
            <Header/>
              <Post showError={updateErrorMessage} updateTitle={updateTitle}/>
            </Route>
          </Switch>
       </div>
   </div>
  </Router>
  );
}

export default App;
