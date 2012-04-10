package no.niths.external;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


/**
 * 
 * @author NITHs
 *
 */
public class QRCodeDecoder {

    public Long decodeFadderGroupQRCode(byte[] data) throws Exception {
        BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
        img = resizeImage(img);
        
        
        Result result = new MultiFormatReader().decode(
                new BinaryBitmap(
                        new HybridBinarizer(
                                new BufferedImageLuminanceSource(
                                        img
                                        /*
                                        ImageIO.read(
                                                
                                                new File("/home/whirlwin/tmp/qux.jpg")
                                                //new URL("http://www.qrstuff.com/images/sample.png")
                                                //new ByteArrayInputStream(data)
                                        )
                                        */
                                )
                        )
                ),
                new Hashtable<DecodeHintType, String>() {{
                    put(DecodeHintType.TRY_HARDER, "TRUE");
                }}
         );

        if (result != null) {
            String text = result.getText();

            if (text != null) {
                System.out.println(",,,,,,,,,,,, " + text);
            }
        }

        return 1L;
    }

    private BufferedImage resizeImage(BufferedImage img) {
        BufferedImage newImg = new BufferedImage(100, 100, img.getType());
        Graphics2D g = newImg.createGraphics();
        g.drawImage(img, 0, 0, 100, 100, null);
        g.dispose();

        return newImg;
    }
}