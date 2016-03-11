
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


$(".fa-comment").on('click', function () {

    $("#create-topic-modal").modal('show');

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


$(".fa-envelope-o").on('click', function () {
    var url = "/user/subscribedTopics";
    var success = function (result) {
        $(".modal-topic-drop-down").html(result);
        $("#send-invitation-modal").modal('show');
    };
    ajaxRequest(url, '', success)

});



$("#topic-post-search-button").on('click', function () {
    var url = "/resource/search";
    var searchText = $("#topic-post-search-textbox").val();
    if (searchText != "") {
        var data = {topicId: $("#hidden-topic-id").val(), q: searchText};
        var success = function (result) {
            $("#post-panel-body").html(result)
        };
        ajaxRequest(url, data, success);
    } else
        alert("Please enter something")

});
$("#topic-post-search-clear-button").on('click', function () {
    $("#topic-post-search-textbox").val(" ");
});
$("#global-search-clear-button").on('click', function () {
    $("#global-search-textbox").val(" ");
});



$(".seriousness").change(function () {
    var url = "/subscription/update";
    var data = {topicId: $(this).attr('topicId'), seriousnessString: $(this).val()};
    ajaxRequest(url, data, success);
});

$(".visibility").change(function () {
    var url = "/topic/save";
    var data = {topicName: $(this).attr('topicName'), visibilityString: $(this).val()};
    ajaxRequest(url, data, success);
});


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
    }

    ajaxRequest(subscriptionHref, '', success);

});


