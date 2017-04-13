package se.smartson.selection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import se.smartson.selection.Person;
import se.smartson.selection.Campaign;
import com.googlecode.objectify.ObjectifyService;


public class CsvParserServlet extends HttpServlet  {
	private static final long serialVersionUID = 1L;

	
	static Settings settings;


	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Settings settings = new Settings("swe");

		try {
			parseCsvToObjects();
		} catch (NoSuchFieldException | IllegalAccessException | NoSuchMethodException | SecurityException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		resp.sendRedirect("/welcome.jsp");

	}

	private static void parseCsvToObjects() throws IOException,NoSuchFieldException,IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException{



		// Ought to be removed when using csv as input
	    BufferedReader br = new BufferedReader(
	            new InputStreamReader(new FileInputStream("TestforSmartson.csv"), "UTF-8"));

	    // First row of csv-file, ergo the "headerRow"
	    String[] headerRow = br.readLine().split(";");

		int count = 0;
		String row;
		int emptyHeaders = 1;


		Campaign c =  new Campaign();

	    //Iterate through every row
	    while ((row = br.readLine()) != null) {

	    	//Creating a new person and a new Application for every row
	    	Person p = new Person();
	    	Application a = new Application(); //TODO: Force to send campaign Key

			// for every row, we go through every column of the csv, special split function to split on ";" but not the ";" incapsled by quotation marks.
			String[] rowValues = row.split(";(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
	    	for (int i = 0 ; i<rowValues.length; i++){

	    		//remove double quotation mark from input
				rowValues[i] = InputValidation.trimAndRemove(rowValues[i]);

				// If header is empty we will set it to undefined and then treat it with the same algoritm
				if (headerRow[i].equals("")){
					headerRow[i] = "Undefined nr:"+ emptyHeaders;
					emptyHeaders ++;
				}

				//If header is in our ignoreList the column will be skipped.
				if (settings.iA.contains(headerRow[i])){
					continue;
				}
				//If header in personAttribute, or applicationAttribute or campaignAttribute,
				else if (settings.pA.containsKey(headerRow[i]) || settings.aA.containsKey(headerRow[i]) || settings.cA.containsKey(headerRow[i]) ){

	    			// If header in personAttribute, we setinstance to the personobject
	    			if (settings.pA.containsKey(headerRow[i])){
						p.setInstance(settings.aD.get(headerRow[i]),rowValues[i],settings.pA.get(headerRow[i]));
					}
					// If header in applicationAttribute, we set instance to the applicationobject
	    			if (settings.aA.containsKey(headerRow[i])){
						a.setInstance(settings.aD.get(headerRow[i]),rowValues[i],settings.aA.get(headerRow[i]));
					}
					//We want to check headers that belong to campaign just once, because they are static; e.g: ProductName or Canvas URL.
					if (count < 1 && settings.cA.containsKey(headerRow[i])){
						c.setInstance(settings.aD.get(headerRow[i]),rowValues[i],settings.cA.get(headerRow[i]));
					}

				}

				//If the queston (header) isn't in the question hashmap, we will generate a new object in hashmap with question (header)
				// as key and then a list with answer as value
				else if (a.qHm.get(headerRow[i]) == null) {
						a.setNewQuestion(headerRow[i],rowValues[i]);
						
						//We will also append the answer and question to campaignobject
						c.checkQuestionInCampaign(headerRow[i],rowValues[i]);
						}
				else{
					// If question (header) is already in questionHashmap we will get the list with answers,
					// check if answer is already list; otherwise append the answer to the answerlist
					a.addAnswerToQuestion(headerRow[i],rowValues[i]);
					
					//We will also append the answer and question to campaignobject
					c.checkQuestionInCampaign(headerRow[i],rowValues[i]);
			}

	    	}
	    	count ++;

			// Here we add the persons and application to datastore
			DatastoreUpdate.addApplication(a);
			DatastoreUpdate.addPerson(p);

	    }

	    // If there is multiple answers, ergo >2 we don't want "" (Empty string) in our alternatives; therefore we remove out the answer "".
		c.multipleAnswerSpecialCheck();
	    c.participants = count;
	    
	    DatastoreUpdate.addCampaign(c);


	    System.out.println(count);
	}
}

