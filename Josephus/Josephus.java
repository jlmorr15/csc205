import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Queue;

public class Josephus {
	private Scanner input;
	private Queue<Integer> soldiers;
	
	private int groupSize;
	private int skipCount;
	private int executed;
	private int lastSoldier;
	
	public static void main(String[] args)
	{
		System.out.println("Josephus - Justin Morris");
		Josephus game = new Josephus();
		while(game.play())
		{
			System.out.println("\nLets play again!");
		}
		System.out.println("\n\nI can understand why you wouldn't want to play again... Bye!");
	}

	public Josephus()
	{
		input = new Scanner(System.in);
	}
	
	public boolean play()
	{
		setGroupSize();
		setSkipCount();
		
		populateArmy();
		System.out.println("You want to be in position #"+performExecutions());
		
		System.out.print("\n\nWant to go again? [Y/N]? ");
		return input.next().equalsIgnoreCase("y") ? true:false;
	}
	
	public void setGroupSize()
	{
		try {
			System.out.print("How many members of the group? ");
			groupSize = input.nextInt();
		}
		catch(InputMismatchException ex) {
			System.out.println("ERROR: Group size must be an integer.");
			input.next();
			setGroupSize();
		}
	}
	
	public void setSkipCount()
	{
		try {
			System.out.print("What is the skip count? ");
			skipCount = input.nextInt();
		}
		catch(InputMismatchException ex) {
			System.out.println("ERROR: Skip count must be an integer.");
			input.next();
			setSkipCount();
		}
	}
	
	public void populateArmy()
	{
		soldiers = new LinkedList<Integer>();
		for(int i = 0; i<groupSize; i++)
		{
			soldiers.add(i+1); //initial position
		}
	}
	
	public int performExecutions()
	{
		int count=1;
		while(!soldiers.isEmpty())
		{
			lastSoldier = soldiers.remove(); //remove from queue
			//System.out.println("The count is "+count+"  and we just removed soldier "+lastSoldier);
			if(count<skipCount) {
				//whew, they get to live!
				soldiers.add(lastSoldier);
				//System.out.println("Soldier "+lastSoldier+" gets to live... for now.");
				count++;
			} else {
				//OH NOOOOOO!
				count = 1;
			}
		}
		return lastSoldier;
	}
}
