<%-- 
    Document   : ManagerMainPage
    Created on : 19 Jun 2022, 13:47:29
    Author     : HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager main page</title>
        <style>
        .sideside{
        background-color:rgb(0,0,50);
        width:100px;
        height:100%;
        position:fixed;
        top:0px;
        left: 120px;
        text-align:left;
        }
        .b{
        border:none;
        background-color:rgb(0,0,50);
        color:white;
        transition-duration: all 0.1s;
        height:80px;
        width:100%;
        cursor: pointer;
        }
        #copyright{
        Position:absolute;
        top:800px;
        left:640px;
        }
        #heading{
        position:absolute;
        left:600px;
        }
        .c{
          background-color: rgb(0, 0, 128);
          border: none;
          color: white;

          text-align: left;
          text-decoration: none;

          font-size: 18px;
          height:70px;
          width:120px;
          transition-duration: all 0.1s;
          cursor: pointer;
        }
        #side {
        align:right;

          height: 100%;
          width: 120px;
          position: fixed;
          z-index: 1;
          top: 0;
          left: 0;
          background-color: rgb(0, 0, 128);
          overflow-x: hidden;
          padding-top: 10px;
        }
        #mid{
        background-color:white;
        width:900px;
        height:700px;
        position:absolute;
        top:80px;
        left:280px;
        border-style:double;
        border-width:5px;
        border-color:grey;
        overflow: auto;
        }
        .b:hover {
          background-color: rgb(0, 0, 90);
        }
        .b.active{
          background-color:rgb(0,0,128);
        }
        .c:hover {
          background-color: rgb(0, 0, 80);
        }

        .c.active{
          background-color:rgb(0,0,50);
        }

        </style>
    </head>
    <body style="background-color:rgb(204, 224, 255)">
        <h1 id ="heading">Carol's Boutique</h1>
        <div id="side">
            <button  class="c" id = "keepaside"  name = "button" onclick="openCity(event, 'Keepasidebar')">keep aside</button>
            <button class="c" id = "viewreport" name = "button" onclick="openCity(event, 'Reportbar')">view report </button>
            <button class ="c" id = "registeremployee"  name = "button" >Register Employee</button> 
            <button class ="c" id = "registerstore"  name = "button" >Register Store</button> 
            <button class ="c" id = "ibt"  name = "button" onclick="openCity(event, 'IBTbar')">IBT</button>
            <button class="c" id = "stock"  name = "button" onclick="openCity(event, 'Stockbar')">Stock</button>
            <button class="c" id = "sale"  name = "button" onclick="openCity(event, 'Salebar')" >Sale</button>
        </div>

        <div id = "Salebar" class = "sideside">
            <button id="Exchanged" class="b" name ="button" value ="Exchanged page">Exchanged</button>
            <button id="Return" class="b" name ="button" value ="Return page">Return</button>
            <button id="LineItems" class="b" name ="button" value ="LineItems page">LineItems</button>
        </div>
        <div id = "Keepasidebar" class = "sideside">
            <button id="createkeepaside" class="b" name ="button" value ="create keep aside page">create keep aside</button>
            <button id="removekeepaside" class="b" name ="button" value ="remove keep aside page">remove keep aside</button> 
        </div>
        <div id = "Stockbar" class = "sideside">
            <button id="createproduct" class="b" name ="button" value ="createproduct page">create product</button>
            <button id="deleteproduct" class="b" name ="button" value ="delete product page">delete product</button>
            <button id="addtostock" class="b" name ="button" value ="add to stock page">add to stock</button>
            <button id="removefromstock" class="b" name ="button" value ="remove from stock page">remove from stock</button>
        </div>
        <div id = "Reportbar" class = "sideside">
            <button  id ="viewtopemp" class="b" name ="button" value ="view top employees page">view top employees page</button>
            <button  id ="monthlysales" class="b" name ="button" value ="view monthly sales page">view monthly sales</button>
            <button   id ="topsellingemps" class="b" name ="button" value ="view top selling employees page">view top selling employees</button>
            <button  id ="achievedtarget" class="b" name ="button" value ="view stores that achieved target page">View stores that achieved target page</button>
            <button  id ="topsellingproducts" class="b" name ="button" value ="view top selling products page">View top selling products</button>
            <button  id ="leastperformingstores" class="b" name ="button" value ="view least performing stores page">View least performing stores</button>
            <button  id ="productsreport" class="b" name ="button" value ="view product report page">View product report</button>
            <button  id ="dailysales" class="b" name ="button" value ="view daily sales page">View daily sales page</button>
        </div>
        <div id = "IBTbar" class = "sideside">
            <button id ="requestibt" class="b" name ="button" value ="Request IBT">Request IBT</button>
            <button id ="ibtrequestpage" class="b" name ="button" value ="IBT Requests page">IBT Requests</button>
        </div>
        
        <label id="copyright">Carols Boutique pty.Ltd.<br>Reg.131 482 9132</label>
        <div class ="mid" id ="ckapage">
            <h2 style ="font-size:30px;">Create keep aside</h2>
            <br>
            <label>ProductID:</label>
            <br>
            <input style ="font-size:30px" type="text" name ="productID">
            <br>
            <br>
            <label>Amount:<label>
                    <br>
                    <input style ="font-size:30px" type="number" min="0" name ="amount">
                    <br>
                    <br>
                    <label style ="font-size:30px"><br>is success:??yes??<br></label>
                    <br>

                    <button id="createkeepaside" class ="mb" name="button" value ="create keep aside">create keep aside</button>
                    <button id="clear" class="mb">clear</button>
                    </div>
        <script>
        function openCity(evt, cityName) {
          var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("sideside");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("c");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
            }

            // Get the element with id="defaultOpen" and click on it
            document.getElementById("viewreport").click();
        function updatePage(evt,TabPage){
            var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("sideside");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("c");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(cityName).style.display = "block";
                evt.currentTarget.className += " active";
        }
        
        </script>
    </body>
</html>
