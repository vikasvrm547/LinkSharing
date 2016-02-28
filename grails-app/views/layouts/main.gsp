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

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.css">

    <g:layoutHead/>
</head>

<body>
<div class="container-fuid" >
    <nav class="nav navbar-default navbar-fixed-top" style="margin-left: 2%;margin-right: 2%" >
        <div class="container-fluid" >

            <div class="navbar-header">
                <a class="navbar-brand" href="#" style="color:white">Link sharing</a>
            </div>

            <form class="form-inline  pull-right" role="form">
                <div class="form-group ">
                    <div id="custom-search-input" style="text-align:right">
                        <div class="input-group col-md-12">
                            <input type="text" class="form-control input-lg" placeholder="Search.."/>
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-lg" type="button">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                                <button class="btn btn-info btn-lg" type="button">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                </div>

                <g:if test="${session.user}">
                    <g:if test="${!controllerName.equals("topic")}">
                        <i class="form-group fa fa-comment nav_icon "></i>
                        <i class="form-group fa fa-envelope-o nav_icon "></i>
                    </g:if>
                    <span class="form-group glyphicon glyphicon-link  nav_icon "></span>
                    <i class="form-group fa fa-file-o nav_icon" data-toggle="modal" data-target="#gridSystemModal"></i>

                    <div class="dropdown form-group">

                        <select class="btn btn-primary dropdown-toggle nav-dropdown" type="button"
                                name="headerUserDropDown" id="headerUserDropDown" onchange="location = this.options[this.selectedIndex].value;">
                            <option value="">----${session.user}----</option>
                            <option value="#">Profile</option>
                            <g:if test="${controllerName.equals('user')}">
                                <option value="#">Users</option>
                                <option value="#">Topic</option>
                                <option value="#">Post</option>
                            </g:if>
                            <option value="<g:createLink controller='login' action='logout'/> ">Logout</option>
                        </select>
                    </div>
                </g:if>
            </form>
        </div>
    </nav>
</div>

<div class="container-fluid body-top-container">
    <g:if test="${flash.error}">
        ${flash.error}
    </g:if>
    <g:layoutBody/>

</div>
</body>
</html>
