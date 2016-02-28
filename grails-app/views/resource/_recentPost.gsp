<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Recent share</h3>
    </div>

    <div class="panel-body recent-post-panel-body">
        <g:each in="${recentShares}">

            <div class="row recent-post">
                <div class="list-group  col-xs-3">
                    <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>
                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <h5 class="col-xs-6">${it.createdBy}<small>@vikas 5min</small></h5>
                        <a class="col-xs-6 profile-content-top-right-anchor">${it.topic}</a></div>

                    <div class="row" style="">
                        <p class="col-xs-12">${it.description}</p>
                    </div>

                    <div class="row">
                        <i id="facebook_icon" class="fa fa-facebook-official col-xs-1"></i>
                        <i id="twitter_icon" class="fa fa-twitter col-xs-1"></i>
                        <i id="google_plus_icon" class="fa fa-google-plus col-xs-1"></i>

                        <a class="col-xs-9 view-post-anchor">View post</a>
                    </div>
                </div>
            </div>

        </g:each>
    </div>
</div>