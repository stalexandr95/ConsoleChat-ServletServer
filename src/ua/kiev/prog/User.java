package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
    private String login;
    private String password;
    private String present = "offline";
    private String chatName = "no chat";

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

    public boolean equalsChat(String str) {
        return (this.chatName.equals(str));
    }

    public boolean equalsByLogin(User us) {
        return (this.login.equals(us.login));
    }

    public String getPassword() {
        return password;
    }

    public String getLogin() {
        return login;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public void setPresent(String present) {
        this.present = present;
    }

    public String getPresent() {
        return present;
    }
}
