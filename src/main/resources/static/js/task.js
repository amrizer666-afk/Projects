let taskTable;

$(document).ready(function () {

    taskTable = $('#taskTable').DataTable({

        ajax: {
            url: '/api/tasks/project/' + projectId,
            type: 'GET',
            dataSrc: ''
        },

        columns: [

            { data: 'taskCode' },

            { data: 'taskName' },

            { data: 'assignedEmployee' },

            { data: 'priority' },

            { data: 'status' },

            { data: 'dueDate' },

            {
                data: null,
                orderable: false,
                className: "text-center",
                render: function(data, type, row) {

                    // ADMIN
                    if (userRole === 'ADMIN') {

                        return `
                            <button class="btn-edit"
                                    onclick="editTask(${row.id})">
                                Edit
                            </button>

                            <button class="btn-delete"
                                    onclick="deleteTask(${row.id})">
                                Delete
                            </button>
                        `;
                    }

                    // STAFF
                    if (userRole === 'STAFF') {

                        if (row.status !== 'COMPLETED') {

                            return `
                                <button class="btn-edit"
                                        onclick="editTask(${row.id})">
                                    Edit
                                </button>
                            `;
                        }

                        return '';
                    }

                    // WORKER/VIEWER
                    return "";
                }
            }
        ],

        paging: true,
        searching: true,
        ordering: true,
		dom: 'lBfrtip',

		buttons: [
		    {
		        extend: 'excelHtml5',
		        title: 'Task_Report'
		    },
		    {
		        extend: 'pdfHtml5',
		        title: 'Task_Report'
		    },
		    {
		        extend: 'csvHtml5',
		        title: 'Task_Report'
		    }
		]
    });

});
function openAdd(){

    document
        .getElementById(
            'popupTitle')
        .innerHTML='Add Task';

    document
        .getElementById(
            'taskId')
        .value='';

    document
        .getElementById(
            'taskName')
        .value='';

    document
        .getElementById(
            'assignedEmployee')
        .value='';

    document
        .getElementById(
            'priority')
        .value='';

    document
        .getElementById(
            'status')
        .value='INITIATED';

    document
        .getElementById(
            'dueDate')
        .value='';

    document
        .getElementById(
            'taskModal')
        .style.display='block';
}

function editTask(id){

    fetch('/api/tasks/'+id)

    .then(r=>r.json())

    .then(task=>{

        document
            .getElementById(
                'popupTitle')
            .innerHTML='Edit Task';

        document
            .getElementById(
                'taskId')
            .value=task.id;

        document
            .getElementById(
                'taskName')
            .value=task.taskName;

        document
            .getElementById(
                'assignedEmployee')
            .value=task.assignedEmployee;

        document
            .getElementById(
                'priority')
            .value=task.priority;

        document
            .getElementById(
                'status')
            .value=task.status;

        document
            .getElementById(
                'dueDate')
            .value=task.dueDate;

        if(task.status==='COMPLETED'
                && userRole!=='ADMIN'){

            document
                .getElementById(
                    'status')
                .disabled=true;
        }
        else{

            document
                .getElementById(
                    'status')
                .disabled=false;
        }

        document
            .getElementById(
                'taskModal')
            .style.display='block';
    });
}

function saveTask(){   debugger

    if(!validateTask())
        return;

    let id=
        document
            .getElementById(
                'taskId')
            .value;

    let task={

        taskName:
            document
                .getElementById(
                    'taskName')
                .value,

        assignedEmployee:
            document
                .getElementById(
                    'assignedEmployee')
                .value,

        priority:
            document
                .getElementById(
                    'priority')
                .value,

        status:
            document
                .getElementById(
                    'status')
                .value,

        dueDate:
            document
                .getElementById(
                    'dueDate')
                .value,

        project:{
            id:projectId
        }
    };

    let url='/api/tasks';
    let method='POST';

    if(id){

        url='/api/tasks/'+id;
        method='PUT';
    }

    fetch(url,{

        method:method,

        headers:{
            'Content-Type':
            'application/json'
        },

        body:
        JSON.stringify(task)
    })

    .then(r=>r.text())

    .then(msg=>{

		showMessage(
				        "Success",
				        msg
				    );

        closeTask();

		taskTable.ajax.reload(null, false);

    });
}

function showMessage(title, message){

    document.getElementById(
        "msgTitle").innerHTML = title;

    document.getElementById(
        "msgText").innerHTML = message;

    document.getElementById(
        "messageModal").style.display = "block";
}

function closeMessage(){

    document.getElementById(
        "messageModal").style.display = "none";
}

function deleteTask(id){

    if(confirm(
        'Delete task?')){

        fetch('/api/tasks/'+id,{

            method:'DELETE'

        })

        .then(r=>r.text())

        .then(msg=>{

            alert(msg);

			taskTable.ajax.reload(null, false);

        });
    }
}






function validateTask() {

    // Clear old errors
    document.getElementById('taskNameError').innerHTML = '';
    document.getElementById('employeeError').innerHTML = '';
    document.getElementById('priorityError').innerHTML = '';
    document.getElementById('dueDateError').innerHTML = '';

    let taskName =
        document.getElementById('taskName')
                .value
                .trim();

    let employee =
        document.getElementById('assignedEmployee')
                .value
                .trim();

    let priority =
        document.getElementById('priority')
                .value;

    let dueDate =
        document.getElementById('dueDate')
                .value;

    // Task Name validation
    if (taskName === '') {

        document
            .getElementById('taskNameError')
            .innerHTML =
            'Task name is required';

        return false;
    }

    // Employee validation
    if (employee === '') {

        document
            .getElementById('employeeError')
            .innerHTML =
            'Employee is required';

        return false;
    }

    // Priority validation
    if (priority === '') {

        document
            .getElementById('priorityError')
            .innerHTML =
            'Please select priority';

        return false;
    }

    // Due date required validation
    if (dueDate === '') {

        document
            .getElementById('dueDateError')
            .innerHTML =
            'Please enter the due date';

        return false;
    }

    // Project end date validation
    if (projectEndDate &&
        dueDate > projectEndDate) {

        document
            .getElementById('dueDateError')
            .innerHTML =
            'Due date exceeds project end date';

        return false;
    }

    return true;
}

function goBack(){
	window.location.href = '/projects';
}

function closeTask(){

    document
        .getElementById(
            'taskModal')
        .style.display='none';
}

function logout(){

    window.location.href=
        '/logout';
}