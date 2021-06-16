<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:if test="${empty user }">
	<c:redirect url="/index" />
</c:if>

<!-- <!DOCTYPE html> -->
<html>
<head>
<meta charset="UTF-8">
<title>登録画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
	<p>
		登録情報を入力してください<br> <span class="required"></span>は必須です
	</p>

	<p class="error">
		<c:if test="${not empty insertErrMsg }">${insertErrMsg }</c:if>
	</p>

	<form:form action="insertConfirm" modelAttribute="insert"
		>
		<fieldset class="label-60">
			<div>
				<label class="required">ID</label>
				<form:input path="loginId" />
				<form:errors class="error" path="loginId" />
			</div>
			<div>
				<label class="required">名前</label>
				<form:input path="userName" />
				<form:errors class="error" path="userName" value="${param.userName }" />
			</div>
			<div>
				<label class="required">TEL</label>
				<form:input path="telephone" />
				<form:errors class="error" path="telephone" value="${registerUser.telephone }" />
			</div>
			<div>
				<label class="required">権限</label>
				<form:select path="roleId">
<%-- 					<form:option items="${roleMap }" /> --%>
					<form:option value="1">管理者</form:option>
					<form:option value="2">一般</form:option>
				</form:select>
			</div>
			<div>
				<label class="required">PASS</label>
				<form:password path="password" />
				<form:errors class="error" path="password" />
			</div>
		</fieldset>
		<form:button>確認</form:button>
	</form:form>
	<div>
		<a href="menu">メニューに戻る</a>
	</div>
</body>
</html>

