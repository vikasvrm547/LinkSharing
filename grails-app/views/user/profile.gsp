<%--
  Created by IntelliJ IDEA.
  User: vikas
  Date: 11/3/16
  Time: 11:54 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Profile</title>
    <meta name="layout" content="main"/>
    <script>
        $(document).ready(function(){

            $.ajax({
                url: "/user/topics",
                data:{id: $("#userId").val()},
                success: function(result){
                     $("#createdTopics").html(result);
                }

            });

            $.ajax({
                url: "/user/subscriptions",
                data:{id: $("#userId").val()},
                success: function(result){
                    $("#subscribedTopics").html(result);
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

                %{--<div class="col-sm-6 col-sm-offset-3">
                    <div id="custom-search-input" style="margin: 1px;">
                        <div class="input-group col-md-12">
                            <input type="text" id="topic-post-search-textbox" class="form-control input-lg" placeholder="Search.."/>
                            <span class="input-group-btn">
                                <button class="btn btn-info btn-lg" id="topic-post-search-button" type="button">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                                <button class="btn btn-info btn-lg" id="topic-post-search-clear-button" type="button">
                                    <i class="glyphicon glyphicon-remove"></i>
                                </button>
                            </span>
                        </div>
                    </div>
                    <input type="hidden" value="${topic.id}" id="hidden-topic-id"/>
                </div>--}%
            </div>
        </div>

        <div class="panel-body post-panel-body" id = "post-panel-body">
            <g:each in="${resources}" var="post">
                <g:render template="/resource/show" model="[post: post]"/>
            </g:each>
        </div>
    </div>
</div>

<input type="hidden" value="${currentUser.id}" id="userId"/>
</body>

</html>