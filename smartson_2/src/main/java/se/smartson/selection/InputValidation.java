package se.smartson.selection;



/**
 * Created by Christian on 2017-04-08.
 */
public class InputValidation {

    public static String checkAndCapitalize(String s){

        // replace all symbols that is not letters or "-"
        String[] sList = s.trim().toLowerCase().replaceAll(" +", " ").replaceAll("-+", "-").replaceAll("[^a-zA-Z -]","").split("");

        String sChecked = "";

        //state to check if next string are uppercase
        boolean state = true;
        String nextString = null;

        // Iterate through every letter in word
        for (String str: sList){

            //If state is true, make next letter uppercase
            if (state){nextString = str.toUpperCase();state=false;}

            else {nextString = str;}

            // Check if string is space or "-", in that case next letter should be uppercase, ergo state=true
            if (str.equals(" ") || str.equals("-")){state=true;}
            sChecked += nextString;

        }

        return sChecked;}

    public static int checkOnlyNumbers(String s){
        return Integer.parseInt(s.replaceAll("[^0-9]",""));
    }

    public static String trimAndRemove(String s){
        return s.trim().replaceAll("^\"|\"$", "");
    }

}
