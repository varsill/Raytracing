import scala.Int;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;




public class Converter {
    static public BufferedImage ppm(int width, int height, int maxcolval, String path) {

        int r, g, b;
        int x, y;
        x = y = 0;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        try {
            FileInputStream fstream = new FileInputStream(path);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            br.readLine();
            br.readLine();
            br.readLine();
            while ((strLine = br.readLine()) != null) {
                //System.out.println(strLine);
                String[] tokens = strLine.split(" ");

                r = Integer.parseInt(tokens[0]);
                g = Integer.parseInt(tokens[1]);
                b = Integer.parseInt(tokens[2]);

                image.setRGB(x, y, (new Color(r, g, b, 0)).getRGB());
                x += 1;
                if (x >= width) {
                    x = 0;
                    y += 1;
                }

            }
            in.close();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return image;

    }
}



