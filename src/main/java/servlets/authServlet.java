package servlets;

import entities.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.userData;

/**
 *
 * @author Валентин Сервлет регистрации пользователей
 */
public class authServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("auth.jsp"); // Передаем управление 
        requestDispatcher.forward(request, response);                                 // в страницу регистрации пользователей
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        String name = request.getParameter("name"); // Получаем имя пользователя
        String password = request.getParameter("pass"); // Получаем пароль пользователя

        userData model = userData.getInstance();  // Получения ссылки к экземлпяру класса 
        
        boolean check = false;

        if (check) {
            int idUser;       // Создаем нового пользователя с именем паролем и порядковым номером
            idUser = model.getIdUser();
            User user = new User(name, password, idUser);
            model.add(user);
            model.addIdUser(); // Увеличиваем счетчик пользователей
            request.setAttribute("userName", name); // Передаем имя пользователя запросу

            userData.random();   // Загадываем число
            userData.allarrRemove(); // Очищаем данные о ходе игры 
            request.setAttribute("userName", name);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("authSucces.jsp"); // Передаем управление странице
            requestDispatcher.forward(request, response);
        } else {
            
            request.setAttribute("userName", name);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("auth.jsp"); // Передаем управление странице
            requestDispatcher.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
