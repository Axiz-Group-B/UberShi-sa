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
<title>更新内容入力画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		１箇所以上の項目を変更してください<br>
	</p>

	<p class="error">
		<c:if test="${not empty updateInputErrMsg }">${updateInputErrMsg }</c:if>
	</p>

	<form:form action="updateConfirm" modelAttribute="updateInput" method="post">
		<fieldset>
			<div>
				<label>ID</label>
				<form:input path="loginId"
					value="${fn:escapeXml(updateUser.loginId) }" />
				<form:errors class="error" path="loginId" />
			</div>
			<div>
				<label>名前</label>
				<form:input path="userName"
					value="${fn:escapeXml(updateUser.userName) }" />
				<form:errors class="error" path="loginId" />
			</div>
			<div>
				<label>TEL</label>
				<form:input path="telephone"
					value="${fn:escapeXml(updateUser.telephone) }" />
				<form:errors class="error" path="telephone" />
			</div>
			<div>
				<label>権限</label>
				<form:select path="roleId">
					<form:option value="1">管理者</form:option>
					<form:option value="2" selected="true">一般</form:option>
				</form:select>
			</div>
			<div>
				<label>PASS</label>
				<form:password path="password"
					value="${fn:escapeXml(updateUser.password) }" />
				<form:errors class="error" path="password" />
			</div>
		</fieldset>
		<div>
			<form:button>確認</form:button>
			<form:button type="submit"
				onclick="location.href='update'; return false;">戻る</form:button>
		</div>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>
