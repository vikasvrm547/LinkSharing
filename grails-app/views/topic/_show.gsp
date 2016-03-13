<div class="trending-topic">
    <div>
        <div class="row">
            <div class="list-group  col-xs-3">
                <ls:userImage userId="${topic?.createdBy?.id}" class="img-thumbnail" height="100" width="100"/>
            </div>

            <div class="col-xs-9">
                <div class="row" id="topic-edit-${uniqueIdForTopicEdit}" style="display: none">
                    <form class="form-inline" role="form">
                        <div class="form-group">

                            <input type="hidden" id="topic-hidden-user-id-${uniqueIdForTopicEdit}"
                                   value="${topic?.createdBy?.id}"/>
                            <input type="hidden" id="topic-hidden-topic-id-${uniqueIdForTopicEdit}"
                                   value="${topic?.id}"/>
                            <input type="text" class="form-control col-xs-4"
                                   id="topic-name-edit-textbox-${uniqueIdForTopicEdit}">
                            <button type="button" class="btn btn-primary"
                                    onclick="updateTopic(${uniqueIdForTopicEdit})">save</button>
                            <button type="button" class="btn btn-default"
                                    onclick="toggleTopicEditName(${uniqueIdForTopicEdit})">cancel</button>

                        </div>
                    </form>
                </div>

                <g:link name="topicName" controller="topic" action="show"
                        params='[topicId: "${topic?.id}"]'>
                    <div class="row" id="topic-name-${uniqueIdForTopicEdit}">
                        ${topic?.name}
                    </div>
                </g:link>
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
                        <small class="col-xs-12">Posts</small>
                        <small class="badge badge-blue"><ls:resourceCount topicId="${topic?.id}"/></small>

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
                                   class="btn btn-primary visibility" topicId = "${topic?.id}"/>
                <i class="glyphicon glyphicon-edit nav_icon" style="color: #337ab7;"
                   onclick="toggleTopicEditName(${uniqueIdForTopicEdit})"></i>

                <g:link controller="topic" action="delete" params='[topicId: "${topic?.id}"]'
                        class="glyphicon glyphicon-trash nav_icon" title="Delete topic">
                </g:link>
            </ls:canUpdateTopic>
            <ls:showInvitation class="fa fa-envelope-o nav_icon" topicId="${topic?.id}" title="Invite friends"/>
        </div>
    </g:if>
    <input type="hidden" id="hidden-current-user-id"  value="${session.user?.id}"/>
</div>
