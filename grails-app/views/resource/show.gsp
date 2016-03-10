<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Post</title>
    <asset:stylesheet src="resource.css"/>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="col-xs-7 page-container-inner-left-div">

    <!-- post content start-->
    <div class="panel panel-primary">

        <div class="panel-body post-panel-body">

            <div class="row">
                <div class="list-group  col-xs-3">
                    <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>
                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <h5 class="col-xs-6">${postVO?.getNameOfUser()}<small>@${postVO?.userUserName} 5min</small></h5>
                        <a class="col-xs-6 input-top-right-anchor">
                            <g:link name="topicName" controller="topic" action="show"
                                    class="col-xs-6 input-top-right-anchor"
                                    params='[topicId: "${postVO?.topicId}"]'>
                                ${postVO?.topicName}
                            </g:link>
                        </a>
                    </div>

                    <div class="row">
                        <p class="col-xs-12">hello vikassss</p>
                        <g:if test="${session.user}">
                            <g:form controller="resourceRating" action="save" params="[userId    : currentUser?.id,
                                                                                       resourceId: postVO?.resourceID]">
                                <g:select name="score" from="${1..5}"
                                          value="${currentUser?.getScore(postVO?.resourceID) ?: 1}"/>
                                <g:submitButton name="submit"/>
                            </g:form>
                        </g:if>

                    </div>
                </div>

                <div class="row post-content">
                    <p class="col-xs-12">${postVO?.description}</p>
                </div>

                <div class="row">
                    <div class="col-xs-5">
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

                    <div class="col-xs-7 post-footer-links">
                        <g:if test="${session.user}">
                            <a>Edit</a>
                        </g:if>
                        <ls:resourceTypeLink resourceId="${postVO?.resourceID}" url="${postVO?.url}"
                                             filePath="${postVO?.filePath}"/>
                        <ls:canDeleteResource currentUser="${currentUser}" resourceId="${postVO.resourceID}"
                                              class="inline post-panel-anchor">Delete</ls:canDeleteResource>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- post content in -->
</div>

<div class="col-xs-5 page-container-inner-right-div">
    <ls:trendingTopics tendingTopics="${tendingTopics}"/>
</div>
</body>
</html>