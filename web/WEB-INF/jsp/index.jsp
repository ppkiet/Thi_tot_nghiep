<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <p><i>To display a different welcome page for this project, modify</i>
            <tt>index.jsp</tt> <i>, or create your own welcome page then change
                the redirection in</i> <tt>redirect.jsp</tt> <i>to point to the new
                welcome page and also update the welcome-file setting in</i>
            <tt>web.xml</tt>.</p>

        <form action="insert.htm" method="POST" id="myform" enctype="multipart/form-data"> 
            <h3 id="loi"></h3>
            <table>
                <tr>
                    <td>ID</td>
                    <td><input type="text" name="id" value="${emp.id}" readonly id="id"/></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" value="${emp.name}" id="name"/></td>
                </tr>
                <tr>
                    <td>Salary</td>
                    <td><input type="number" name="sal" value="${emp.salary}" id="salary"/></td>
                </tr>
                <tr>
                    <td>City</td>
                    <td>
                        <select name="city">

                            <c:forEach items="${cityl}" var="rows">
                                <option value="${rows.id}" <c:if test="${emp.city == rows.id}">selected</c:if>>${rows.city}</option>
                            </c:forEach>

                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Phone</td>
                    <td><input type="text" name="phone" value="${emp.phone}" id="phone"/></td>
                </tr>
                <tr>
                    <td>Image</td>
                    <td><input type="file" name="image" value="${emp.image}" id="image"/></td>
                    <td><input type="text" name="imagebk" value="${emp.image}"/></td>
                </tr>
                <tr>
                    <td><input type="submit" name="action" value="insert" onclick="return validate()"/></td>
                    <td><input type="submit" name="action" value="update"/></td>
                </tr>
            </table>
        </form>

        <table border="1px">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Salary</th>
                <th>City</th>
                <th>Phone</th>
                <th>Image</th>
                <th>Action</th>
            </tr>
            <c:forEach items="${empl}" var="rows">
                <form action="delete.htm">
                    <input type="hidden" value="${rows.id}" name="id"/>
                    <tr>
                        <td>${rows.id}</td>
                        <td>${rows.name}</td>
                        <td>${rows.salary}</td>
                        <td>${rows.city}</td>
                        <td>${rows.phone}</td>
                        <td><img src="${rows.image}" width="150" /> </td>
                        <td>
                            <a href="edit.htm?id=${rows.id}">Edit</a>
                            <input type="submit" value="Delete"/>
                        </td>
                    </tr>
                </form>
            </c:forEach>

        </table>
    </body>
</html>

<script>
    function validate() {
//        var nameformat = /^[A-Za-z]+$/;
//        var numberformat = /-?\\d+?/;
//        && phone.match(numberformat) && salary.match(numberformat)
        document.getElementById('loi').innerHTML = '';
        
        var name = document.getElementById('name').value.trim();
        var phone = document.getElementById('phone').value.trim();
        var salary = document.getElementById('salary').value.trim();
        var image = document.getElementById('image').value.trim();
        if (name.length > 0 && phone.length > 0 && salary.length > 0 && image.length > 0){
            return true;
        } else {
            document.getElementById('loi').innerHTML = 'Chua nhap xong du lieu';
            return false;
        }
    }
</script>
