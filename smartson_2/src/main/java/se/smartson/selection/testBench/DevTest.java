package se.smartson.selection;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.googlecode.objectify.ObjectifyService.ofy;
import se.smartson.selection.TestEntity;

/**
 * Form Handling Servlet
 * Most of the action for this sample is in webapp/guestbook.jsp, which displays the
 * {@link Greeting}'s. This servlet has one method
 * {@link #doPost(<#HttpServletRequest req#>, <#HttpServletResponse resp#>)} which takes the form
 * data and saves it.
 */
public class DevTest extends HttpServlet {

	@Override
  	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


	  	// Process the http POST of the form
		TestEntity t = new TestEntity();
		ofy().save().entity(t).now();

	    TestEntity te = ofy().load().type(TestEntity.class).first().now();
	    System.out.println(te.qHm.get("A"));
	    te = ofy().load().type(TestEntity.class).filter("qHm.A =", "S2").first().now();
	    System.out.println(te.qHm.get("A"));

	    resp.sendRedirect("/welcome.jsp");
  }
}
//[END all]
