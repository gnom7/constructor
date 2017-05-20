<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:wrapper title="Login">
    <jsp:attribute name="body">
        <br/>
        <div class="col-lg-6">
            <div class="well bs-component">
                <form class="form-horizontal" method="post" action="<c:url value="/login"/>">
                    <fieldset>
                        <legend>Login</legend>
                        <div class="form-group">
                            <label for="inputEmail" class="col-lg-2 control-label">Email</label>
                            <div class="col-lg-10">
                                <input class="form-control" name="email" id="inputEmail" type="text" placeholder="Email">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputPassword" class="col-lg-2 control-label">Password</label>
                            <div class="col-lg-10">
                                <input class="form-control" name="password" id="inputPassword" type="password" placeholder="Password">
                                <div class="checkbox">
                                    <label>
                                        <input type="checkbox" name="remember-me">
                                        Remember me
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-lg-10 col-lg-offset-2">
                                <button type="reset" class="btn btn-default" type="submit">Cancel</button>
                                <button typeof="submit" class="btn btn-primary" type="submit">Login</button>
                            </div>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    </fieldset>
                </form>
            </div>
        </div>
    </jsp:attribute>
</t:wrapper>
