<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page isELIgnored="false" %>

<!doctype html>
<html lang="ja">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>JavaDevelop</title>
    <link rel="shortcut icon" href="<c:url value="/assets/favicon.ico" />">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap.min.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/bootstrap-icons.css" />">
    <link rel="stylesheet" href="<c:url value="/assets/styles.css" />">
</head>

<body>

    <%@ include file="./_headerNavbar.jsp" %>

    <main>
        <div class="container workspace">
            <h2>DB Work</h2>
            <hr/>

            <table class="table table-striped table-hover caption-top">
                <caption>DB Table DB_WORK</caption>
                <thead>
                    <tr>
                        <th></th>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>VERSION</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${dbWorks}" var="dbWork">
                        <tr>
                            <form action="./db_work_update" method="post">
                                <td>
                                    <i class="bi bi-person-fill"></i>
                                </td>
                                <td><c:out value="${dbWork.id}"/></td>
                                <td><input type="text" name="name" size="20" value="${dbWork.name}" required placeholder="Update Name" /></td>
                                <td><c:out value="${dbWork.version}"/></td>
                                <input type="hidden" name="id" value="${dbWork.id}">
                                <input type="hidden" name="version" value="${dbWork.version}">
                                <td><button><i class="bi bi-pencil-fill"></i></button></td>
                            </form>
                            <form action="./db_work_delete" method="post">
                                <input type="hidden" name="id" value="${dbWork.id}">
                                <input type="hidden" name="version" value="${dbWork.version}">
                                <td><button><i class="bi bi-trash-fill"></i></button></td>
                            </form>
                        </tr>
                    </c:forEach>
                    <tr>
                        <form action="./db_work_insert" method="post">
                            <td>
                                <i class="bi bi-person"></i>
                            </td>
                            <td></td>
                            <td><input type="text" name="name" size="20" required placeholder="Create Name" /></td>
                            <td></td>
                            <td><button><i class="bi bi-person-plus-fill"></i></button></td>
                            <td></td>
                        </form>
                    </tr>
                </tbody>
            </table>
            <hr/>
        </div>
    </main>

    <footer class="footer mt-auto fixed-bottom py-3 bg-secondary"></footer>

    <script src="<c:url value="/assets/bootstrap.bundle.min.js" />"></script>
    <script src="<c:url value="/assets/script.js" />"></script>
</body>

</html>