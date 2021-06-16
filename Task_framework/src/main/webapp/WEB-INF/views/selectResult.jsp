<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索結果画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<table>
		<caption>検索結果</caption>
		<thead>
			<tr>
				<th>ID</th>
				<th>名前</th>
				<th>TEL</th>
				<th>権限</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList }">
				<tr>
					<td>${user.loginId }</td>
					<td>${user.userName }</td>
					<td>${user.telephone }</td>
					<td><c:choose>
							<c:when test="${user.roleId eq 1 }">管理者</c:when>
							<c:otherwise>一般</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
