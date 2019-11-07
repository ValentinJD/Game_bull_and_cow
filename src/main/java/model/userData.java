package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import entities.User;
import java.text.ParseException;
import java.util.Random;
import java.util.stream.Collector;

/**
 *
 * @author Валентин Класс необходимый для взаимодействия между всеми классами
 * приложения хранит информацию о текущих параметрах игры
 */
public class userData {

    private static userData instance = new userData();

    private static ArrayList<User> model = new ArrayList<User>(); // Список пользователей
    public static int kol_tries = 1; // Текущее количество попыток пользователя
    public static int game = 0; // Состояние игры начало 0 окончание 1
    private static int user_inp = -1; // Переменная для вывода типа неверного ввода пользователя
    public static int bulls; // Количество быков
    private String question; // Попытка от пользователя в угадывании числа
    private String answer; // Ответ пользователю количество быков + коров
    static int[] sys = new int[4]; // Массив хранения загаданного числа
    static int[] us = new int[4]; // Массив для хранения попытки пользователя
    public static int idUser = 0; // Порядковый номер пользователя

    public static ArrayList arrTries = new ArrayList(); // Список попыток
    public static ArrayList<String> arrQuestion = new ArrayList<String>(); // Список запросов пользователя
    public static ArrayList<String> arrAnswer = new <String> ArrayList(); // Список ответов комьютера на попытки пользователя

    private userData() {
    }
    // Геттеры для доступа к соответствующим полям и ссылкам

    public static userData getInstance() {
        return instance;
    }

    public static int getkol_tries() {
        return kol_tries;
    }

    public static ArrayList getarrTries() {
        return arrTries;
    }

    public static ArrayList<String> getarrQuestion() {
        return arrQuestion;
    }

    public static ArrayList<String> getarrAnswer() {
        return arrAnswer;
    }

    public static ArrayList<User> getUser() {
        return model;
    }

    public static User getUserObgect(int idUser) {
        User user;
        user = model.get(idUser);
        return user;
    }

    public void add(User user) {
        model.add(user);
    }

    public static ArrayList<User> getArrlistUser() {
        return model;
    }

    public static int[] random() {   // Загадываем число
        Random rand = new Random();

        for (int i = 0; i < 4; i++) {
            sys[i] = rand.nextInt(10);
            for (int j = 0; j < i; j++) {
                if (sys[i] == sys[j]) {
                    i--;
                    break;
                }
            }
        }
        return sys;
    }

    public static String getRandom() {   // Геттер загаданного числа
        String s = "" + sys[0] + sys[1] + sys[2] + sys[3];
        return s;
    }

    public static int getIdUser() {
        return idUser;
    }

    public static int quest_Str_in_Int(String answ) { // Преобразование запроса пользователя в число
        int user_Int_quest = 0;
        try {
            String str = answ;
            user_Int_quest = Integer.parseInt(str);
        } catch (NumberFormatException err) {
        }
        return user_Int_quest;
    }

    public static void addIdUser() { // Увеличиваем значение переменной при регистрации пользователя
        idUser++;
    }

    public static String answBullandCow(String answ) { // Получаем ответ для пользователя
        int[] us = new int[4];
        int cows = 0;
        int mulls = 0;
        int user_num = 0;
        user_num = quest_Str_in_Int(answ);

        for (int i = 0; i < 4; i++) {
            us[3 - i] = user_num % 10;
            user_num /= 10;
        }

        for (int i = 0; i < 4; i++) {
            if (us[i] == sys[i]) {
                mulls++;
            }
            for (int j = 0; j < 4; j++) {
                if (us[i] == sys[j] && i != j) {
                    cows++;
                }
            }
        }
        bulls = mulls;
        user_inp = user_num;
        return "Коров = " + cows + ", Быков = " + mulls;
    }

    public static boolean win() {  // Выиграл пользовател или нет для вывода сообщений и другого
        boolean an;
        if (bulls == 4) {
            an = true;
        } else {
            an = false;
        }
        return an;
    }

    public static int checkDoubles(String quest) {  // Проверка ввода дублирующихся чисел
        int userInt = quest_Str_in_Int(quest);
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            us[3 - i] = userInt % 10;
            userInt /= 10;
        }

        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < i; j++) {
                    if (us[i] == us[j] && i != j) {
                        ans = 2;
                        //kol_tries--;
                        throw new Exception();
                    }
                }
            }
        } catch (Exception err) {
        }

        return ans;
    }

    public static int checkInp(String quest) {  // Проверка ввода пользователя и присваивание
        int userInt = quest_Str_in_Int(quest);  // значения перемнной для вывода соответсвующего сообщения
        int ch = 99;

        if (userInt == 0) {
            ch = -1; // пустой запрос
        } else if (userInt < 100 || userInt > 9999) { // Число не в диапазоне
            ch = 1;
        } else if (checkDoubles(quest) == 2) { // Число содержит дубликаты
            ch = 2;
        }
        return ch;
    }

    public static String answChInp(String quest) {  // Текст для сообщения пользователю при 
        int chInp = checkInp(quest);                // не соответсвющем игре запросу
        String a;
        if (chInp == -1) {
            a = "Неверный формат числа";
        } else if (chInp == 1) {
            a = "Число должно быть в диапазоне [100..9999]";
        } else if (chInp == 2) {
            a = "Число не должно содержать дублирующихся цифр";
        } else {
            a = "Ок";
        }
        return a;
    }

    public static void addTable(int tries, String question, String answer) {  // Добавление данных о ходе игры в 
        // соотвествующий список
        arrTries.add(kol_tries);  // Количество попыток
        arrQuestion.add(question); // Запрос пользователя в угадывании числа
        arrAnswer.add(answer);     // Ответы компьютера пользователю
        game = 1;  // Игра в процессе

    }

    public static void mulls_0() {  // Обнуляем число быков при выигрыше
        bulls = 0;
    }

    public static void addkol_tries(int i) {  // Обновляем количество попыток при выигрыше
        kol_tries = i;
    }

    public static void allarrRemove() {  // Удаляем информацию о попытках - Сброс игры 
        // запросах и ответах
        arrTries.removeAll(arrTries);
        arrQuestion.removeAll(arrQuestion);
        arrAnswer.removeAll(arrAnswer);
        kol_tries = 1;
    }

    public boolean checkUserName(String name) {
        boolean check = false;
        String nameUser;
        if (model.isEmpty()) {
            check = true;
        } else {
            for (int i = 0; i < model.size(); i++) {
                User user= model.get(i);
                if (user.name.equals(name)) {
                    check = false;
                }
            }

        }
        return check;
    }

}
