import java.util.Stack;
import java.util.Scanner;

/**
 * Represents an integer evaluator of postfix expressions. Assumes 
 * the operands are constants.
 *
 * @author Lewis and Chase
 * @version 4.0
 */
public class PostfixEvaluator
{
    private Stack<Integer> stack;

    /**
     * Sets up this evalutor by creating a new stack.
     */
    public PostfixEvaluator()
    {
        stack = new Stack<Integer>();
    }
    
    
    
    
    
    /**
     * Evaluates the specified postfix expression. If an operand is
     * encountered, it is pushed onto the stack. If an operator is
     * encountered, two operands are popped, the operation is
     * evaluated, and the result is pushed onto the stack.
     * @param expr string representation of a postfix expression
     * @return value of the given expression
     */
    public int evaluate(String expr) throws ArithmeticException
    {
        int op1, op2, op3, result = 0;
        String token;
        Scanner parser = new Scanner(expr);

        while (parser.hasNext())
        {
            token = parser.next();
            
            if(Operator.isValidOperator(token))
            {
            	try {
            		Operator operator = new Operator(token);
            		switch(operator.numberOfOperands())
            		{
            			case 1:
            				if(stack.size()<1)
            					throw new InvalidOperatorException("ERROR: Insufficient operands for "+token);
            				result = operator.evaluateOperands((stack.pop().intValue()));
            				break;
            			case 2:
            				if(stack.size()<2)
            					throw new InvalidOperatorException("ERROR: Insufficient operands for "+token);
            				op1 = stack.pop().intValue(); //First off
            				op2 = stack.pop().intValue(); //Last off
            				result = operator.evaluateOperands(op2, op1);
            				break;
            			case 3:
            				if(stack.size()<3)
            					throw new InvalidOperatorException("ERROR: Insufficient operands for "+token);
            				op1 = stack.pop().intValue(); //First off
            				op2 = stack.pop().intValue(); //Middle child?
            				op3 = stack.pop().intValue(); //Last off
            				result = operator.evaluateOperands(op3, op2, op1);
            				
            				//Test misuse of method
//            				op1 = stack.pop().intValue(); //First off
//            				op2 = stack.pop().intValue(); //Middle child?
//            				result = operator.evaluateOperands(op3, op2);
            				break;
            		}
            		stack.push(Integer.valueOf(result)); //Updated deprecated "new Integer(result)" usage - JM
            	}
            	catch(InvalidOperatorException ex)
            	{
            		System.out.println(ex.getMessage());
            	}
            } else {
            	stack.push(Integer.parseInt(token)); //Updated deprecated usage of "new Integer(value)" usage - JM
            }
        }
        if(stack.size()>1)
        	throw new ArithmeticException("Too many operands compared to operators. (e.g. 1 2 3 +)");
        return result;
    }
}

