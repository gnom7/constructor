<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Login">
    <jsp:attribute name="body">
        <br/>
        <form class="col-xs-offset-4 col-xs-4" method="post" action="<c:url value="/login"/>">
            <div class="form-group">
                <input name="email" type="text" placeholder="Email">
                <input name="password" type="password" placeholder="Password">
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <input class="btn btn-default" type="submit" value="Login">
        </form>
    </jsp:attribute>
</t:wrapper>
