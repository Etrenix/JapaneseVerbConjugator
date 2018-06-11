import java.util.*;
/**
 * Write a description of class Verb here.
 *
 * @author Owen Santoso
 * @version 10/06/2018
 */
public class Verb
{
    // instance variables - replace the example below with your own
    private static final String VOWELS = " aiueo";
    private static final String CONSONANTS = " kgsjztdnfhbpmrwy";
    private String word, theword, group;
    private ArrayList<String> roma = new ArrayList<String>();
    private HashMap RH = new HashMap();

    /**
     * Constructor for objects of class Verb
     * Initialises variables and checks for valid verb input
     */
    public Verb(String w) throws IllegalArgumentException
    {
        // initialise instance variables
        if (w.charAt(w.length()-1) != 'u')
            throw new IllegalArgumentException("Please enter a valid Japanese verb in plain form (ends in u)");
        this.word = w;
        theword = w;
        initialiseRHConverter();
        createArray();
        whichGroup();

    }

    private void initialiseRHConverter()
    {
        FileIO yote = new FileIO("RH.txt");
        for(int i = 1; i < yote.lines.size(); i = i+2){
            RH.put(yote.lines.get(i), yote.lines.get(i+1));
        }
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void whichGroup()
    {
        // put your code here
        if (theword.charAt(theword.length()-2) == 'r'){
            if (theword.charAt(theword.length()-3) == 'i' || theword.charAt(theword.length()-3) == 'e')
                group = "ichidan";
        }
        else group = "godan";
        if (theword.equals("suru") || theword.equals("kuru"))
            group = word;
    }

    private static boolean isVowel(char c){return VOWELS.indexOf(Character.toLowerCase(c)) > 0;}

    private static boolean isConsonant(char c){return CONSONANTS.indexOf(Character.toLowerCase(c)) > 0;}

    public void createArray() throws IllegalArgumentException
    {
        for(int i = 0; i < theword.length(); i++){
            char letter1 = theword.charAt(i);
            char letter2 = theword.charAt(i+1);
            if(isVowel(letter1)){
                roma.add(Character.toString(letter1));
            }
            else if(letter1 == 'n' && isConsonant(letter2)){
                roma.add(Character.toString(letter1));
            }       
            else if(isConsonant(letter1) && letter1 == letter2){
                roma.add(Character.toString(letter1));
            }
            else if(letter2 == 'h' && (letter1 == 's' || letter1 == 'c') ||
                    letter2 == 'y' && (letter1 == 'k' || letter1 == 'g' || letter1 == 'b' || letter1 == 'p') ||
                    letter2 == 's' && letter1 == 't'){
                roma.add(theword.substring(i, i+3));
                i += 2;
            }
            else if(isConsonant(letter1)){
                roma.add(theword.substring(i, i+2));
                i++;
            } else throw new IllegalArgumentException("Please enter a valid Japanese verb in plain form (invalid romaji input)");
        }
    }

    public void convertToHiragana(){
        for(String x : roma){
            System.out.print(RH.get(x));
        }
        System.out.println("");
    }

    public void conjugateTe() throws IllegalArgumentException
    {
        word = theword.substring(0, theword.length() - 2);
        switch (group) {
            case "godan"  : switch (theword.charAt(theword.length() - 2)){
                case 'k': word += "ite";
                break;
                case 'g': word += "ide";
                break;
                case 'a': word += "atte";
                break;
                case 'r': word += "tte";
                break;
                case 's': if(theword.charAt(theword.length() - 3) == 't')
                    word += "tte";
                else word += "shite";
                break;
                case 'n': 
                case 'm':
                case 'b': word += "nde";
                break;
                default : throw new IllegalArgumentException("Input was invalid.");
            }
            break;
            case "ichidan": word = theword.substring(0, theword.length() - 2) + "te";
            break;
            case "suru"   : word = "shite";
            break;
            case "kuru"   : word = "kite";
            break;
        }
        System.out.println(word);
    }

    public void conjugatePast()
    {
        conjugateTe();
        word = word.substring(0, word.length() - 1) + "a";
        System.out.println(word);
    }
    
    public void conjugatePolite() throws IllegalArgumentException
    {
        // put your code here
        switch (group) {
            case "godan"  : word = theword.substring(0, theword.length() - 1) + "imasu";
            break;
            case "ichidan": word = theword.substring(0, theword.length() - 2) + "masu";
            break;
            case "suru"   : word = "shimasu";
            break;
            case "kuru"   : word = "kimasu";
            break;
            default: throw new IllegalArgumentException("Input was invalid.");
        }
        System.out.println(word);
    }

    public void conjugatePolitePast()
    {
        conjugatePolite();
        word = word.substring(0, word.length() - 1) + "hita";
        System.out.println(word);
    }
    
    public void conjugate(){
        
    }
}
