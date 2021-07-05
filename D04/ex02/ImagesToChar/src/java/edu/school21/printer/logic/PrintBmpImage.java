package edu.school21.printer.logic;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi;

public class PrintBmpImage {

    String filePath;
    String white;
    String black;

    public PrintBmpImage(String filePath, String white, String black) {
        this.filePath = filePath;
        this.white = white;
        this.black = black;
    }

    public void seeBMPImage() throws IOException {
        File file = new File(filePath);
        BufferedImage image = ImageIO.read(file);

        Ansi.BColor.valueOf(white);
        Ansi.BColor.valueOf(black);

        for (int xPixel = 0; xPixel < image.getWidth(); xPixel++)
        {
            for (int yPixel = 0; yPixel < image.getHeight(); yPixel++)
            {
                ColoredPrinter cp = new ColoredPrinter();
                if (image.getRGB(yPixel, xPixel) == -1) {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(white));
                } else {
                    cp.print(" ", Ansi.Attribute.NONE, Ansi.FColor.NONE, Ansi.BColor.valueOf(black));
                }
            }
            System.out.println();
        }
    }
}
