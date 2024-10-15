package checklist.controller;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import checklist.dao.UserLogic;
import checklist.dto.User;
@WebServlet("/signup")
public class SignUp extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long number=Long.parseLong(req.getParameter("number"));
		LocalDate dob=LocalDate.parse(req.getParameter("dob"));
		int age=LocalDate.now().getYear()-dob.getYear();
		
		UserLogic logic=new UserLogic();
		User l = logic.fetchByEmail(email);
		if(l==null)
		{
			User user=new User();
			user.setAge(age);
			user.setEmail(email);
			user.setDob(dob);
			user.setName(name);
			user.setPassword(password);
			user.setPhoneNumber(number);
			logic.addUser(user);
			resp.getWriter().print("<h1>Account created</h1>");
			req.getRequestDispatcher("login.html").include(req, resp);
			
		}
		else
		{
			resp.getWriter().print("<h1 style=color:red>email is present </h1>");
			req.getRequestDispatcher("signup.html").include(req, resp);
		}
	}
}











