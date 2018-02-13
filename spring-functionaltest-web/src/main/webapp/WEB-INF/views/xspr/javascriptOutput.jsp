<script type="text/javascript">
function setAlert() {
       var aaa = '${f:js(warnCode)}';
       alert(aaa);
}
</script>

<input id="write" type="button" value="write" class="mainbtn" onclick="setAlert()">