<%-- 
    Document   : authSucces
    Created on : 02.07.2019, 20:41:53
    Author     : Валентин
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/w3.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Регистрация нового пользователя</title>
    </head>
    <body>
        
        <div>
            <% // Выводим сообщение с именем зарегистрированного игрока
                
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n"
                            + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                            + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n"
                            + "   <h5>Пользователь '" + request.getAttribute("userName") + "' успешно зарегистрирован!</h5>\n"
                            + "</div>");
                
            %>
        </div>
        <div class="w3-container w3-center">
    <div class="w3-bar w3-padding-large w3-padding-24">
        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='game'"><h1>ИГРАТЬ</h1></button>
        
    </div>
</div>
    </body>
</html>
