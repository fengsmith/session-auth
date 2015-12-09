<%--
  Created by IntelliJ IDEA.
  User: shfq
  Date: 2015/12/8
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <%@ include file="/WEB-INF/pages/include/header.jsp" %>
    <script type="text/javascript">
        $(function () {
//            $("#userInfo").hide();
        });
        function getUserInfo() {
            $.ajax({
                type: "POST",
                url: ctx + "/userCenter",
                dataType: "json",
                success: function (data) {
                    if (data && data.result) {
                        $("#userInfo").val(data.message);
                        //$("#userInfo").show();
                    }
                }
            });
        }

    </script>


</head>
<body>
<div>

    <a href="preLogin">去登录</a><br>
    <a href="preUserCenter">去个人中心</a><br>
    <input type="button" id="test" onclick="getUserInfo();" value="异步获取用户信息">
    <input type="text" id="userInfo">
</div>


</body>
</html>
