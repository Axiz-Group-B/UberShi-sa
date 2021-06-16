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
<title>削除確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>これでよろしいですか？</p>

	<form action="delete" modelAttribute="delete" method="post">
		<fieldset>
			<div>
				<label>ID</label> <input type="text" name="loginId"
					value="${deleteUser.loginId }" readonly>
			</div>
			<div>
				<label>名前</label> <input type="text" name="userName"
					value="${deleteUser.userName }" readonly>
			</div>
			<div>
				<label>TEL</label> <input type="text" name="tel"
					value="${deleteUser.telephone }" readonly>
			</div>
			<div>
				<label>権限</label>
				<c:choose>
					<c:when test="${deleteUser.roleId eq 1 }">
						<input type="text" value="管理者" readonly>
					</c:when>
					<c:otherwise>
						<input type="text" value="一般" readonly>
					</c:otherwise>
				</c:choose>
			</div>
			<input type="hidden" name="userId" value="${deleteUser.userId }" />
		</fieldset>
		<div>
			<button type="submit">削除</button>
			<button type="submit" onclick="location.href='delete'; return false;">戻る</button>
		</div>
	</form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
