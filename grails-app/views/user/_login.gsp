<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Login</h3>
    </div>

    <div class="panel-body">

        <g:form class="form-horizontal" role="form" url="[controller: 'login', action: 'loginHandler']">
            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Email/Username*:</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control" id="loginEmailOrUsername" name="loginEmailOrUsername"
                           placeholder="Email/Username">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Password*:</label></div>

                <div class="col-sm-8">
                    <input type="password" class="form-control" id="loginPassword" name="loginPassword"
                           placeholder="Password">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">

                    <span id="forgot-password" class="col-sm-6">Forgot Password</span>
                    <g:submitButton name="Submit" class="btn btn-primary col-sm-6"></g:submitButton>
                </div>
            </div>
        </g:form>

    </div>
</div>