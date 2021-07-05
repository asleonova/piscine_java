package edu.school21.printer.logic;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;


public class PrintBmpImage {

    String filePath;

    public PrintBmpImage(String filePath) {
        this.filePath = filePath;
    }

    public void seeBMPImage() throws IOException {
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++)
        {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++)
            {
                int color = ((BufferedImage) image).getRGB(yPixel, xPixel);
                if (color == Color.BLACK.getRGB()) {
                    System.out.print("0");
                } else {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
