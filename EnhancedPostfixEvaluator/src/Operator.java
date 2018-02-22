/**
 * Represents an operator in a postfix expression
 * @author Justin Morris
 *
 */


public class Operator {

	private static final String unaryOperators = "!~";
	private static final String binaryOperators = "+-*/^%=&|><";
	private static final String ternaryOperators = "?";
	
	private String operator;
	
	private int requiredOperands; //How many operands this operator requires.
	private int[] operands; //The operands we will be evaluating.
	
	
	/**
	 * Get all acceptable operators for our postfix evaluator
	 * @return
	 */
	public static String getAllOperators()
	{
		return Operator.unaryOperators + Operator.binaryOperators + Operator.ternaryOperators;
	}
	
	/**
	 * Determing if input is a valid operator in our postfix evaluator.
	 * @param operator
	 * @return
	 */
	public static Boolean isValidOperator(String operator)
	{
		return Operator.getAllOperators().contains(operator);
	}
	
	
	/**
	 * "New Up" our operator.
	 * @param operator
	 * @throws InvalidOperatorException
	 */
	public Operator(String operator) throws InvalidOperatorException
	{
		if(!Operator.isValidOperator(operator)) {
			throw new InvalidOperatorException("You can't use "+operator+" as an operator.");
		}
		this.operator = operator;
		this.defineOperatorBehavior();
	}
	
	/**
	 * Sets our required amount of operands based on the operator.
	 */
	private void defineOperatorBehavior()
	{
		if(Operator.unaryOperators.contains(this.operator)) {
			this.requiredOperands = 1;
		}
		
		if(Operator.binaryOperators.contains(this.operator)) {
			this.requiredOperands = 2;
		}
		
		if(Operator.ternaryOperators.contains(this.operator)) {
			this.requiredOperands = 3;
		}
	}
	
	/**
	 * How the outside world will know how many operands they can pass in.
	 * @return
	 */
	public int numberOfOperands()
	{
		return this.requiredOperands;
	}
	
	/**
	 * evaluateOperands
	 * Sets and Evaluates one, two, or three operands
	 * @param operand
	 * @return
	 * @throws InvalidOperatorException
	 */
	public int evaluateOperands(int operand) throws InvalidOperatorException
	{
		this.operands = new int[1];
		this.operands[0] = operand;
		if(this.operands.length!=this.requiredOperands)
			throw new InvalidOperatorException("This operator ("+this.operator+") cannot handle "+this.operands.length+" operands.");
		
		//do magic
		return unaryExpression();
	}
	
	public int evaluateOperands(int operandA, int operandB) throws InvalidOperatorException
	{
		this.operands = new int[2];
		this.operands[0] = operandA;
		this.operands[1] = operandB;
		if(this.operands.length!=this.requiredOperands)
			throw new InvalidOperatorException("This operator ("+this.operator+") cannot handle "+this.operands.length+" operands.");
		
		//do magic
		return this.binaryExpression();
	}
	
	public int evaluateOperands(int operandA, int operandB, int operandC) throws InvalidOperatorException
	{
		this.operands = new int[3];
		this.operands[0] = operandA;
		this.operands[1] = operandB;
		this.operands[2] = operandC;
		if(this.operands.length!=this.requiredOperands)
			throw new InvalidOperatorException("This operator ("+this.operator+") cannot handle "+this.operands.length+" operands.");
		
		//do magic
		return this.ternaryExpression();
	}
	
	/**
	 * unaryExpression
	 * Executes the expression for the given operator and operands.
	 * @return
	 */
	private int unaryExpression() throws InvalidOperatorException
	{
		switch(this.operator)
		{
			case "!":
				return this.factorial();
			case "~":
				return this.switchSign();
		}
		throw new InvalidOperatorException("No known way to handle given unary operator: "+this.operator+".");
	}
	
	
	/**
	 * binaryExpression
	 * Executes the expression for the given operator and operands.
	 */
	private int binaryExpression() throws InvalidOperatorException
	{
		switch(this.operator)
		{
			case "+":
				return this.add();
			case "-":
				return this.subtract();
			case "*":
				return this.multiply();
			case "/":
				return this.divide();
			case "^":
				return this.power();
			case "%":
				return this.modulo();
			case "&":
				return this.and();
			case "|":
				return this.or();
			case ">":
				return this.greaterThan();
			case "<":
				return this.lessThan();
			case "=":
				return this.equal();
		}
		throw new InvalidOperatorException("No known way to handle given binary operator: "+this.operator+".");
	}
	
	private int ternaryExpression() throws InvalidOperatorException
	{
		switch(this.operator)
		{
			case "?":
				return this.ternary();
		}
		throw new InvalidOperatorException("No known way to handle given ternary operator: "+this.operator+".");
	}
	
	
	/*********************************************************
	 *                TERNARY OPERATIONS                     *
	 ********************************************************/
	/**
	 * If the first operand is NOT 0, return the second operand, otherwise return the third.
	 * @return
	 */
	public int ternary()
	{
		return this.operands[0]!=0 ? this.operands[1]:this.operands[2];
	}
	
	
	/*********************************************************
	 *                 BINARY OPERATIONS                     *
	 ********************************************************/
	/**
	 * If the operands are the same value return 1
	 * @return
	 */
	private int equal()
	{
		return this.operands[0] == this.operands[1] ? 1:0;
	}
	
	/**
	 * Returns 1 if the first operand is less than the second
	 * @return
	 */
	private int lessThan()
	{
		return this.operands[0] < this.operands[1] ? 1:0;
	}
	
	/**
	 * Returns 1 if the first operand is greater than the second.
	 * @return
	 */
	private int greaterThan()
	{
		return this.operands[0] > this.operands[1] ? 1:0;
	}
	
	/**
	 * Returns 1 if EITHER operand is NOT zero
	 * @return
	 */
	private int or()
	{
		return this.operands[0]!=0 || this.operands[1]!=0 ? 1:0;
	}
	
	/**
	 * Returns 1 if BOTH operands are NOT zero
	 * @return
	 */
	private int and()
	{
		return this.operands[0]!=0 && this.operands[1]!=0 ? 1:0;
	}
	
	/**
	 * Performs the modulo operation on the operands
	 * @return
	 */
	private int modulo()
	{
		return this.operands[0] % this.operands[1];
	}
	
	/**
	 * Raises an operand to the power of the other.
	 * @return
	 */
	private int power()
	{
		return (int)Math.pow(this.operands[0], this.operands[1]);
	}
	
	/**
	 * Divides the operands
	 * @return
	 */
	private int divide()
	{
		return this.operands[0] / this.operands[1];
	}
	
	/**
	 * Multiplies the operands
	 * @return
	 */
	private int multiply()
	{
		return this.operands[0] * this.operands[1];
	}
	
	/**
	 * Subtracts the operands
	 * @return
	 */
	private int subtract()
	{
		return this.operands[0] - this.operands[1];
	}
	
	/**
	 * Adds the operands
	 * @return
	 */
	private int add()
	{
		return this.operands[0] + this.operands[1];
	}
	
	
	
	/*********************************************************
	 *                  UNARY OPERATIONS                     *
	 ********************************************************/
	/**
	 * multiplies first (only) operand by -1 to switch its sign.
	 * @return
	 */
	private int switchSign()
	{
		return this.operands[0] * (-1);
	}
	
	/**
	 * returns the factorial value of the first (only) operand.
	 * @return
	 */
	private int factorial()
	{
		int output=1;
		for (int i=1; i<=this.operands[0]; i++)
		{
			output *= i;
		}
		return output;
	}
}
