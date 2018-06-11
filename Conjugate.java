import java.util.*;
/**
 * Write a description of class Conjugate here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Conjugate
{
    // instance variables - replace the example below with your own
    private Verb verb;
    /**
     * Conjugates to -te form
     */
    public Conjugate(String v)
    {
        // initialise instance variables
        this.verb = new Verb(v);
        verb.conjugateTe();
        conjugateInput();
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public void conjugateInput()
    {
        Scanner reader = new Scanner(System.in);
        String n;
        int x = 0;
        do{
            System.out.printf("Type a Japanese verb in plain form to conjugate it to %s \n", "te form");
            while(x < 1) {System.out.println("Type END to exit"); x++;}
            n = reader.next();
            Verb yeet = new Verb(n);
            yeet.conjugateTe();
        }while(n != "END");
    }

}
