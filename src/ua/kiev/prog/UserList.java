package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

public class UserList {
    private static final UserList usList = new UserList();

    private final List<User> listOfUsers = new LinkedList<>();

    public UserList() {
        listOfUsers.add(new User("user1", "password1"));
        listOfUsers.add(new User("user2", "password2"));
        listOfUsers.add(new User("user3", "password3"));
        listOfUsers.add(new User("user4", "password4"));
        listOfUsers.add(new User("user5", "password5"));
    }

    public boolean checkUser(User us) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.getLogin().equals(us.getLogin()) && user.getPassword().equals(us.getPassword())) {
                user.setPresent("online");
                result = true;
            }
        }
        return result;
    }

    public boolean checkUserByLogin(User us) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.equalsByLogin(us)) {
                result = true;
            }
        }
        return result;
    }

    public boolean setOffline(User us) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.equalsByLogin(us)) {
                user.setPresent("offline");
                result = true;
            }
        }
        return result;
    }

    public boolean createChat(User us) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.equalsByLogin(us)) {
                user.setChatName(us.getChatName());
                result = true;
            }
        }
        return result;
    }

    public boolean deleteChat(String str) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.getChatName().equals(str)) {
                user.setChatName("no chat");
                result = true;
            }
        }
        return result;
    }

    public boolean logoutChat(String login) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.getLogin().equals(login)) {
                if (user.getChatName().equals("no chat")) {
                    break;
                } else {
                    user.setChatName("no chat");
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean loginChat(String chatName, String login) {
        boolean result = false;
        for (User user : listOfUsers) {
            if (user.getChatName().equals(chatName)) {
                for (User us : listOfUsers) {
                    if (us.getLogin().equals(login)) {
                        us.setChatName(chatName);
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public String checkLogin(String login) {
        String myChatName = "";
        for (User user : listOfUsers) {
            if (user.getLogin().equals(login)) {
                myChatName = user.getChatName();
            }
        }
        return myChatName;
    }

    public static UserList getInstance() {
        return usList;
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }
}