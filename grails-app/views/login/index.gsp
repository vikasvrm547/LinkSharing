<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="Login.css"/>
    <asset:stylesheet src="BrowseFile.css"/>
</head>

<body>

<div class="col-xs-7 page-container-inner-left-div">

   <g:render template="/resource/recentPost"/>
    <g:render template="/resource/topPost"/>

</div>

<div class="col-xs-5 page-container-inner-right-div">

    <g:render template="/user/login"/>
    <g:render template="/user/register"/>

</div>

</body>
</html>