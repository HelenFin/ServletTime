package com.example;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;

public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String timezone = request.getParameter("timezone");
        ZoneId zoneId;

        try {
            if (timezone != null && !timezone.isEmpty()) {
                timezone = timezone.replace(" ", "+");
                zoneId = ZoneId.of(timezone);
            } else {
                zoneId = ZoneId.of("UTC");
            }
        } catch (Exception e) {
            out.println("<html><body>");
            out.println("<h1>Invalid timezone</h1>");
            out.println("<p>Please provide a valid timezone.</p>");
            out.println("</body></html>");
            return;
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");
        String currentTime = ZonedDateTime.now(zoneId).format(dtf);

        out.println("<html><body>");
        out.println("<h1>Current Time in " + zoneId + "</h1>");
        out.println("<p>" + currentTime + "</p>");
        out.println("</body></html>");
    }
}
