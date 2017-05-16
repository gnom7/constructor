<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Login">
    <jsp:attribute name="body">
        <br/>
        <link href='http://fonts.googleapis.com/css?family=Oswald' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<div class="wrapper">
    <h1>Register For An Account</h1>
    <p>To sign-up for a free basic account please provide us with some basic information using
        the contact form below. Please use valid credentials.</p>
    <form class="form" method="post" action="<c:url value="/register"/>">
        <input type="text" class="name" placeholder="Name">
        <div>
            <p class="name-help">Please enter your first and last name.</p>
        </div>
        <input type="email" class="email" placeholder="Email">
        <div>
            <p class="email-help">Please enter your current email address.</p>
        </div>
        <input type="submit" class="submit" value="Register">
    </form>
</div>
<p class="optimize">
    Optimized for Chrome & Firefox!
</p>
        <%--<br/><br/><br/>--%>
        <%--<form class="col-xs-offset-4 col-xs-4" method="post" action="<c:url value="/register"/>">--%>
            <%--<div class="form-group">--%>
                <%--<label>Email: <input class="form-control" name="email" type="text"></label>--%>
                <%--<label>Password: <input class="form-control" name="password" type="password"></label>--%>
                <%--<label>Confirm password: <input class="form-control" name="password" type="password"></label>--%>
            <%--</div>--%>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
            <%--<input class="btn btn-default" type="submit" value="Login">--%>
        <%--</form>--%>
    </jsp:attribute>
</t:wrapper>
