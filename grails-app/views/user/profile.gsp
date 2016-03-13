
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <asset:stylesheet src="profile.css"/>
    <meta name="layout" content="main"/>
    <script>
        $(document).ready(function(){
            $.ajax({
                url: "/user/topics",
                data:{id: $("#userId").val()},
                success: function(result){
                    $("#createdTopics").html(result);
                    setVisibilityOnChangeEvent();
                    setSeriousnessOnchangeEvent();
                    setSubscriptionOnTopicDelete();
                    setInviteModal();
                    setSubscriptionUpdateEvent();

                }
            });

            $.ajax({
                url: "/user/subscriptions",
                data:{id: $("#userId").val()},
                success: function(result){
                    $("#subscribedTopics").html(result);
                    setVisibilityOnChangeEvent();
                    setSeriousnessOnchangeEvent();
                    setSubscriptionOnTopicDelete();
                    setInviteModal();
                    setSubscriptionUpdateEvent();

                }
            });
        });


    </script>
</head>

<body>

<div class="col-xs-5 page-container-inner-left-div">
    <!-- panel profile start -->
    <div class="panel panel-primary">
        <div class="panel-body">
            <g:render template="/user/show" model="[user: user ?: null]"/>
        </div>
    </div>
    <!-- end panel profile start -->

    <div class="panel panel-primary">
        <div class="panel-heading">
            Subscriptions
        </div>

        <div class="panel-body subscription-panel-body" id="subscribedTopics">

        </div>
    </div>


    <div class="panel panel-primary">
        <div class="panel-heading">
            Topics
        </div>

        <div class="panel-body topic-panel-body" id="createdTopics">

        </div>
    </div>
</div>



<!-- Post start-->

<div class="col-xs-7 page-container-inner-right-div">
    <div class="panel panel-primary">
        <div class="panel-heading">
            <div class="row">
                <div class="col-sm-3">
                    Posts
                </div>
            </div>
        </div>

        <div class="panel-body post-panel-body" id = "post-panel-body">
            <g:each in="${resources}" var="post">
                <g:render template="/resource/show" model="[post: post]"/>
            </g:each>
        </div>
    </div>
</div>

<input type="hidden" value="${user.id}" id="userId"/>
</body>

</html>