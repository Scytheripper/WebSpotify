<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="mediaPane">
  <div class="col-xs-12">
    <div id="mediaInfo" class="col-xs-8">
      <div class="row">
        <a href="#">
          <h3 class="mediaName">Artists</h3>    
        </a>
      </div>
    </div>
  </div>
</div>
<div class="row" id="tableContainer">
  <table class="table playlistTable">
    <tr>
      <th>Name</th>
      <th></th>
    </tr>
    <c:forEach items="${allArtists}" var="Artist">
        <tr class="tableRow">
        <td><a href="#" onclick="viewArtist(${Artist.artistId})">${Artist.artistName}</a></td>
         <c:choose>
             <c:when test="${currentUser.accountType == 'Admin'}">
             <td>
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" >
                      Options
                      <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#" onclick="adminRemoveArtist(${Artist.artistId})">Remove From artists</a></li>
                    </ul>
                </div>
             </td>
             </c:when>
             <c:otherwise>
                 <td>show nothing</td>
             </c:otherwise>
         </c:choose>
                 </tr>
    </c:forEach> 
        
  </table>
</div>