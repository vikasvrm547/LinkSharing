<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Register</h3>
    </div>

    <div class="panel-body">
        <g:form controller="user" action="register" class="form-horizontal" role="form">
            <div class="form-group">
                <div class="col-sm-4"><label class="register_label control-label">First name*:</label>
                </div>

                <div class="col-sm-8">
                    <input type="text" class="form-control" placeholder="First name" name="firstName"
                           value="${registerCO?.firstName}" id="firstName">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Last name*:</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control" placeholder="Last name" name="lastName"
                           value="${registerCO?.lastName}" id="lastName">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Email*:</label></div>

                <div class="col-sm-8">
                    <input type="email" class="form-control" placeholder="Email" name="email"
                           value="${registerCO?.email}" id="email">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">User name*:</label></div>

                <div class="col-sm-8">
                    <input type="text" class="form-control" placeholder="User name" name="userName"
                           value="${registerCO?.userName}" id="userName">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Password*:</label></div>

                <div class="col-sm-8">
                    <input type="password" class="form-control" placeholder="Password" name="password"
                           value="${registerCO?.password}" id="password">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Confirm_Password*:</label></div>

                <div class="col-sm-8">
                    <input type="password" class="form-control" placeholder="confirm Password" name="confirmPassword"
                           value="${registerCO?.confirmPassword}" id="confirmPassword">
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-4"><label class="control-label">Photo</label></div>

                <div class="col-lg-8 col-sm-8 col-8">
                    <div class="input-group">
                        <span class="input-group-btn">
                            <span class="btn btn-primary btn-file">
                                Browse&hellip; <input type="file" multiple>
                            </span>
                        </span>
                        <input type="text" class="form-control" readonly>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <div class=" col-sm-6 pull-right">
                    <g:submitButton name="Submit" class="form-control btn btn-primary col-sm-6">Submit</g:submitButton>
                </div>
            </div>
        </g:form>

    </div>
</div>