<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script>
	$(document).ready(function(){
		$("#insert").click(function(){
			var jval = $("#job").val();
			$.ajax({
				type : "post",
				url : '/api/insert',
				dataType : 'text',
				data : { job : jval },
				success : function(data){
					var obj = JSON.parse(data);
					if (obj.result == "OK") {
						var li = '<li><input type="radio" name="job" value="'+obj.id+'"/>'+jval+'</li>, false';
						$("#list").append(li);
						$("#job").val("");
					} else {
						
					}
				}
			});
		});
		
		$("#update").click(function(){
			var selValue = $("input[name=job]:checked").val();
			alert(selValue);
			$.ajax({
				type : "post",
				url : '/api/update',
				dataType : 'text',
				data : { id : selValue },
				success : function(data){
					var obj = JSON.parse(data);
					if (obj.result == "OK") {
						alert("Updated"); 
						var divid= "#"+selValue;
						$(divid).text("true");
						
					} else {
						
					}
				}
			});
		});
		
	});
</script>
</head>
<body>
	<h1>Info</h1>

	<input type="text" id="job" />
	<button id="insert">ADD</button>
	
	<button id="update">UPDATE</button>
	<ul id="list">
		<c:forEach var="row" items="${rs}">
			<li><input type="radio" name="job" value="${row.id}" />${row.job}, <div id='${row.id}'>${row.done}</div></li>
		</c:forEach>
	</ul>
</body>
</html>