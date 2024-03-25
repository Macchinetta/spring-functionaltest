if (!cspr)
    var cspr = {};

(function(cspr) {

    var contextPath = $("meta[name='contextPath']").attr("content");
    var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");

    cspr.search = function() {

        $("#customerResult").remove();

        var csrfToken = $("meta[name='_csrf']").attr("content");

        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(csrfHeaderName, csrfToken);
        });

        $.ajax(contextPath + "/search", {
            type : "GET",
            data : $("#committerCriteria").serialize(),
            dataType : "json"
        }).done(function(json) {
            searchNormalResultDisplay(json);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });

        return false;
    };

    cspr.editProfile = function() {

        var csrfToken = $("meta[name='_csrf']").attr("content");

        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(csrfHeaderName, csrfToken);
        });

        $.ajax(contextPath + "/edit", {
            type : "POST",
            data : $("#committerForm").serialize(),
            dataType : "json"
        }).done(function(json) {
            editProfileNormalResultDisplay(json);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });

        return false;
    };

    function escapeHTML(val) {
        return $('<div>').text(val).html();
    }

    function searchNormalResultDisplay(json) {
        $("#returnMessage").remove();
        $("#resultMessage").addClass('hidden');
        $("#xMLHttpRequestResult").addClass('hidden');

        var committerResultTable = $("#committerResultTable");

        committerResultTable.find("tbody *").remove();

        if (0 < json.committers.length) {
            var committerResult = $("#committerResult");
            for (var i = 0; i < json.committers.length; i++) {
                committerResult
                        .append("<tr><td>"
                                + (i + 1)
                                + "</td><td>"
                                + escapeHTML(json.committers[i].username)
                                + "</td><td>"
                                + escapeHTML(json.committers[i].email)
                                + "</td><td><input type=\"submit\" class=\"btn btn-default\" value=\"詳細\" /></td></tr>");
            }

            committerResultTable.removeClass('hidden');
        } else {
            committerResultTable.addClass('hidden');
        }
    }

    function editProfileNormalResultDisplay(json) {
        $("#xMLHttpRequestResult").addClass('hidden');
        setResultMessageNormal();

        var returnMessage = $("#returnMessage");
        for (var i = 0; i < json.messages.length; i++) {
            returnMessage.append("<li>" + escapeHTML(json.messages[i])
                    + "</li>");
        }
    }

    function errorResultDisplay(xhr) {
        setXMLHttpRequestResultError(xhr);
        setStatusCodeDom(xhr);

        var json = xhr.responseJSON;

        if (0 < json.errorResults.length) {
            setResultMessageError();
            var returnMessage = $("#returnMessage");
            for (var i = 0; i < json.errorResults.length; i++) {
                returnMessage.append("<li>"
                        + escapeHTML(json.errorResults[i].message) + "</li>");
            }
        }
    }

    function setXMLHttpRequestResultError(xhr) {
        var xMLHttpRequestResult = $("#xMLHttpRequestResult");
        xMLHttpRequestResult.removeClass('hidden');
        xMLHttpRequestResult.removeClass('alert-success');
        xMLHttpRequestResult.addClass('alert-danger');
    }

    function setStatusCodeDom(xhr) {
        var returnStatus = $("#returnStatus");
        returnStatus.text("");
        returnStatus.append("ステータスコード:" + escapeHTML(xhr.status));
    }

    function setResultMessageNormal() {
        var resultMessage = $("#resultMessage");
        setResultMessage(resultMessage);
        resultMessage.removeClass('alert-danger');
        resultMessage.addClass('alert-success');
    }

    function setResultMessageError() {
        var resultMessage = $("#resultMessage");
        setResultMessage(resultMessage);
        resultMessage.removeClass('alert-success');
        resultMessage.addClass('alert-danger');
    }

    function setResultMessage(resultMessage) {
        resultMessage.removeClass('hidden');
        resultMessage.text("");
        resultMessage.append("<ul id=\"returnMessage\"></ul>");
    }
})(cspr);