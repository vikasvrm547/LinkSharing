<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Profile</title>
    <asset:stylesheet src="UserProfile.css"/>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="col-xs-5 page-container-inner-left-div">
    <!-- panel profile start -->
    <div class="panel panel-primary">
        <div class="panel-body">
            <g:render template="/user/show" model="[user: currentUser ?: null]"/>
        </div>
    </div>
    <!-- end panel profile start -->

    <!-- panel subscription start -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            Subscriptions
        </div>

        <div class="panel-body subscription-panel-body">
            <g:each in="${subscribedTopics}">
                <g:render template="/topic/show" model='[topic: it,uniqueIdForTopicEdit:"${session.uniqueIdForTopicEdit++}"]'/>
            </g:each>
        </div>
    </div>

    <!-- end panel subscription  -->

    <ls:trendingTopics tendingTopics="${tendingTopics}"/>
</div>

<!-- inbox start-->
<div class="col-xs-7 page-container-inner-right-div">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Inbox</h3>
        </div>

        <div class="panel-body inbox-panel-body">
            <g:each in="${readingItems}" var="post">
                <g:render template="/resource/show" model="[post: post]"/>

            </g:each>
            <ul class="pagination pull-right" >
                <boots:paginate total="${totalReadingItems}" controller="user" action="show"  max="${searchCO.max}" offset="${searchCO.offset}"/>
            </ul>
        </div>
    </div>
</div>


<asset:javascript src="UserProfile.js"/>

</body>
</html>