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
                        data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="<c:url value="/"/>">Constructor</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li>
                        <a href="<c:url value="/gallery"/>">Gallery</a>
                    </li>
                    <li>
                        <a href="<c:url value="/profile"/>">Profile</a>
                    </li>
                    <li>
                        <a href="<c:url value="/sites"/>">My sites</a>
                    </li>
                    <li>
                        <a href="" onclick="$.post('<c:url value="/logout"/>')">Logout</a>
                    </li>
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
