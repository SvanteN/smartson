
package se.smartson.selection;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;

import java.io.Serializable;
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
@Entity
public class Campaign implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id public Long id;
	public String name;
	public String desc ;
	public List<String> questions;

	public Campaign(){}

	public Campaign(String n, Long i, String d, List<String> q){
		this();
		name = n;
		id = i;
		desc = d;
		questions = q;
	}
}
