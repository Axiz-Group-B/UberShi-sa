<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:if test="${empty user }">
	<c:redirect url="/index" />
</c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>検索画面</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <p>
    検索したいデータ情報を入力してください<br> ※全て空白の場合は全検索を行います
  </p>

  <p class="error"><c:if test="${not empty selectErrMsg }">${selectErrMsg }</c:if> </p>

  <form action="list">
    <fieldset>
      <div>
        <label>名前</label>
        <input type="text" name="userName">
      </div>
      <div>
        <label>TEL</label>
        <input type="text" name="telephone">
      </div>
    </fieldset>
    <button type="submit">検索</button>
  </form>
  <div>
    <a href="menu">メニューに戻る</a>
  </div>
</body>
</html>
