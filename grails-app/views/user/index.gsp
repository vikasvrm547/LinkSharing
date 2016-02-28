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
            <div class="list-group  col-xs-3">

                <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>
            </div>

            <div class="col-xs-9">
                <div class="row">
                    <h4 class="col-xs-12">${session.user.name} <br/><small>@${session.user.firstName} </small></h4>
                </div>
                <br/>

                <div class="row">
                    <div class="col-xs-6">
                        <small class="col-xs-12">Subscriptions</small>
                        <small class="col-xs-12">50 </small>
                    </div>

                    <div class="col-xs-6">
                        <small class="col-xs-12">Topics</small>
                        <small class="col-xs-12">10 </small>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <!-- end panel profile start -->


    <!-- panel subscription start -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            Subscriptions <a href="#" class="inline subscription-header-top-left-anchor"
                             style="float:right">view all</a>
        </div>

        <div class="panel-body subscription-panel-boby">
            <div>
                <div class="row">
                    <div class="list-group  col-xs-3">

                        <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                    </div>

                    <div class="col-xs-9">
                        <div class="row">
                            <a href="#">Grails</a>
                        </div>
                        <br/><br/>

                        <div class="row">
                            <div class="col-xs-4">
                                <small class="col-xs-12">@vikas</small>
                                <small class="col-xs-12"><a>Unsubscribe</a></small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Subscriptions</small>
                                <small class="col-xs-12">50</small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Topics</small>
                                <small class="col-xs-12">10</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="dropdown col-sm-4">
                        <button class="btn btn-primary dropdown-toggle panels-dropdown" type="button"
                                data-toggle="dropdown">serious
                            <span class="label label-primary"><span class="caret"></span></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">HTML</a></li>
                            <li><a href="#">CSS</a></li>
                            <li><a href="#">JavaScript</a></li>
                        </ul>
                    </div>

                    <div class="dropdown col-sm-4">
                        <button class="btn btn-primary dropdown-toggle panels-dropdown" type="button"
                                data-toggle="dropdown">Private
                            <span class="label label-primary"><span class="caret"></span></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">Public</a></li>
                        </ul>
                    </div>
                    <i class="fa fa-envelope-o nav_icon "></i>
                    <span class="glyphicon glyphicon-edit nav_icon "></span>
                    <span class="glyphicon glyphicon-trash nav_icon "></span>
                </div>
            </div>

            <div>
            </div>
            <hr/>

            <div>
                <div class="row">
                    <div class="list-group  col-xs-3">

                        <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                    </div>

                    <div class="col-xs-9">
                        <div class="row">
                            <a href="#">Grails</a>
                        </div>
                        <br/><br/>

                        <div class="row">
                            <div class="col-xs-4">
                                <small class="col-xs-12">@vikas</small>
                                <small class="col-xs-12"><a>Unsubscribe</a></small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Subscriptions</small>
                                <small class="col-xs-12">50</small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Topics</small>
                                <small class="col-xs-12">10</small>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row " style="text-align:right">
                    <div class="dropdown col-sm-10">
                        <button class="btn btn-primary dropdown-toggle panels-dropdown" type="button"
                                data-toggle="dropdown">serious
                            <span class="label label-primary"><span class="caret"></span></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a href="#">HTML</a></li>
                            <li><a href="#">CSS</a></li>
                            <li><a href="#">JavaScript</a></li>
                        </ul>
                    </div>

                    <div class=" col-sm-2">
                        <i class="fa fa-envelope-o nav_icon"></i>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- end panel subscription  -->

    <g:render template="tendingTopics"/>
</div>


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


<g:render template="/topic/create"/>
<g:render template="/topic/email"/>
<g:render template="/linkResource/create"/>
<g:render template="/documentResource/create"/>
<asset:javascript src="UserProfile.js"/>
<asset:javascript src="BrowseFile.js"/>
</body>
</html>