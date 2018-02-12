import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private String input = "";
    private File inFile;
    private String outFile;
    private Scanner scanner;
    public Menu() {
        //Not much to do here.
    }

    /**
     * getInput()
     * returns this.input
     * @return
     */
    public String getInput() {
        return this.input;
    }

    /**
     * initScanner
     * sets this.scanner to a new scanner instance.
     */
    private void initScanner()
    {
    	this.scanner = new Scanner(System.in);
    }
    
    /**
     * closeScanner
     * Closes the scanner resource to prevent leaks
     */
    private void closeScanner()
    {
    	this.scanner.close();
    }
    
    /**
     * prompt
     * Prompts the user to enter a file name.
     */
    public void prompt()
    {
    	System.out.print("Enter a file name or [exit] to quit: ");
    	this.input = scanner.next();
    }

    /**
     * render
     * Shows the menu on a loop until exit or [exit] is entered.
     */
    public void render()
    {
        try {
        	this.initScanner(); //Set up our scanner resource
        	this.prompt(); //Prompt for a file
        	
            while(!this.getInput().toLowerCase().equals("exit") && !this.getInput().toLowerCase().equals("[exit]"))
            {
                System.out.println("Scraping File: "+this.getInput());
                this.inFile = new File(this.getInput());
                if(inFile.canRead()) {
                    this.outFile = inFile.getName().replace(".java",".txt");
                    System.out.println("Output File: "+this.outFile);
                    Parser parser = new Parser(this.inFile, this.outFile);
                    parser.parse();
                } else {
                    System.out.println("Unable to read file, please try again.");
                }
                this.prompt();
            }
            
            this.closeScanner(); //Close the Scanner
        } catch (IOException e) {
            System.out.println("Oh No! There's a problem reading or writing the file you entered!");
        }

    }


}
