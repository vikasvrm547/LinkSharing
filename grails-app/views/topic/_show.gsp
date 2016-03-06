
<div class="trending-topic">
    <div>
        <div class="row">
            <div class="list-group  col-xs-3">

                <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

            </div>

            <div class="col-xs-9">
                <div class="row">
                    <g:link name="topicName" controller="topic" action="show" params='[topicId:"${topic.id}"]'>${topic.name}</g:link>
                </div>
                <br/><br/>

                <div class="row">
                    <div class="col-xs-4">
                        <small class="col-xs-12">@${topic.createdBy}</small>
                        <small class="col-xs-12"><ls:showSubscribe topicId="${topic.id}"/> </small>

                    </div>

                    <div class="col-xs-4">
                        <small class="col-xs-12">Subscriptions</small>

                        <small class="col-xs-12"><ls:subscriptionCount topicId="${topic.id}"/> </small>
                    </div>

                    <div class="col-xs-4">
                        <small class="col-xs-12">Topics</small>
                       %{-- <small class="col-xs-12">${topic?.count}</small>--}% %{-- why not this--}%
                        <small class="col-xs-12"><ls:topicCount user="${topic.createdBy}" /></small>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="footer">
        <div class="dropdown col-sm-4">
            <button class="btn btn-primary dropdown-toggle panels-dropdown" type="button"
                    data-toggle="dropdown">serious
                <span class="label label-primary"><span class="caret"></span></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#">HTML</a></li>
                <li><a href="#">CSS</a></li>
                <li><a href="#">JavaScript</a></li>
            </ul>
        </div>

        <div class="dropdown col-sm-4">
            <g:render template="/share/visibilityDropDown" model="value:${topic.visibility}"/>
        </div>
        <i class="fa fa-envelope-o nav_icon "></i>
        <span class="glyphicon glyphicon-edit nav_icon "></span>
        <span class="glyphicon glyphicon-trash nav_icon "></span>
    </div>
</div>
