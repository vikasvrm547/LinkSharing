<div class="row inbox-post">
    <div class="list-group  col-xs-3">

        <ls:userImage userId="${post.userId}" class="img-rounded" height="100" width="100"/>

    </div>

    <div class="col-xs-9">
        <div class="row">
            <h5 class="col-xs-6">
                <g:link controller="user" action="profile"
                        params='[id: "${post.userId}"]'>${post?.getNameOfUser()}</g:link>
                <small>@${post?.userUserName}</small>
            </h5>
            <g:link name="topicName" controller="topic" action="show" class="col-xs-6 input-top-right-anchor"
                    params='[topicId: "${post?.topicId}"]'>${post?.topicName}</g:link>
        </div>

        <div class="row post-description">
            <p class="col-xs-12">${post?.description}</p>
        </div>

        <div class="row">
            <div class="col-xs-3">
                <a href="#" class ="share_button"
                   description="${post?.description}" topicName="${post?.topicName}" >
                    <div class="fa fa-facebook-official" style= "font-size: medium;margin-right: 5px"></div>
                </a>
                <a href="#">
                    <div class="fa fa-twitter inline"  style= "font-size: medium;margin-right: 5px"></div>
                </a>
                <a href="#">
                    <div class="inline g-plus"  data-action="share" data-annotation="none" action="share" style= "margin-right: 5px"></div>
                </a>
            </div>

            <div class="col-xs-9 post-footer-links">
                <g:if test="${controllerName.equals("user") && actionName.equals("show")}">
                    <ls:readLink resourceId="${post?.resourceID}" isRead="${post?.isRead}"
                                 user="${currentUser ?: null}"/>
                </g:if>
                <g:link controller="resource" action="show"
                        params='[resourceId: "${post?.resourceID}"]'>View post</g:link>

                <ls:resourceTypeLink resourceId="${post?.resourceID}" url="${post?.url}"
                                     filePath="${post?.filePath}"/>
            </div>
        </div>
    </div>
</div>