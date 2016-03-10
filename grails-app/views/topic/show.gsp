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
            <g:render template="show"/>
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
                        <g:render template="/user/show" model="[user: it]"/>
                    </div>
                </div>
            </g:each>
        </div>
    </div>
</div>


<!-- inbox start-->
<div class="col-xs-7 page-container-inner-right-div">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="col-sm-3">
                    Posts:"${topic}"
                </div>

                <div class="col-sm-6 col-sm-offset-3">
                    <div id="custom-search-input" style="margin: 1px;">
                        <div class="input-group col-md-12">
                            <input type="text" id="topic-post-search-textbox" class="form-control input-lg" placeholder="Search.."/>
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-lg" id="topic-post-search-button" type="button">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                                <button class="btn btn-info btn-lg" id="topic-post-search-clear-button" type="button">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                    <input type="hidden" value="${topic.id}" id="hidden-topic-id"/>
                </div>
            </div>
        </div>

        <div class="panel-body post-panel-body" id = "post-panel-body">
            <g:each in="${topicPosts}" var="post">
                <g:render template="/resource/show" model="[post: post]"/>
            </g:each>
        </div>
    </div>
</div>
</body>
</html>