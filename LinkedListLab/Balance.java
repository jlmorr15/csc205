import java.util.Scanner;

public class Balance {
	private LinkedStack<Character> stack;
	private BracketPairList pairList;
	private String reservedSymbols;
	
	public static void main(String[] args) {
	    {
	        String expression, again;
	        boolean result;
	    
	        Scanner in = new Scanner(System.in);
	      
	        do
	        {  
	            Balance tester = new Balance();
	            tester.pairList = new BracketPairList();
	            
				System.out.println("Enter a string with bracket pairs in it: ");
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
		if(this.reservedSymbols.contains(o) || this.reservedSymbols.contains(c)) {
			System.out.println(o+" AND/OR "+c+" ARE ALREADY IN USE.");
		} else {
			this.pairList.registerPair(new BracketPair(o,c));
			this.reservedSymbols = this.reservedSymbols+o+c;
		}
	}
	
	public Balance() {
		stack = new LinkedStack<Character>();
	}
	
	public boolean isBalanced(String s) {
		char a = 'a';
		
		for (int i = 0; i < s.length(); i++) {
			char x = s.charAt(i);
			if(this.pairList.isValidOpener(s)) {
				//
			}
			
			switch (x) {
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
