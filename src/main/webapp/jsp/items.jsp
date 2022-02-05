<%@ page import="com.newssite.model.*" %>
<%
  Details postDetails = (Details) request.getAttribute("abc");
%>
<html lang="en" op="item">
   <style>Post
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
   <head>
      <meta name="referrer" content="origin">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title> | Hacker News</title>
   </head>
   <body>
      <center>
         <table id="hnmain" border="0" cellpadding="0" cellspacing="0" width="85%" bgcolor="#f6f6ef">
            <tr>
               <td bgcolor="#ff6600">
                  <table border="0" cellpadding="0" cellspacing="0" width="100%" style="padding:2px">
                     <tr>
                        <td style="line-height:12pt; height:10px;"><span class="pagetop"><b class="hnname"><a>News</a></b>
                                      | <a href="submit">submit</a>            </span></td><td style="text-align:right;padding-right:4px;"><span class="pagetop">
                        </td>
                        <td style="text-align:right;padding-right:4px;"><span class="pagetop">
                           <a href="login?goto=news">login</a>
                           </span>
                        </td>
                     </tr>
                  </table>
               </td>
            </tr>
            <tr id="pagespace" title="<%=postDetails.getLink()%>" style="height:10px"></tr>
            <tr>
               <td>
                  <table class="fatitem" border="0">
                     <tr class='athing' id='<%=postDetails.getId()%>'>
                        <td align="right" valign="top" class="title"><span class="rank"></span></td>
                        <td valign="top" class="votelinks">
                           <center>
                              <a id='up_<%=postDetails.getId()%>' href='vote?id=<%=postDetails.getId()%>&amp;how=up&amp;goto=item%3Fid%3D<%=postDetails.getId()%>'>
                                 <div class='votearrow' title='upvote'></div>
                              </a>
                           </center>
                        </td>
                        <td class="title"><a href="//<%=postDetails.getLink()%>/" class="storylink" rel="nofollow"><%=postDetails.getTittle()%>></a><span class="sitebit comhead"> (<a href="//<%=postDetails.getLink()%>/"><span class="sitestr"><%=postDetails.getLink()%></span></a>)</span></td>
                     </tr>
                     <tr>
                        <td colspan="2"></td>
                        <td class="subtext">
                           <span class="score" id="score_<%=postDetails.getId()%>"><%=postDetails.getUpvote()%> points</span> by <a href="user?id=<%=postDetails.getName()%>" class="hnuser"><%=postDetails.getName()%></a> <span class="age" title="2021-08-31T19:27:47"><a href="item?id=<%=postDetails.getId()%>"><%=postDetails.getTime()%> ago</a></span> <span id="unv_<%=postDetails.getId()%>"></span><a href="hide?id=<%=postDetails.getId()%>&amp;goto=item%3Fid%3D<%=postDetails.getId()%>"></a> <a href="https://hn.algolia.com/?query=Whose%20Authority%3F&type=story&dateRange=all&sort=byDate&storyText=false&prefix&page=0" class="hnpast"></a> <a href="fave?id=<%=postDetails.getId()%>&amp;auth=4a233d52827c753d78f928dd46111d64326b0b62"></a> | <a href="item?id=<%=postDetails.getId()%>"><%=postDetails.getCommentsCount()%>&nbsp;comment</a>
                        </td>
                     </tr>
                     <tr style="height:10px"></tr>
                     <tr>
                        <td colspan="2"></td>
                        <td>
                           <form method="post" action="comment"><input type="hidden" name="id" value="<%=postDetails.getId()%>"><input type="hidden" name="goto" value="item?id=<%=postDetails.getId()%>"><input type="hidden" name="hmac" value="be8ce3f66fccc9f3ea94a34815c4e8339cdc1cbe"><textarea name="cmtcontent" rows="6" cols="60"></textarea>
                              <br><br><input type="submit" value="add comment">
                           </form>
                        </td>
                     </tr>
                  </table>
                  <br><br>
                  <table border="0" class='comment-tree'>
                     <tr class='athing comtr' id='<%=postDetails.getId()%>'>
                        <td>
                        <%
                         System.out.println(postDetails.getCommentText().size());
                         for(int i = 0; i < postDetails.getCommentText().size() ; i++)
                         {
                         com.newssite.model.Comment comet = postDetails.getCommentText().get(i);
                         %>
                           <table border='0'>
                              <tr>
                                 <td valign="top" class="votelinks">
                                    <center>
                                       <a id='up_<%=postDetails.getId()%>' href='vote?id=<%=postDetails.getId()%>&amp;how=up&amp;goto=item%3Fid%3D<%=postDetails.getId()%>'>
                                          <div class='votearrow' title='upvote'></div>
                                       </a>
                                    </center>
                                 </td>

                                 <td class="default">
                                    <div style="margin-top:2px; margin-bottom:-10px;"><span class="comhead">
                                       <a href="user?id=idworks1" class="hnuser"><%=comet.getName()%></a> <span class="age" title="2021-09-02T00:34:12"><a href="item?id=<%=postDetails.getId()%>"><%=comet.getTime()%> ago</a></span> <span id="unv_<%=postDetails.getId()%>"></span><span class="par"></span> <a class="togg" n="1" href="javascript:void(0)" onclick="return toggle(event, 28387311)">[–]</a>          <span class='storyon'></span>
                                       </span>
                                    </div>
                                    <br>
                                    <div class="comment">
                                       <span class="comment text"><%=comet.getCmd()%>
                                          </span>
                                    </div>
                                 </td>
                              </tr>
                           </table>
                           <%}%>
                        </td>
                     </tr>
                  </table>
                  <br><br>
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
         </table>
      </center>
   </body>
   <script type='text/javascript'></script>
</html>

