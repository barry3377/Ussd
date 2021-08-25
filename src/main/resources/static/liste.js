$(document).ready(function() {

	// Do GET all Customers from Back-End with JQUERY AJAX
	$(function () {
		$.ajax({
			type : "GET",
			url :  window.location + "/api/sante/ussd/getAllHeur",
			success: function(result){
				$.each(result.object, function(i, heure) {
            								var heure = "-  Id = "
            								+ heure.id
            										+ ", intervalle d'heure = "
            										+ heure.interval_heur
					$('#customerTable tbody').append(heure);

		        });

				$( "#customerTable tbody tr:odd" ).addClass("info");
				$( "#customerTable tbody tr:even" ).addClass("success");
			},
			error : function(e) {
				alert("ERROR: ", e);
				console.log("ERROR: ", e);
			}
		});
	});