package com.cscrape;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class CScrape {
    /* This is a single line block comment */

    /* //This wont be picked up by the // single line because its already recording the block comment */
    // "this should get picked up by the scraper since the quote comes AFTER we start recording."

    /**
     * CScrape method
     * Kicks off the program
     * @param args
     */
    public static void main(String[] args) {
        Boolean debug = false;
        try {
            // The comment in the string of the welcome message should be skipped because we ignore escaped quotes.
            // Thus the system knows we are still inside a string and wont start recording.
            System.out.println("Justin's Super-Duper \"/* Comment Scraper */\" for CSC205");
            if(debug) {
                Parser parser = new Parser(); //Run demo on CScrape.java and output Test.text
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
