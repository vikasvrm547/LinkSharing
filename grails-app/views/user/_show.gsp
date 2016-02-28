<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Users:${topic}</h3>
    </div>

    <div class="panel-body show-users-panel-body">
        <g:each in="${subscribedUsers}">
            <div class="user-info">

                <div class="row">
                    <div class="list-group  col-xs-3">
                        <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>
                    </div>

                    <div class="col-xs-9">
                        <div class="row">
                                <p class="topic-show-user-name" >${it?.name}</p>
                            <small class="col-xs-12">@${it?.firstName}</small>
                        </div>
                        <br/>
                        <div class="row">

                            <div class="col-xs-4">
                                <small class="col-xs-12">Subscriptions</small>
                                <small class="col-xs-12">${it?.subscribedTopics.size()}</small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Topics</small>
                                <small class="col-xs-12">10</small>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </g:each>
    </div>
</div>
