<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title col-xs-6">Top stories</h3>

        <div class="dropdown top-post-panel-dropdown">
            <button class="btn btn-primary dropdown-toggle top-post-panel-dropdown-button" style="color:black"
                    type="button" data-toggle="dropdown">Dropdown Example
                <span class="caret"></span></button>
            <ul class="dropdown-menu pull-right">
                <li><a href="#">HTML</a></li>
                <li><a href="#">CSS</a></li>
                <li><a href="#">JavaScript</a></li>
            </ul>
        </div>

    </div>

    <div class="panel-body top-post-panel-body">
        <g:each in="${topPosts}">

            <div class="row top-post">

                <div class="list-group  col-xs-3">

                    <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <h5 class="col-xs-6">${it.createdBy}<small>@vikas 5min</small></h5>
                        <a class="col-xs-6 profile-content-top-right-anchor">${it.topic}</a></div>

                    <div class="row">
                        <p class="col-xs-12">${it.description}</p>
                    </div>

                    <div class="row">
                        <i id="facebook_icon" class="fa fa-facebook-official col-xs-1"></i>
                        <i id="twitter_icon" class="fa fa-twitter col-xs-1"></i>
                        <i id="google_plus_icon" class="fa fa-google-plus col-xs-1"></i>

                        <a class="col-xs-9 view-post-anchor">View post</a>
                    </div>
                </div>
                <hr/>
            </div>
        </g:each>

    </div>

</div>