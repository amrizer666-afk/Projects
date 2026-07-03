let projectTable;
$(document).ready(function () {

    projectTable = $('#projectTable').DataTable({
        ajax: {
            url: '/api/projects',
            type: 'GET',
            dataSrc: ''
        },

        columns: [


			{
			       data: 'projectCode',
			       render: function(data, type, row) {
			           return `
			               <a href="/tasks/${row.id}"
			                  class="project-link">
			                   ${data}
			               </a>
			           `;
			       }
			   }, 

            { data: 'projectName' },
            { data: 'clientName' },
            { data: 'startDate' },
            { data: 'endDate' },

			{
			    data: null,
			    orderable: false,
			    className: "text-center",
				visible: userRole !== "WORKER",
			    render: function (data, type, row) {

			    
			        if (userRole === "WORKER") {
			            return "";
			        }

			       
			        if (userRole === "STAFF") {
			            return `
			                <button class="btn-edit"
			                        onclick="editProject(${row.id})">
			                    Edit
			                </button>

			   
			            `;
			        }

			       
			        if (userRole === "ADMIN") {
			            return `
			              

			                <button class="btn-edit"
			                        onclick="editProject(${row.id})">
			                    Edit
			                </button>

			                <button class="btn-delete"
			                        onclick="deleteProject(${row.id})">
			                    Delete
			                </button>
			            `;
			        }

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
		        title: 'Project_Report'
		    },
		    {
		        extend: 'pdfHtml5',
		        title: 'Project_Report'
		    },
		    {
		        extend: 'csvHtml5',
		        title: 'Project_Report'
		    }
		]
    });

});

function openAdd(){

    document
        .getElementById(
            'popupTitle')
        .innerHTML=
        'Add Project';

    document
        .getElementById(
            'projectId')
        .value='';

    document
        .getElementById(
            'projectName')
        .value='';

    document
        .getElementById(
            'clientName')
        .value='';

    document
        .getElementById(
            'startDate')
        .value='';

    document
        .getElementById(
            'endDate')
        .value='';

    document
        .getElementById(
            'projectModal')
        .style.display='block';
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

function editProject(id){

    fetch('/api/projects/'+id)

    .then(r=>r.json())

    .then(project=>{

        document
            .getElementById(
                'popupTitle')
            .innerHTML=
            'Edit Project';

        document
            .getElementById(
                'projectId')
            .value=
            project.id;

        document
            .getElementById(
                'projectName')
            .value=
            project.projectName;

        document
            .getElementById(
                'clientName')
            .value=
            project.clientName;

        document
            .getElementById(
                'startDate')
            .value=
            project.startDate;

        document
            .getElementById(
                'endDate')
            .value=
            project.endDate;
			
			document.getElementById('projectNameError').innerHTML = '';
			document.getElementById('clientNameError').innerHTML = '';
			document.getElementById('startDateError').innerHTML = '';
			document.getElementById('endDateError').innerHTML = '';

        document
            .getElementById(
                'projectModal')
            .style.display=
            'block';
    });
}

function saveProject(){
	
	if(!validateProject()){
	      return;
	  }



    let id=
        document
            .getElementById(
                'projectId')
            .value;

    let project={

        projectName:
        document
            .getElementById(
                'projectName')
            .value,

        clientName:
        document
            .getElementById(
                'clientName')
            .value,

        startDate:
        document
            .getElementById(
                'startDate')
            .value,

        endDate:
        document
            .getElementById(
                'endDate')
            .value
    };

    let url='/api/projects';

    let method='POST';

    if(id){

        url='/api/projects/'+id;

        method='PUT';
    }

    fetch(url,{

        method:method,

        headers:{
            'Content-Type':
            'application/json'
        },

        body:
        JSON.stringify(project)

    })

    .then(r=>r.text())

    .then(msg=>{

        

        closeModal();

		projectTable.ajax.reload(null, false);
		
		showMessage(
		        "Success",
		        msg
		    );
    });
}


let deleteProjectId = null;

function deleteProject(id){

    deleteProjectId = id;

    document
        .getElementById('deleteModal')
        .style.display = 'block';
}

function closeDeleteModal(){

    deleteProjectId = null;

    document
        .getElementById('deleteModal')
        .style.display = 'none';
}

function confirmDelete(){

    fetch('/api/projects/' + deleteProjectId,{
        method:'DELETE'
    })

    .then(r => r.text())

    .then(msg => {

        closeDeleteModal();

        showMessage(
            'Success',
            msg
        );

        projectTable.ajax.reload(null,false);
    });
}

function goBack(){
	window.location.href = '/home';
}

function closeModal(){

    document
        .getElementById(
            'projectModal')
        .style.display=
        'none';
}

function logout(){

    window.location.href=
        '/logout';
}

function validateProject(){

    let valid=true;

    document
        .querySelectorAll('.error')
        .forEach(e=>e.innerHTML='');

    document
        .querySelectorAll('input')
        .forEach(e=>
            e.classList
             .remove('invalid'));

    let projectName =
        document
            .getElementById(
                'projectName');

    let clientName =
        document
            .getElementById(
                'clientName');

    let startDate =
        document
            .getElementById(
                'startDate');

    let endDate =
        document
            .getElementById(
                'endDate');

    if(projectName.value.trim()===''){

        projectName.classList
            .add('invalid');

        document
            .getElementById(
                'projectNameError')
            .innerHTML=
            'Project Name is required';

        valid=false;
    }

    if(clientName.value.trim()===''){

        clientName.classList
            .add('invalid');

        document
            .getElementById(
                'clientNameError')
            .innerHTML=
            'Client Name is required';

        valid=false;
    }

    if(startDate.value===''){

        startDate.classList
            .add('invalid');

        document
            .getElementById(
                'startDateError')
            .innerHTML=
            'Start Date is required';

        valid=false;
    }

    if(endDate.value===''){

        endDate.classList
            .add('invalid');

        document
            .getElementById(
                'endDateError')
            .innerHTML=
            'End Date is required';

        valid=false;
    }

    if(startDate.value!=''
        && endDate.value!=''
        && new Date(startDate.value)
            > new Date(endDate.value)){

        endDate.classList
            .add('invalid');

        document
            .getElementById(
                'endDateError')
            .innerHTML=
            'End Date must be after Start Date';

        valid=false;
    }

    return valid;
}