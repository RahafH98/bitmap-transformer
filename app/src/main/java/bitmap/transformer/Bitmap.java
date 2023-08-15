
package bitmap.transformer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class Bitmap {

    private static int height;
    private static int width;
    private static BufferedImage image;

    public Bitmap(String inputFile) throws IOException {
        image = ImageIO.read(new File(inputFile));
        height = image.getHeight();
        width = image.getWidth();
        System.out.println(image);
    }



    public void flipVertically() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height / 2; y++) {
                int topColor = image.getRGB(x, y);
                int bottomColor = image.getRGB(x, height - 1 - y);
                image.setRGB(x, y, bottomColor);
                image.setRGB(x, height - 1 - y, topColor);
            }
        }
    }

        public void resizeImage ( int newWidth, int newHeight){
            BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics2D = resizedImage.createGraphics();
            graphics2D.drawImage(image, 0, 0, newWidth, newHeight, null);
            graphics2D.dispose();
            image = resizedImage;
        }

        public void addBorder () {
            Color borderColor = Color.BLUE;
            int borderWidth = 10;

            int width = image.getWidth();
            int height = image.getHeight();

            BufferedImage newImage = new BufferedImage(
                    width + 2 * borderWidth,
                    height + 2 * borderWidth,
                    this.image.getType()

            );

            for (int i = 0; i < newImage.getWidth(); i++) {
                for (int j = 0; j < newImage.getHeight(); j++) {
                    if (i < borderWidth || i >= width + borderWidth || j < borderWidth || j >= height + borderWidth) {
                        newImage.setRGB(i, j, borderColor.getRGB());
                    } else {
                        Color color = new Color(this.image.getRGB(i - borderWidth, j - borderWidth));
                        newImage.setRGB(i, j, color.getRGB());
                    }
                }
            }

            this.image = newImage;
        }

        public void saveImage (String outputFilePath){
            try {
                File outputFile = new File(outputFilePath);
                ImageIO.write(image, "bmp", outputFile);

            } catch (IOException e) {

            }
        }
    }
