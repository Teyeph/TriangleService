import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Triangle", urlPatterns = "/triangle")
public class TriangleServlet extends HttpServlet {

    public TriangleServlet() {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        assert req != null;
        assert resp != null;

        resp.setContentType("text/html");

        // Ensure the parameters are there
        if (!req.getParameterMap().containsKey("a") || !req.getParameterMap().containsKey("b") || !req.getParameterMap().containsKey(("c"))) {
            resp.sendError(400);
            return;
        }

        String getA = req.getParameter("a");
        String getB = req.getParameter("b");
        String getC = req.getParameter("c");
        if (getA == null || getB == null || getC == null) {
            resp.sendError(400);
            return;
        }

        int a, b, c;
        try {
            a = Integer.parseInt(getA);
            b = Integer.parseInt(getB);
            c = Integer.parseInt(getC);
        } catch (NumberFormatException e) {
            resp.sendError(400);
            return;
        }

        PrintWriter out = resp.getWriter();
        if (a == b && b == c) {
            out.print("EQUILATERAL");
        } else if (a == b || b == c || c == a) {
            out.print("ISOCELES");
        } else {
            out.print("SCALENE");
        }

        out.close();
        resp.setStatus(200);

    }
}
