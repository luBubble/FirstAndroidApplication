<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  
    String path = request.getContextPath();  
    String basePath = request.getScheme() + "://"  
            + request.getServerName() + ":" + request.getServerPort()  
            + path + "/";  
%>  
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>编辑用户</title>

<script type="text/javascript">
    function updateUser() {
        var form = document.forms[0];
        form.action = "<%=basePath %>user/updateUser.do";
        form.method = "post";
        form.submit();
    }
</script>

</head>
<body>
    <h1>添加用户</h1>
    <form action="" name="userForm">
        <input type="hidden" name="userId" value="${user.userId }" /> 姓名：<input
            type="text" name="userName" value="${user.userName }" /> 密码：<input
            type="text" name="userPassword" value="${user.userPassword }" /> <input type="button"
            value="编辑" onclick="updateUser()" />
    </form>
</body>

</html>