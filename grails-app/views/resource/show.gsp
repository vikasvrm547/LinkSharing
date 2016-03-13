<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Post</title>
    <asset:stylesheet src="resource.css"/>
    <asset:javascript src="starRating.js"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.0.1/jquery.rateyo.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.0.1/jquery.rateyo.min.js"></script>
    <meta name="layout" content="main"/>
    <script>
        $(function () {

            $("#rateYo").rateYo({
                fullStar: true,
                onSet: function (rating, rateYoInstance) {

                    $.ajax({
                        url: "/resourceRating/save",
                        data: {
                            userId: $("#hidden-user-id").val(), resourceId: $("#hidden-resource-id").val(),
                            score: rating
                        },
                    })
                }
            });
            $("#rateYo").rateYo("rating", $("#default-hidden-resource-rating").val());
        });
    </script>
</head>

<body>
<div class="col-xs-7 page-container-inner-left-div">

    <!-- post content start-->
    <div class="panel panel-primary">

        <div class="panel-body post-panel-body">

            <div class="row">
                <div class="list-group  col-xs-3">
                    <asset:image src="user.png" class="img-thumbnail" height="100" width="100"/>
                </div>

                <div class="col-xs-9">
                    <div class="row">
                        <h5 class="col-xs-6">
                            <g:link controller="user" action="profile"
                                    params='[id: "${postVO.userId}"]'>${postVO?.getNameOfUser()}</g:link>
                            <small>@${postVO?.userUserName}</small></h5>
                        <a class="col-xs-6 input-top-right-anchor">
                            <g:link name="topicName" controller="topic" action="show"
                                    class="col-xs-6 input-top-right-anchor"
                                    params='[topicId: "${postVO?.topicId}"]'>
                                ${postVO?.topicName}
                            </g:link>
                        </a>
                    </div>
                    <div class="row"  style="text-align: right">
                        <g:formatDate format="hh:mm a dd MMM yyyy" date="${postVO?.lastUpdated}"/>
                    </div>
                    <div class="row">

                        <g:if test="${session.user}">
                            <div id="rateYo" class="pull-right"></div>
                            <input type="hidden" id="default-hidden-resource-rating"
                                   value="${currentUser?.getScore(postVO?.resourceID)}"/>
                            <input type="hidden" id="hidden-user-id" value="${currentUser?.id}"/>
                            <input type="hidden" id="hidden-resource-id" value="${postVO?.resourceID}"/>
                        </g:if>

                    </div>
                </div>

                <div class="row post-content">
                    <p class="col-xs-12">${postVO?.description}</p>
                </div>

                <div class="row">
                    <div class="col-xs-5">
                        <a href="#">
                            <div class="fa fa-facebook-official" style= "font-size: large;margin-left: 10px"></div>
                        </a>
                        <a href="#">
                            <div class="fa fa-twitter inline" style= "font-size: large;margin-left: 10px"></div>
                        </a>
                        <a href="#">
                            <div class="fa fa-google-plus inline" style= "font-size: large;margin-left: 10px"></div>
                        </a>
                    </div>

                    <div class="col-xs-7 post-footer-links">
                        <ls:canEditResource currentUser="${currentUser}" resourceId="${postVO?.resourceID}">
                            <a onclick='showResourceEditModal(${postVO?.resourceID}, "${postVO?.description}")'>Edit</a>

                        </ls:canEditResource>
                        <ls:resourceTypeLink resourceId="${postVO?.resourceID}" url="${postVO?.url}"
                                             filePath="${postVO?.filePath}"/>
                        <ls:canDeleteResource currentUser="${currentUser}" resourceId="${postVO?.resourceID}"
                                              class="inline post-panel-anchor">Delete</ls:canDeleteResource>

                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- post content in -->
</div>

<div class="col-xs-5 page-container-inner-right-div">
    <ls:trendingTopics tendingTopics="${tendingTopics}"/>
</div>

</body>
</html>