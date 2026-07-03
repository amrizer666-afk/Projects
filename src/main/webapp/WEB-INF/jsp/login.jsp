<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Solverminds - Project Management</title>

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="${pageContext.request.contextPath}/js/login.js"></script>

<style>
*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial,sans-serif;
}

body{
    height:100vh;
    overflow:hidden;
    background:#f5f7fa;
}

.container{
    display:flex;
    height:100vh;
}

/* LEFT PANEL */
.left-panel{
    width:55%;
    position:relative;
    background:
        linear-gradient(
            rgba(15,23,42,0.45),
            rgba(79,70,229,0.45)
        ),
        url('/images/login-bg.png');

    background-size:cover;
    background-position:center;

    display:flex;
    flex-direction:column;
    justify-content:center;

    color:white;
    padding:70px;
}

.company{
    position:absolute;
    top:40px;
    left:50px;

    font-size:32px;
    font-weight:bold;
    letter-spacing:2px;
}

.company small{
    display:block;
    font-size:14px;
    font-weight:normal;
    margin-top:5px;
}

.title{
    font-size:64px;
    font-weight:bold;
    line-height:1.1;
}

.highlight{
    color:#ffc107;
}

.subtitle{
    margin-top:25px;
    font-size:24px;
}

.description{
    margin-top:15px;
    font-size:18px;
    line-height:1.7;
    width:75%;
}

/* RIGHT PANEL */
.right-panel{
    width:45%;
    background:white;

    display:flex;
    justify-content:center;
    align-items:center;
}

.login-container{
    width:400px;
}

.login-container h2{
    font-size:42px; 
    color:#1e293b;
    margin-bottom:10px;
}

.login-container p{
    color:#64748b;
    margin-bottom:35px;
}

.form-group{
    margin-bottom:20px;
}

.form-group label{
    display:block;
    margin-bottom:8px;
    font-weight:bold;
    color:#334155;
}

.form-group input{
    width:100%;
    padding:15px;
    border:1px solid #d1d5db;
    border-radius:10px;
    font-size:15px;
}

.form-group input:focus{
    outline:none;
    border-color:#4f46e5;
}

.login-btn{
    width:100%;
    padding:15px;
    border:none;
    border-radius:10px;

    background:
        linear-gradient(
            90deg,
            #2563eb,
            #7c3aed
        );

    color:white;
    font-size:17px;
    font-weight:bold;
    cursor:pointer;
    transition:.3s;
}

.login-btn:hover{
    transform:translateY(-2px);
    box-shadow:0 10px 20px rgba(0,0,0,.2);
}

.extra-links{
    margin-top:20px;
    text-align:center;
}

.extra-links a{
    color:#2563eb;
    text-decoration:none;
}

#message{
    margin-top:20px;
    text-align:center;
    color:red;
    font-weight:bold;
}

/* Loading */
#loadingOverlay{
    display:none;
    position:fixed;
    top:0;
    left:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,.7);
    z-index:9999;

    justify-content:center;
    align-items:center;
    flex-direction:column;

    color:white;
}

#loadingOverlay.show{
    display:flex;
}

.loader{
    border:6px solid #f3f3f3;
    border-top:6px solid #2563eb;
    border-radius:50%;
    width:60px;
    height:60px;
    animation:spin 1s linear infinite;
    margin-bottom:15px;
}

@keyframes spin{
    0%{
        transform:rotate(0deg);
    }
    100%{
        transform:rotate(360deg);
    }
}
</style>
</head>

<body>

<div id="loadingOverlay">
    <div class="loader"></div>
    <p>Logging in...</p>
</div>

<div class="container">

    <!-- LEFT SIDE -->
    <div class="left-panel">

        <div class="company">
            SOLVERMINDS
            <small>TECHNOLOGIES</small>
        </div>

        <div class="title">
            Project<br>
            <span class="highlight">Management</span><br>
            System
        </div>

        <div class="subtitle">
            Plan. Track. Collaborate. Deliver.
        </div>

        <div class="description">
            Manage projects efficiently,
            assign tasks, monitor progress,
            and achieve your business goals.
        </div>

    </div>

    <!-- RIGHT SIDE -->
    <div class="right-panel">

        <div class="login-container">

            <h2>Welcome Back!</h2>

            <p>Sign in to continue</p>

            <div class="form-group">
                <label>Username</label>

                <input type="text"
                       id="username"
                       placeholder="Enter username">
            </div>

            <div class="form-group">
                <label>Password</label>

                <input type="password"
                       id="password"
                       placeholder="Enter password">
            </div>

            <button class="login-btn"
                    onclick="login()">
                Sign In
            </button>

            <div class="extra-links">
                <a href="#">
                    Forgot Password?
                </a>
            </div>

            <div id="message"></div>

        </div>

    </div>

</div>

</body>
</html>