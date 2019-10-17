package ua.kiev.prog;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class GetUsersServlet extends HttpServlet {

    private UserList usList = UserList.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String json = usList.toJSON();
        OutputStream os = response.getOutputStream();
        byte[] buf = json.getBytes(StandardCharsets.UTF_8);
        os.write(buf);
    }
}
