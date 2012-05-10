package no.niths.application.rest.helper;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import no.niths.application.rest.exception.QRCodeException;

import org.imgscalr.Scalr;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


/**
 * Class too decode QR codes for fadderGroups
 */
public class QRCodeDecoder {

    public Long decodeFadderGroupQRCode(byte[] data) throws QRCodeException {
        System.out.println("--------------------- bytes:");
        System.out.println("::: "+data[0]+','+data[1]+','+data[2]+','+data[3]);
        
        Long groupNumber = null;

        // Resize and scan the QR code code on the image
        try {
            Result result = new MultiFormatReader().decode(
                    new BinaryBitmap(
                            new HybridBinarizer(
                                    new BufferedImageLuminanceSource(
                                            resizeImage(ImageIO.read(
                                                    new ByteArrayInputStream(
                                                            data
                                                    )
                                            ))
                                    )
                            )
                    ),
                    new Hashtable<DecodeHintType, String>() {
                        private static final long serialVersionUID =
                                -2668362696248033192L;

                        // Try to decode the contents by any means
                        { put(DecodeHintType.TRY_HARDER, "TRUE"); }
                    }
             );

        groupNumber = parseGroupNumber(result.getText());

        } catch (NotFoundException e) {
            System.err.println("=============================");
            System.err.println("Kan ikke lese koden...");
            System.err.println("=============================");
            throw new QRCodeException(
                    "Could not read the contents of the QR code");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return groupNumber;
    }

    private BufferedImage resizeImage(BufferedImage originalImg) {
        
        return Scalr.resize(originalImg, 300);
        
//        int w = originalImg.getWidth()/5;
//        int h = originalImg.getHeight()/5;
//        BufferedImage resizedImg = new BufferedImage(
//                w, h, originalImg.getType());
//        Graphics2D g = resizedImg.createGraphics();
//        g.drawImage(originalImg, 0, 0, w, h, null); // 100x100 px
//        g.dispose();
//
//        // TODO: Check if this works
//        // You may need to change the File's path 
//        try {
//            ImageIO.write(resizedImg, "JPG", new File(System.getProperty("user.home") + "/balle.jpg"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return resizedImg;
    }

    private Long parseGroupNumber(String data) throws QRCodeException {
        System.err.println("=============================");
        System.err.println("You did it! The data stored is: " + data);
        System.err.println("=============================");
        String[] info = data.split(":");
        Long groupNumber = null;
        if(info.length == 2){
            try {
                if(info[0].equals("gruppe")){
                    groupNumber = Long.parseLong(info[1]);
                    System.err.println("FOUND GROUPNUMBER : " + groupNumber);                    
                }else{
                    throw new QRCodeException("Invalid QR code content");
                }
            } catch (NumberFormatException e) {
                throw new QRCodeException("Invalid QR code format");
            }
            
        }
        return groupNumber;            
    }
}