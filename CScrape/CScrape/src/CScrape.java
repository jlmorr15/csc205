import java.io.IOException;
public class CScrape {
    /* This is a single line block comment */

    /* //This wont be picked up by the // single line because its already recording the block comment */
    // "this should get picked up by the scraper since the quote comes AFTER we start recording."

    /**
     * Main method
     * Kicks off the program
     * @param args
     */
    public static void main(String[] args) {
    	//I was going to allow a -d option to toggle debug, but figured there was no real point in putting in the extra effort.
        Boolean debug = false; //Change to True for debug mode (Automatically scrapes CScrape.java and outputs Test.txt)
        try {
            // The comment in the string of the welcome message should be skipped because we ignore escaped quotes in string detection.
            // Thus the system knows we are still inside a string and wont start recording.
            System.out.println("Justin's Super-Duper \"/* Comment Scraper */\" for CSC205");
            if(debug) {
                Parser parser = new Parser(); //Run demo on CScrape.java and output Test.txt
                parser.parse();
            } else {
                Menu menu = new Menu(); //instantiate the menu
                menu.render(); //"render" the menu.
                //They opted to exit when they leave render()
                System.out.println("Bye.");
            }
        } catch (IOException e) {
            System.out.println("The file you are looking for doesn't exist. Please Try Again.");
        } catch (IllegalStateException e) {
            System.out.println("EXCEPTION! "+e.getMessage());
        }
    }
}