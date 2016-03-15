<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><g:layoutTitle default="Link Sharing"/></title>
    <asset:stylesheet src="main.css"/>
    <asset:javascript src="main.js"/>
    <asset:javascript src="jquery.validate.js"/>
    <asset:stylesheet src="BrowseFile.css"/>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.css"/>

    <g:layoutHead/>
</head>

<body>
<div class="container-fluid">
    <nav class="nav navbar-default navbar-fixed-top" style="margin-left: 2%;margin-right: 2%">
        <div class="container-fluid">

            <div class="navbar-header">
                <a class="navbar-brand" href="${g.createLink(controller: 'login', action: 'index')}"
                   style="color:white">
                    Link sharing
                </a>
            </div>

            <g:form class="form-inline  pull-right" name="global-search-form" controller="resource" action="search"
                    onsubmit="return globalSearchBoxValidation()" method="get" role="form">
                <div class="form-group ">
                    <input type="hidden" name="visibility" value="PUBLIC"/>

                    <div id="custom-search-input" style="text-align:right">
                        <div class="input-group col-md-12">
                            <input type="text" class="form-control input-lg " name="q"
                                   id="global-search-textbox" placeholder="Search.."/>
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-lg" id="global-search-button" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                                <button class="btn btn-info btn-lg" id="global-search-clear-button" type="button">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>

                <g:if test="${session.user}">

                    <g:if test="${controllerName.equals("user")}">
                     <ls:canSeeCreateTopicHeaderIcon >
                         <i class="form-group fa fa-comment nav_icon" title="Create Topic"></i>
                     </ls:canSeeCreateTopicHeaderIcon>
                     <ls:canSeeInviteHeaderIcon>
                         <i class="form-group fa fa-envelope-o nav_icon " title="Send Invitation"></i>
                     </ls:canSeeInviteHeaderIcon>
                    </g:if>
                    <span class="form-group glyphicon glyphicon-link  nav_icon " title="Create Link Resource"></span>
                    <i class="form-group fa fa-file-o nav_icon" title="Create Document Resource" data-toggle="modal" data-target="#gridSystemModal"></i>

                    <div class="dropdown form-group">

                        <select class="btn btn-primary dropdown-toggle nav-dropdown" type="button"
                                id="headerUserDropDown"
                                onchange="location = this.options[this.selectedIndex].value;">
                            <option value="" disabled selected>${session.user}</option>
                            <option value="<g:createLink controller='user' action='profile'
                                                         params="[id: session.user?.id]"/>">
                                Profile
                            </option>
                            <option value="<g:createLink controller='user' action='edit'/>">
                                Edit
                            </option>
                            <g:if test="${session.user?.admin}">
                                <option value="<g:createLink controller='user' action='list'/>">Users</option>
                            </g:if>
                            <option value="<g:createLink controller='login' action='logout'/> ">Logout</option>
                        </select>
                    </div>
                </g:if>
            </g:form>
        </div>
    </nav>
</div>

<div class="container-fluid body-top-container">
    <g:if test="${flash.error}">
        <div class="alert alert-danger">
            ${flash.error}
        </div>
    </g:if>
    <g:elseif test="${flash.message}">
        <div class=" alert alert-success">
            ${flash.message}
        </div>
    </g:elseif>
    <div class="messageAlert">
    </div>

    <g:layoutBody/>

    <g:render template="/topic/create"/>
    <g:render template="/topic/email"/>
    <g:render template="/linkResource/create"/>
    <g:render template="/documentResource/create"/>
    <g:render template="/resource/edit"/>
    <asset:javascript src="validation.js"/>
    <asset:javascript src="BrowseFile.js"/>
    <asset:javascript src="application.js"/>
</div>
</body>
</html>
