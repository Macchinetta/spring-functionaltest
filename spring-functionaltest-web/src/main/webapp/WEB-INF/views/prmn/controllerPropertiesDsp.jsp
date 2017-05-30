<div id="wrapper">

  <h1 id="screenTitle">controllerでの取得プロパティの表示</h1>

  <fieldset>
    <table
      class="table table-bordered table-condensed col-xs-5 col-sm-5 col-md-5 col-g-5">
      <tr>
        <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">uploadTitle</th>
        <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span
          id="uploadTitle"> ${f:h(uploadTitle)} </span></td>
      </tr>
      <tr>
        <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">uploadDir</th>
        <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span
          id="uploadDir"> ${f:h(uploadDir)} </span></td>
      </tr>
      <tr>
        <th class="col col-xs-2 col-sm-2 col-md-2 col-g-2">maxUpdateFileNum</th>
        <td class="col col-xs-3 col-sm-3 col-md-3 col-g-3"><span
          id="maxUpdateFileNum"> ${f:h(maxUpdateFileNum)} </span></td>
      </tr>
    </table>
  </fieldset>

</div>
