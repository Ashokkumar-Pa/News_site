<%@ page import="com.newssite.model.*" %>
<%
        java.util.List<Post> posts = (java.util.List<Post>) request.getAttribute("postsList");
        System.out.println("________.................In Post.jsp file.................________");
%>
<html lang="en" op="news">
    <head>
       <meta name="referrer" content="origin">
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
       <title>Hacker News</title>
  <style>item?id=4
  body  { font-family:Verdana, Geneva, sans-serif; font-size:10pt; color:#828282; }
  td    { font-family:Verdana, Geneva, sans-serif; font-size:10pt; color:#828282; }
  a:link    { color:#000000; text-decoration:none; }
  a:visited { color:#828282; text-decoration:none; }
  .title   { font-family:Verdana, Geneva, sans-serif; font-size: 10pt; color:#828282; overflow:hidden; }
  .subtext { font-family:Verdana, Geneva, sans-serif; font-size:  7pt; color:#828282; }
  .comhead { font-family:Verdana, Geneva, sans-serif; font-size:  8pt; color:#828282; }
  .subtext a:hover { text-decoration:underline; }
  .comhead a:hover { text-decoration:underline; }
  </style>
    </head>
    <body>
       <center>
          <table id="hnmain" border="0" cellpadding="0" cellspacing="0" width="85%" bgcolor="#f6f6ef">
             <tr>
                <td bgcolor="#ff6600">
                   <table border="0" cellpadding="0" cellspacing="0" width="100%" style="padding:2px">
                      <tr>
                         <td style="line-height:12pt; height:10px;"><span class="pagetop"><b class="hnname"><a href="">News</a></b>
                                        <a href="newest">new</a> | <a href="submit">submit</a>            </span></td><td style="text-align:right;padding-right:4px;"><span class="pagetop">
                         </td>
                         <td style="text-align:right;padding-right:4px;"><span class="pagetop">
                         <script type = "text/javascript">
                             function ReadCookie()
                             {
                                var allcookies = document.cookie;
                                cookiearray = allcookies.split(';');
                                for(var i=0; i<cookiearray.length; i++) {
                                   name = cookiearray[i].split('=')[0];
                                   value = cookiearray[i].split('=')[1];
                                   return value;
                                }
                             }
                             function RemoveCookie()
                             {
                                document.cookie = "loggeginuser=; max-age=0";
                                location.reload();
                             }
                               </script>
                               <form name = "myform" action = "">
                                        <script type ="text/javascript">document.write(ReadCookie())</script>
                                        <input type = "button" value = "Logout" onclick = "RemoveCookie()"/>
                                     </form>
                            </span>
                         </td>
                      </tr>
                   </table>
                </td>
             </tr>
             <script>
               function AddVote(id) {
                 var xhttp = new XMLHttpRequest();
                 document.getElementById("Button_" + id).disabled = true;
                 document.getElementById("DownButton_" + id).disabled = false;
                 xhttp.open("POST", "upvote?id=" + id , true);
                 xhttp.send();
               }
               function DownVote(id){
                 var xhttp = new XMLHttpRequest();
                 document.getElementById('Button_' + id).disabled = false;
                 document.getElementById('DownButton_' + id).disabled = true;
                 xhttp.open("POST", "downvote?id=" + id , true);
                 xhttp.send();
               }
               </script>
             <tr id="pagespace" title="" style="height:10px"></tr>
             <tr>
                <td>
                   <table border="0" cellpadding="0" cellspacing="0" class="itemlist">
                      <%
                      for(int i = 0; i <posts.size(); i++)
                      {
                      String idNum = String.valueOf(posts.get(i).getId());
                      %>
                      <tr class='athing' id= '<%=idNum%>' >
                         <td align="right" valign="top" class="title">
                         <span class="rank"><%=i+1%>.&nbsp;</span>
                      </td>
                         <td valign="top" class="votelinks">
                            <center>
                               <a id='up_<%=idNum%>'
                               <body>
                               <button id='Button_<%=idNum%>' style="font-size:12px" type= "button" onclick = "AddVote(<%=posts.get(i).getId()%>)"><i class="fa fa-thumbs-up"></i></i></button>

                               </body>
                                  <div class='votearrow' title='upvote'></div>
                               </a>
                            </center>
                         </td>
                         <td class="title"><a href="//<%=posts.get(i).getLink()%>/" class="storylink"> &nbsp; <%=posts.get(i).getTittle()%></a><span class="sitebit comhead"> (<a href="//<%=posts.get(i).getLink()%>/"><span class="sitestr"><%=posts.get(i).getLink()%></span></a>)</span></td>
                      </tr>
                      <tr>
                         <td colspan="2"></td>
                         <td class="subtext">
                            <span class="score" id="score_<%=idNum%>">&nbsp;&nbsp;<%=posts.get(i).getUpvote()%> points</span> by <%=posts.get(i).getName()%></a> <span class="age" title="<%=posts.get(i).getTime()%>"><%=posts.get(i).getTime()%> ago</a></span> <span id="unv_<%=idNum%>"></span> |<a href="javascript:void(0);" id="DownButton_<%=idNum%>" type= "button" onclick = "DownVote(<%=posts.get(i).getId()%>)">unvote</a>
 | <a href="hide?id=<%=idNum%>&amp;goto=news"></a><a href="item?id=<%=idNum%>"><%=posts.get(i).getCommentsCount()%>&nbsp;comments</a>
                         </td>
                      </tr>
                      <tr class="spacer" style="height:5px"></tr>
                      <tr class="morespace" style="height:10px"></tr>
                      <% } %>
                   </table>
                </td>
             </tr>
             <tr>
                <td>
                      <tr>
                         <td bgcolor="#ff6600"></td>
                      </tr>
                   </table>
                         </td>
                      </tr>
                      <tr class="spacer" style="height:5px"></tr>
                      <tr class="morespace" style="height:10px"></tr>

                   </table>
                </td>
             </tr>
             <tr>
                <td>
                      <tr>
                         <td bgcolor="#ff6600"></td>
                      </tr>
                   </table>
                   <br>
                   <center><a>
                   <br>
                   <br>
                </td>
             </tr>
          </table>
       </center>
    </body>
    <script type='text/javascript'></script>
 </html>