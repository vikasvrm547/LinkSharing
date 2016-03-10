<div class="topic-info">
    <div class="row">
        <div class="list-group  col-xs-3">
            <ls:userImage userId="${topic?.createdBy?.id}" class="img-thumbnail" height="100" width="100"/>
        </div>

        <div class="col-xs-9">
            <div class="row">
                <a href="#">${topic ?: ''}</a>
            </div>
            <br/><br/>

            <div class="row">
                <div class="col-xs-4">
                    <small class="col-xs-12">@vikas</small>
                    <small class="col-xs-12"><asset:image src="loading.gif" height="50" width="50" style="display: none"/><ls:showSubscribe topicId="${topic?.id}"/></small>
                </div>

                <div class="col-xs-4 make-align-center">
                    <small class="col-xs-12">Subscriptions</small>
                    <small class=" badge badge-blue"><ls:subscriptionCount topicId="${topic?.id}"/></small>
                </div>

                <div class="col-xs-4 make-align-center">
                    <small class="col-xs-12">Posts</small>
                    <small class="badge badge-blue"><ls:resourceCount topicId="${topic?.id}"/></small>
                </div>
            </div>
        </div>
    </div>

    <div class="row " style="text-align:right">
    <form class="form-inline pull">
        <g:if test="${session.user}">
            <i class="fa fa-envelope-o nav_icon col-sm-2 form-group pull-right" style="font-size: 30px"></i>
            <div class="dropdown col-sm-4 form-group pull-right">
                <g:render template="/share/seriousnessDropDown"/>
            </div>
            </form>
        </g:if>
    </div>
</div>