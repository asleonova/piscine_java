import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Base64;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length == 0)
        {
            System.err.println("no args");
            System.exit(-1);
        }

        try {
            PrintBmpImage im = new PrintBmpImage(args[0]);
            im.seeBMPImage();
        }
        catch (IOException e) {
            System.out.println("error: file not valid.");
        }
    }
}


