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
<title>ログイン画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

	<p class="error"><c:if test="${not empty loginErrMsg }">${loginErrMsg }</c:if></p>

	<form:form action="menu" modelAttribute="login" method="post" >
		<div>
			<label>ID</label>
			<form:input path="loginId" />
			<form:errors class="error" path="loginId" />
		</div>
		<div>
			<label>PASS</label>
			<form:password path="password" />
			<form:errors class="error" path="password" />
		</div>
		<form:button type="submit">ログイン</form:button>
	</form:form>
	<div>
		<a href="index">TOP画面に戻る</a>
	</div>
</body>
</html>
