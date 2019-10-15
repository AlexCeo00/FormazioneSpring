<%@ page import="app.model.User" %><%--
  Created by IntelliJ IDEA.
  User: SI2001
  Date: 13/09/2019
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>Pagina Parco Auto:</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 87%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: center;
            padding: 8px;
        }
    </style>
    <script>
        function myFunction() {
            // Declare variables
            var input, filter, table, tr, td, i, txtValue, v1;
            //v1 = document.getElementById("b1");
            v1=document.querySelector('input[name="colum"]:checked').value;
            input = document.getElementById("myInput");
            filter = input.value.toUpperCase();
            table = document.getElementById("myTable");
            tr = table.getElementsByTagName("tr");

            // Loop through all table rows, and hide those who don't match the search query
            for (i = 0; i < tr.length; i++) {
                td = tr[i].getElementsByTagName("td")[v1];
                if (td) {
                    txtValue = td.textContent || td.innerText;
                    if (txtValue.toUpperCase().indexOf(filter) > -1) {
                        tr[i].style.display = "";
                    } else {
                        tr[i].style.display = "none";
                    }
                }
            }
        }
    </script>
</head>
<body><%@include file="nav.jsp"%>
<div style="text-align: center;">
    <h3>Auto Management</h3>
    <h4>
        <c:if test="${usern.tipologia == 'Admin'}">
            <a href="/newAuto">Aggiungi Nuove Auto</a>
        </c:if>
    </h4>
</div>
<div align="center">
    <label class="radio-inline">
        <input type="radio" name="colum" id="b1" value="0"> Id<br>
    </label>
    <label class="radio-inline">
        <input type="radio" name="colum" id="b1" value="1"> Nome<br>
    </label>
    <label class="radio-inline">
        <input type="radio" name="colum" id="b1" value="2"> Tipologia <br>
    </label>
    <label class="radio-inline">
        <input type="radio" name="colum" id="b1" value="4"> Prezzo <br>
    </label>
    <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Cerca per Selezione">
    <table border="1" cellpadding="5" id="myTable">
        <caption><h2>Lista Auto</h2></caption>
        <tr class="header">
            <th>ID</th>
            <th>Nome</th>
            <th>Tipo</th>
            <th>Targa</th>
            <th>Prezzo</th>
            <th>Disponibilita</th>
            <c:if test="${usern.tipologia == 'Admin'}">
                <th>Azioni</th>
            </c:if>
        </tr>
        <c:forEach var="auto" items="${requestScope.listAuto}">

            <c:url var="linkModifica" value="/editauto">
                <c:param name="id" value='${auto.getId()}'>
                </c:param>
            </c:url>

            <c:url var="linkCanc" value="/deleteauto">
                <c:param name="id" value='${auto.getId()}'>
                </c:param>
            </c:url>
            <tr>
                <td><c:out value="${auto.getId()}" /></td>
                <td><c:out value="${auto.getNome()}" /></td>
                <td><c:out value="${auto.getTipo()}" /></td>
                <td><c:out value="${auto.getTarga()}" /></td>
                <td><c:out value="${auto.getPrezzo()}" /> &euro;</td>
                <td><c:out value="${auto.getDispon()}" /></td>
                <c:if test="${usern.tipologia == 'Admin'}">
                    <td>
                        <a class="btn btn-primary" href="${linkModifica}">Modifica</a>
                        &nbsp;&nbsp;
                        <a class="btn btn-outline-danger" onclick="return confirm('Sei sicuro di voler eliminare questa auto?');"href="${linkCanc}">Elimina</a>
                        &nbsp;&nbsp;

                    </td>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
