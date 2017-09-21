<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <title>Просмотр книги</title>
    </head>
    <body>
        <jsp:include page="include/header.jsp"/>
        <jc:choose>
            <jc:when test="${book != null}">
                <form method="get" action="${pageContext.request.contextPath}/books/download">
                    <input id ="bookIdHide" name="id" value="${book[0][0].id}">
                    <table id="bookTable">
                        <caption>Просмотр книги</caption>
                        <tr>
                            <td rowspan="9">
                                <div id="bookImageBlock"></div>
                            </td>
                            <td>
                                <strong>Название книги:</strong> ${book[0][0].title}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Автор:</strong> ${book[0][1]}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Жанр:</strong> ${book[0][2]}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Рейтинг:</strong> ${book[0][0].averageRating}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Год издания: </strong>${book[0][0].year}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Издательство:</strong> ${book[0][0].publisher}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Количество страниц:</strong> ${book[0][0].pages}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>ISBN:</strong> ${book[0][0].isbn}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <strong>Описание: </strong>${book[0][0].description}
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <button type="submit" id="readButton">Читать книгу</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </jc:when>
            <jc:otherwise>
                <span>Нет данных о выбранной книге</span>
            </jc:otherwise>
        </jc:choose>
        <button id="backButton">Вернуться назад</button>
    </body>
    <jsp:include page="include/footer.jsp"/>
</html>

<script type="text/javascript">
    $("#backButton").click(
        function () {
            window.location.href = "${pageContext.request.contextPath}/"
    });

    $(document).ready(
        function () {
            var image = new Image();
            image.src = "data:image/png;base64," + ${image};
            $("#bookImageBlock").append(image)
        }
    )
</script>