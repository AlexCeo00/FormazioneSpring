<%@ page import="app.model.User" %><%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 18/09/2019
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <%
        if (session != null) {
            if (session.getAttribute("user") != null) {
                // String name = (String) session.getAttribute("nome");
                User usern = (User) session.getAttribute("user");
                request.setAttribute("usern",usern);
            } else {
                response.sendRedirect("index.jsp");
            }
        }
    %>
    <title>Problem Management Application:</title>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 90%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
    </style>
</head>
<body>
<%@include file="nav.jsp"%>
<h2 align="center">Management Problemi</h2>
<div align="center">
    <form:form method="post" action="/saveProbl"  modelAttribute="probl">
        <form:hidden path="id"/>
            <table border="1" cellpadding="5">
                <tr>
                    <th>Descrizione: </th>
                    <td>
                        <form:input path="descrizione" class="form-control" type="text" name="descrizione" size="45"/>
                    </td>
                </tr>
                <tr>
                    <th>data: </th>
                    <td>
                        <form:input path="dataprob" class="form-control" type="date" name="dataprobl" min="1900-1-31" /><br>
                    </td>
                </tr>
<%--                <tr>--%>
<%--                <th>Seleziona il numero di prenotazione da associare:  </th>--%>
<%--                <td>--%>
<%--                    <select  class="form-control" id="idprenotazione" name="idprenotazione">--%>
<%--                        <c:forEach var="pren" items="${requestScope.listPren}">--%>
<%--                            &lt;%&ndash;                        <% for(int i = 1; i < 40; i++) { %>&ndash;%&gt;--%>
<%--                            <option value="${pren.getId()}"--%>
<%--                            >Prenotazione Numero ${pren.getId()} &nbsp ${pren.getDettagli()} &nbsp ${pren.getDatap()}  </option>--%>
<%--                            &lt;%&ndash;                        <%}%>&ndash;%&gt;--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--            </tr>--%>
                <tr>
                    <th>Seleziona il numero di prenotazione da associare1:  </th>
                    <td>
                        <form:select path="pren.id" class="form-control" id="idprenotazione" name="idprenotazione">
                            <c:forEach var="pren" items="${requestScope.listPren}">
                                <%--                        <% for(int i = 1; i < 40; i++) { %>--%>
                                <form:option value="${pren.getId()}"
                                >Prenotazione Numero ${pren.getId()} &nbsp ${pren.getDettagli()} &nbsp ${pren.getDatap()}  </form:option>
                                <%--                        <%}%>--%>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input class="btn btn-primary" type="submit" value="Save" />
<%--                        <input name="id2" type="hidden" value= "${link}">--%>
<%--                        <c:url var="link" value="/saveProbl">--%>
<%--                            <c:param name="idut" value="${requestScope.idut}">--%>
<%--                            </c:param>--%>
<%--                        </c:url>--%>
                        <input name="id2" type="hidden" value="${requestScope.idut}">
                    </td>
                </tr>
            </table>
        </form:form>
        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>