package edu.school21.printer.app;
import java.io.*;
import edu.school21.printer.logic.PrintBmpImage;

public class Main {

    public static void main(String[] args) throws IOException {

        try {
            PrintBmpImage im = new PrintBmpImage("./target/resources/it.bmp");
            im.seeBMPImage();
        }
        catch (IOException e) {
            System.out.println("error: file not valid.");
        }
    }
}


