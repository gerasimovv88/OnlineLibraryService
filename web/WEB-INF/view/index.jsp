<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="jc" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/index.css" />
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <title>Online book's library</title>
    </head>

    <jsp:include page="include/header.jsp"/>
    <body>
        <div id="content">
            <div id="genresBlock">
                <table id="genresTable">
                    <caption>Жанры</caption>
                    <jc:if test="${not empty genres}">
                        <jc:forEach var="genre" items="${genres}">
                            <tr>
                                <td class="links genreRow">
                                    <p class="genreText">${genre.title}</p>
                                </td>
                            </tr>
                        </jc:forEach>
                    </jc:if>
                </table>
            </div>

            <div id="centerBlock">
                <div id="searchElements">
                    <input type="search" id="searchField">
                    <select>
                        <option>По названию</option>
                        <option>По автору</option>
                        <option>По жанру</option>
                    </select>
                    <button id="searchButton">Найти</button>
                </div>
                <div id="books">
                    <table id="booksTable">
                        <tr>
                            <p id="countBook"></p>
                        </tr>
                    </table>
                </div>
            </div>

            <div id="authorsBlock">
                <table id="authorsTable">
                    <caption>Авторы</caption>
                    <jc:if test="${not empty authors}">
                        <jc:forEach var="author" items="${authors}">
                            <tr>
                                <td class="links authorRow">
                                    <p class="authorText">${author.fio}</p>
                                </td>
                            </tr>
                        </jc:forEach>
                    </jc:if>
                </table>
            </div>
        </div>
    </body>
    <jsp:include page="include/footer.jsp"/>
</html>

<script type="text/javascript">
//    $(document).ready(
//        function () {
//            alert("Добро пожаловать в онлайн библиотеку!")
//    });

    $("#searchButton").click(
        function searchBooks() {
            cleanTable("#booksTable");
            var searchText = $("#searchField").val();
            var searchParam = $("#searchElements").find(":selected").text();

            var author = "";
            var genre = "";
            var title = "";
            if (searchParam.indexOf("автор") !== -1) {  // if string contains main unique part
                author = searchText;
            }
            if (searchParam.indexOf("жанр") !== -1) {
                genre = searchText;
            }
            if (searchParam.indexOf("назван") !== -1) {
                title = searchText;
            }

            $.ajax({
                url: "${pageContext.request.contextPath}/books/search",
                type: "GET",
                async: true,
                dataType: 'json',
                data: {
                    title: title,
                    author: author,
                    genre: genre
                },
                success: function (books) {
                    if (books !== null && books.length > 0) {
                        $("#countBook").text("Найдено книг: " + books.length);
                        $.each(books, function (i, book) {
                            var img = document.createElement('img');
                            img.src = "data:image/png;base64," + book[0].image;
                            img.width = 150;
                            img.height = 200;

                            img.onclick = function () {
                                window.location.href = "${pageContext.request.contextPath}/get?id=" + book[0].id;
                            };

                            var description = $('<td>').html(
                                'Название: ' + book[0].title + "<br>" +
                                'Автор: ' + book[1] + "<br>" +
                                'Жанр: ' + book[2] + "<br>" +
                                "</td>");
                            var imageRow = $('<tr>').append(img);
                            imageRow.append(description);
                            $("#booksTable").append(imageRow);
                        });
                    } else {
                        $("#countBook").text("Книг не найдено")
                    }
                },
                error: function () {
                    alert("Ошибка при поиске книг :(")
                }
            })
        }
    );

    function cleanTable(id) {
        $(id + " tr").remove();
    }

    $(".genreText").click(
        function getAllBooksOfSelectedGenre() {
            cleanTable("#booksTable");
            var genre = $(this).text();
            $.ajax({
                url: "${pageContext.request.contextPath}/books/search",
                type: "GET",
                async: true,
                dataType: 'json',
                data: {
                    genre: genre
                },
                success: function (books) {
                    if (books !== null && books.length > 0) {
                        $("#countBook").text("Найдено книг: " + books.length);
                        $.each(books, function (i, book) {
                            var img = document.createElement('img');
                            img.src = "data:image/png;base64," + book[0].image;
                            img.width = 150;
                            img.height = 200;

                            img.onclick = function () {
                                window.location.href = "${pageContext.request.contextPath}/get?id=" + book[0].id;
                            };

                            var description = $('<td>').html(
                                'Название: ' + book[0].title + "<br>" +
                                'Автор: ' + book[1] + "<br>" +
                                'Жанр: ' + book[2] + "<br>" +
                                "</td>");
                            var imageRow = $('<tr>').append(img);
                            imageRow.append(description);
                            $("#booksTable").append(imageRow);

                        });
                    } else {
                        $("#countBook").text("Книг не найдено")
                    }
                },
                error: function () {
                    alert("Ошибка при получении книг по жанру :(")
                }
            })
        }
    );


    $(".authorText").click(
        function getAllBooksOfSelectedAuthor() {
            cleanTable("#booksTable");
            var author = $(this).text();
            $.ajax({
                url: "${pageContext.request.contextPath}/books/search",
                type: "GET",
                async: true,
                dataType: 'json',
                data: {
                    author: author
                },
                success: function (books) {
                    if (books !== null && books.length > 0) {
                        $("#countBook").text("Найдено книг: " + books.length);
                        $.each(books, function (i, book) {
                            var img = document.createElement('img');
                            img.src = "data:image/png;base64," + book[0].image;
                            img.width = 150;
                            img.height = 200;

                            img.onclick = function () {
                                window.location.href = "${pageContext.request.contextPath}/get?id=" + book[0].id;
                            };

                            var description = $('<td>').html(
                                      'Название: ' + book[0].title + "<br>" +
                                        'Автор: ' + book[1] + "<br>" +
                                        'Жанр: ' + book[2] + "<br>" +
                                    "</td>");
                            var imageRow = $('<tr>').append(img);
                            imageRow.append(description);
                            $("#booksTable").append(imageRow);

                        });
                    } else {
                        $("#countBook").text("Книг не найдено")
                    }
                },
                error: function () {
                    alert("Ошибка при получении книг по автору :(")
                }
            })
        }
    )

</script>
