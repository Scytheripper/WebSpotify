<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
  <head>
    <title>Browse Overview</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap Core CSS-->
    <link href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.css" rel="stylesheet">
    <!-- Fonts CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!-- Custom Theme CSS -->
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/stylesheet.css">
    
    <link rel="icon" href="/resources/img/team0n3.png">
  </head>
  <body>
    <div>
      <%@include file="/resources/toolbars/top.jsp" %>
      

      <div id="main-section">
        <div id="leftTool">
          <%@include file="/resources/toolbars/left.jsp" %>
        </div>
        <div class="col-xs-10 col-xs-offset-2" id="center-pane">
              <!-- JQUERY LOADS COMPONENTS HERE -->
        </div>
        <!--
        <div class="container" style="text-align:center">
             <form class="form-artist" action="adminAddArtist" method="post" >
                <h2 >Add an Artist </h2>
                <div class="" >
                    <input type="text" id="artistName" name="artistName" placeholder="artistName" class="form-control" required autofocus>
                    <input type="text" id="popularity" name="popularity" placeholder="popularity" class="form-control" required autofocus>
                    <input type="text" id="imagePath" name="imagePath" placeholder="imagePath" class="form-control" required autofocus>
                     <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
                </div>
          </form>
        </div>
        -->
      <div id="lyricModal"class="modal fade" role="dialog">
          <div class="modal-dialog">
              <div id="lyricsHere"></div>
          </div>
      </div>
      </div>
      <div id="bottomTool">
        <%@include file="/resources/toolbars/bottom.jsp" %>
      </div>
    </div>

    <!-- JAVASCRIPT -->

    <!-- jQuery script -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>

    <!-- Bootstrap Javascript -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

    <!-- For including HTML Snippets -->
    <script src="${pageContext.request.contextPath}/resources/js/w3.js"></script>

    <!-- Main Page Script -->
    <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/js/admin.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/js/lyrics.js"></script>
    
    <script src="${pageContext.request.contextPath}/resources/js/playlist.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/user.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/song.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/artist.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/album.js"></script>

    <!-- Script to display images-->
    <script>
      $(function () {
        $(":file").change(function () {
          if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = imageIsLoaded;
            reader.readAsDataURL(this.files[0]);
          }
        });
      });
      function imageIsLoaded(e) {
        $('#playlist-image').attr('src', e.target.result);
      };
    /** got from http://jsfiddle.net/vacidesign/ja0tyj0f/**/
    </script>
  </body>
</html>
