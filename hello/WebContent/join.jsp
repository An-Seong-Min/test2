<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script>
	$(document).ready(function() {
		$("#btn").click(function() {
			var query = {
				id : $("#inpu").val()
			};

			$.ajax({
				type : "post",
				url : '/api/hello',
				dataType : 'text',
				data : query,
				success : function(data) {
					var obj = JSON.parse(data);
					if (obj.result == "OK") {
						$("#message").removeClass().addClass("normal");
						$("#message").text("사용 가능");
					} else {
						$("#message").removeClass().addClass("warning");
						$("#message").text("사용 불가능");
					}
				}

			});
		});
	});
</script>
<style>
.invisible {
	visibility: hidden;
}

.normal {
	visibility: visible;
	color: blue;
}

.warning {
	visibility: visible;
	color: red;
}
</style>
</head>
<body>
	<h1>Join</h1>
	<input type="text" id="inpu"></input>
	<input type="button" id="btn" value="확인">
	<p id="message" class="invisible"></p>
</body>
</html>