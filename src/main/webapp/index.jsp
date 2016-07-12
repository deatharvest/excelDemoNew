<%@page contentType="text/html; charset=utf-8" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <script type="text/javascript" src="/static-resources/jquery-1.12.1.js"></script>
    <script type="text/javascript">
        function downloadtest(){
            var url = "<%=request.getContextPath()%>/app/download" + "/" + id;
            $("#pluginurl").attr("href",url);
        }
    </script>
</head>
<body>

<h2>Hello World!</h2>
<a href="/hi/excel.do" onclick="JavaScript:downloadtest()"  id="pluginurl"  style="color: #83AFE2;text-decoration:underline;">下载</a>
</body>
</html>
