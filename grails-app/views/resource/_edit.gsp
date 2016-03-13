<div id="resource-edit-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Edit description</h4>
            </div>

            <div class="modal-body">
                <g:form class="form-horizontal" role="form"
                        url="[controller: 'resource', action: 'update']">
                    <input type="hidden" name="resourceId" value="" id="resource-edit-resource-id-hidden-field"/>
                    <div class="form-group">
                        <div class="col-sm-4 "><label class="control-label">Description:</label></div>

                        <div class="col-sm-8">
                            <textarea class="form-control" rows="5" placeholder="Comment" id="edit-description-textarea"
                                          name="description" required></textarea>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <g:submitButton name="Update" class="btn btn-primary"></g:submitButton>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                    </div>
                </g:form>

            </div>

        </div>
    </div>
</div>
