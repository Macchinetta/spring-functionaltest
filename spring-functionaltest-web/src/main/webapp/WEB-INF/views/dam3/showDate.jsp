<%@page import="java.time.LocalDate"%>
<div id="wrapper">
  <p>
    Date:<span id="getDateResult">${f:h(resultDate)}</span><br /> Class:<span
      id="getDateClassResult">${f:h(resultDate.getClass().getName())}</span><br /> Certification:<span
      id="getObjectCertification"><%=request.getAttribute("resultDate") instanceof LocalDate%></span>
  </p>
</div>