    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Result Page</title>
    </head>
    <body>
        <h2>Result Page</h2>

        <p>Student ID: <%= request.getAttribute("id") %></p>
        <p>Course Marks:</p>
        <ul>
            <c:forEach items="${courseMarks}" var="entry">
                <li>${entry.key}: ${entry.value}</li>
            </c:forEach>
        </ul>
        <p>Highest Mark: <%= request.getAttribute("highestMark") %></p>
        <p>Minimum Mark: <%= request.getAttribute("minimumMark") %></p>
        <p>Average Mark: <%= request.getAttribute("averageMark") %></p>
        <p>Median Mark: <%= request.getAttribute("medianMark") %></p>
    </body>
    </html>
