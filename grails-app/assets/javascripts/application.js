function toggleTopicEditName(uniqueIdForTopicEdit) {
    $("#topic-name-" + uniqueIdForTopicEdit).toggle();
    $("#topic-edit-" + uniqueIdForTopicEdit).toggle();
}

function updateTopic(uniqueIdForTopicEdit) {

    var topicUpdatedName = $("#topic-name-edit-textbox-" + uniqueIdForTopicEdit).val();
    var topicName = $("#topic-name-" + uniqueIdForTopicEdit).text();
    var topicId = $("#topic-hidden-topic-id-" + uniqueIdForTopicEdit).val();
    var userId = $("#topic-hidden-user-id-" + uniqueIdForTopicEdit).val();
    if (/\S/.test(topicUpdatedName)) {
        var url = "/topic/save";
        var data = {topicUpdatedName: topicUpdatedName, topicName: topicName, topicId: topicId, userId: userId};
        var success = function (result) {
            var messageAlert = $(".messageAlert");
            for (item in result) {
                if (item === "message") {
                    messageAlert.text(result[item]);
                    messageAlert.addClass("alert alert-success");
                    $("#topic-name-" + uniqueIdForTopicEdit).text(topicUpdatedName);
                    location.reload()
                }
                else {
                    messageAlert.text(result[item]);
                    messageAlert.addClass("alert alert-danger");
                }
                $("#topic-name-" + uniqueIdForTopicEdit).toggle();
                $("#topic-edit-" + uniqueIdForTopicEdit).toggle();
            }
        };
        ajaxRequest(url, data, success)
    } else {
        alert("Please enter something")
    }
}

function ajaxRequest(url, data, success, beforeSend, complete) {
    $.ajax({
        url: url,
        data: data,
        success: success,
        beforeSend: beforeSend,
        complete: complete
    });
}


function success(result) {
    var messageAlert = $(".messageAlert");
    for (item in result) {
        if (item === "message") {
            messageAlert.text(result[item]);
            messageAlert.addClass("alert alert-success");
            location.reload()
        }
        else {
            messageAlert.text(result[item]);
            messageAlert.addClass("alert alert-danger");
        }
    }
}
function ajaxifiedTopicCreate() {
    var url = "/topic/save";
    var data = {topicName: $('.topicName').val(), visibilityString: $('.visibilityString').val()};
    ajaxRequest(url, data, success);
}

function showResourceEditModal(resourceId, description) {
    $("#resource-edit-resource-id-hidden-field").val(resourceId);
    $("#edit-description-textarea").text(description);

    $("#resource-edit-modal").modal('show');
}

$(".fa-comment").on('click', function () {

    $("#create-topic-modal").modal('show');

});


$("#forgot-password").on('click', function () {

    $("#forgot-password-modal").modal('show');

});

$(".glyphicon-link").on('click', function () {
    var url = "/user/subscribedTopics";
    var success = function (result) {
        $(".modal-topic-drop-down").html(result);
        $("#share-link-modal").modal('show');
    };
    ajaxRequest(url, '', success)
});


$(".fa-file-o").on('click', function () {
    var url = "/user/subscribedTopics";
    var success = function (result) {
        $(".modal-topic-drop-down").html(result);
        $("#share-document-modal").modal('show');
    };
    ajaxRequest(url, '', success)

});

function setInviteModal() {
    $(".fa-envelope-o").on('click', function (e) {
        e.preventDefault()
        var url = "/user/subscribedTopics";
        var success = function (result) {
            $(".modal-topic-drop-down").html(result);
            $("#send-invitation-modal").modal('show');
        };
        ajaxRequest(url, '', success);
    });
}
setInviteModal();

$("#topic-post-search-clear-button").on('click', function () {
    $("#topic-post-search-textbox").val(" ");
});
$("#global-search-clear-button").on('click', function () {
    $("#global-search-textbox").val(" ");
});


function setSeriousnessOnchangeEvent() {
    $(".seriousness").change(function () {
        var url = "/subscription/update";
        var data = {topicId: $(this).attr('topicId'), seriousnessString: $(this).val()};
        ajaxRequest(url, data, success);
    });
}
setSeriousnessOnchangeEvent();
function setVisibilityOnChangeEvent() {

    $(".visibility").change(function () {
        var url = "/topic/save";
        var data = {
            topicName: $(this).attr('topicName'), visibilityString: $(this).val(),
            userId: $("#hidden-current-user-id").val(), topicId: $(this).attr('topicId')
        };
        ajaxRequest(url, data, success);
    });
}
setVisibilityOnChangeEvent();

$("#topic-post-search-button").on('click', function () {
    var url = "/resource/search";
    var searchText = $("#topic-post-search-textbox").val();
    if (searchText != "") {
        var data = {topicId: $("#hidden-topic-id").val(), q: searchText};
        var success = function (result) {
            $("#post-panel-body").html(result)
        };
        ajaxRequest(url, data, success);
    }

});
function setSubscriptionUpdateEvent() {
    $(".subscription").click(function (e) {
        e.preventDefault();
        var tagObj = $(e.target);
        var subscriptionHref = tagObj.attr('href');

        tagObj.css("display", "none");
        tagObj.siblings("img").css("display", "block");

        var success = function (result) {
            tagObj.css("display", "block");
            tagObj.siblings("img").css("display", "none");
            /*for (item in result) {
             if (item === "message") {
             $(".messageAlert").text(result[item]);
             var parameter = subscriptionHref.substring(subscriptionHref.lastIndexOf('\?'));
             if (subscriptionHref.indexOf("/subscription/delete") === 0) {
             $(tagObj).attr('href', "/subscription/save" + parameter);
             $(tagObj).html("Subscribe");
             } else {
             $(tagObj).attr('href', "/subscription/delete" + parameter);
             $(tagObj).html("Unsubscribe");
             }
             }
             else {
             $(".messageAlert").text(result[item]);
             }
             }*/
            location.reload();
        };

        ajaxRequest(subscriptionHref, '', success);

    });
}
setSubscriptionUpdateEvent();
function setSubscriptionOnTopicDelete() {

    $(".glyphicon-trash").click(function (e) {
        if (!confirm("Do you really want to delete the topic")) {
            e.preventDefault();
        }
    });
}
setSubscriptionOnTopicDelete();

window.fbAsyncInit = function() {
    FB.init({
        appId      : '1713588548880157',
        xfbml      : true,
        version    : 'v2.5'
    });
};

(function(d, s, id){
    var js, fjs = d.getElementsByTagName(s)[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement(s); js.id = id;
    js.src = "//connect.facebook.net/en_US/sdk.js";
    fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));

    $(document).ready(function(){

        $('.share_button').click(function(e){
            e.preventDefault();
            FB.ui(
                {
                    method: 'feed',
                    name: this.getAttribute("topicName"),
                    link: ' http://www.tothenew.com/',
                    picture: 'https://thumb-gr.s3.envato.com/files/121676101/LINK-SHARING.png',
                    description: this.getAttribute("description")
                });
        });
    });


