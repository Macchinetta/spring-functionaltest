function ajaxTest() {
    var contextPath = $("meta[name='contextPath']").attr("content");
    var tokenValue = $("meta[name = '_TRANSACTION_TOKEN_INFO']")
            .attr("content");

    $("#outputData").append("");

    $.ajax(contextPath + "/dbsp/0301/007?ajax", {
        type : "GET",
        data : {
            "_TRANSACTION_TOKEN" : tokenValue
        },
        dataType : "json",
    }).done(function(json) {
        $("#outputData").text(json.firstName + " " + json.lastName);
    }).fail(
            function(XMLHttpRequest, textStatus, errorThrown) {
                $("#outputData").text(
                        "status:" + textStatus + ", error:" + XMLHttpRequest
                                + ", errorThrown:" + errorThrown);
            });

    return false;
};
