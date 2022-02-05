<html>
   <head>
       <style>
       .center {
       text-align: center;
         border: 3px solid red;
         margin: auto;
         width: 60%;
         padding: 10px;
       }
       .centernoborder {
              text-align: center;
                margin: auto;
                width: 90%;
                padding: 10px;
              }
       </style>
      <script type = "text/javascript">
            function SetCookie()
            {
               if( document.myform.customer.value == "" ) {
                  alert("Enter some value!");
                  return;
               }
               cookievalue = escape(document.myform.customer.value) + ";";
               document.cookie = "loggeginuser=" + cookievalue + "; max-age=" + (3600*24);
               location.reload();
            }
      </script>

   </head>

   <body>
   <div class="centernoborder">
   <h1>............News Site............</h1>
   </div>
        <div class="center">
        <h2>............Log In............</h2>
          <form name = "myform" action = "">
                   Enter User Name: <input type = "text" name = "customer"/>
                   <input type = "button" value = "Go" onclick = "SetCookie();"/>
                </form>
        </div>
        <div class="center">
                <h2>............Create New User Name............</h2>
                  <form action="signin" method="post">
                    Enter User Name: <input type="text"  name="name">
                    <button type="submit" value="Submit">Submit</button>
                  </form>
                </div>
   </body>
</html>