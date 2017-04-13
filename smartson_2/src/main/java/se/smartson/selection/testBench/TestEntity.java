package se.smartson.selection;



import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.googlecode.objectify.Key;


import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;




@Entity
public class TestEntity  {
	@Index public HashMap<String, List<String>> qHm;
	
	public String entryId;
	public String date;
	public String productName;
	public String ip;
	public String recommendFriend;
	public String couponFromStore;
	@Id public String email;

	public String firstname;
	public String lastname;
	public int zip;
	public String city;
	@Index public String gender;
	public String adress;
	@Index public int birthyear;
	// Constructor
	
	public TestEntity(){
		qHm = new HashMap<String, List<String>>();
		List answers = new ArrayList<String>();
		answers.add("S2");
		answers.add("S3");
		qHm.put("A", answers);
		List b = new ArrayList<String>();
		b.add("S1");
		qHm.put("B", b);

		entryId = "8";
		date = "2017-04-13";
		productName = "Allans Slang";
		ip = "192.168.1.1";
		recommendFriend = "Ja";
		couponFromStore = "Ja";
		email = "allan@gmail.se";
		firstname = "allan";
		lastname = "ballan";
		zip = 11559;
		city = "Stockholm";
		gender = "Man";
		adress = "Vägen 1";
		birthyear = 1991;
	}
	
}