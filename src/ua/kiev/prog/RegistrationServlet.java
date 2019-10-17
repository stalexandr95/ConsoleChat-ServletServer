package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class RegistrationServlet extends HttpServlet {

    private UserList usList = UserList.getInstance();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        byte[] buf = requestBodyToArray(request);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

        User us = User.fromJSON(bufStr);
        if (us.getPresent().equals("offline") && !us.getPassword().equals("createChat")) {
            if (us.getPassword().equals("privateMessage")) {
                if (usList.checkUserByLogin(us))
                    response.setStatus(HttpServletResponse.SC_OK);
                else
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            } else {
                if (usList.checkUser(us))
                    response.setStatus(HttpServletResponse.SC_OK);
                else
                    response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (us.getPresent().equals("exiting")) {
            if (usList.setOffline(us))
                response.setStatus(HttpServletResponse.SC_OK);
            else
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            if (usList.createChat(us))
                response.setStatus(HttpServletResponse.SC_OK);
            else
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteChat = request.getParameter("deleteChat");
        String login = request.getParameter("login");
        String logInChatName = request.getParameter("chatNameForLogIn");
        if (deleteChat != null) {
            if (usList.deleteChat(deleteChat)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else if (login != null && logInChatName == null) {
            if (usList.logoutChat(login)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            if (usList.loginChat(logInChatName, login)) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            }
        }
    }

    private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
