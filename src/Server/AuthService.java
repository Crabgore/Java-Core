package Lesson_6.Server;


import java.sql.*;
import java.util.Objects;

public class AuthService {

    private static Connection connection;
    private static Statement stmt;

    public static void connect() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static String getNickByLoginAndPass(String login, String pass) {
        String sql = String.format("SELECT nickname, password FROM main\n" +
                "WHERE login = '%s'", login);

        int myhash = pass.hashCode();

        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);

                if (myhash == dbHash) {
                    return nick;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addUser(String login, String pass, String nick) {
        String sql = String.format("INSERT INTO main (login, password, nickname)" +
                "VALUES ('%s', '%s', '%s')", login, pass.hashCode(), nick);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addToBlacklist (String user, String nick){
        String sql = String.format("UPDATE main SET blacklist = '%s' WHERE nickname = '%s'", nick, user);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getBlacklistByNick(String nick) {
        // формирование запроса
        String sql = String.format("SELECT nickname FROM main where blacklist = '%s'", nick);

        try {
            // оправка запроса и получение ответа
            ResultSet rs = stmt.executeQuery(sql);

            // если есть строка возвращаем результат если нет то вернеться null
            if(rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getNickByBlacklist(String nick) {
        // формирование запроса
        String sql = String.format("SELECT blacklist FROM main where nickname = '%s'", nick);

        try {
            // оправка запроса и получение ответа
            ResultSet rs = stmt.executeQuery(sql);

            // если есть строка возвращаем результат если нет то вернеться null
            if(rs.next()) {
                return rs.getString(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addHistory(String nick, String text){
        String sql = String.format("INSERT INTO History (nick, text)" +
                "VALUES ('%s', '%s')", nick, text);
        try {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getHistory(String name){
        String str = "";
        try {
            ResultSet rs = stmt.executeQuery("SELECT nick, text FROM History");
            while (rs.next()) {
//                if (!getNickByBlacklist(name).equals(rs.getString("nick")))
                str += rs.getString("nick") + ": " + rs.getString("text") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return str;
    }
}
