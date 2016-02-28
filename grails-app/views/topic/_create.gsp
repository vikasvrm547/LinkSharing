<div id="create-topic-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Create topic</h4>
            </div>

            <div class="modal-body">

                <g:form class="form-horizontal" url="[controller: 'topic', action: 'save']">
                    <div class="form-group">

                        <div class="col-sm-6 "><label class="control-label" for="name">Name*:</label></div>

                        <div class="col-sm-6">
                            <input type="text" class="form-control" placeholder="Topic Name" id="topicName"
                                   name="topicName">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-6 "><label class="control-label" for="visibility">Visibility*:</label>
                        </div>

                        <div class="col-sm-6">
                            <g:render template="/share/visibilityDropDown"/>

                        </div>
                    </div>

                    <div class="modal-footer">
                        <g:submitButton name="Create" class="btn btn-primary"></g:submitButton>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                </g:form>

            </div>

        </div>
    </div>
</div>
