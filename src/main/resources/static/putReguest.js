
    function ajaxPut(){
    	// PREPARE FORM DATA
    	var formData = {
    			id: $("#updateId").val(),
    			interval_heur : $("#updHeure").val(),


    	}

    	var id = $("#updateId").val();

    	console.log("mise à jour: " + formData);

    	// DO PUT
    	$.ajax({
			type : "PUT",
			contentType : "application/json",
			url : window.location +"/api/sante/ussd/updateHeure/{id}" + id,
			data : JSON.stringify(formData),
			dataType : 'json',

			// SUCCESS response
			success : function(heure) {

				$("#putResultDiv").html("" +
											"mise à jour success! " +
											"--> {id: " + heure.id +
												"heure: " + heure.interval_heur +
											"}");


				$("#updId").val(heure.id);
				$("#updateFormName").val(heure.interval_heur);

			},

			// ERROR response
			error : function(e) {
				alert("Error!")
				console.log("ERROR: ", e);
			}
		});
    }