
$(document)
		.ready(
				function() {

					// SUBMIT FORM
					$("#customerForm").submit(function(event) {

						event.preventDefault();
						ajaxPost();
					});

					function ajaxPost() {

						// PREPARE FORM DATA
						var formData = {
							interval_heur : $("input[name=interval_heur]").val(),

						}

						// DO POST
						$
								.ajax({
									type : "POST",
									contentType : "application/json",
									url : window.location + "/api/sante/ussd/addheur",
									data : JSON.stringify(formData),
									dataType : 'json',
									success : function(result) {
										if (result.status == "success") {
											$("#postResultDiv")
													.html(
															"<p style='background-color:#7FA7B0; color:white; padding:20px 20px 20px 20px'>"
																	+ "Post Successfully! <br>"
																	+ "---> Congrats !!"
																	+ result.object.intterval_heur
																	+ "</p>");
										} else {
											$("#postResultDiv").html(
													"<strong>Error</strong>");
										}
										console.log(result);
									},
									error : function(e) {
										alert("Error!")
										console.log("ERROR: ", e);
									}
								});

					}

				})

