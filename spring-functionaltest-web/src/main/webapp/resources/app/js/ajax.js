if (!ajax)
    var ajax = {};

(function(ajax) {

    var contextPath = $("meta[name='contextPath']").attr("content");
    var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");
    var csrfToken = $("meta[name='_csrf']").attr("content");

    $(document).ajaxSend(function(event, xhr, options) {
        xhr.setRequestHeader(csrfHeaderName, csrfToken);
    });

    ajax.search = function() {

        $.ajax(contextPath + "/ajax/search", {
            type : "GET",
            data : $("#personalComputerCriteria").serialize(),
            dataType : "json"
        }).done(function(json, status, xhr) {
            searchNormalResultDisplay(json, xhr);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });

        return false;
    };

    ajax.editPersonalComputer = function(path) {

        $.ajax(contextPath + "/ajax/" + path, {
            type : "POST",
            data : $("#personalComputerForm").serialize(),
            dataType : "json"
        }).done(function(json, status, xhr) {
            editNormalResultDisplay(json, xhr);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });

        return false;
    };

    ajax.editPersonalComputerForJson = function(path) {

        $.ajax(contextPath + "/ajax/" + path, {
            type : "POST",
            contentType : "application/json",
            data : toJson($("#personalComputerForm")),
            dataType : "json"
        }).done(function(json, status, xhr) {
            editNormalResultDisplay(json, xhr);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });

        return false;
    };

    ajax.editPersonalComputerForJsonAndBidingResultHandle = function(path) {

        $.ajax(contextPath + "/ajax/" + path, {
            type : "POST",
            contentType : "application/json",
            data : toJson($("#personalComputerForm")),
            dataType : "json"
        }).done(function(json, status, xhr) {
            sharedNormalResultDisplay(json, xhr);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });

        return false;
    };

    ajax.write = function(path) {
        var result = $("#result");
        var comment = $("#comment").val();

        $.ajax(contextPath + "/ajax/" + path, {
            type : "POST",
            contentType : 'text/xml;charset=UTF-8',
            data : comment,
            dataType : "xml"
        }).done(function(xml, status, xhr) {
            writeNormalResultDisplay(xml, xhr, result);
        }).fail(function(xhr) {
            xmlErrorResultDisplay(xhr);
        });

        return false;
    };

    function escapeHTML(val) {
        return $('<div>').text(val).html();
    }

    function toJson($form) {
        var data = {};
        $($form.serializeArray()).each(function(i, v) {
            data[v.name] = v.value;
        });
        return JSON.stringify(data);
    }

    function searchNormalResultDisplay(json, xhr) {
        $("#returnMessage").remove();
        $("#resultMessage").addClass('hidden');
        setXMLHttpRequestResultNormal(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);

        var personalComputerResultTable = $("#personalComputerResultTable");

        personalComputerResultTable.find("tbody *").remove();

        if (0 < json.personalComputerResult.length) {
            var personalComputerResult = $("#personalComputerResult");
            for (var i = 0; i < json.personalComputerResult.length; i++) {
                personalComputerResult
                        .append("<tr><td>"
                                + (i + 1)
                                + "</td><td><a href=\"#\">"
                                + escapeHTML(json.personalComputerResult[i].personalComputerName)
                                + "</a></td><td>"
                                + escapeHTML(json.personalComputerResult[i].os)
                                + "</td><td>"
                                + escapeHTML(json.personalComputerResult[i].cpu)
                                + "</td><td>"
                                + escapeHTML(json.personalComputerResult[i].ram)
                                + "</td><td>"
                                + escapeHTML(json.personalComputerResult[i].videocard)
                                + "</td><td>"
                                + escapeHTML(json.personalComputerResult[i].hdd)
                                + "</td><td>"
                                + escapeHTML(json.personalComputerResult[i].power)
                                + "</td><td>"
                                + escapeHTML(json.personalComputerResult[i].price)
                                + "円</td></tr>");
            }
            personalComputerResultTable.removeClass('hidden');
        } else {
            personalComputerResultTable.addClass('hidden');
        }
    }

    function editNormalResultDisplay(json, xhr) {
        setNormalBaseDom(xhr);

        var returnMessage = $("#returnMessage");
        for (var i = 0; i < json.messages.length; i++) {
            returnMessage.append("<li>" + escapeHTML(json.messages[i])
                    + "</li>");
        }
    }

    function errorResultDisplay(xhr) {
        setErrorBaseDom(xhr);

        var json = xhr.responseJSON;

        var returnMessage = $("#returnMessage");
        for (var i = 0; i < json.errorResults.length; i++) {
            returnMessage.append("<li>"
                    + escapeHTML(json.errorResults[i].message) + "</li>");
        }
    }

    function sharedNormalResultDisplay(json, xhr) {
        if (json.messages != null) {
            setNormalBaseDom(xhr);
            var returnMessage = $("#returnMessage");
            for (var i = 0; i < json.messages.length; i++) {
                returnMessage.append("<li>" + escapeHTML(json.messages[i])
                        + "</li>");
            }

        } else {
            setErrorBaseDom(xhr);
            var errroMessages = $("#returnMessage");
            for (var i = 0; i < json.errorResults.length; i++) {
                errroMessages.append("<li>"
                        + escapeHTML(json.errorResults[i].message) + "</li>");
            }
        }
    }

    function writeNormalResultDisplay(xml, xhr, result) {
        $("#returnMessage").remove();
        $("#resultMessage").addClass('hidden');
        setXMLHttpRequestResultNormal(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);

        $(xml).find('resultMessages').each(
                function() {
                    var resultMessages = $(this).text();
                    result.append("<ul><li>" + escapeHTML(resultMessages)
                            + "</li><ul>");
                });
    }

    function xmlErrorResultDisplay(xhr) {
        setXMLHttpRequestResultError(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);

        var xml = xhr.responseXML;

        var errorResults = $(xml).find('errorResults').find('errorResults');

        if ($(errorResults).find('message').text() != '') {
            setResultMessageError();

            var returnMessage = $("#returnMessage");
            $(errorResults).each(function() {
                var message = $(this).find('message').text();
                returnMessage.append("<li>" + escapeHTML(message) + "</li>");
            });
        }
    }

    function setNormalBaseDom(xhr) {
        setXMLHttpRequestResultNormal(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);
        setResultMessageNormal();
    }

    function setErrorBaseDom(xhr) {
        setXMLHttpRequestResultError(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);
        setResultMessageError();
    }

    function setXMLHttpRequestResultNormal(xhr) {
        var xMLHttpRequestResult = $("#xMLHttpRequestResult");
        xMLHttpRequestResult.removeClass('hidden');
        xMLHttpRequestResult.removeClass('alert-danger');
        xMLHttpRequestResult.addClass('alert-success');
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
        returnStatus.append(escapeHTML(xhr.status));
    }

    function setContentTypeDom(xhr) {
        var contentType = $("#contentType");
        contentType.text("");
        contentType.append(escapeHTML(xhr.getResponseHeader("Content-Type")));
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
})(ajax);