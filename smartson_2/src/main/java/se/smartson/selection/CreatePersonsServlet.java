/**
 * Copyright 2014-2015 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
//[START all]
package se.smartson.selection;

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

public class CreatePersonsServlet extends HttpServlet {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    
		Long a = new Long(445);
		List<String> questions = Arrays.asList(new String[]{"Äter du?","Sover du?","Samlar du på mynt?"});
        List<Long> campaigns = Arrays.asList(a);
        
        Campaign newCampaign = new Campaign("ChokladKampanj", a, "This is a chocolate campaign!", questions);

        ObjectifyService.ofy().save().entity(newCampaign).now();

        BufferedReader br = new BufferedReader(
                   new InputStreamReader(new FileInputStream("test.csv"), "UTF-8"));
        
        String str;
        while ((str = br.readLine()) != null) {
            
            List<String> list = new ArrayList<String>(Arrays.asList(str.split(";")));
            
            String firstname = list.remove(0);
            String secondname = list.remove(0);
            int birthyear = Integer.parseInt(list.remove(0));
            int postnumber = Integer.parseInt(list.remove(0));
            String adress = list.remove(0);
            String city = list.remove(0);
            
            boolean gender = true;
            
            String g = list.remove(0);
            if (g  == "Kvinna") { gender = false;}
         
            
            String email = list.remove(0);
            
            String a1 = list.remove(0);
            String a2 = list.remove(0);
            String a3 = list.remove(0);
            
            boolean answer1 = true;
            boolean answer2 = true;
            boolean answer3 = true;
            
            if (a1 == "Nej"){answer1 = false;}
            if (a2 == "Nej"){answer2 = false;}
            if (a3 == "Nej"){answer3 = false;}
          
            
            System.out.println( firstname + secondname + birthyear + postnumber + adress + city + gender + answer1 + answer2 + answer3);
            
            List answers = Arrays.asList(answer1,answer2,answer3);
            
            HashMap<String, List<Boolean>> answerMap= new HashMap<String, List<Boolean>>();
            answerMap.put(a.toString(), answers);
            
            
            Person tempPerson = new Person(firstname,secondname,postnumber,birthyear,email, city,adress,gender, campaigns,answerMap);
            ObjectifyService.ofy().save().entity(tempPerson).now();
            
        }
        
        br.close();




    // resp.sendRedirect("/guestbook.jsp?guestbookName=" + guestbookName);
    resp.sendRedirect("/welcome.jsp");
  }
}
//[END all]
