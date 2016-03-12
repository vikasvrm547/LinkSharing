<div id="send-invitation-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Send invitation</h4>
            </div>

            <div class="modal-body">

                <g:form controller="topic" action="invite" class="form-horizontal" role="form">
                    <div class="form-group">
                        <div class="col-sm-6 "><label class="control-label">Email*:</label></div>

                        <div class="col-sm-6">
                            <input type="email" name="email" class="form-control" placeholder="Email" id="email" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-6 "><label class="control-label">Topics*:</label>
                        </div>

                        <div class="col-sm-6 modal-topic-drop-down">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary">Share</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>

                    </div>
                </g:form>

            </div>


        </div>
    </div>
</div>
