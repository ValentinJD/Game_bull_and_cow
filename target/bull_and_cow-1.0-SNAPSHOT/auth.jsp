<%-- 
    Document   : index
    Created on : 02.07.2019, 19:42:56
    Author     : Валентин
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="entities.User"%>
<%@page import="model.userData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles/w3.css">
        <title>Регистрация пользователя</title>
    </head>
    <div class="widthPage">

        <body class="w3-light-grey">
            <%
                userData model = (userData) request.getAttribute("userData"); // Получаем данные от сервлета
                
                 
                ArrayList<User> users = model.getUser(); // получаем ссылку на экземпляр пользователя
                 
                String name = (String) request.getAttribute("userName");  // Имя игрока 
                boolean check = true; 
                if (check) {                                                             

                        out.println("<div class=\"w3-panel w3-yellow w3-display-container w3-card-4 w3-round\">\n"
                                + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                                + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-yellow w3-border w3-border-red w3-hover-border-grey\">×</span>\n"
                                + "   <h5>" + "Пользователь " + name + " существует." + "</h5>\n"
                                + "</div>");  
                }             

            %>
            <div class="w3-container w3-padding w3-center">

                <div class="w3-card-4">
                    <div class="w3-container w3-center w3-green">
                        <h2>Регистрация</h2>
                    </div>
                    <div class="w3-container w3-center">
                        <form method="post" class="w3-selection w3-light-grey w3-center">
                            <label class="w3-center">Введите имя:
                                <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large w3-center" style="width: 30%"><br />
                            </label>
                            <label>Введите пароль:
                                <input type="password" name="pass" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
                            </label>
                            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Регистрация</button>
                        </form>
                    </div>
                </div>
            </div>
            <div> 
                <%                   
                    String name1; // Имя игрока                   
                    int numTries_to_win; // Количество попыток игрока
                    int allnumTries_to_win; // Среднее количество попыток всех игроков

                    if (users.size() != 0) {
                        out.println("<div><h5>Список пользователей и количество попыток до выигрыша</h5></div>");
                        for (int i = 0; i < users.size(); i++) {  // Выводим список пользователей и попыток
                            name1 = users.get(i).name;
                            numTries_to_win = users.get(i).numTries_to_win;

                            out.println("<div class=\"w3-panel w3-yellow w3-display-container w3-card-4 w3-round\">\n"
                                    + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                                    + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-yellow w3-border w3-border-red w3-hover-border-grey\">×</span>\n"
                                    + "   <h5>" + "Пользователь " + name1 + " выиграл за " + numTries_to_win + " попыток" + "</h5>\n"
                                    + "</div>");
                        }

                    }

                    if (users.size() != 0) {
                        allnumTries_to_win = 0;
                        for (int i = 0; i < users.size(); i++) { // Вычисляем среднее число попыток всех игроков
                            allnumTries_to_win = allnumTries_to_win + users.get(i).numTries_to_win;
                            if (i == users.size() - 1) {
                                allnumTries_to_win = allnumTries_to_win / users.size();
                            }
                        }
                        out.println("<div class=\"w3-panel w3-yellow w3-display-container w3-card-4 w3-round\">\n"
                                + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                                + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-yellow w3-border w3-border-red w3-hover-border-grey\">×</span>\n"
                                + "   <h5>" + "Среднее количество попыток всех пользователей равно " + allnumTries_to_win + "</h5>\n"
                                + "</div>");
                    }

                %>
            </div>
        </body>
</html>
