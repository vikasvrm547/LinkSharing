<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile Edit</title>
    <asset:javascript src="jquery.validate.js"/>
    <asset:stylesheet src="BrowseFile.css"/>
    <meta name="layout" content="main"/>
</head>

<body>

<div class="col-xs-5 page-container-inner-left-div">
    <div class="panel panel-primary">
        <div class="panel-body">
            <g:render template="/user/show" model="[user: currentUser ?: null]"/>
        </div>
    </div>
</div>

<div class="col-xs-7 page-container-inner-right-div">

    <!-- panel register start -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Profile</h3>
        </div>

        <div class="panel-body">
            <g:form class="form-horizontal" controller="user" action="save" enctype="multipart/form-data">
                <input type="hidden" name="userId" value="${currentUser?.id}"/>

                <div class="form-group">
                    <div class="col-sm-4"><label id="fname" class="register_label control-label">First name<small
                            class="asterisk"> *</small></label>
                    </div>

                    <div class="col-sm-8">
                        <input type="text" class="form-control" placeholder="First name" id="firstName" name="firstName"
                               value="${currentUser?.firstName}">

                        <div class="error"><g:fieldError field="firstName" bean="${user}"/></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4"><label class="control-label">Last name<small class="asterisk"> *</small>
                    </label></div>

                    <div class="col-sm-8">
                        <input type="text" class="form-control" placeholder="Last name" id="lastname" name="lastName"
                               value="${currentUser?.lastName}">

                        <div class="error"><g:fieldError field="lastName" bean="${user}"/></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4"><label class="control-label">User name<small class="asterisk"> *</small>
                    </label></div>

                    <div class="col-sm-8">
                        <input type="text" class="form-control" placeholder="User name" name="userName" id="username"
                               value="${currentUser?.userName}">

                        <div class="error"><g:fieldError field="userName" bean="${user}"/></div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4"><label class="control-label">Photo</label></div>

                    <div class="col-lg-8 col-sm-8 col-8">
                        <div class="input-group">
                            <span class="input-group-btn">
                                <span class="btn btn-primary btn-file">
                                    Browse&hellip; <input type="file" name="userPhoto">
                                </span>
                            </span>
                            <input type="text" class="form-control" readonly>
                        </div>
                        <g:hasErrors bean="${user}" field="photo">
                            <div class="error">
                                <g:message code="com.tothenew.User.photo.type"/>
                            </div>
                        </g:hasErrors>
                    </div>

                </div>

                <div class="form-group">
                    <div class=" col-sm-3 pull-right">
                        <button type="submit" class="form-control btn btn-primary col-sm-3">Update</button>
                    </div>
                </div>
            </g:form>

        </div>
    </div>
    <!-- end register-->

    <!-- panel change password start -->
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3 class="panel-title">Change password</h3>
        </div>

        <div class="panel-body">

            <g:form class="form-horizontal" name="change-password-form" id="change-password-form"
                    controller="user" action="updatePassword">
                <input type="hidden" name="userId" value="${currentUser?.id}"/>

                <div class="form-group">
                    <div class="col-sm-4"><label class="control-label">Old Password<small class="asterisk"> *</small>
                    </label></div>

                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="oldPassword" placeholder="Password">
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4"><label class="control-label">Password<small class="asterisk"> *</small></label>
                    </div>

                    <div class="col-sm-8">
                        <input type="password" class="form-control" name="password" placeholder="Password">
                        <g:hasErrors bean="${user}" field="password">
                            <div class="error">
                                <g:message code="com.tothenew.User.password.minSize"/>
                            </div>
                        </g:hasErrors>
                    </div>
                </div>

                <div class="form-group">
                    <div class=" col-sm-3 pull-right">
                        <button type="submit" class="form-control btn btn-primary col-sm-3">Update</button>
                    </div>
                </div>
            </g:form>

        </div>
    </div>

    <!-- end change password panel -->

</div>

</div>
</body>
</html>