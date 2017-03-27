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

import com.googlecode.objectify.ObjectifyService;
import se.smartson.selection.Campaign;
import se.smartson.selection.Person;

public class LoadServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, NullPointerException {
		
		HttpSession session = req.getSession();
		
		Long c = Long.parseLong(req.getParameter("campaign"));
		
		
		Campaign campaign = ObjectifyService.ofy()
				.load()
				.type(Campaign.class)
				.id(c)
				.now();
		
		if (campaign == null) {
			System.out.println("Empty");
			resp.sendRedirect("/welcome.jsp");
		}
		
		
		List<Person> persons = ObjectifyService.ofy()
				.load()
				.type(Person.class)
				.filter("campaigns", c)
				.list();
		
		//System.out.println(campaign.id);
		//System.out.println(persons.size());
		
		if (persons.size() <= 0) {
			System.out.println("Empty");
			resp.sendRedirect("/welcome.jsp");
		}
		
		session.setAttribute("campaign", campaign);
		session.setAttribute("persons", persons);
		
		resp.sendRedirect("/selection.jsp");
		
		/*ServletContext sc = getServletContext();
	    *RequestDispatcher rd = sc.getRequestDispatcher("/selection.jsp");

	    *req.setAttribute("campaign", campaign);
	    *req.setAttribute("persons", persons);
	    *rd.forward(req, resp);
	    */
  }
}

