<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/>

<footer>
    <div class="row">
        <div class="col-lg-12">
            <p><fmt:formatDate value="${now}" pattern="yyyy"/> &copy; Made by <a href="<c:url value="https://github.com/gnom7"/>" target="_blank">gnom_7</a></p>
        </div>
    </div>
</footer>
