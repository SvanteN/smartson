package se.smartson.selection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.smartson.selection.Campaign;
import se.smartson.selection.Person;

public class FilterServlet extends HttpServlet {
 
	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		
		HttpSession session = req.getSession();
		List<Person> persons = (List<Person>) session.getAttribute("persons");
		List<Person> personsFiltered = new ArrayList<Person>();
		Campaign campaign = (Campaign) session.getAttribute("campaign");
		
		//Hardcoded for trial
		String[] cities = new String[]{"Stockholm", "Gothenburg", "Malmo"};
		String[] locs = req.getParameterValues("loc");
		
		boolean pass;
		for (Person p:persons) {
			pass = true;
			for(String l:locs) {
				if (p.city.equals(l)) {
					pass = true;
					break;
				} else {
					pass = false;
				}
			}
			if (pass) {
				personsFiltered.add(p);
			}
			/*List<Boolean> answers = p.campaignAnswers.get(campaign.id.toString());
			for (Integer i=0; i<campaign.questions.size(); i++) {
				if (answers.get(i) != (boolean) req.getAttribute("yes" + i.toString()) || answers.get)
			}*/
		}
		
		System.out.println(personsFiltered);
		
		req.setAttribute("personsFiltered", personsFiltered);
	    
	    ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/selection.jsp");
		rd.forward(req, resp);
  }
}


