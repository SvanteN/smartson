

package se.smartson.selection;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

import java.lang.String;
import java.util.Date;
import java.util.List;

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

// Hej
@Entity
public class Person {
	//HEJ Svante
  @Id public Long id;

  public String firstname;

  public String secondname ;

 
  public int postnumber;

  public int birthyear;

  @Index public List<Long> campaigns;



  /**
   * Takes all important field
   **/
  
  public Person() {
	  
  }
  
  public Person(String n, String s, int p, int y, List<Long> c){
    this();
    firstname = n;
    secondname = s;
    postnumber= p;
    birthyear = y;
    campaigns = c;

    
  }

}
