
package servlets;

import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.userData;
import static model.userData.checkInp;

/**
 *
 * @author Валентин
 * Сервлет обрабатывающий данные о ходе игры
 */
public class gameServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // Установка для ответа кодировки UTF-8
        
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8"); // Установка для ответа кодировки UTF-8
        request.setCharacterEncoding("UTF-8"); // Установка для запроса кодировки UTF-8
        
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp"); // Передаем управление
        requestDispatcher.forward(request, response);                              // Странице игры
        processRequest(request, response);
    }
 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8"); // Установка для ответа кодировки UTF-8
        request.setCharacterEncoding("UTF-8"); // Установка для запроса кодировки UTF-8
        
        userData model = userData.getInstance();  // Получение ссылки для доптупа к основному классу
        request.setAttribute("userData", model);  // передаем ссылку в ответе
        String question = request.getParameter("question"); // устанавливаем параметр для хранения запроса пользователя
        String questionUserCheck = model.answChInp(question); // Проверяем запрос пользователя на формат чисел
                                                              // и получаем ответ в виде строки 
        if (userData.game == 0) {       // При начале игры очищаем данные о ходе игры    
            userData.allarrRemove();
        }

        if (questionUserCheck != "Ок") {   // Если запрос пользователя не соответствует формату передаем сообщение
            request.setAttribute("questionUserCheck", questionUserCheck);
        }
        
        if (questionUserCheck == "Ок" && questionUserCheck != null) { // Если запрос пользователя соответсвует формату
                                                                      
            request.setAttribute("arrTries", userData.getarrTries());  // Передаем ссылку на список попыток

            ArrayList<User> users = userData.getUser(); // Получаем ссылку на объект текущего пользователя

            request.setAttribute("arrQuestion", userData.getarrQuestion()); // Передаем запрос пользователя

            String answer = userData.answBullandCow(question); // Проверяем запрос пользователя и получаем ответ
            request.setAttribute("answer", userData.getarrAnswer()); // передаем ответ 
            
            int kol_tries = userData.kol_tries; // получаем количество попыток пользователя
           
            if (users.get(model.idUser - 1).win == 0) { // Добавляем данные о ходе игры 
                userData.addTable(kol_tries, question, answer); // пока пользовател не выиграл
            }
             if (!model.win()) {
                userData.kol_tries++; // Увеличиваем количетво попыток пока игрок не победит 
            }

            if (model.win()) {  // Когда игрок победил
                users.get(model.idUser - 1).win = 1; // Устанавливаем переменную в 1 конец игры 
                users.get(model.idUser - 1).numTries_to_win = userData.kol_tries; // Сохраняем количество попыток 
                userData.kol_tries = 1; // Инициализируем количество попыток для следующего игрока
                model.mulls_0(); // Обнуляем количество быков
                userData.game = 0;       // Обнуляем начало игры         
            }
        }
        
        ArrayList<User> names = model.getUser(); // Получаем ссылку на игрока
        request.setAttribute("userNames", names); // передаем имя игрока

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("game.jsp"); // Передаем данные в страницу игры
        requestDispatcher.forward(request, response);

        processRequest(request, response);
    }
  
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
