import { withRouter } from "react-router-dom";
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../../constants/api';
import '../Post/post.css';
function Post(props) {
  const [state, setState] = useState({
    items: [],
    commentsPerPost: [],
    posteId: "",
    commentContent: "",
    postContent: "",
    username: ""
  })
  const [comment, setComment] = useState({
    email: null,
    content: null,
    posteId: null
  })

  const [post, setPost] = useState({
    email: null,
    statut: null
    })
  const handleChangePost = (e) => {
    setPost({
      email: localStorage.getItem("email"),
      statut: e.target.value,
    })  
  }

  const handleChangeComment = (e) => {
    console.log("ichange"+comment.posteId);
    setComment({
      email: localStorage.getItem("email"),
      content: e.target.value,
    })
  }

  const deleteComment = (commentId,postId) => {
    console.log(commentId,postId)
    axios.delete(API_BASE_URL + 'comments/' + commentId + "?email=" + localStorage.getItem("email"), {
      auth: {
        username: localStorage.getItem("email"),
        password: localStorage.getItem("password")
      },
      headers: {
        Accept: 'application/json',
      },
    })
      .then(response => {

        if (response.status === 200) {
          const items = [...state.items];
          let postIndex = items.findIndex(item => item.posteId === postId);
          let commentIndex = items[postIndex].comments.findIndex(item1=> item1.commentId=== commentId);
          const commentToDelete = items[postIndex].comments[commentIndex];
          items[postIndex].comments.splice(commentIndex,1);
          const newState = { ...state };
          newState.items = items;
          setState(newState)
        }
      })
  }
  const deletePost = (postId) => {
    axios.delete(API_BASE_URL + 'posts/' + postId + "?email=" + localStorage.getItem("email"), {
      auth: {
        username: localStorage.getItem("email"),
        password: localStorage.getItem("password")
      },
      headers: {
        Accept: 'application/json',
      },
    })
      .then(response => {
        if (response.status === 200) {
          window.location.reload();
          console.log("Post deleted with success" + response.status, response);
        }
      })
  }
  const addComment = (posteId) => {
      const newComment ={
        email:comment.email,
        content:comment.content,
        posteId:posteId,
      }
    axios.post(API_BASE_URL + 'comments/', newComment, {
      auth: {
        username: localStorage.getItem("email"),
        password: localStorage.getItem("password")
      },
      headers: {
        Accept: 'application/json',
      },
    })
      .then(response => {
        if (response.status === 200) {
          comment.datePublication=response.data.datePublication;
          comment.username = response.data.username;
          comment.commentId=response.data.commentId;
          const items = [...state.items];
          let postIndex = items.findIndex(item => item.posteId === posteId);
          if(!items[postIndex].comments)
          items[postIndex].comments = [];
          items[postIndex].comments.push(comment);
          const newState = { ...state };
          newState.items = items;
          setState(newState)
          document.getElementById(posteId).setAttribute('value', "");
          setComment({
            content:""
          })
        }
      })
  }
  const addPost = () => {
    console.log("====username===="+post.username)
    const newPost = {
      email: post.email,
      statut: post.statut,
    }
    axios.post(API_BASE_URL + 'posts/', newPost, {
      auth: {
        username: localStorage.getItem("email"),
        password: localStorage.getItem("password")
      },
      headers: {
        Accept: 'application/json',
      },
    })
      .then(response => {
        if (response.status === 200) {
          post.datePublication=response.data.datePublication;
          post.username = response.data.username;
          const items = [...state.items];
          items.unshift(post);
          const newState = { ...state };
          newState.items = items;
          setState(newState)
          //post.statut= null;
          setPost({
            statut:"null"
          })
        }
      })
  }


  useEffect(() => {
    console.log("=========Run use effect======");
    if (!localStorage.getItem("username")) {
      axios.get(API_BASE_URL + 'user?email=' + localStorage.getItem("email"), {
        auth: {
          username: localStorage.getItem("email"),
          password: localStorage.getItem("password")
        },
        headers: {
          Accept: 'application/json',
        },
      })
        .then(response => {
          if (response.status === 200) {
            localStorage.setItem("username",response.data.username);
            setState({username :response.data.username});
          }
        })
    }
    if (state.items && state.items.length === 0) {
      axios.get(API_BASE_URL + 'posts?', {
        auth: {
          username: localStorage.getItem("email"),
          password: localStorage.getItem("password")
        },
        headers: {
          Accept: 'application/json',
        },
      })
        .then(response => {
          if (response.status === 200) {
            setState({
              items: [...response.data],
              commentsPerPost: [],
              posteId: "",
              commentContent: "",
              postContent: "",
            });
            console.log("======test======", state.items)
          }
        })

    }

  });
  let array = [];

  if (state.items) {
    for (let i = 0; i < state.items.length; i++) {
      if (state.items[i].comments != null) {
        state.commentsPerPost = state.items[i].comments;
      } else
        state.commentsPerPost = [];
      array.push(
        <div className="card" key={i}>
          <div className="card-header">
            <p className="card-text">[{state.items[i].datePublication}]
                <span className="txt">{state.items[i].username}</span>
              {state.items[i].email === localStorage.getItem('email') ? <span
                className="spanForDeletePost"

                onClick={() => deletePost(state.items[i].posteId)}
              >
                Delete
        </span> : null}
            </p>
          </div>
          <div className="card-body">
            <p className="card-text">{state.items[i].statut}</p>
            <hr />
            <p className="txt">Comments</p>
            {state.commentsPerPost.map((item, index) => (
              <div className="comment" key={index}>
                <p>
                  {item.username}:{item.content}<br />
                  {item.datePublication}
                  {item.email === localStorage.getItem('email') ? <span
                    className="spanForDeleteComment"
                    onClick={() => deleteComment(item.commentId, state.items[i].posteId)}
                  >
                    Delete
        </span> : null}
                </p>

              </div>

            ))}
            <textarea id={state.items[i].posteId} placeholder="Enter a comment"
              onChange={handleChangeComment}
            ></textarea>
            <button
              className="btn btn-primary"
              onClick={() => addComment(state.items[i].posteId)

              }

            >Publish</button>


          </div>
        </div>
      );
    }
  }
  return (
    <div class="container">
      <div className="card">
        <div className="card-header">
          <p className="card-text">Hi {state.username}, what's new ?</p>
        </div>
        <div className="card-body">
          <textarea placeholder="" onChange={handleChangePost}
          ></textarea><button
            className="btn btn-primary"
            onClick={() => addPost()}

          >Publish</button>

        </div>
      </div>
      {array}
    </div>
  );


}
export default withRouter(Post);
