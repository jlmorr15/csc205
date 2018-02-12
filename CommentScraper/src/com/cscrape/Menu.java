package com.cscrape;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Menu {
    private String input = "";
    private File inFile;
    private String outFile;
    public Menu() {
        this.prompt();
    }

    public String getInput() {
        return this.input;
    }

    public void prompt()
    {
        System.out.print("Enter a filename or [exit] to quit: ");
        Scanner scanner = new Scanner(System.in);
       this.input = scanner.next();
    }

    public void render()
    {
        try {
            while(!this.getInput().toLowerCase().equals("exit") && !this.getInput().toLowerCase().equals("[exit]"))
            {
                System.out.println("Scraping File: "+this.getInput());
                this.inFile = new File(this.getInput());
                if(inFile.canRead()) {
                    this.outFile = inFile.getName().replace(".java",".txt");
                    System.out.println("Output File: "+this.outFile);
                    Parser parser = new Parser(this.inFile, this.outFile);
                } else {
                    System.out.println("Unable to read file, please try again.");
                }
                this.prompt();
            }
        } catch (IOException e) {
            System.out.println("Oh No! There's a problem reading or writing the file you entered!");
        }

    }


}
