<div id="wrapper">
  <p>
    DateMidnight:<span id="getDateResult"> <joda:format pattern="yyyy/MM/dd HH:mm:ss.SSS ZZ"
        value="${resultDate}" /></span><br /> Class:<span id="getDateClassResult">${f:h(resultDate.getClass().getName())}</span>
  </p>
</div>