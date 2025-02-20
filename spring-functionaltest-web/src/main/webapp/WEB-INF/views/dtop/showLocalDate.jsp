<div id="wrapper">
    <p>
        LocalDate:<span id="getDateResult"> <joda:format pattern="yyyy/MM/dd" value="${resultDate}" /></span><br />
        Style:<span id="getResultStyle"><joda:format value="${resultDate}" style="S-" /></span><br />
        Class:<span id="getDateClassResult">${f:h(resultDate.getClass().getName())}</span>
    </p>
</div>
