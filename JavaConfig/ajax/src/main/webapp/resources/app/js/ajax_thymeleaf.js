if (!ajax) var ajax = {};

(function (ajax) {
    function escapeHTML(val) {
        return $("<div>").text(val).html();
    }

    ajax.toJson = function ($form) {
        var data = {};
        $($form.serializeArray()).each(function (i, v) {
            data[v.name] = v.value;
        });
        return JSON.stringify(data);
    };

    ajax.searchNormalResultDisplay = function (json, xhr) {
        $("#returnMessage").remove();
        $("#resultMessage").addClass("hidden");
        setXMLHttpRequestResultNormal(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);

        var personalComputerResultTable = $("#personalComputerResultTable");

        personalComputerResultTable.find("tbody *").remove();

        if (0 < json.personalComputerResult.length) {
            var personalComputerResult = $("#personalComputerResult");
            for (var i = 0; i < json.personalComputerResult.length; i++) {
                personalComputerResult.append(
                    "<tr><td>" +
                        (i + 1) +
                        '</td><td><a href="#">' +
                        escapeHTML(json.personalComputerResult[i].personalComputerName) +
                        "</a></td><td>" +
                        escapeHTML(json.personalComputerResult[i].os) +
                        "</td><td>" +
                        escapeHTML(json.personalComputerResult[i].cpu) +
                        "</td><td>" +
                        escapeHTML(json.personalComputerResult[i].ram) +
                        "</td><td>" +
                        escapeHTML(json.personalComputerResult[i].videocard) +
                        "</td><td>" +
                        escapeHTML(json.personalComputerResult[i].hdd) +
                        "</td><td>" +
                        escapeHTML(json.personalComputerResult[i].power) +
                        "</td><td>" +
                        escapeHTML(json.personalComputerResult[i].price) +
                        "円</td></tr>"
                );
            }
            personalComputerResultTable.removeClass("hidden");
        } else {
            personalComputerResultTable.addClass("hidden");
        }
    };

    ajax.editNormalResultDisplay = function (json, xhr) {
        setNormalBaseDom(xhr);

        var returnMessage = $("#returnMessage");
        for (var i = 0; i < json.messages.length; i++) {
            returnMessage.append("<li>" + escapeHTML(json.messages[i]) + "</li>");
        }
    };

    ajax.errorResultDisplay = function (xhr) {
        setErrorBaseDom(xhr);

        var json = xhr.responseJSON;

        var returnMessage = $("#returnMessage");
        for (var i = 0; i < json.errorResults.length; i++) {
            returnMessage.append("<li>" + escapeHTML(json.errorResults[i].message) + "</li>");
        }
    };

    ajax.sharedNormalResultDisplay = function (json, xhr) {
        if (json.messages != null) {
            setNormalBaseDom(xhr);
            var returnMessage = $("#returnMessage");
            for (var i = 0; i < json.messages.length; i++) {
                returnMessage.append("<li>" + escapeHTML(json.messages[i]) + "</li>");
            }
        } else {
            setErrorBaseDom(xhr);
            var errroMessages = $("#returnMessage");
            for (var i = 0; i < json.errorResults.length; i++) {
                errroMessages.append("<li>" + escapeHTML(json.errorResults[i].message) + "</li>");
            }
        }
    };

    ajax.writeNormalResultDisplay = function (xml, xhr, result) {
        $("#returnMessage").remove();
        $("#resultMessage").addClass("hidden");
        setXMLHttpRequestResultNormal(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);

        $(xml)
            .find("resultMessages")
            .each(function () {
                var resultMessages = $(this).text();
                result.append("<ul><li>" + escapeHTML(resultMessages) + "</li><ul>");
            });
    };

    ajax.xmlErrorResultDisplay = function (xhr) {
        setXMLHttpRequestResultError(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);

        var xml = xhr.responseXML;

        var errorResults = $(xml).find("errorResults").find("errorResults");

        if ($(errorResults).find("message").text() != "") {
            setResultMessageError();

            var returnMessage = $("#returnMessage");
            $(errorResults).each(function () {
                var message = $(this).find("message").text();
                returnMessage.append("<li>" + escapeHTML(message) + "</li>");
            });
        }
    };

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
        xMLHttpRequestResult.removeClass("hidden");
        xMLHttpRequestResult.removeClass("alert-danger");
        xMLHttpRequestResult.addClass("alert-success");
    }

    function setXMLHttpRequestResultError(xhr) {
        var xMLHttpRequestResult = $("#xMLHttpRequestResult");
        xMLHttpRequestResult.removeClass("hidden");
        xMLHttpRequestResult.removeClass("alert-success");
        xMLHttpRequestResult.addClass("alert-danger");
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
        resultMessage.removeClass("alert-danger");
        resultMessage.addClass("alert-success");
    }

    function setResultMessageError() {
        var resultMessage = $("#resultMessage");
        setResultMessage(resultMessage);
        resultMessage.removeClass("alert-success");
        resultMessage.addClass("alert-danger");
    }

    function setResultMessage(resultMessage) {
        resultMessage.removeClass("hidden");
        resultMessage.text("");
        resultMessage.append('<ul id="returnMessage"></ul>');
    }
})(ajax);
