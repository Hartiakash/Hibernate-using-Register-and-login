package checklist.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import checklist.dao.UserLogic;
import checklist.dto.User;

@WebServlet("/login")
public class Login extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		UserLogic logic=new UserLogic();
		User l = logic.fetchByEmail(email);
		if(l!=null)
		{
			if(l.getPassword().equals(password))
			{
				resp.getWriter().print("<h1 style=color:Green>welcome to home page </h1>");
				req.getRequestDispatcher("home.html").include(req, resp);
			}
			else
			{
				resp.getWriter().print("<h1 style=color:red>incorrect email or Password  </h1>");
				req.getRequestDispatcher("login.html").include(req, resp);
			}
		}
		else
		{
			resp.getWriter().print("<h1 style=color:red>incorrect  email or password  </h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
		}
	}
}
