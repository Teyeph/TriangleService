package com.teyeph.server;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Triangle", urlPatterns = {"/triangle"})
public class TriangleServlet extends HttpServlet {

    public TriangleServlet() {

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req == null) {
            resp.sendError(400, "Request is null.");
            return;
        }
        if (resp == null) {
            resp.sendError(500, "Response is null.");
            return;
        }

        resp.setContentType("text/plain");

        // Ensure the parameters are there
        if (!req.getParameterMap().containsKey("a") || !req.getParameterMap().containsKey("b") || !req.getParameterMap().containsKey(("c"))) {
            resp.sendError(400, "Missing arguments. Requires three numerical arguments, a, b, and c.");
            return;
        }

        String getA = req.getParameter("a");
        String getB = req.getParameter("b");
        String getC = req.getParameter("c");
        if (getA == null || getB == null || getC == null) {
            resp.sendError(400, "Received a null argument.");
            return;
        }

        double a, b, c;
        try {
            a = Double.parseDouble(getA);
            b = Double.parseDouble(getB);
            c = Double.parseDouble(getC);
        } catch (NumberFormatException e) {
            resp.sendError(400, "Received a non-numerical argument.");
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
