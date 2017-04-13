

package se.smartson.selection;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.Key;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



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
@Cache
public class Person implements Serializable{

	private static final long serialVersionUID = 1L;


	public String firstname;
	public String lastname ;
	public int zip;
	public String city;

	public String adress;
	public int birthyear;
	@Id public String email;
	public String gender;
	public List<Long> campaigns;
	
	
  	// P
	public Person() {}
	public Person(String n, String s, int p, int y, String e, String ci, String a, String g, List<Long> c ){
	    this();
	    firstname = n;
	    lastname = s;
	    zip= p;
	    city = ci;
	    birthyear = y;
	    campaigns = c;
	    email = e;
	    gender = g;
	   
	    adress = a;
	    
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


}
