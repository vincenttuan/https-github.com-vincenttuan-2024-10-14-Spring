<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Jakarta JSTL 表單標籤 -->
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>    
<!-- Spring Form 表單標籤 -->
<%@ taglib prefix="sp" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Room</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		<div class="pure-form">
			<fieldset>
				<legend>Room List</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<tr>
							<th>ID</th><th>Name</th><th>Size</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="room" items="${ rooms }">
							<tr>
								<td>${ room.roomId }</td>
								<td>${ room.roomName }</td>
								<td>${ room.roomSize }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</fieldset>
		</div>
		
	</body>
</html>