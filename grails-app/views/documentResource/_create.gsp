<div id="share-document-modal" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Share document</h4>
            </div>

        <div class="modal-body">

            <g:form class="form-horizontal" controller="documentResource" action="save" enctype="multipart/form-data">
                <div class="form-group">
                    <div class="col-sm-4 "><label class="control-label">Document*:</label></div>

                    <div class="col-lg-8 col-sm-8 col-8">
                        <div class="input-group">
                            <span class="input-group-btn">
                                <span class="btn btn-primary btn-file">
                                    Browse&hellip; <input type="file" name="file">
                                </span>
                            </span>
                            <input type="text" class="form-control" readonly>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 "><label class="control-label" for="comment">Comment:</label></div>

                    <div class="col-sm-8">
                        <textarea class="form-control" rows="5" placeholder="Comment" name="comment" id="comment"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4 "><label class="control-label">Topics*:</label>
                    </div>

                    <div class="col-xs-8">
                        <g:render template="/share/topicsDropDown"/>
                    </div>
                </div>

                </div>

                <div class="modal-footer">
                    <g:submitButton name="submit" class="btn btn-primary">Share</g:submitButton>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                </div>
            </g:form>
        </div>
    </div>
</div>
