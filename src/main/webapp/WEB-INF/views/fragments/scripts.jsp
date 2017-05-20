<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="<c:url value="/webjars/jquery/1.11.1/jquery.min.js"/>"></script>
<script src="<c:url value="/webjars/jquery-ui/1.11.1/jquery-ui.min.js"/>"></script>
<script src="<c:url value="/static/js/constructor.js"/>"></script>
<c:choose>
    <c:when test="${not empty cookie.theme}">
        <script src="<c:url value="/webjars/${cookie.theme.value}/3.3.7/js/bootstrap.min.js"/>"></script>
    </c:when>
    <c:otherwise>
        <script src="<c:url value="/webjars/bootswatch-darkly/3.3.7/js/bootstrap.min.js"/>"></script>
    </c:otherwise>
</c:choose>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
