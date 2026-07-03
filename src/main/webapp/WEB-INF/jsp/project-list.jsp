<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Management</title>

<style>
	
	.msg-modal{
	    display:none;
	    position:fixed;
	    top:0;
	    left:0;
	    width:100%;
	    height:100%;
	    background:rgba(0,0,0,0.5);
	    z-index:9999;
	}

	.msg-modal-content{
	    width:400px;
	    background:#fff;
	    margin:15% auto;
	    border-radius:10px;
	    overflow:hidden;
	    box-shadow:0 5px 20px rgba(0,0,0,0.3);
	    animation:popup .3s;
	}

	@keyframes popup{
	    from{
	        transform:scale(.8);
	        opacity:0;
	    }
	    to{
	        transform:scale(1);
	        opacity:1;
	    }
	}

	.msg-header{
	    background:#0d6efd;
	    color:white;
	    padding:15px;
	    display:flex;
	    justify-content:space-between;
	    align-items:center;
	    font-size:18px;
	    font-weight:bold;
	}

	.msg-close{
	    cursor:pointer;
	    font-size:24px;
	}

	.msg-body{
	    padding:25px;
	    text-align:center;
	    font-size:16px;
	}

	.msg-footer{
	    padding:15px;
	    text-align:center;
	}

	.msg-btn{
	    background:#0d6efd;
	    color:white;
	    border:none;
	    padding:10px 20px;
	    border-radius:5px;
	    cursor:pointer;
	}

	.msg-btn:hover{
	    background:#0b5ed7;
	}

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial,sans-serif;
}

body{
    background:#f4f6f9;
}

.header{
    background:#2c3e50;
    color:white;
    padding:15px 30px;
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.container{
    padding:25px;
}

.top-bar{
    display:flex;
    justify-content:space-between;
    margin-bottom:20px;
}

.add-btn{
    background:#27ae60;
    color:white;
    border:none;
    padding:10px 18px;
    border-radius:5px;
    cursor:pointer;
}

table{
    width:100%;
    border-collapse:collapse;
    background:white;
}

table,th,td{
    border:1px solid #ddd;
}

th{
    background:#34495e;
    color:white;
    padding:12px;
}

td{
    padding:10px;
}

tr:hover{
    background:#f5f5f5;
}


.btn-edit{
    background:#0d6efd;
    color:white;
    border:none;
    padding:8px 16px;
    border-radius:8px;
    cursor:pointer;
    margin-right:8px;
    font-weight:bold;
}

.btn-edit:hover{
    background:#0b5ed7;
}

.btn-delete{
    background:#dc3545;
    color:white;
    border:none;
    padding:8px 16px;
    border-radius:8px;
    cursor:pointer;
    font-weight:bold;
}

.btn-delete:hover{
    background:#bb2d3b;
}

.edit-btn{
    background:#3498db;
    color:white;
    border:none;
    padding:6px 10px;
    cursor:pointer;
    margin-right:5px;
}

.delete-btn{
    background:#e74c3c;
    color:white;
    border:none;
    padding:6px 10px;
    cursor:pointer;
}

.view-btn{
    background:#95a5a6;
    color:white;
    border:none;
    padding:6px 10px;
}

.modal{
    display:none;
    position:fixed;
    left:0;
    top:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,.5);
}

.modal-content{
    background:white;
    width:500px;
    margin:100px auto;
    padding:25px;
    border-radius:8px;
}

.form-group{
    margin-bottom:15px;
}

.form-group label{
    display:block;
    margin-bottom:5px;
}

.form-group input{
    width:100%;
    padding:8px;
}

.error{
    color:red;
    font-size:12px;
    margin-top:4px;
}

.invalid{
    border:1px solid red;
}

.project-link{
    color:#3498db;
    text-decoration:none;
    font-weight:bold;
}

.project-link:hover{
    text-decoration:underline;
}

.logout-btn{
    background:#e74c3c;
    color:white;
    border:none;
    padding:8px 18px;
    border-radius:5px;
    cursor:pointer;
}

.logout-btn:hover{
    background:#c0392b;
}

/* background */
.modal{
    display:none;
    position:fixed;
    left:0;
    top:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,0.5);
    z-index:1000;
}

/* popup */
.modal-content{
    background:#fff;
    width:500px;
	max-height: 150vh;  
	   display: flex;
	   flex-direction: column;
    margin:7% auto;
    border-radius:15px;
    overflow:hidden;
    box-shadow:0 8px 25px rgba(0,0,0,0.3);
}

.modal-body {
    overflow-y: auto;   
    max-height: 70vh;
    padding: 20px;
}

/* header */
.modal-header{
    background:#0d6efd;
    color:white;
    padding:15px 20px;
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.modal-header h3{
    margin:0;
}

/* close icon */
.close-icon{
    font-size:28px;
    cursor:pointer;
    font-weight:bold;
}

.close-icon:hover{
    color:#ddd;
}

/* body */
.modal-body{
    padding:20px;
}

/* form */
.form-group{
    margin-bottom:15px;
}

.form-group label{
    display:block;
    margin-bottom:5px;
    font-weight:bold;
}

.form-group input{
    width:100%;
    padding:10px;
    border:1px solid #ccc;
    border-radius:6px;
    box-sizing:border-box;
}

/* buttons */
.button-group{
    margin-top:20px;
    text-align:right;
}

.save-btn{
    background:#0d6efd;
    color:white;
    border:none;
    padding:10px 18px;
    border-radius:6px;
    cursor:pointer;
    margin-right:10px;
}

.save-btn:hover{
    background:#0b5ed7;
}

.close-btn{
    background:#dc3545;
    color:white;
    border:none;
    padding:10px 18px;
    border-radius:6px;
    cursor:pointer;
}

.close-btn:hover{
    background:#bb2d3b;
}

/* validation */
.error{
    color:red;
    font-size:12px;
    margin-top:4px;
}

.delete-modal{
    display:none;
    position:fixed;
    left:0;
    top:0;
    width:100%;
    height:100%;
    background:rgba(0,0,0,0.5);
    z-index:9999;
}

.delete-modal-content{
    width:400px;
    background:white;
    margin:15% auto;
    border-radius:10px;
    overflow:hidden;
    box-shadow:0 5px 20px rgba(0,0,0,0.3);
}

.delete-header{
    background:#dc3545;
    color:white;
    padding:15px;
    display:flex;
    justify-content:space-between;
    align-items:center;
    font-size:18px;
    font-weight:bold;
}

.delete-x{
    cursor:pointer;
    font-size:24px;
}

.delete-body{
    padding:25px;
    text-align:center;
    font-size:16px;
}

.delete-footer{
    padding:15px;
    text-align:center;
}

.btn-ok{
    background:#0d6efd;
    color:white;
    border:none;
    padding:10px 20px;
    border-radius:5px;
    cursor:pointer;
    margin-right:10px;
}

.btn-close{
    background:#dc3545;
    color:white;
    border:none;
    padding:10px 20px;
    border-radius:5px;
    cursor:pointer;
}

.back-btn{
    background:#0d6efd;
    color:white;
    border:none;
    padding:5px 5px;
    border-radius:5px;
    cursor:pointer;
    font-weight:bold;
    margin-right:5px;
}

.back-btn:hover{
    background:#0b5ed7;
}

.header-title{
    color: #4FC3F7;
    font-size: 28px;     
    font-weight: bold;   
    letter-spacing: 2px;
	margin-right: 70px; 
}

.user-info{
    font-size:15px;
}


.dt-button {
    background: #3498db !important;
    color: white !important;
    border: none !important;
    border-radius: 3px !important;
    padding: 2px 5px !important;
    font-size: 11px !important;
    line-height: 1.2 !important;
    margin-right: 3px !important;
    min-height: auto !important;
}

.dt-button:hover {
    background: #2980b9 !important;
}

</style>
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.8/css/jquery.dataTables.min.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script src="https://cdn.datatables.net/1.13.8/js/jquery.dataTables.min.js"></script>

<link rel="stylesheet"
      href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.dataTables.min.css">
	  
	

	 

	  <script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>

	  <script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>

	  <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>

	  <script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>

	  <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>

	  <script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>
</head>

<body>

<script>
var userRole='${sessionScope.user.role}';
</script>

<div class="header">

    <div class="user-info">
        User :
        <b>${sessionScope.user.username}</b>

        &nbsp;&nbsp;|

        &nbsp;&nbsp;Role :
        <b>${sessionScope.user.role}</b>
    </div>
	
	<div class="header-title">
		      Solverminds
		  </div>

    <button  class="logout-btn" onclick="logout()">
        Logout
    </button>

</div>

<div class="container">
	
	<button class="back-btn"
	                onclick="goBack()">
	            ← Back
	        </button>

    <div class="top-bar">
		
		

        <h2>Project Management</h2>
		


        <% if(!"WORKER".equals(
              ((com.solverminds.projectmanagement.entity.User)
               session.getAttribute("user"))
               .getRole())) { %>

            <button class="add-btn"
                    onclick="openAdd()">

                + Add Project

            </button>

        <% } %>

    </div>

	<table id="projectTable" class="display">
	    <thead>
	        <tr>
	            <th>Project ID</th>
	            <th>Project Name</th>
	            <th>Client</th>
	            <th>Start Date</th>
	            <th>End Date</th>
	            <th>Actions</th>
	        </tr>
	    </thead>
	    <tbody></tbody>
	</table>

</div>

<!-- POPUP -->

<div class="modal" id="projectModal">

    <div class="modal-content">

        <!-- Header -->
        <div class="modal-header">

            <h3 id="popupTitle">
                Add Project
            </h3>

            <span class="close-icon"
                  onclick="closeModal()">
                &times;
            </span>

        </div>

        <div class="modal-body">

            <input type="hidden"
                   id="projectId">

            <!-- Project Name -->
            <div class="form-group">

                <label>Project Name</label>

                <input type="text"
                       id="projectName">

                <div id="projectNameError"
                     class="error"></div>

            </div>

            <!-- Client Name -->
            <div class="form-group">

                <label>Client Name</label>

                <input type="text"
                       id="clientName">

                <div id="clientNameError"
                     class="error"></div>

            </div>

            <!-- Start Date -->
            <div class="form-group">

                <label>Start Date</label>

                <input type="date"
                       id="startDate">

                <div id="startDateError"
                     class="error"></div>

            </div>

            <!-- End Date -->
            <div class="form-group">

                <label>End Date</label>

                <input type="date"
                       id="endDate">

                <div id="endDateError"
                     class="error"></div>

            </div>

            <div class="button-group">

                <button class="save-btn"
                        onclick="saveProject()">
                    Save
                </button>

                <button class="close-btn"
                        onclick="closeModal()">
                    Close
                </button>

            </div>

        </div>

    </div>

</div>

<div id="messageModal" class="msg-modal">

    <div class="msg-modal-content">

        <div class="msg-header">

            <span id="msgTitle">Message</span>

            <span class="msg-close"
                  onclick="closeMessage()">
                &times;
            </span>

        </div>

        <div class="msg-body">
            <p id="msgText"></p>
        </div>

        <div class="msg-footer">

            <button class="msg-btn"
                    onclick="closeMessage()">
                Close
            </button>

        </div>

    </div>

</div>

<div id="deleteModal" class="delete-modal">

    <div class="delete-modal-content">

        <div class="delete-header">

            <span>Delete Confirmation</span>

            <span class="delete-x"
                  onclick="closeDeleteModal()">
                &times;
            </span>

        </div>

        <div class="delete-body">

            Are you sure you want to delete this project?

        </div>

        <div class="delete-footer">

            <button class="btn-ok"
                    onclick="confirmDelete()">
                OK
            </button>

            <button class="btn-close"
                    onclick="closeDeleteModal()">
                Close
            </button>

        </div>

    </div>

</div>

</div>

<script src="/js/project.js"></script>

</body>
</html>