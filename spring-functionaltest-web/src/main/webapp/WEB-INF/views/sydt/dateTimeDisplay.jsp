<div id="wrapper">
    <h1 id="screenTitle">Server Time</h1>
    
    <span>(1)${factory_name}.newDateTime() first</span><br/>
    <span id="jodaFactory_1"><joda:format value="${jodaFactory1}" pattern="yyyy-MM-dd HH:mm:ss.SSS" /></span><br/>
    <br/>
    <span>(2)new DateTime() first</span><br/>
    <span id="DateTime_1"><joda:format value="${DateTime1}" pattern="yyyy-MM-dd HH:mm:ss.SSS" /></span><br/>
    <br/>
    
    <span>(1)${factory_name}.newDateTime() second</span><br/>
    <span id="jodaFactory_2"><joda:format value="${jodaFactory2}" pattern="yyyy-MM-dd HH:mm:ss.SSS" /></span><br/>
    <br/>
    <span>(2)new DateTime() second</span><br/>
    <span id="DateTime_2"><joda:format value="${DateTime2}" pattern="yyyy-MM-dd HH:mm:ss.SSS" /></span><br/>
    <br/>

    <span>(1)${factory_name}.newDateTime() third</span><br/>
    <span id="jodaFactory_3"><joda:format value="${jodaFactory3}" pattern="yyyy-MM-dd HH:mm:ss.SSS" /></span><br/>
    <br/>
    <span>(2)new DateTime() third</span><br/>
    <span id="DateTime_3"><joda:format value="${DateTime3}" pattern="yyyy-MM-dd HH:mm:ss.SSS" /></span><br/>
</div>
    
