<div id="forgot-password-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Forgot Password</h4>
            </div>
            <div class="modal-body">

                <g:form controller="user" action="forgotPassword" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-6 "><label class="control-label">Email<small class="asterisk"> *</small></label></div>
                        <div class="col-sm-6">
                            <input type="email" name="email" class="form-control" placeholder="Email" id="email" required>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Send</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                </g:form>

            </div>


        </div>
    </div>
</div>
