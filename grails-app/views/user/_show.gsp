<div class="list-group  col-xs-3">
    <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>
</div>

<div class="col-xs-9">
    <div class="row">
        <p class="topic-show-user-name" >${user?.name}</p>
        <small class="col-xs-12">@${user?.firstName}</small>
    </div>
    <br/>
    <div class="row">

        <div class="col-xs-4">
            <small class="col-xs-12">Subscriptions</small>
            <small class="col-xs-12"><ls:subscriptionCount user="${user?:null}"/></small>
        </div>

        <div class="col-xs-4">
            <small class="col-xs-12">Topics</small>
            <small class="col-xs-12"><ls:topicCount user="${user?:null}"/></small>
        </div>
    </div>
</div>