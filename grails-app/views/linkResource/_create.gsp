<div id="share-link-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Share link</h4>
            </div>

            <div class="modal-body">
            %{-- <g:renderErrors bean="${linkResourceCO}"/>--}%
                <g:form class="form-horizontal" role="form"
                        url="[controller: 'resource', action: 'saveLinkResource']">
                    <div class="form-group">
                        <div class="col-sm-4 "><label class="control-label">Link*:</label></div>

                        <div class="col-xs-8">
                            <input type="text" class="form-control" placeholder="Link" id="linkResourceLink"
                                   name="linkResourceLink">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 "><label class="control-label">Comment:</label></div>

                        <div class="col-sm-8">
                            <textarea class="form-control" rows="5" placeholder="Comment" id="linkResourceComment"
                                      name="linkResourceComment"></textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-4 "><label class="control-label">Visibility*:</label>
                        </div>

                        <div class="col-xs-8">
                            <g:render template="/share/topicsDropDown"/>
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
