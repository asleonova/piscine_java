package edu.school21.printer.app;

import java.io.*;

import com.beust.jcommander.JCommander;
import edu.school21.printer.logic.Args;

public class Main {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Wrong num of args");
            System.exit(-1);
        }
        try {
            Args arguments = new Args();
            JCommander.newBuilder()
                    .addObject(arguments)
                    .build()
                    .parse(args);
            arguments.run();
        } catch (Exception e) {
            System.err.println("color invalid");
        }
    }
}

