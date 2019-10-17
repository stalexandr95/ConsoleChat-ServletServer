package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CheckChatNameServlet extends HttpServlet {

    Gson gson = new GsonBuilder().create();
    private UserList usList = UserList.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String login = request.getParameter("login");
        String chatName = usList.checkLogin(login);
        String json = gson.toJson(chatName);
        OutputStream os = response.getOutputStream();
        byte[] buf = json.getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }
}
