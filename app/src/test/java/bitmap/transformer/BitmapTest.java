package bitmap.transformer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


import static javax.imageio.ImageIO.read;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BitmapTest {

    static String inputFilePath ="C:/Users/USER/Downloads/images.bmp";
    static String outputFilePath ="C:/Users/USER/Downloads/editedimage.bmp";
    private static BufferedImage image;

    static {
        try {
            image = ImageIO.read(new File(inputFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


//    @BeforeEach
//    public void setup() throws IOException {
//        testImage = ImageIO.read(new File("C:\Users\USER\Downloads\images.bmp"));
//    }

    @Test
    public void testFlipVertically() throws IOException {
        Bitmap bitmap = new Bitmap(inputFilePath);
        bitmap.flipVertically();
        bitmap.saveImage(outputFilePath);

        BufferedImage flippedImage = null;
        try {
            flippedImage = read(new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(image.getHeight(), flippedImage.getHeight());

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                assertEquals(image.getRGB(x, y), flippedImage.getRGB(x, image.getHeight() - 1 - y));
            }
        }
    }

    @Test
    public void testResizeImage() throws IOException {
        String inputImagePath = "C:/Users/USER/Downloads/images.bmp";
        Bitmap bitmap = new Bitmap(inputFilePath);
        int newWidth = 200;
        int newHeight = 150;
        bitmap.resizeImage(newWidth, newHeight);
        bitmap.saveImage(outputFilePath);

        BufferedImage resizedImage = null;
        try {
            resizedImage = read(new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(newWidth, resizedImage.getWidth());
        assertEquals(newHeight, resizedImage.getHeight());
    }

    @Test
    public void testAddBorder() throws IOException {

        Bitmap bitmap = new Bitmap(inputFilePath);
        Color borderColor = Color.BLUE;
        int borderWidth = 10;

        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage newImage = new BufferedImage(
                width + 2 * borderWidth,
                height + 2 * borderWidth,
                this.image.getType()

        );

        bitmap.addBorder();
        bitmap.saveImage(outputFilePath);

        BufferedImage borderedImage = null;
        try {
            borderedImage = read(new File(outputFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        assertEquals(image.getWidth() + 20, newImage.getWidth());
        assertEquals(image.getHeight() + 20, newImage.getHeight());

        for (int x = 0; x < borderedImage.getWidth(); x++) {
            for (int y = 0; y < borderedImage.getHeight(); y++) {
                if (x < 10 || x >= image.getWidth() + 10 || y < 10 || y >= image.getHeight() + 10) {
                    assertEquals(Color.BLUE.getRGB(), borderedImage.getRGB(x, y));
                }
            }
        }
    }
}
