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
    // TESTING CHANGES ON GITHUB
    private static final String VOWELS = " aiueo";
    private static final String CONSONANTS = " kgsjztdnfhbpmrwy";
    private String word;
    private String theword;
    private String group;
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
        RH.put("a", "あ");
        RH.put("i", "い");
        RH.put("u", "う");
        RH.put("e", "え");
        RH.put("o", "お");

        RH.put("ka", "か"); RH.put("ga", "が");
        RH.put("ki", "き"); RH.put("gi", "ぎ");
        RH.put("ku", "く"); RH.put("gu", "ぐ");
        RH.put("ke", "け"); RH.put("ge", "げ");
        RH.put("ko", "こ"); RH.put("go", "ご");

        RH.put("sa", "さ"); RH.put("za", "ざ");
        RH.put("shi", "し");RH.put("ji", "じ");
        RH.put("su", "す"); RH.put("zu", "ず");
        RH.put("se", "せ"); RH.put("ze", "ぜ");
        RH.put("so", "そ"); RH.put("zo", "ぞ");
        
        RH.put("ta", "た"); RH.put("da", "だ");
        RH.put("chi", "ち");RH.put("dzi", "ぢ");
        RH.put("tsu", "つ");RH.put("dzu", "づ");
        RH.put("te", "て"); RH.put("de", "で");
        RH.put("to", "と"); RH.put("do", "ど");

        RH.put("na", "な");
        RH.put("ni", "に");
        RH.put("nu", "ぬ");
        RH.put("ne", "ね");
        RH.put("no", "の");

        RH.put("ha", "は"); RH.put("ba", "ば"); RH.put("pa", "ぱ");
        RH.put("hi", "ひ"); RH.put("bi", "び"); RH.put("pi", "ぴ");
        RH.put("fu", "ふ"); RH.put("hu", "ふ"); RH.put("bu", "ぶ"); RH.put("pu", "ぷ");
        RH.put("he", "へ"); RH.put("be", "べ"); RH.put("pe", "ぺ");
        RH.put("ho", "ほ"); RH.put("bo", "ぼ"); RH.put("po", "ぽ");
        
        RH.put("ma", "ま");
        RH.put("mi", "み");
        RH.put("mu", "む");
        RH.put("me", "め");
        RH.put("mo", "も");

        RH.put("ra", "ら");
        RH.put("ri", "り");
        RH.put("ru", "る");
        RH.put("re", "れ");
        RH.put("ro", "ろ");

        RH.put("wa", "わ");
        RH.put("wo", "を");

        RH.put("ya", "や");
        RH.put("yu", "ゆ");
        RH.put("yo", "よ");
        
        RH.put("n", "ん");

        RH.put("kya", "きゃ"); RH.put("gya", "ぎゃ");
        RH.put("kyu", "きゅ"); RH.put("gyu", "ぎゅ");
        RH.put("kyo", "きょ"); RH.put("gyo", "ぎょ");

        RH.put("bya", "びゃ"); RH.put("pya", "ぴゃ");
        RH.put("byu", "びゅ"); RH.put("pyu", "ぴゅ");
        RH.put("byo", "びょ"); RH.put("pyo", "ぴょ");

        RH.put("sha", "しゃ"); RH.put("ja", "じゃ");
        RH.put("shu", "しゅ"); RH.put("ju", "じゅ");
        RH.put("sho", "しょ"); RH.put("jo", "じょ");

        RH.put("cha", "ちゃ");
        RH.put("chu", "ちゅ");
        RH.put("cho", "ちょ");

        RH.put("k", "っ");
        RH.put("s", "っ");
        RH.put("j", "っ");
        RH.put("t", "っ");
        RH.put("p", "っ");
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
