
package dictionary;
import java.util.*;



/**
 *
 * @author Samy Barakat
 */

public class Dictionary {

    
    String userInput;
    Map<String, Map<String, List<String>>> wordbatch = new TreeMap<String, Map<String, List<String>>>();
    
       // For word , parts of speech, and description

    // Here, we have our data base
    private enum dataCollection {
        // Here, we have our data
        a1("Arrow", "noun", "Here is one arrow: <IMG> -=>> </IMG>"),
        a2("Distinct", "adjective", "Familiar. Worked in Java."),
        a3("Distinct", "adjective", "Unique. No duplicates. Clearly different or of a different kind."),
        a4("Distinct", "adverb", "Uniquely. Written \"distinctly\"."),
        a5("Distinct", "noun", "A keyword in this assignment."),
        a6("Distinct", "noun", "A keyword in this assignment."),
        a7("Distinct", "noun", "A keyword in this assignment."),
        a8("Distinct", "noun", "An advanced search option."),
        a9("Distinct", "noun", "Distinct is a parameter in this assignment."),
        a10("CSC340", "adjective", "= C++ version of CSC210 + CSC220 + more."),
        a11("CSC340", "noun", "A CS upper division course."),
        a12("CSC340", "noun", "Many hours outside of class."),
        a13("CSC340", "noun", "Programming Methodology."),
        a14("CSC220", "adjective", "Ready to create complex data structures."),
        a15("CSC220", "verb", "To create data structures."),
        a16("Book", "noun", "A set of pages."),
        a17("Book", "noun", "A written work published in printed or electronic form."),
        a18("Book", "verb", "To arrange for someone to have a seat on a plane."),
        a19("Book", "verb", "To arrange something on a particular date."),
        a20("Adverb", "noun", "Adverb is a word that adds more information about place, time, manner, cause or degree to a verb, an adjective, a phrase or another adverb."),
        a21("Adjective", "noun", "Adjective is a word that describes a person or thing, for example big, red and clever in a big house, red wine and a clever idea."),
        a22("Interjection", "noun", "Interjection is a short sound, word or phrase spoken suddenly to express an emotion. Oh!, Look out! and Ow! are interjection."),
        a23("Noun", "noun", "Noun is a word that refers to a person, (such as Ann or doctor), a place (such as Paris or city) or a thing, a quality or an activity (such as plant, sorrow or tennis."),
        a24("Placeholder", "adjective", "To be updated..."),
        a25("Placeholder", "adjective", "To be updated..."),
        a26("Placeholder", "adverb", "To be updated..."),
        a27("Placeholder", "conjunction", "To be updated..."),
        a28("Placeholder", "interjection", "To be updated..."),
        a29("Placeholder", "noun", "To be updated..."),
        a30("Placeholder", "noun", "To be updated..."),
        a31("Placeholder", "noun", "To be updated..."),
        a32("Placeholder", "preposition", "To be updated..."),
        a33("Placeholder", "pronoun", "To be updated..."),
        a34("Placeholder", "verb", "To be updated...");

        private String word;       // represents the word
        private String pospeech;   // represents the parts of speech
        private String descrip;    // represents the description

        private dataCollection(String word, String pospeech, String descrip) {
            this.word = word;
            this.pospeech = pospeech;
            this.descrip = descrip;
        }

        // Here, we create the getter of word
        public String getWord() {
            return word;
        }

        // Here, we create the getter of pospeech
        public String getPospeech() {
            return pospeech;
        }

        // Here, we create the getter of descrip
        public String getDescrip() {
            return descrip;
        }
    }

    // Here, we get data from dataCollection
    private void getData() {

        // We print this to obtain desired output
        System.out.println("! Loading data...");

        dataCollection[] datas = dataCollection.values();

        for (dataCollection data : datas) {
            String word = "";               // Temporarily store word here
            String pospeech = "";           // Temporarily store pospeech here
            String descrip = "";            // Temporarily store descrip here

            word = data.getWord();
            pospeech = data.getPospeech();
            descrip = data.getDescrip(); 
            
            // below, the if statement is saying:
            // if the wordbatch, which is the TreeMap, we created
            // if the TreeMap contains a word that is being mapped (true),
            // then we get the word
            
            // if the TreeMap we created contains a word that is mapped to a value (which is true)
            // then the map info gets the actual word
            
            // if the map, info, which contains the word we just chose, if it has a part of speech that is being mapped to a value (which is true)
            // then the newly created list, description, 
            
            
            if (wordbatch.containsKey(word)) {//map which include pospeech and descrip is existed
                Map info = wordbatch.get(word); // info has a datatype of map, so it is a map

                if (info.containsKey(pospeech)) {// pospeech list is existed
                    List description = (List) info.get(pospeech);                        // We obtain list of description
                    description.add(descrip);
                    info.put(pospeech, description);
                    wordbatch.put(word, info);
                } 
                
            else {// pospeech list is not existed
                    List description = new ArrayList<String>();                          // We create a new list of description
                    description.add(descrip);
                    info.put(pospeech, description);
                    wordbatch.put(word, info);
                }

            } 
            
            else {//map which include pospeech and descrip is not existed
                Map<String, List<String>> tempInfo = new TreeMap<String, List<String>>();   //temporary store <pospeech, list of description>
                List<String> tempDescrip = new ArrayList<String>();                         //temporary list of description
                tempDescrip.add(descrip);
                tempInfo.put(pospeech, tempDescrip);
                wordbatch.put(word, tempInfo);
            }
        }


        /*
         for ( String key : wordbatch.keySet()){
         System.out.println("        " + key);
         Map<String, List<String>> tempPospeech = new TreeMap<String, List<String>>();

         tempPospeech = wordbatch.get(key);

         for ( String key1 : tempPospeech.keySet()){
         System.out.println(key1);
         List<String> temp = new ArrayList<String>();
         temp = tempPos.get(key1);
         System.out.println(temp);
         }
         }
         */
        
        System.out.println("! Loading completed...");
    }   // This is the end of get data from dataCollection

    private void operation(String insertion) {
        String[] partOfSpeech = {"adjective", "adverb", "conjunction", "interjection", "noun", "preposition", "pronoun", "verb"}; //all parts of speech
        Queue<String> tokens = new LinkedList<>();
        boolean isfound = false;

        Scanner scan = new Scanner(insertion);
        scan.useDelimiter(" ");
        while (scan.hasNext()) {
            boolean isNumber = false;

            String temp = scan.next();
            temp.trim();

            // Here, we see if there is number
            char[] chars = temp.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    isNumber = true;
                }
            }

            // Here, we change String to proper form
            if (!isNumber) {
                temp = temp.toLowerCase();
                temp = temp.substring(0, 1).toUpperCase() + temp.substring(1);
            } 
            
            else {
                temp = temp.toUpperCase();
            }

            tokens.add(temp);
        }

        String searchingWord = tokens.poll();           //get first input word
        if (tokens.isEmpty()) {//no other input words
            for (String key : wordbatch.keySet()) {
                if (key.equals(searchingWord)) {
                    isfound = true;
                    Map<String, List<String>> tempPospeech = new TreeMap<String, List<String>>();
                    tempPospeech = wordbatch.get(key);

                    for (String key1 : tempPospeech.keySet()) {
                        List<String> temp = new ArrayList<String>();
                        temp = tempPospeech.get(key1);
                        for (String key2 : temp) {
                            System.out.println("    " + key + "[" + key1 + "] : " + key2);
                        }
                    }
                }
            }
        } else {
            String searchingWord2 = tokens.poll();      //get the second word
            searchingWord2 = searchingWord2.toLowerCase();

            //check second word
            if (searchingWord2.equals("distinct")) {
                List<String> output = new ArrayList<>();

                for (String key : wordbatch.keySet()) {
                    if (key.equals(searchingWord)) {
                        isfound = true;
                        Map<String, List<String>> tempPospeech = new TreeMap<String, List<String>>();
                        tempPospeech = wordbatch.get(key);

                        for (String key1 : tempPospeech.keySet()) {
                            List<String> temp = new ArrayList<String>();
                            temp = tempPospeech.get(key1);
                            for (String key2 : temp) {
                                boolean isExisted = false;

                                for (String o : output) {
                                    if (key2.equals(o))
                                        isExisted = true;
                                }

                                if (!isExisted) {
                                    System.out.println("    " + key + "[" + key1 + "] : " + key2);
                                    output.add(key2);
                                }
                            }
                        }
                    }
                }
            } else {
                boolean isDistinct = false;
                boolean isPartOfspeech = false;
                String searchingWord3 = "";
                //check if there is a third words in input
                if (!tokens.isEmpty())
                    searchingWord3 = tokens.poll();
                //check if it is distinct
                if (searchingWord3.equals("Distinct"))
                    isDistinct = true;
                //check if second word is one of the parts of speech
                for (String s : partOfSpeech) {
                    if (searchingWord2.equals(s))
                        isPartOfspeech = true;
                }

                //output
                if (isPartOfspeech) {
                    List<String> output = new ArrayList<>();

                    for (String key : wordbatch.keySet()) {
                        if (key.equals(searchingWord)) {
                            isfound = true;
                            Map<String, List<String>> tempPospeech = new TreeMap<String, List<String>>();
                            tempPospeech = wordbatch.get(key);

                            for (String key1 : tempPospeech.keySet()) {
                                if (key1.equals(searchingWord2)) {
                                    List<String> temp = new ArrayList<String>();
                                    temp = tempPospeech.get(key1);

                                    for (String key2 : temp) {
                                        boolean isExisted = false;

                                        //If third word is distinct
                                        if (isDistinct) {
                                            for (String o : output) {
                                                if (key2.equals(o))
                                                    isExisted = true;
                                            }

                                            if (!isExisted) {
                                                System.out.println("    " + key + "[" + key1 + "] : " + key2);
                                                output.add(key2);
                                            }

                                        } else {
                                            System.out.println("    " + key + "[" + key1 + "] : " + key2);
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("    <2nd argument must be a part of speech or \"distinct\">");
                }

            }
        }
        if (!isfound)
            System.out.println("    <Not found>");
    }


    public static void main(String[] args) {
        Dictionary dic = new Dictionary();
        dic.getData();
        String insertion = "";
        System.out.println("\n-----DICTIONARY 340 JAVA-----\n");
        while (!insertion.equals("!Q")) {
            System.out.print("Search: ");
            Scanner scanner = new Scanner(System.in);
            insertion = scanner.nextLine();
            if (insertion.equals("!Q")) {
                System.out.println("-----THANK YOU-----");
            } else {
                System.out.println("   |");
                dic.operation(insertion);
                System.out.println("   |");
            }
        }
    }

}

