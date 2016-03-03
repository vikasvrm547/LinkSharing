<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Topic</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="Topic.css"/>
    <asset:stylesheet src="BrowseFile.css"/>
</head>

<body>

<div class="col-xs-5 page-container-inner-left-div">

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Topic:${topic}</h3>
        </div>

        <div class="panel-body topic-info-panel-body">
            <g:render template="topicInfo"/>
        </div>

    </div>


    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Users:${topic}</h3>
        </div>
        <div class="panel-body show-users-panel-body">
            <g:each in="${subscribedUsers}">
                <div class="user-info">
                    <div class="row">
                        <g:render template="/user/show" model="[user:it,subscribedUserCount:it.subscribedTopics.size()]"/>
                    </div>
                </div>
            </g:each>
        </div>
    </div>


</div>

<div class="col-xs-7 page-container-inner-right-div">

    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Indox</h3>
        </div>

        <div class="panel-body inbox-panel-body">

            <div class="row">
                <div class="list-group  col-xs-3">

                    <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <h5 class="col-xs-6">Vikas verma <small>@vikas 5min</small></h5>
                        <a class="col-xs-6 input-top-right-anchor">Grails</a>
                    </div>

                    <div class="row">
                        <p class="col-xs-12">hello vikassss</p>
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
                            <a href="#" class="inline inbox-panel-anchor">View Post</a>
                            <a href="#" class="inline inbox-panel-anchor">Mark as read</a>
                            <a href="#" class="inline inbox-panel-anchor">view full site</a>
                            <a href="#" class="inline inbox-panel-anchor">Download</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    <g:render template="/linkResource/create"/>
    <g:render template="/documentResource/create"/>
    <asset:javascript src="topic.js"/>
    <asset:javascript src="BrowseFile.js"/>
</div>
</body>
</html>