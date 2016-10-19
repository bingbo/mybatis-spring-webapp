<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<link rel="stylesheet" href="<%=basePath %>public/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath %>public/css/site.css">