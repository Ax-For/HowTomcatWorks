import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author dongyafei
 * @date 2021/11/30
 */
public class PrimitiveServlet implements Servlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("==================  init  ==================");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("==================  service start  ==================");
        PrintWriter writer = res.getWriter();
        writer.print("HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html\r\n\r\n");
        writer.println("Hello ,Road is red.");
        writer.println("Violets are blue.");
        System.out.println("==================  service end  ==================");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("==================  destroy  ==================");
    }

    public static void main(String[] args) {

    }
}
