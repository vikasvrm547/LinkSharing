<%@ page import="com.tothenew.Topic" contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>User Profile</title>
    <asset:stylesheet src="UserProfile.css"/>
    <asset:stylesheet src="BrowseFile.css"/>
    <meta name="layout" content="main"/>
</head>
<body>

<ls:trendingTopics tendingTopics="${trendingTopics}"/>

<!-- inbox start-->
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
</div>


</body>
</html>