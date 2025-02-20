<div id="wrapper">
    <h1 id="screenTitle">messagesPanelタグの属性変更</h1>
    <div class="container">
        <div id="panelElement">
            <h1>panelElementの変更</h1>
            <t:messagesPanel messagesAttributeName="messages_panelElement" panelElement="span" />
        </div>
        <div id="panelClassName_panelTypeClassPrefix">
            <h1>panelClassName,panelTypeClassPrefixの変更</h1>
            <t:messagesPanel messagesAttributeName="messages_classs" panelClassName="" panelTypeClassPrefix="" />
        </div>
        <div id="outerElement_innerElement">
            <h1>outerElement,innerElementの変更</h1>
            <t:messagesPanel messagesAttributeName="messages_outer_inner_element" outerElement="" innerElement="span" />
        </div>
        <spring:message var="informationMessage" code="i.ex.od.0001" />
        <div id="disableHtmlEscape_true">
            <h1>disableHtmlEscape=true</h1>
            <t:messagesPanel messagesAttributeName="informationMessage" messagesType="info" disableHtmlEscape="true" />
        </div>
        <div id="disableHtmlEscape_false">
            <h1>disableHtmlEscape=false(default)</h1>
            <t:messagesPanel messagesAttributeName="informationMessage" messagesType="info" />
        </div>
    </div>
</div>