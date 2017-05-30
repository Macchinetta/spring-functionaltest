<div id="wrapper">
    <p>
        LocalTime:<span id="getDateResult"> <joda:format
                pattern="HH:mm:ss" value="${resultDate}" /></span><br />
        Style:<span id="getResultStyle"><joda:format
                value="${resultDate}" style="-M" /></span><br />
        Class:<span
            id="getDateClassResult">${f:h(resultDate.getClass().getName())}</span>
    </p>
</div>