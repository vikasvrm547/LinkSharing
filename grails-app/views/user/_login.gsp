<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
    </div>

    <div class="panel-body">

       %{-- <g:form class="form-horizontal" role="form" url="[controller: 'login', action: 'loginHandler']">--}%
        <form class="form-horizontal" role="form" method="post" action="${resource(file: 'j_spring_security_check')}">
            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Username<small class="asterisk"> *</small>
                </label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control" id="loginEmailOrUsername" name='j_username'
                           placeholder="Username">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Password<small class="asterisk"> *</small>
                </label></div>

                <div class="col-sm-8">
                    <input type="password" class="form-control" id="loginPassword" name='j_password'
                           placeholder="Password">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                    <span id="forgot-password" class="col-sm-6" style="text-decoration: underline;color:green">
                        Forgot Password
                    </span>
                    <g:submitButton name="login" value="Login" class="btn btn-primary col-sm-6"></g:submitButton>
                </div>
            </div>
        </form>

    </div>
</div>