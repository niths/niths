<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8"%> 
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Admin panel</title>
    <style type="text/css">
      <%@include file='assets/stylesheets/admin.css'%>
    </style>
    <script type="text/javascript">
      <%@include file='assets/javascripts/jquery.js'%>
      <%@include file='assets/javascripts/admin.js'%>
    </script>
    </head>
  <body>
    <section id="container">
      <h1>NITHs Admin Panel</h1>
      <section id="content">
        <div id="searcher">
          <form method="get">
            <input type="radio" value="firstName" name="columnName" checked="checked" id="r1"><label for="r1">Fornavn</label>
            <input type="radio" value="lastName" name="columnName"  id="r2"> <label for="r2">Etternavn</label>
            <input type="radio" value="email" name="columnName"  id="r3"> <label for="r3">Email</label>
            <input type="text" name="query" id= "query" placeholder="Søkeord..."/> 
            <input type="submit" value="Søk"/>
          </form>
        </div>
    <hr/>
      
      <c:if test="${exception != null}" >
        is null yeah
      </c:if>
        <c:forEach items="${studentList}" var="student">
          <form method="post">
            <input type="hidden" value="${student.id}" id=studentId
              name="studentId"> <b> <c:out value="${student.id}" />
              <c:out value="${student.firstName}" /> <c:out
                value="${student.lastName}" /> <c:out value="${student.email}" />
            </b> <br />
            
            <c:choose>
              <c:when test="${!student.roles.isEmpty()}">
              
   
                <c:forEach items="${listOfRoles}" var="roles">
                
                  <c:set value="false" var="temp" />
                  
                  <c:forEach items="${student.roles}" var="studRoles">
                    <c:choose>
                      <c:when test="${temp == 'true'}">
                        <!-- do nothing -->
                      </c:when>
                      
                      <c:otherwise>
                        <c:if test="${studRoles.id == roles.id}">
                          <input type="checkbox" name=checkedRoles checked="checked"
                            id="checkedRoles" value="${roles.id}">
                          <c:out value="${roles.trimedRoleName}" />
                          <c:set value="true" var="temp" />
                        </c:if>
                      </c:otherwise>
                    </c:choose>
                  </c:forEach>
                  <c:if test="${temp == 'false'}">
                    <input type="checkbox" name=checkedRoles id="checkedRoles"
                      value="${roles.id}">
                    <c:out value="${roles.trimedRoleName}" />
                  </c:if>
        
                </c:forEach>
              
              </c:when>
              <c:otherwise>
              
                <c:forEach items="${listOfRoles}" var="roles">
                  <input type="checkbox" name=checkedRoles id="checkedRoles"
                    value="${roles.id}">
                  <c:out value="${roles.trimedRoleName}" />
                </c:forEach>
                
              </c:otherwise>
            </c:choose>
            <input type="submit" value="Oppdater Roller" />
          </form>
          
          <form action="delete" method="post">
            <input type="hidden" value="${student.id}" name="studId" /> <input
              type="submit" value="Slett bruker">
          </form>
          <br />
        </c:forEach>
      </section>
    <br style="clear: both" />
    <div id="footer">&copy; NITHs 2012</div>
  </section>
</body>
</html>

