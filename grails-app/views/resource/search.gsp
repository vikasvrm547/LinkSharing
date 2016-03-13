<%@ page import="com.tothenew.Topic" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Search</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="search.css"/>
</head>
<body>
<div class="col-xs-5 page-container-inner-left-div">
    <ls:trendingTopics tendingTopics="${trendingTopics}"/>
</div>

<div class="col-xs-7 page-container-inner-right-div">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Search result</h3>
        </div>

        <div class="panel-body search-result-panel-body">
            <g:if test="${postVOList.size() == 0}">
                <h1 >No resource found</h1>
            </g:if>
            <g:each in="${postVOList}" var="post">
                <g:render template="/resource/show" model="[post: post]"/>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>