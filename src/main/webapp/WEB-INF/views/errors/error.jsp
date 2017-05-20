<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Error">
    <jsp:attribute name="body">
        <h2>Application Error, please contact support.</h2>

        <h3>Debug Information:</h3>

        Requested URL= ${url}<br><br>

        Exception= ${exception.message}<br><br>

        <strong>Exception Stack Trace</strong><br>
        <c:forEach items="${exception.stackTrace}" var="ste">
        	${ste}
        </c:forEach>

    </jsp:attribute>
</t:wrapper>