<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Profile</title>
    <asset:stylesheet src="UserProfile.css"/>
    <asset:stylesheet src="BrowseFile.css"/>
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
            Subscriptions <a href="#" class="inline subscription-header-top-left-anchor"
                             style="float:right">view all</a>
        </div>

        <div class="panel-body subscription-panel-boby">%{--change boby to body--}%
       %{-- <g:render template="/topic/show"/>--}%

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
               %{-- <div class="row inbox-post">
                    <div class="list-group  col-xs-3">

                        <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                    </div>

                    <div class="col-xs-9">
                        <div class="row">
                            <h5 class="col-xs-6">${post?.getNameOfUser()}<small>@${post?.userUserName} 5min</small></h5>
                            <a class="col-xs-6 input-top-right-anchor">${post?.topicName}</a>
                        </div>

                        <div class="row">
                            <p class="col-xs-12">${post?.description}</p>
                        </div>

                        <div class="row">
                            <div class="col-xs-3">
                                <a href="#">
                                    <div class="fa fa-facebook-official"></div>
                                </a>
                                <a href="#">
                                    <div class="fa fa-twitter inline"></div>
                                </a>
                                <a href="#">
                                    <div class="fa fa-google-plus inline"></div>
                                </a>
                            </div>

                            <div class="col-xs-9">

                                <ls:readLink resource="${post?.resourceID}" isRead="${post?.isRead}"
                                             user="${currentUser ?: null}"/>
                                <g:link controller="resource" action="show" methods="post"
                                        params='[resourceId: "${post?.resourceID}", userId: "${post?.userId}"]'>View post</g:link>
                                <ls:resourceTypeLink resourceId="${post?.resourceID}" url="${post?.url}"
                                                     filePath="${post?.filePath}"/>
                            </div>
                        </div>
                    </div>
                </div>--}%
                <g:render template="/resource/show" model="[post:post]"/>
            </g:each>
        </div>
    </div>
</div>


<g:render template="/topic/create"/>
<g:render template="/topic/email"/>
<g:render template="/linkResource/create"/>
<g:render template="/documentResource/create"/>
<asset:javascript src="UserProfile.js"/>
<asset:javascript src="BrowseFile.js"/>
</body>
</html>