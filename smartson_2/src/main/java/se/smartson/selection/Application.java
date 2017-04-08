package se.smartson.selection;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * The @Entity tells Objectify about our entity.  We also register it in OfyHelper.java -- very
 * important. Our primary key @Id is set automatically by the Google Datastore for us.
 *
 * We add a @Parent to tell the object about its ancestor. We are doing this to support many
 * guestbooks.  Objectify, unlike the AppEngine library requires that you specify the fields you
 * want to index using @Index.  This is often a huge win in performance -- though if you don't Index
 * your data from the start, you'll have to go back and index it later.
 *
 * NOTE - all the properties are PUBLIC to keep this simple; otherwise,
 * Jackson wants us to write a BeanSerializer for cloud endpoints.
 **/
@Entity
public class Application implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	String entryId;
	String date;
	String productName;
	String ip;
	String recommendFriend;
	String couponFromStore;
	String email;

	String firstname;
	String lastname ;
	int zip;
	String city;
	String gender;
	String adress;
	int birthyear;
	HashMap<String, List<String>> qHm = new HashMap<String, List<String>>();

	public Application() {
		
		
	}
	public Application(String e,String d,String p, String i, String r, String c, String em){
	    this();
	    entryId = e;
	    date = d;
	    productName = d;
	    ip = i;
	    recommendFriend = r;
	    couponFromStore = c;
	    email = em;

	    
	}

	public void setInstance(String fieldName, String value, String checkMethodName) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, SecurityException, InvocationTargetException {

		//Get instance field
		Field field = getClass().getDeclaredField(fieldName);

		Object instancevalue = value;
		//System.out.println(fieldName);

		//If checkMethodName isn't null, we must perform the formatcheck of input
		if (checkMethodName != ""){

			//System.out.println(checkMethodName);

			Method method = InputValidation.class.getDeclaredMethod(checkMethodName,String.class);
			instancevalue = method.invoke(null, value);
		}

		field.set(this,instancevalue);
	}

	public void setNewQuestion(String q, String a){
		List answers = new ArrayList<String>();
		answers.add(a);
		qHm.put(q,answers);
	}

	public void addAnswerToQuestion(String q, String a){
		List answerList = qHm.get(q);
		if (!answerList.contains(a)){
			answerList.add(a);}

	}


	}

