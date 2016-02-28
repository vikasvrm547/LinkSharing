<div class="topic-info">
    <div class="row">
        <div class="list-group  col-xs-3">

            <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

        </div>

        <div class="col-xs-9">
            <div class="row">
                <a href="#">${topic ?: ''}</a>
            </div>
            <br/><br/>

            <div class="row">
                <div class="col-xs-4">
                    <small class="col-xs-12">@vikas</small>
                    <small class="col-xs-12"><a>Subscribe</a></small>
                </div>

                <div class="col-xs-4">
                    <small class="col-xs-12">Subscriptions</small>
                    <small class="col-xs-12">${subscribedUsers?.size()}</small>
                </div>

                <div class="col-xs-4">
                    <small class="col-xs-12">Posts</small>
                    <small class="col-xs-12">${topic.resources.size()}</small>
                </div>
            </div>
        </div>
    </div>

    <div class="row " style="text-align:right">
        <form class="form-inline pull">

            <i class="fa fa-envelope-o nav_icon col-sm-2 form-group pull-right" style="font-size: 30px"></i>

            <div class="dropdown col-sm-4 form-group pull-right">
                <g:render template="/share/seriousnessDropDown"/>

            </div>
        </form>
    </div>
</div>