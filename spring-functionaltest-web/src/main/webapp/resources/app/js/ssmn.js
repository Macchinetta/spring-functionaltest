var contextPath = $("meta[name='contextPath']").attr("content");
var csrfToken = $("meta[name='_csrf']").attr("content");
var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");

var url = location.href;
urlHierarchy = url.split("/");
var middleItemsURL = urlHierarchy[5];
var smallItemsURL = urlHierarchy[6];

function addItem() {
    var resultMessageArea = $("#resultMessage");
    resultMessageArea.removeClass("errorMessage");
    resultMessageArea.text("");
    var form = $("#cartItemForm");
    $.ajax(contextPath + "/ssmn/" + middleItemsURL + "/" + smallItemsURL + "/shopping/cart?add", {
        type: "POST",
        data: form.serialize(),
        dataType: "json",
    })
        .done(function (json) {
            resultMessageArea.text(json.message);
        })
        .fail(function (xhr) {
            var contentType = xhr.getResponseHeader("Content-Type");
            if (contentType == null || contentType.indexOf("json") == -1) {
                document.location = contextPath + "/ajaxError";
            } else {
                json = $.parseJSON(xhr.responseText);
                resultMessageArea.addClass("errorMessage");
                resultMessageArea.text(json.message);
                $("#xTrack").text(xhr.getResponseHeader("X-Track"));
            }
        });
    return false;
}

$(document).ajaxSend(function (event, xhr, options) {
    xhr.setRequestHeader(csrfHeaderName, csrfToken);
});

$(function () {
    $("#add").on("click", addItem);
});
