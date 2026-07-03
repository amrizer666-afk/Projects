<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ page session="true"%>

<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">

<title>Active Logs</title>

<link rel="stylesheet"
      href="https://cdn.datatables.net/1.13.8/css/jquery.dataTables.min.css">

<link rel="stylesheet"
      href="https://cdn.datatables.net/buttons/2.4.2/css/buttons.dataTables.min.css">

<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>

<script src="https://cdn.datatables.net/1.13.8/js/jquery.dataTables.min.js"></script>

<script src="https://cdn.datatables.net/buttons/2.4.2/js/dataTables.buttons.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.10.1/jszip.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/pdfmake.min.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.2.7/vfs_fonts.js"></script>

<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.html5.min.js"></script>

<script src="https://cdn.datatables.net/buttons/2.4.2/js/buttons.print.min.js"></script>

<style>

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

.user-info{
    font-size:15px;
}

.header-title{
    color:#4FC3F7;
    font-size:28px;
    font-weight:bold;
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

.back-btn{
    background:#0d6efd;
    color:white;
    border:none;
    padding:8px 15px;
    border-radius:5px;
    cursor:pointer;
    margin-bottom:20px;
}

.back-btn:hover{
    background:#0b5ed7;
}

.top-bar{
    display:flex;
    justify-content:space-between;
    align-items:center;
    margin-bottom:20px;
}

table.dataTable{
    width:100% !important;
    background:white;
    border-collapse:collapse !important;
    border:1px solid #ddd;
}

table.dataTable thead th{
    background:#34495e;
    color:white;
    text-align:center;
    border:1px solid #ddd !important;
    padding:10px;
}

table.dataTable tbody td{
    text-align:center;
    border:1px solid #ddd !important;
    padding:8px;
}

table.dataTable tbody tr:nth-child(even){
    background:#f8f9fa;
}

table.dataTable tbody tr:hover{
    background:#eaf3ff;
}

#activityTable{
    border:1px solid #ddd !important;
}

.dataTables_wrapper .dataTables_paginate .paginate_button{
    padding:4px 10px;
}

.dataTables_filter input{
    padding:5px;
    border:1px solid #ccc;
    border-radius:4px;
}

.dt-button{
    background:#3498db !important;
    color:white !important;
    border:none !important;
    font-size:11px !important;
    padding:3px 8px !important;
    min-height:24px !important;
    line-height:1.2 !important;
    border-radius:3px !important;
    margin-right:3px !important;
}

.dt-button:hover{
    background:#2980b9 !important;
}

</style>

</head>

<body>

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

    <button class="logout-btn"
            onclick="logout()">
        Logout
    </button>

</div>

<div class="container">

    <button class="back-btn"
            onclick="goBack()">
        ← Back
    </button>

    <div class="top-bar">
        <h2>Activity Logs</h2>
    </div>

    <table id="activityTable"
           class="display nowrap"
           style="width:100%">

        <thead>
            <tr>
                <th>ID</th>
                <th>User</th>
                <th>Role</th>
                <th>Action</th>
                <th>Module</th>
                <th>Reference</th>
                <th>Description</th>
                <th>IP Address</th>
                <th>Date Time</th>
            </tr>
        </thead>

        <tbody>

            <c:forEach items="${activityLogs}"
                       var="log">

                <tr>

                    <td>${log.id}</td>

                    <td>${log.username}</td>

                    <td>${log.role}</td>

                    <td>${log.actionType}</td>

                    <td>${log.moduleName}</td>

                    <td>${log.referenceId}</td>

                    <td>${log.description}</td>

                    <td>${log.ipAddress}</td>

                    <td>${log.createdAt}</td>

                </tr>

            </c:forEach>

        </tbody>

    </table>

</div>

<script>

$(document).ready(function(){

    $('#activityTable').DataTable({

        pageLength:10,

        scrollX:true,

        order:[[0,'desc']],

        dom:'lBfrtip',

        buttons:[
            {
                extend:'copy',
                text:'Copy'
            },
            {
                extend:'excel',
                text:'Excel'
            },
            {
                extend:'pdfHtml5',
                text:'PDF',
                orientation:'landscape',
                pageSize:'A3',
                customize:function(doc){

                    doc.defaultStyle.fontSize=8;

                    doc.styles.tableHeader.fontSize=9;

                    doc.content[1].table.widths=
                        Array(
                            doc.content[1]
                               .table.body[0].length+1
                        ).join('*').split('');

                    doc.pageMargins=
                        [20,20,20,20];
                }
            }
        ],

        language:{
            search:"Search : ",
            lengthMenu:"Show _MENU_ records",
            info:"Showing _START_ to _END_ of _TOTAL_ records",
            emptyTable:"No records found"
        }
    });

});

function logout(){
    window.location.href='/logout';
}

function goBack(){
    window.history.back();
}

</script>

</body>
</html>