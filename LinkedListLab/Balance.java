import java.util.Scanner;

public class Balance {
	private LinkedStack<Character> stack;
	private LinkedStack<BracketPair> pairStack;
	
	public static void main(String[] args) {
	    {
	        String expression, again;
	        boolean result;
	    
	        Scanner in = new Scanner(System.in);
	      
	        do
	        {  
	            Balance tester = new Balance();
	            
				System.out.println("Enter a string with ()'s: ");
	            expression = in.nextLine();

	            result = tester.isBalanced(expression);
	            
	            System.out.println("\nThat expression " + (result ? "is balanced" : "is NOT balanced"));

	            System.out.print("Evaluate another expression [Y/N]? ");
	            again = in.nextLine();
	            System.out.println();
	        }
	        while (again.equalsIgnoreCase("y"));
	        
	        in.close();
	  }
	}

	public void registerPair(String o, String c)
	{
		
	}
	
	public Balance() {
		stack = new LinkedStack<Character>();
	}
	
	public boolean isBalanced(String s) {
		char a = 'a';
		
		for (int i = 0; i < s.length(); i++) {
			switch (s.charAt(i)) {
				case '(': 
					stack.push(new Character(a));
					System.out.print(a++);
					break;
				case ')':
					if (stack.size() == 0) return false;
					System.out.print(stack.pop().charValue());
					break;
				default:
					System.out.print(' ');
			}
		}
		return stack.isEmpty();
	}
}
