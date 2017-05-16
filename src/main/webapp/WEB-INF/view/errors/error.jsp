<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Error">
    <jsp:attribute name="body">
        <h1>Error Page</h1>
<p>Application has encountered an error. Please contact support on ...</p>

<!--
Failed URL: ${url}
        Exception:  ${exception.message}
        <c:forEach items="${exception.stackTrace}" var="ste">    <c:out value="${ste}"/>
</c:forEach>
-->
    </jsp:attribute>
</t:wrapper>
