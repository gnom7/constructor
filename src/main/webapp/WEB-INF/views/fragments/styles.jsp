<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link rel="shortcut icon" href="<C:url value="/static/favicon.ico"/>">
<link href="<c:url value="/webjars/jquery-ui/1.11.1/jquery-ui.min.css"/>" rel="stylesheet"/>
<link href="<c:url value="/webjars/jquery-ui/1.11.1/jquery-ui.structure.min.css"/>" rel="stylesheet"/>
<link href="<c:url value="/webjars/jquery-ui/1.11.1/jquery-ui.theme.min.css"/>" rel="stylesheet"/>
<link href="<c:url value="/static/css/constructor.css"/>" rel="stylesheet"/>
<c:choose>
    <c:when test="${not empty cookie.theme}">
        <link href="<c:url value="/webjars/${cookie.theme.value}/3.3.7/css/bootstrap.min.css"/>" rel="stylesheet"/>
    </c:when>
    <c:otherwise>
        <link href="<c:url value="/webjars/bootswatch-darkly/3.3.7/css/bootstrap.min.css"/>" rel="stylesheet"/>
    </c:otherwise>
</c:choose>
