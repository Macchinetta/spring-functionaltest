$(function() {
    /**
     * 単一ファイル用
     */
    $("#browseFile").on("click", function() {
        $("#multipartFile").click();
    });
    $("#selectedFile").on("click", function() {
        $("#multipartFile").click();
    });
    $("#multipartFile").change(function() {
        $("#selectedFile").val($(this).val());
    });

    /**
     * HTML5複数ファイル用
     */
    $("#browseFiles").on("click", function() {
        $("#multipartFiles").click();
    });
    $("#selectedFiles").on("click", function() {
        $("#multipartFiles").click();
    });
    $("#multipartFiles").change(function() {
        $("#selectedFiles").val($(this).val());
    });

    /**
     * 複数ファイル用
     */
    $("#browseFiles0").on("click", function() {
        $("#uploadForms0\\.multipartFile").click();
    });
    $("#selectedFiles0").on("click", function() {
        $("#uploadForms0\\.multipartFile").click();
    });
    $("#uploadForms0\\.multipartFile").change(function() {
        $("#selectedFiles0").val($(this).val());
    });

    $("#browseFiles1").on("click", function() {
        $("#uploadForms1\\.multipartFile").click();
    });
    $("#selectedFiles1").on("click", function() {
        $("#uploadForms1\\.multipartFile").click();
    });
    $("#uploadForms1\\.multipartFile").change(function() {
        $("#selectedFiles1").val($(this).val());
    });

    /**
     * 説明が未入力だった場合の表示領域の制御
     */
    var $descriptionText = $("#descriptionText");
    if ($descriptionText.text() === "") {
        $descriptionText.removeClass("display-item");
    }

});
