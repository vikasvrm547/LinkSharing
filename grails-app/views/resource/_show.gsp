<div class="row inbox-post">
    <div class="list-group  col-xs-3">

       %{-- <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>--}%
       <ls:userImage class="img-thumbnail" height="100" width="100"/>

    </div>

    <div class="col-xs-9">
        <div class="row">
            <h5 class="col-xs-6">${post?.getNameOfUser()}<small>@${post?.userUserName} 5min</small></h5>
            <a class="col-xs-6 input-top-right-anchor">${post?.topicName}</a>
        </div>

        <div class="row">
            <p class="col-xs-12">${post?.description}</p>
        </div>

        <div class="row">
            <div class="col-xs-3">
                <a href="#">
                    <div class="fa fa-facebook-official"></div>
                </a>
                <a href="#">
                    <div class="fa fa-twitter inline"></div>
                </a>
                <a href="#">
                    <div class="fa fa-google-plus inline"></div>
                </a>
            </div>

            <div class="col-xs-9">
                <ls:readLink resourceId="${post?.resourceID}" isRead="${post?.isRead}"
                             user="${currentUser ?: null}"/>
                <g:link controller="resource" action="show" methods="post"
                        params='[resourceId: "${post?.resourceID}", userId: "${post?.userId}"]'>View post</g:link>
                <ls:resourceTypeLink resourceId="${post?.resourceID}" url="${post?.url}"
                                     filePath="${post?.filePath}"/>
            </div>
        </div>
    </div>
</div>