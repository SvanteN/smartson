package se.smartson.selection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import se.smartson.selection.Campaign;
import se.smartson.selection.Person;

public class FilterServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		List<Person> persons = (List<Person>) session.getAttribute("persons");
		List<Person> personsFiltered = persons;
		
		int genderM = (int) req.getAttribute("gender");
		int genderF = 100 - genderM;
		int living = (int) req.getAttribute("living");
		
		
		
	    req.setAttribute("personsFiltered", personsFiltered);
  }
}


