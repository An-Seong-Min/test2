<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<body>
	<div>
		<jsp:include page="head.jsp" flush="false" />
	</div>
	<div>
		<jsp:include page="${content}" flush="false" />
	</div>
</body>
</html>