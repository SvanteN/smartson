package se.smartson.selection;

import com.googlecode.objectify.ObjectifyService;

public class DatastoreUpdate {
	
//OLD CODE:
	// ObjectifyService.ofy().save().entity(tempPerson).now();

	    public static void addPerson(Person p){

	        // check if person datastore?
	        checkPerson(p);
	        //TODO
	        // Here we can check if person is already in datastore and thereafter
	    }

	    public static void addApplication(Application a){

	        //TODO
	        // Here we add application to datastore
	    }

	    public static void checkPerson(Person p){

	        //TODO
	        //Check if person in datastore?
	    }

	    public static void addCampaign(Campaign c){

	        //TODO
	        //We add the campaign to datastore
	    }

	}



