<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Clock Display</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css" type="text/css" />
</head>

<body>
    <div id="wrapper">
        <a id="back" href="${pageContext.request.contextPath}">HOMEに戻る</a>
    </div>

    <hr />

    <h1 id="screenTitle">Clock Display</h1>

    <table border="1">
        <tr>
            <th>Label</th>
            <th>Value</th>
        </tr>
        <tr>
            <td>Base LocalDateTime</td>
            <td id="base">
                <javatime:format value="${baseDateTime}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>Database LocalDateTime</td>
            <td id="database">
                <javatime:format value="${targetDateTime}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
    </table>

    <br>

    <div>1</div>
    <table border="1">
        <tr>
            <th>Label</th>
            <th>Value</th>
        </tr>
        <tr>
            <td>fixedClock1</td>
            <td id="fixedLocalDateTime1_1">
                <javatime:format value="${fixedLocalDateTime1_1}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>fixedClock2</td>
            <td id="fixedLocalDateTime2_1">
                <javatime:format value="${fixedLocalDateTime2_1}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>tickClock1</td>
            <td id="tickLocalDateTime1_1">
                <javatime:format value="${tickLocalDateTime1_1}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>tickClock2</td>
            <td id="tickLocalDateTime2_1">
                <javatime:format value="${tickLocalDateTime2_1}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
    </table>

    <div>2</div>
    <table border="1">
        <tr>
            <th>Label</th>
            <th>Value</th>
        </tr>
        <tr>
            <td>fixedClock1</td>
            <td id="fixedLocalDateTime1_2">
                <javatime:format value="${fixedLocalDateTime1_2}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>fixedClock2</td>
            <td id="fixedLocalDateTime2_2">
                <javatime:format value="${fixedLocalDateTime2_2}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>tickClock1</td>
            <td id="tickLocalDateTime1_2">
                <javatime:format value="${tickLocalDateTime1_2}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>tickClock2</td>
            <td id="tickLocalDateTime2_2">
                <javatime:format value="${tickLocalDateTime2_2}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
    </table>

    <div>3</div>
    <table border="1">
        <tr>
            <th>Label</th>
            <th>Value</th>
        </tr>
        <tr>
            <td>fixedClock1</td>
            <td id="fixedLocalDateTime1_3">
                <javatime:format value="${fixedLocalDateTime1_3}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>fixedClock2</td>
            <td id="fixedLocalDateTime2_3">
                <javatime:format value="${fixedLocalDateTime2_3}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>tickClock1</td>
            <td id="tickLocalDateTime1_3">
                <javatime:format value="${tickLocalDateTime1_3}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
        <tr>
            <td>tickClock2</td>
            <td id="tickLocalDateTime2_3">
                <javatime:format value="${tickLocalDateTime2_3}" pattern="yyyy/MM/dd HH:mm:ss.SSS" />
            </td>
        </tr>
    </table>

    <hr />

    <jsp:include page="/WEB-INF/views/jsp/layout/footer.jsp" />
</body>

</html>