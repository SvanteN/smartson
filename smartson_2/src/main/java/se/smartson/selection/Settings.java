package se.smartson.selection;


import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;


public class Settings {

    //Person attributes according to csv-headers
    static HashMap<String, String> pA;

    //Application attributes according to csv-headers
    static HashMap<String, String> aA;

    //AttributeDictionary; to convert csv-header-name to java object attribute name
    static HashMap<String, String> aD;

    //campaignAttributes
    static HashMap<String, String> cA;


    //ignoreArray ; List with headers that we want to ignore
    static ArrayList<String> iA;

    //Given language that the csv headers are in
    static String language;



    public Settings(String lang) {

        language = lang;
        init();

        System.out.println("klart");



    }
        public static void init(){
        try {

            File fXmlFile = new File("config2.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            //Specified attributes in config file, to minimize for-loop
            String[] attributeList = {"personAttribute","applicationAttribute","ignoreHeader","campaignAttribute"};

            pA = new HashMap<String, String>();
            aA = new HashMap<String, String>();
            aD = new HashMap<String, String>();
            cA = new HashMap<String, String>();
            iA = new ArrayList<String>();

            // go through every attribute in attribute list
            for (String attribute:attributeList) {
                NodeList nList = doc.getElementsByTagName(attribute);

                // Creating personalAttribute hashmap and attributeDictionary
                //Removing attributes and data from xml file and putting them in correct hashmap according to person- or application attributes
                for (int temp = 0; temp < nList.getLength(); temp++) {

                    Node nNode = nList.item(temp);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;

                        //if attribute is ignoreHeader we will get the value of the tag and put it in our ignoreHeaderlist
                        if (attribute == "ignoreHeader"){
                            String ignoreHeader = eElement.getTextContent();
                            iA.add(ignoreHeader);
                        }

                        // if attribute is person or application or campaign we will get the values of the tag and put in our hashmaps.
                        else {
                            String id = eElement.getAttribute("id");

                            // get the translation of attribute thorugh the specified language
                            String translate = eElement.getElementsByTagName(language).item(0).getTextContent();

                            // get the format method that will check and correct our input
                            String formatMethod = eElement.getElementsByTagName("formatMethod").item(0).getTextContent();

                            if (attribute == "campaignAttribute"){
                                cA.put(translate,formatMethod);
                            }

                            else if (attribute == "personAttribute") {
                                pA.put(translate, formatMethod);
                            }
                            else {
                                aA.put(translate,formatMethod);
                            }
                            aD.put(translate, id);
                    }
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("fic");
    }
}