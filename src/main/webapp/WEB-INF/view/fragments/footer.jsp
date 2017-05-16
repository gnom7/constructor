<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="now" class="java.util.Date"/>

<footer>
    <fmt:formatDate value="${now}" pattern="yyyy"/> &copy; K
</footer>