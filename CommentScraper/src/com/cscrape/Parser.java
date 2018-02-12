package com.cscrape;

import java.io.*;
import java.util.Scanner;

public class Parser {
    private Boolean recording = false; //record data to buffer?
    private File inFile; //The inFile we will parse
    private String outFile; //The inFile we will write to.
    private PrintWriter writer;

    /**
     * Constructor
     * @param file - Input File
     * @param outFile - Output Filename
     */
    public Parser(File file, String outFile) throws IOException {
        this.outFile = outFile;
        this.inFile = file;
        this.initWriter();
        this.parse();
        this.writer.close();
    }

    /**
     * Constructor
     * Debug Constructor - Opens CScrape.java for parsing
     */
    public Parser() throws IOException {
        this.outFile = "Test.txt";
        this.inFile = new File("src\\com\\cscrape\\CScrape.java"); //The inFile we will parse
        this.initWriter();
        this.parse();
        this.writer.close();
    }

    /**
     * initWriter
     * Instantiates PrintWriter into this.writer
     * @throws FileNotFoundException - No File, No Output!
     * @throws UnsupportedEncodingException - Hopefully this doesn't ever come up...
     */
    private void initWriter() throws FileNotFoundException, UnsupportedEncodingException {
        this.writer = new PrintWriter(outFile, "UTF-8");
    }

    /**
     * parse
     * This method inits the scanner and reads the file line by line passing each line
     * to the parseLine method, writing the results to the output file.
     * @throws IOException - File Not Found? Readable? Writable?
     */
    private void parse() throws IOException {
        Scanner scanner;
        scanner = new Scanner(this.inFile);
        scanner.useDelimiter("\r\n");

        this.writer.println("Comment Scrape - J. Morris");
        this.writer.println("LINE#: --- Comment Data ---");
        this.writer.println("");

        int lineNumber = 0;
        while (scanner.hasNext()) {
            lineNumber++;
            String buffer = parseLine(scanner.next());

            if(!buffer.isEmpty())
                this.writer.println(String.format("%05d",lineNumber)+": "+buffer.trim());
            //Zero fill our line numbers to 5 digits. If someone scrapes a file with > 99999 lines, they deserve the ugly output.
        }
        System.out.println("Scraping completed.");
    }

    /**
     * parseLine
     * The "Meat and Potatoes" of our class.
     * This method goes line by line looking for comments and acts on them if warranted.
     * @param line - a line of text from input inFile
     * @return String
     */
    private String parseLine(String line)
    {
        Boolean inString = false; //are we currently in a string?
        Boolean singleLine = false; //Should we clear the recording status once we finish this line?
        String buffer = "";
        String output = "";

        for(int i=0; i<line.length(); i++) {
            String cursor = line.substring(i,i+1);
            String peekAhead = "";
            String lookBehind = buffer+cursor; //our 2 character look behind for finding escaped quotes
            buffer=cursor;
            //get our peekAhead values which will be used for finding comments
            if(i<line.length()-1) {
                peekAhead = line.substring(i,i+2);
            }

            //if we see a quote, and its not escaped, and we are NOT recording, toggle the inString flag.
            if(cursor.equals("\"") && !lookBehind.equals("\\\"") && !recording) {
                inString = !inString; //toggle inString value.
            }

            if(!recording && !inString) {
                switch(peekAhead) {
                    case "//":
                        //Single Line Found!
                        recording = true;
                        singleLine = true;
                        break;
                    case "/*":
                        //Block Style Found!
                        recording = true;
                        break;
                }
            } else if(peekAhead.equals("*/") && !singleLine && !inString) {
                output = output+"*/"; //Manually close the comment
                recording = false; //Stop Recording
            }

            //If we are recording, concat our output String
            if(this.recording) {
                output = output+cursor;
            }
        }
        //Turn off recording status at the end of the line if it was for a single line comment.
        if(singleLine) {
            this.recording = false;
        }
        return output.trim();
    }
}
