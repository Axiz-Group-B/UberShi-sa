<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty user }">
	<c:redirect url="/index" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>メニュー画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		<c:if test="${not empty user }">${user.userName }</c:if>
		さん、 こんにちは
	</p>

	<p>
		<a href="select">検索</a>
	</p>
	<c:if test="${not empty user && user.roleId eq 1 }">
		<p>
			<a href="insert">登録</a>
		</p>
		<p>
			<a href="update">更新</a>
		</p>
		<p>
			<a href="delete">削除</a>
		</p>
	</c:if>

	<form action="logout" method="post">
		<button type="submit">ログアウト</button>
	</form>
</body>
</html>
