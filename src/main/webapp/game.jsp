<%-- 
    Document   : game
    Created on : 02.07.2019, 21:03:52
    Author     : Валентин
--%>

<%@page import="entities.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.userData"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles/w3.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Страница игры</title>
    </head>
    <body> 
    <th>
        <h1>Ну наконец-то добрались до игры!</h1>
        <div>
            <% //Выводим имя игрока
                userData model = (userData) request.getAttribute("userData"); // получаем имя из ответа сервлета

                ArrayList<User> users = model.getUser(); // получаем ссылку на игрока
                String name; // Имя игрока
                int IdUser1; // Порядковый номер игрока
                IdUser1 = model.getIdUser(); // Получаем порядковый номер игрока
                User user1;
                name = users.get(IdUser1 - 1).name; // Получаем имя игрока
                if (users.size() != 0) { // Если игроки существуют выводим сообщение
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n"
                            + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                            + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n"
                            + "   <h5>Пользователь '" + name + "' в игре!</h5>\n"
                            + "</div>");
                }
            %>
            <%
                if (users.get(IdUser1 - 1).win == 1) { // Если игра закончена win == 1 выводим сообщение вы победили.
                    out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n"
                            + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                            + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n"
                            + "   <h5>Поздравляем вы победили!!!</h5>\n"
                            + "</div>");

                }
            %>
            <% if (!model.win()) {
                    String questionUserCheck = (String) request.getAttribute("questionUserCheck"); // Если формат числа 
                    if (questionUserCheck != "Ок" && questionUserCheck != null) { // не верный выводим сообщение
                        out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                                + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                                + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n"
                                + "   <h5>" + questionUserCheck + "</h5>\n"
                                + "</div>");
                    }
                }
            %>
        </div> 
        <div>
            <h1>Бык и Корова</h1>
        </div> 
        <div >
            <div >

                Эта народная игра школьников и студентов для убивания времени доступна теперь и для Вас.<br>
                Игра ведется с комьютером, комьпютер в начале игры загадывает 4-х значное число. <br>
                Цифры в загаданном числе не повторются. <br>
                Вы отгадаваете число загаданное компьютером, вводя вопрос в поле в таком же формате 4-х значного числа.<br>
                Комьютер выдет подсказку количество быков и коров. <br>
                Бык - это угаданная цифра на правильной позиции,а корова- это правильная цифра , но не на своей позиции.<br>
                Вы задаете вопросы нажимая на кнопку "Угадать" до тех пор пока не разгадаете загаданное число.<br>
                Число может иметь следующий формат "0567", число "0" также входит в загаданное.       


            </div>




        </div> 
        <div>
            <form method="POST">
                <label>Введите число: <br>
                    <input type="text" name="question"  style="width: 30%">
                </label>
                <button method="POST" onclick="location.href = 'game'">Угадать</button>  <!-- Кнопка Угадать 
                 для отправки запроса пользователя в сервлет game -->               
            </form>
        </div> 
        <div>
            <br>
            <table>
                <thead>
                    <tr>
                        <th>№ попытки</th>
                        <th>Число</th>
                        <th>Подсказка</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td> <%
                            ArrayList arrTries = model.getarrTries(); // Получаем ссылку на список попыток

                            if (model.win()) { // если игра не закончена

                                int IdUser;
                                IdUser = model.getIdUser();
                                User user;
                                user1 = users.get(IdUser - 1); // Получаем ссылку на объект игрока

                            }
                            if (arrTries.size() != 0) { // выводим список попыток
                                for (int i = 0; i < arrTries.size(); i++) {
                                    out.println(arrTries.get(i) + "<br>");
                                }
                            }
                            //out.println(model.getRandom() + "<br>"); // для режима отладки отображение загаданного числа

                            %>
                        </td>
                        <td>
                            <%   ArrayList<String> arrQuestion = model.getarrQuestion(); // Получаем ссылку на список запросов игрока
                                if (arrQuestion.size() != 0) { // выводим список запросов 
                                    for (int i = 0; i < arrQuestion.size(); i++) {
                                        out.println(arrQuestion.get(i) + "<br>");
                                    }
                                }

                            %>
                        </td>
                        <td>
                            <%  ArrayList<String> rrAnswer = model.getarrAnswer(); // Получаем ссылку на список ответов
                                if (rrAnswer.size() != 0) { // выводим список ответов
                                    for (int i = 0; i < rrAnswer.size(); i++) {
                                        out.println(rrAnswer.get(i) + "<br>");
                                    }
                                }
                            %>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                    </tr>
                </tbody>
            </table>
            <div>

            </div>

        </div>
        <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
            <button class="w3-btn w3-round-large" onclick="location.href = 'index.html'">НОВАЯ ИГРА</button>
        </div>

        <%  
            int allnumTries_to_win; // Среднее количество попыток игроков

            if (users.size() != 0) { 
                allnumTries_to_win = 0;
                for (int i = 0; i < users.size(); i++) { 
                    allnumTries_to_win = allnumTries_to_win + users.get(i).numTries_to_win;// Суммируем все попытки пользователей
                    if (i == users.size() - 1) {
                        allnumTries_to_win = allnumTries_to_win / users.size(); // рассчитваем среднее число попоыток пользователей
                    }
                } // Выводим сообщение о среднем количестве попыток
                out.println("<div class=\"w3-panel w3-yellow w3-display-container w3-card-4 w3-round\">\n"
                        + "   <span onclick=\"this.parentElement.style.display='none'\"\n"
                        + "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-yellow w3-border w3-border-red w3-hover-border-grey\">×</span>\n"
                        + "   <h5>" + "Среднее количество попыток всех пользователей до угадывания числа равно " + allnumTries_to_win + "</h5>\n"
                        + "</div>");
            }
        %>

    </body>
</html>
