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
<title>更新内容確認画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>これでよろしいですか？</p>

	<p class="error">
		<c:if test="${not empty updateConfirmErrMsg }">${updateConfirmErrMsg }</c:if>
	</p>

	<form:form action="update" modelAttribute="updateConfirm" method="post">
		<fieldset class="label-130">
			<div>
				<label>ID</label> <input type="text" name="loginId"
					value="${updatedUser.loginId }" readonly>
			</div>
			<div>
				<label>名前</label> <input type="text" name="userName"
					value="${updatedUser.userName }" readonly>
			</div>
			<div>
				<label>TEL</label> <input type="text" name="tel"
					value="${updatedUser.telephone }" readonly>
			</div>
			<div>
				<label>権限</label>
				<c:choose>
					<c:when test="${updatedUser.roleId eq 1 }">
						<input value="管理者" readonly />
					</c:when>
					<c:otherwise>
						<input value="一般" readonly />
					</c:otherwise>
				</c:choose>
			</div>
			<div>
				<label>PASS（再入力）</label> <form:password path="rePass" />
				<form:errors class="error" path="rePass" />
			</div>
		</fieldset>
		<div>
			<button type="submit">更新</button>
			<button type="submit"
				onclick="location.href='updateInput'; return false; method='post'">戻る</button>
		</div>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
