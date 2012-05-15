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
    <script type="text/javascript" charset="UTF-8">
     <%@include file="assets/javascripts/jquery-1.7.2.min.js"%>
    </script>
    <script type="text/javascript">
        <%@include file='assets/javascripts/admin.js'%>
    </script>
    </head>
  <body>
    <div id="container">
      <h1>NITHs adminpanel</h1>
      <section id="content">
        <div id="searcher">
          <form id="search-form" method="get" action="#">
            <input type="radio" value="firstName" name="column"
                checked="checked" id="r1"><label for="r1">Fornavn</label>
            <input type="radio" value="lastName" name="column"
                id="r2"> <label for="r2">Etternavn</label>
            <input type="radio" value="email" name="column"
                id="r3"> <label for="r3">Email</label>
            <input type="text" name="query" id= "query"
                placeholder="Søkeord..."/> 
            <input type="submit" value="Søk"/>
          </form>
        </div>
        <hr/>
        <div>
          <ul id="students">
          </ul>
        </div>
      </section>
    <br style="clear: both" />
    <div id="footer">&copy; NITHs 2012</div>
  </div>
</body>
</html>

