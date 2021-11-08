/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author XUÂN THÀNH
 */
public class ImageHelper {
    public static Image resize(Image originalImage, int targetWith, int targetHight){
        Image resultingImage = originalImage.getScaledInstance(targetWith, targetHight, Image.SCALE_SMOOTH);
        return resultingImage;
    }
    
    public static byte[] toArrayByte(Image img, String type) throws IOException{
        BufferedImage bimg = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bimg.createGraphics();
        g.drawImage(img,0,0,null);
        g.dispose();
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( bimg, type, baos);
        byte[] imageByte = baos.toByteArray();
        
        return imageByte;
    }
    
    public static Image createImageFromByteArray(byte[] data, String type) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        BufferedImage bimg = ImageIO.read(bais);
        
        Image img = bimg.getScaledInstance(bimg.getWidth(), bimg.getHeight(), Image.SCALE_SMOOTH);
        return img;
    }
}
