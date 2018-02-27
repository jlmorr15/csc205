import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Demonstrates the use of a stack to evaluate postfix expressions.
 *
 * @author Lewis and Chase
 * @version 4.0
 * @Revised By: Justin Morris
 * 
 */
public class PostfixTester    
{
    /**
     * Reads and evaluates multiple postfix expressions.
     */
    public static void main(String[] args)
    {
        String expression, again;
        int result;
    
        Scanner in = new Scanner(System.in);

    	do
        {  
            PostfixEvaluator evaluator = new PostfixEvaluator();
			System.out.println("Postfix Expression Evaluator - Justin Morris");
			System.out.print("Post-fix Expression: ");
			
			expression = in.nextLine();
			
			try {
				result = evaluator.evaluate(expression);
                System.out.println();
                System.out.println("That expression equals " + result);
			}
	        catch(NumberFormatException ex) {
	        	System.out.println("There was an error in your postfix expression, did you try entering a non-numeric and non-operator value?");
	        }
			catch(ArithmeticException ex)
			{
				System.out.println(ex.getMessage());
			}
			catch(EmptyStackException ex) {
	    		System.out.println("There was an error in your postfix expression, I found an operator with an insufficient amount of numbers to apply it to, did you attempt infix (e.g. 5 < 4)?");
	    	}

            

            System.out.print("Evaluate another expression [Y/N]? ");
            again = in.nextLine();
            System.out.println();
        }
        while (again.equalsIgnoreCase("y"));  
        
        in.close();
  }
}
