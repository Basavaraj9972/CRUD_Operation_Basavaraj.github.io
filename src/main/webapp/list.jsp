<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.tap.model.Students,java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">
<style>
body {
    font-family: Arial, sans-serif;
    background-color: #e0f7fa;
    margin: 0;
    padding: 20px;
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 100vh;
}

table {
    border-collapse: collapse;
    width: 80%;
    margin: 20px 0;
    background-color: #fff;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

table th, table td {
    border: 1px solid #ddd;
    padding: 12px;
    text-align: center;
}

table th {
    background-color: #4CAF50;
    color: white;
}

table tr:nth-child(even) {
    background-color: #f2f2f2;
}

table tr:hover {
    background-color: #ddd;
}

.button-container {
    margin-top: 20px;
    display: flex;
    align-items: center;
    gap: 10px;
}

.button {
    padding: 10px 20px;
    margin: 5px;
    border: none;
    color: white;
    cursor: pointer;
    font-size: 16px;
}

input[type="submit"]{
	padding: 8px 20px;
    margin: 5px;
    border: none;
    color: white;
    background-color:blue;
    cursor: pointer;
    font-size: 15px;
}
.button-add {
    background-color: #4CAF50;
}

.button-update {
    background-color: #FF69B4;
}

.button-delete {
    background-color: #F44336;
}

.button:hover {
    opacity: 0.8;
}

.search-container {
    position: relative;
    display: flex;
    align-items: center;
}

.search-container select {
    padding: 10px;
    margin-right: 10px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #fff;
    color: #333;
    font-size: 16px;
}

.search-container input[type="text"] {
    padding: 10px;
    padding-left: 35px; /* Add padding to leave space for the icon */
    border: 1px solid #ccc;
    border-radius: 5px;
    width: 200px;
}

.search-container .fas {
    position: absolute;
    left: 102px;
    color: #333;
}
#searchIcon {
    margin-top: -8px;
}
.error {
    color: red;
    font-size: 14px;
    margin-top: 10px;
}
</style>
<script>

function validateForm() {
    var select = document.forms["searchForm"]["Column_data"].value;
    var input = document.forms["searchForm"]["studentInformation"].value;
    var errorMessage = document.getElementById("error-message");
    

    if (input === "") {
        errorMessage.textContent = "Search input cannot be empty.";
        return false;
    }

    if ((select === "id" || select === "age") && isNaN(input)) {
        errorMessage.textContent = "Please enter a valid number for " + select + ".";
        return false;
    }

    if ((select === "name" || select === "email") && !isNaN(input)) {
        errorMessage.textContent = "Please enter a valid text for " + select + ".";
        return false;
    }

    errorMessage.textContent = "";
    return true;
}
</script>
</head>
<body>
<% List<Students> allStudents = (List<Students>)session.getAttribute("allStudents"); %>
<table>
    <tr>
        <th>Id</th>
        <th>Student Name</th>
        <th>Student Email</th>
        <th>Student Age</th>
    </tr>
    <%
    for (Students s : allStudents) {
    %>
    <tr>
        <td><%= s.getId() %></td>
        <td><%= s.getName() %></td>
        <td><%= s.getEmail() %></td>
        <td><%= s.getAge() %></td>
    </tr>
    <%
    }
    %>
</table>
<div class="button-container">
<form name="searchForm" action="ReadServlet" method="post" onsubmit="return validateForm()">
    <div class="search-container">
        <select name="Column_data">
            <option value="id">Id</option>
            <option value="name">Name</option>
            <option value="email">Email</option>
            <option value="age">Age</option>
        </select>
        <input type="text" name="studentInformation" placeholder="Enter Student Information" required>
        <i id="searchIcon" class="fas fa-search"></i> 
        <input type="submit" value="Search">
    </div>
    <div id="error-message" class="error"></div>
</form>
    <a href="add.jsp"><button class="button button-add">Add</button></a>
    <a href="update.jsp"><button class="button button-update">Update</button></a>
    <a href="delete.jsp"><button class="button button-delete">Delete</button></a>
</div>
<% String msg = null;
	msg = (String)session.getAttribute("msg");
	if(msg != null && msg.equals("Invalid Student Id")){ %>
		<script> alert("Invalid Student Id") </script>
<% 
	
	}else if(msg != null && msg.equals("Invalid Student Name")){ %>
		<script> alert("Invalid Student Name") </script>
<% }
	else if(msg != null && msg.equals("Invalid Student Email")){ %>
		<script> alert("Invalid Student Email") </script>
<% } 
	session.setAttribute("msg", null);
	session.removeAttribute(msg);
	
%>

</body>
</html>
