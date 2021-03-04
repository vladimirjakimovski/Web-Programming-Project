package com.example.demo.web.servlet;
import com.example.demo.model.Category;
import com.example.demo.service.CategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name="category-servlet", urlPatterns = "/servlet/category")
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;
    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = categoryService.listCategories();
        String ipAddress = req.getRemoteAddr();
        String clientAgent = req.getHeader("User-Agent");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<head>");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<h2>User Info</h2>");
        writer.format("IP address: %s<br/>",ipAddress);
        writer.format("Client Agent: %s",clientAgent);
        writer.println("<h2>Category List</h2>");
        writer.println("<ul>");
        categories.forEach(r->
                writer.format("<li>%s (%s)</li>",r.getName(),r.getDescription()));
        writer.println("</ul>");

        writer.println("</ul>");

        writer.println("<h2>Add New Category</h2>");

        writer.println("<form method='POST' action='/servlet/category'/>");
        writer.println("<label for='name'>Name:<label>");
        writer.println("<input id='name' type='text' name='name'/>");
        writer.println("<label for='desc'>Description:<label>");
        writer.println("<input id='desc' type='text' name='desc'/>");
        writer.println("<input type='submit' value='Submit'/>");
        writer.println("</form>");

        writer.println("</body>");
        writer.println("</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("desc");
        categoryService.create(categoryName, categoryDescription);
        resp.sendRedirect("/servlet/category");
    }

}
