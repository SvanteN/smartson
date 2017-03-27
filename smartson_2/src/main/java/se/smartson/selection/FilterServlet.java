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
		
		boolean a = (boolean) req.getAttribute("loc1");
		boolean b = (boolean) req.getAttribute("loc2");
		boolean c = (boolean) req.getAttribute("loc3");
		
		//Hardcoded for trial
		List<String> cities = new ArrayList<String>(Arrays.asList("Stockholm", "Gothenburg", "Malmo"));
		boolean a = (boolean) req.getAttribute("loc1");
		boolean b = (boolean) req.getAttribute("loc2");
		boolean c = (boolean) req.getAttribute("loc3");
		List<Boolean> locs = new ArrayList<Boolean>(Arrays.asList(a, b, c));
		
		boolean pass;
		for (Person p:persons) {
			pass = true;
			if (!(boolean) req.getAttribute("loc4")) {
				for(int i=0; i<cities.size(); i++) {
					if (l.get(i) && p.city == cities.get(i)) {
						pass = true;
						break;
					} else {
						pass = false;
					}
				}
			}
			List<Boolean> answers = p.campaignAnswers.get(campaign.id.toString());
			for (int i=0; i<campaign.questions.size(); i++) {
				if (answer.get(i) ==)
			}
		}
		
		
		req.setAttribute("personsFiltered", personsFiltered);
	    
	    ServletContext sc = getServletContext();
		RequestDispatcher rd = sc.getRequestDispatcher("/selection.jsp");
		rd.forward(req, resp);
  }
}


