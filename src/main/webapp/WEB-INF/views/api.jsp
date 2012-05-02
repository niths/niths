<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>NITHS API</title>
    <style type="text/css">
      <%@include file='assets/stylesheets/spring-summary.css'%>
    </style>
  
    </head>
  <body>
    <div id="container">
    	<div id='request-mappings'>
    	<h1>NITHS API</h1>
    		
    	
           <c:forEach items="${api}" var="listing" varStatus="nr">
           
           <h2>${listing.path}</h2>
           
           ${nr.index == 0 ? '<p><strong>All resources supports basic CRUD actions out of the box</strong></p>
           	<p>Domains may override these methods and provide their own implementations. If so, the method is listed in the domain table</p>' : ''}
           
      		<h3>Request Mappings</h3>
      		<table>
        		<tr>
          			<th>Path</th>
          			<th>Method</th>
          			<th>Header</th>
          			<th>Message</th>
          			<th>Code</th>
          			<th>Auth</th>
        		</tr>
        		<c:forEach items="${listing.methods}" var="method">
           		<tr>
          			<td>${method.path}</td>
          			<td>${method.method}</td>
          			<td>${method.headers}</td>
          			<td>${method.message}</td>
          			<td>${method.code}</td>
          			<td>${method.authorization}</td>
       		 	</tr>
       		 	</c:forEach>
       		 </table>
       		 
           </c:forEach>
      	
      	
	  	</div>
    	<div id="footer">&copy; NITHs 2012</div>
  	</div>
	</body>
</html>