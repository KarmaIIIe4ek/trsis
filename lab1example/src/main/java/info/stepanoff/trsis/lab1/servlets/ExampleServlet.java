/*
 * this code is available under GNU GPL v3
 * https://www.gnu.org/licenses/gpl-3.0.en.html
 */
package info.stepanoff.trsis.lab1.servlets;

import info.stepanoff.trsis.lab1.data.Lesson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "ExampleServlet", urlPatterns = {"/example"})
public class ExampleServlet extends HttpServlet {

    public ArrayList<Lesson> arr = new ArrayList<Lesson>();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws IOException if an I/O error occurs
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Table example</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>");
            out.println("</h1>");
            out.println("<h1>Расписание занятий в институте</h1>");
            out.println("<form method='post'>");
            out.println("<h2>Добавить занятие</h2>");
            out.println("<input name='startTime' type='time'>");
            out.println("<input name='subjectName'>");
            out.println("<select name='lessonType'>");
            out.println("<option>Л</option>");
            out.println("<option>ПР</option>");
            out.println("<option>ЛР</option>");
            out.println("<option>КР</option>");
            out.println("<option>КП</option>");
            out.println("</select>");
            out.println("<input name='classroom'>");
            out.println("<select name='buildingAddress'>");
            out.println("<option>Большая Морская 67</option>");
            out.println("<option>Гастелло 15</option>");
            out.println("<option>Ленсовета 14</option>");
            out.println("<option>Московский 149</option>");
            out.println("</select>");
            out.println("<button type='submit'>Добавить</button>");
            out.println("</form>");
            out.println("<h2>Расписание занятий</h2>");
            out.println("<table>");
            out.println("    <thead>");
            out.println("        <tr>");
            out.println("            <th>Время начала</th>");
            out.println("            <th>Дисциплина</th>");
            out.println("            <th>Тип занятия</th>");
            out.println("            <th>Аудитория</th>");
            out.println("            <th>Адрес здания</th>");
            out.println("        </tr>");
            out.println("    </thead>");
            out.println("    <tbody>");
            for (Lesson ar : arr) {
                out.println("        <tr>");
                out.println("            <td>" + ar.getStartTime() + "</td>");
                out.println("            <td>" + ar.getSubjectName() + "</td>");
                out.println("            <td>" + ar.getLessonType() + "</td>");
                out.println("            <td>" + ar.getClassroom() + "</td>");
                out.println("            <td>" + ar.getBuildingAddress() + "</td>");
                out.println("        </tr>");
            }

            out.println("    </tbody>");
            out.println("</table>");
            out.print("<form method='POST'> <input type='hidden' name='_method' value='DELETE'/><button type='submit'>Очистить</button></form>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String method = request.getParameter("_method");
        if ("DELETE".equalsIgnoreCase(method)) {
            doDelete(request, response); // Вызываем обработку DELETE-запроса
        } else {
            Lesson newLesson = new Lesson(request.getParameter("startTime"),
                    request.getParameter("subjectName"),
                    request.getParameter("lessonType"),
                    request.getParameter("classroom"),
                    request.getParameter("buildingAddress"));
            arr.add(newLesson);
            processRequest(request, response);
        }

    }

    /**
     * Handles the HTTP <code>DELETE</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        arr.clear();
        processRequest(request, response);
    }

}
