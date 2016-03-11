<div class="trending-topic">
    <div>
        <div class="row">
            <div class="list-group  col-xs-3">

                <ls:userImage userId="${topic?.createdBy?.id}" class="img-thumbnail" height="100" width="100"/>

            </div>

            <div class="col-xs-9">
                <div class="row">
                    <g:link name="topicName" controller="topic" action="show"
                            params='[topicId: "${topic?.id}"]'>${topic?.name}</g:link>
                </div>
                <br/><br/>

                <div class="row">
                    <div class="col-xs-4 ">
                        <small class="col-xs-12">@${topic?.createdBy}</small>

                        <small class="col-xs-12"><asset:image src="loading.gif" height="50" width="50"
                                                              style="display: none"/>
                            <ls:showSubscribe topicId="${topic?.id}"/></small>

                    </div>

                    <div class="col-xs-4 make-align-center ">
                        <small class="col-xs-12">Subscriptions</small>

                        <small class="badge badge-blue"><ls:subscriptionCount topicId="${topic?.id}"/></small>
                    </div>

                    <div class="col-xs-4 make-align-center">
                        <small class="col-xs-12">Topics</small>
                        %{-- <small class="col-xs-12">${topic?.count}</small>--}% %{-- why not this--}%
                        <small class="badge badge-blue"><ls:topicCount user="${topic?.createdBy}"/></small>

                    </div>
                </div>
            </div>
        </div>
    </div>
    <g:if test="${session.user}">
        <div class="footer">
            <ls:showSeriousness topicId="${topic?.id}" class="btn btn-primary seriousness"/>
            <ls:canUpdateTopic topicId="${topic?.id}" currentUser="${session.user}">
                <ls:showVisibility topicName="${topic?.name}" visibility="${topic?.visibility}"
                                   class="btn btn-primary visibility"/>
               <i class="glyphicon glyphicon-edit nav_icon "></i>

                <g:link controller="topic" action="delete" params='[topicId: "${topic?.id}"]'
                        class="glyphicon glyphicon-trash nav_icon ">
                </g:link>
            </ls:canUpdateTopic>
            <ls:showInvitation class="fa fa-envelope-o nav_icon" topicId="${topic?.id}"/>
            %{--<i class="fa fa-envelope-o nav_icon"></i>--}%
        </div>
    </g:if>
</div>
