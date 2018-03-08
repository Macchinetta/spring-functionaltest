if (!athr)
    var athr = {};

(function(athr) {
    var contextPath = $("meta[name='contextPath']").attr("content");
    var csrfHeaderName = $("meta[name='_csrf_header']").attr("content");

    athr.send = function() {

        $("#customerResult").remove();

        var csrfToken = $("meta[name='_csrf']").attr("content");

        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(csrfHeaderName, csrfToken);
        });

        $.ajax(contextPath + "/athr/0801/001/afterLogin", {
            type : "GET",
            data : "",
            dataType : "json"
        }).done(function(json) {
            searchNormalResultDisplay(json);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });
        return false;
    };

    athr.search = function() {

        $("#customerResult").remove();

        var csrfToken = $("meta[name='_csrf']").attr("content");

        $(document).ajaxSend(function(event, xhr, options) {
            xhr.setRequestHeader(csrfHeaderName, csrfToken);
        });

        $.ajax(contextPath + "/athr/0701/001/denyjson", {
            type : "GET",
            data : "",
            dataType : "json"
        }).done(function(json) {
            searchNormalResultDisplay(json);
        }).fail(function(xhr) {
            errorResultDisplay(xhr);
        });
        return false;
    };

    function searchNormalResultDisplay(json) {
        //document.write(json)
    }

    function errorResultDisplay(xhr) {

        setXMLHttpRequestResultError(xhr);
        setStatusCodeDom(xhr);
        setContentTypeDom(xhr);
        //        var json = xhr.responseJSON;
        //
        //        
        //        
        //        if (0 < json.errorResults.length) {
        //            setResultMessageError();
        //            var returnMessage = $("#returnMessage");
        //            for (var i = 0; i < json.errorResults.length; i++) {
        //                returnMessage.append("<li>" + escapeHTML(json.errorResults[i].message) + "</li>");
        //            }
        //        }
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

    function setContentTypeDom(xhr) {
        var contentType = $("#contentType");
        contentType.text("");
        contentType.append(escapeHTML(xhr.getResponseHeader("Content-Type")));
    }

    function escapeHTML(val) {
        return $('<div>').text(val).html();
    }

})(athr);
