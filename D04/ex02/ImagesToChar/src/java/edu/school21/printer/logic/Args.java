package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import java.io.IOException;

@Parameters(separators = "=")

public class Args {

    @Parameter(names={"--white"})
    private String arg1;
    @Parameter(names={"--black"})
    private String arg2;

    public void run() throws IOException {
        try {
            PrintBmpImage im = new PrintBmpImage("./target/resources/it.bmp", arg1, arg2);
            im.seeBMPImage();
        }
        catch (Exception e) {
            System.out.println("error: file not valid or invalid parameters.");
        }
    }

}