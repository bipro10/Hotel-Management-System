package model;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		
		if(uid.equals("Biprodas") && pwd.equals("bipro")){
			//response.getWriter().print("Hello <FONT COLOR=GREEN> <B>"+uid+" </B> </FONT>");
			HttpSession session = request.getSession();
			session.setAttribute("userid", uid);
			response.sendRedirect("index.jsp");

		}
		else{
			response.sendRedirect("login.jsp");

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
