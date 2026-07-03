<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Project Management Dashboard</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
    font-family:Arial, sans-serif;
}

body{
    background:#f4f6f9;
}

/* Header */
.header{
    background:#2c3e50;
    color:white;
    padding:15px 30px;
    display:flex;
    justify-content:space-between;
    align-items:center;
}

.user-info{
    font-size:15px;
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

/* Container */
.container{
    display:flex;
    height:calc(100vh - 65px);
}

/* Sidebar */
.sidebar{
    width:250px;
	height:150vh;
    background:#34495e;
    color:white;
    padding-top:20px;
}

.sidebar ul{
    list-style:none;
}

.sidebar ul li{
    padding:18px 25px;
    border-bottom:1px solid #455a64;
    cursor:pointer;
}

.sidebar ul li:hover{
    background:#1abc9c;
}

/* Content */
.content{
    flex:1;
    padding:40px;
}

.card{
    background:white;
    padding:30px;
    border-radius:10px;
    box-shadow:0 2px 10px rgba(0,0,0,0.1);
}

.card h2{
    color:#2c3e50;
    margin-bottom:15px;
}
/* Statistics cards container */
.stats-container{
    display:flex;
    gap:20px;
    margin-bottom:30px;
}

/* Individual cards */
.stat-card{
    flex:1;
    background:#ffffff;
    padding:25px;
    border-radius:12px;
    box-shadow:0 2px 10px rgba(0,0,0,0.1);
    text-align:center;
    border-left:5px solid #0d6efd;
}

.stat-card h3{
    color:#555;
    font-size:18px;
    margin-bottom:10px;
}

.stat-card h1{
    color:#0d6efd;
    font-size:42px;
    font-weight:bold;
    margin:0;
}


.chart-card{
    background:#ffffff;
    padding:25px;
    border-radius:12px;
    box-shadow:0 2px 10px rgba(0,0,0,0.1);
}

.chart-card h2{
    color:#2c3e50;
    margin-bottom:20px;
}

/* Chart size */
#projectChart{
    width:100% !important;
    max-height:1500px;
}

.header-title{
    color: #4FC3F7;     
    font-size: 28px;     
    font-weight: bold;   
    letter-spacing: 2px; 
}
.header-logo{
    height: 10px;   /* Try 30px, 35px, or 40px */
    width: auto;
    object-fit: contain;
}

</style>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>

function openProjects(){
    window.location.href="/projects";
}

function openActive(){
    window.location.href="/activitylogs";
}

function openProject(){
    window.location.href="/projectsanstasks";
}

function logout(){
    window.location.href="/logout";
}

</script>

</head>

<body>

    <!-- Header -->
    <div class="header">

        <div class="user-info">
            <b>User :</b> ${sessionScope.user.username}
            &nbsp;&nbsp; | &nbsp;&nbsp;
            <b>Role :</b> ${sessionScope.user.role}
        </div>
		
		<div class="header-title">
				      Solverminds
				  </div>

        <button class="logout-btn" onclick="logout()">
            Logout
        </button>

    </div>

    <!-- Body -->
    <div class="container">

        <!-- Left Menu -->
        <div class="sidebar">

            <ul>
                <li onclick="openProjects()">
                    Project Management
                </li>
            </ul>
			
			<%
			String role = "";
			if(session.getAttribute("user") != null){
			    role = ((com.solverminds.projectmanagement.entity.User)
			            session.getAttribute("user"))
			            .getRole();
			}
			
			System.out.println("  roler "+ role);

			if("ADMIN".equalsIgnoreCase(role) || "STAFF".equalsIgnoreCase(role)){
			%>

			<ul>
			    <li onclick="openProject()">
			        Project/Task List
			    </li>
			</ul>

			<%
			}
			%>
			
			
			<%
						
						if(session.getAttribute("user") != null){
						    role = ((com.solverminds.projectmanagement.entity.User)
						            session.getAttribute("user"))
						            .getRole();
						}
						
						System.out.println("  roler "+ role);

						if("ADMIN".equalsIgnoreCase(role)){
						%>

						<ul>
						    <li onclick="openActive()">
						        Active Log
						    </li>
						</ul>

						<%
						}
						%>

        </div>

        <!-- Right Content -->
        <div class="content">

            <div class="card">
                <h2>Dashboard</h2>

				<div class="stats-container">

				    <div class="stat-card">
				        <h3>Total Projects</h3>
				        <h1 id="projectCount">0</h1>
				    </div>

				    <div class="stat-card">
				        <h3>Total Tasks</h3>
				        <h1 id="taskCount">0</h1>
				    </div>

				</div>

				<div class="chart-card">
				    <h2>Project Task Statistics</h2>
				    <canvas id="projectChart"></canvas>
				</div>

            </div>

        </div>

    </div>

</body>
</html>

<script>
	
	window.onload = function () {

	    fetch('/api/dashboard')

	    .then(response => response.json())

	    .then(data => {

	        // Total counts
	        document
	            .getElementById('projectCount')
	            .innerHTML =
	            data.totalProjects;

	        document
	            .getElementById('taskCount')
	            .innerHTML =
	            data.totalTasks;

	        // Statistics chart
	        new Chart(
	            document.getElementById('projectChart'),
	            {
	                type: 'bar',

	                data: {

	                    labels:
	                        data.projectNames,

	                    datasets: [
	                        {
	                            label:
	                                'Task Count',

	                            data:
	                                data.taskCounts,

	                            backgroundColor: [
	                                '#3498db',
	                                '#2ecc71',
	                                '#e67e22',
	                                '#9b59b6',
	                                '#e74c3c',
	                                '#1abc9c'
	                            ],

	                            borderWidth: 1
	                        }
	                    ]
	                },

	                options: {

	                    responsive: true,

	                    plugins: {

	                        legend: {
	                            display: true
	                        },

	                        title: {
	                            display: true,
	                            text: 'Project Task Statistics'
	                        }
	                    },

	                    scales: {

	                        y: {
	                            beginAtZero: true,
	                            ticks: {
	                                precision: 0
	                            }
	                        }
	                    }
	                }
	            }
	        );
	    })

	    .catch(error => {

	        console.error(
	            'Dashboard Error:',
	            error
	        );
	    });
	};
</script>