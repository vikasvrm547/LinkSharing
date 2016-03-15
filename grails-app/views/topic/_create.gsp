<div id="create-topic-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Create topic</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal modal-body-form">
                    <div class="form-group">

                        <div class="col-sm-6 "><label class="control-label">Name *:</label></div>

                        <div class="col-sm-6">
                            <input type="text" class="form-control topicName" placeholder="Topic Name" id="topicName"
                                   name="topicName" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-6 "><label class="control-label">Visibility *:</label>
                        </div>

                        <div class="col-sm-6">
                            <g:render template="/share/visibilityDropDown"/>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button onclick="ajaxifiedTopicCreate()" class="btn btn-primary" data-dismiss="modal" >Create</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                </g:form>

            </div>

        </div>
    </div>
</div>
