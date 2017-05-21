<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Simple wrapper tag" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>
<%@ attribute name="body" fragment="true" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>${title}</title>
    <%@ include file="/WEB-INF/views/fragments/styles.jsp" %>
    <%@ include file="/WEB-INF/views/fragments/scripts.jsp" %>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target="#bs-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/"/>">Constructor</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-navbar-collapse-1">
                <form class="navbar-form navbar-left" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>
                <ul class="nav navbar-nav navbar-right">
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="" id="themes" aria-hidden="true">
                            Themes
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-hidden="true">
                            <li>
                                <a id="selectDarkTheme" href="">Dark</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a id="selectBrightTheme" href="">Bright</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="<c:url value="/gallery"/>">Gallery</a>
                    </li>
                    <c:if test="${not empty pageContext.request.userPrincipal}">
                        <li>
                            <a href="<c:url value="/profile"/>">Profile</a>
                        </li>
                        <li>
                            <a href="<c:url value="/sites"/>">My sites</a>
                        </li>
                        <li>
                            <a href="<c:url value="/constructor"/>">Constructor</a>
                        </li>
                    </c:if>
                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="" id="i18n" aria-hidden="true">
                            <span class="glyphicon glyphicon-globe"></span>
                            <span class="caret"></span>
                        </a>
                        <ul class="dropdown-menu" aria-hidden="true">
                            <li>
                                <a href="<c:url value="/lang?eng"/>">English</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="<c:url value="/lang?ru"/>">Russian</a>
                            </li>
                        </ul>
                    </li>
                    <c:choose>
                        <c:when test="${not empty pageContext.request.userPrincipal}">
                            <li>
                                <a href="" onclick="$.post('<c:url value="/logout"/>')">Logout</a>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li>
                                <a href="<c:url value="/login"/>">Login</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
</head>
<body>
<div class="container">
    <jsp:invoke fragment="body"/>
</div>
<%@ include file="/WEB-INF/views/fragments/footer.jsp" %>
</body>
</html>
