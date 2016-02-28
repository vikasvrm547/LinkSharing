<div class="panel panel-primary">
    <div class="panel-heading">
        <h3 class="panel-title">Trending topic</h3>
    </div>

    <div class="panel-body tending-topic-panel-body">
        <g:each in="${tendingTopics}">
            <div class="trending-topic">
                <div>
                    <div class="row">
                        <div class="list-group  col-xs-3">

                            <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                        </div>

                        <div class="col-xs-9">
                            <div class="row">
                                <g:link name="topicName" controller="topic" action="show" params='[topicId:"${it?.id}"]'>${it?.name}</g:link>
                            </div>
                            <br/><br/>

                            <div class="row">
                                <div class="col-xs-4">
                                    <small class="col-xs-12">@${it?.createdBy}</small>
                                    <small class="col-xs-12"><a>Subscribe</a></small>
                                </div>

                                <div class="col-xs-4">
                                    <small class="col-xs-12">Subscriptions</small>
                                    <small class="col-xs-12">50</small>
                                </div>

                                <div class="col-xs-4">
                                    <small class="col-xs-12">Topics</small>
                                    <small class="col-xs-12">${it?.count}</small>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <hr/>

                <div class="row">
                    <div class="list-group  col-xs-3">

                        <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>

                    </div>

                    <div class="col-xs-9">
                        <div class="row">
                            <form class="form-inline" role="form">
                                <div class="form-group">

                                    <input type="text" class="form-control col-xs-4" placeholder="Grails" id="name">
                                    <button type="button" class="btn btn-primary">save</button>
                                    <button type="button" class="btn btn-default">cancel</button>

                                </div>
                            </form>
                        </div>
                        <br/>

                        <div class="row">
                            <div class="col-xs-4">
                                <small class="col-xs-12">@${it?.createdBy}</small>
                                <small class="col-xs-12"><a>Subscribe</a></small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Subscriptions</small>
                                <small class="col-xs-12">50</small>
                            </div>

                            <div class="col-xs-4">
                                <small class="col-xs-12">Topics</small>
                                <small class="col-xs-12">${it?.count}</small>
                            </div>
                        </br>
                        </div>
                    </div>
                </div>

                <div class="row">
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
                        <g:render template="/share/visibilityDropDown" model="value:${it?.visibility}"/>
                    </div>
                    <i class="fa fa-envelope-o nav_icon "></i>
                    <span class="glyphicon glyphicon-edit nav_icon "></span>
                    <span class="glyphicon glyphicon-trash nav_icon "></span>
                </div>
            </div>
        </g:each>
    </div>
</div>
