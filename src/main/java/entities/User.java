package entities;

/**
 *
 * @author Валентин
 * Данный клас хранит информацию о пользователях игры.
 */
public class User {
    public String name;  // Имя пользователя
    public int numTries_to_win; // Количество попыток до выигрыша
    public int idUser; // Порядоквый номер пользователя
    public String password; // Пароль
    public int win;  // Переменная отображающая ход игры 0 начало игры 1 окончание

    public User() {
    }

    public User(String name, String password, int idUser) { // инициализация пользователя при регистрации
        this.name = name;
        this.password = password;
        this.idUser = idUser;
        win = 0;
    }
      // геттеры соответствующих полей
    public String getName() {
        return name;
    }
    public int getwin() {
        return win;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
