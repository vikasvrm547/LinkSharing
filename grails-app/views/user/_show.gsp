<div class="list-group  col-xs-3">
    <ls:userImage userId="${user?.id}" class="img-thumbnail" height="100" width="100"/>
</div>

<div class="col-xs-9">
    <div class="row">
        <p class="topic-show-user-name" >${user?.name}</p>
        <small class="col-xs-12">@${user?.firstName}</small>
    </div>
    <br/>
    <div class="row">

        <div class="col-xs-4 make-align-center">
            <small class="col-xs-12">Subscriptions</small>

            <small class="badge badge-blue"><ls:subscriptionCount user="${user?:null}"/></small>
        </div>

        <div class="col-xs-4 make-align-center">
            <small class="col-xs-12">Topics</small>
            <small class="badge badge-blue"><ls:topicCount user="${user?:null}"/></small>
        </div>
    </div>
</div>