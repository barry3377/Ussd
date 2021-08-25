$(document).ready(
		function() {

			// GET REQUEST
			$("#getAllUser").click(function(event) {
				event.preventDefault();
				ajaxGet();
			});

			// DO GET
			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : window.location + "/api/sante/ussd/getAllHeur",
					success : function(result) {
						if (result.status == "success") {
							$('#getResultDiv ul').empty();
							var custList = "";
							$.each(result.object, function(i, heure) {
								var heure = "-  Id = "
								+ heure.id
										+ ", intervalle d'heure = "
										+ heure.interval_heur

								$('#customerTable tbody').append(heure)
							});
							console.log("Success: ", result);
						} else {
							$("#getResultDiv").html("<strong>Error</strong>");
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						$("#getResultDiv").html("<strong>Error</strong>");
						console.log("ERROR: ", e);
					}
				});
			}
		})
