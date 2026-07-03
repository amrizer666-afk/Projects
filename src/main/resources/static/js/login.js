function login() {debugger
	
	$("#loadingOverlay").addClass("show");

	
	 console.log("hitted");

    var username = $("#username").val();
    var password = $("#password").val();

    if (username === "" || password === "") {
        $("#message").css("color", "red").text("Enter username and password");
        return;
    }

    var requestData = {
        username: username,
        password: password
    };

    $.ajax({
        url: "/api/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(requestData),

        success: function(res) {
			

			
            if (res.status === "success") {
                $("#message").css("color", "green").text("Login successful");
				$("#loadingOverlay").removeClass("show");
                // redirect to JSP
                setTimeout(function () {
                    window.location.href = "/home";
                }, 800);

            } else {
				$("#loadingOverlay").removeClass("show");
                $("#message").css("color", "red").text("Invalid username/password");
            }
        },

        error: function() {
			
			$("#loadingOverlay").removeClass("show");
            $("#message").css("color", "red").text("Server error");
        }
    });
}