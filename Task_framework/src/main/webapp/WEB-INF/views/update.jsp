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
<title>更新画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    更新するIDを入力してください<br> <span class="required"></span>は必須です
  </p>

  <p class="error"><c:if test="${not empty updateErrMsg }">${updateErrMsg }</c:if> </p>

  <form:form action="updateInput" modelAttribute="update" method="post">
    <fieldset>
      <div>
        <label class="required">ID</label>
        <form:input path="loginId" />
        <form:errors class="error" path="loginId" />
      </div>
    </fieldset>
    <form:button>確認</form:button>
  </form:form>
  <div>
    <a href="menu">メニューに戻る</a>
  </div>
</body>
</html>
