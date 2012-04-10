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

        // Resize and scan the QR code code on the image
        Result result = new MultiFormatReader().decode(
                new BinaryBitmap(
                        new HybridBinarizer(
                                new BufferedImageLuminanceSource(
                                        resizeImage(ImageIO.read(
                                                new ByteArrayInputStream(data))
                                        )
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

    private BufferedImage resizeImage(BufferedImage originalImg) {
        BufferedImage resizedImg = new BufferedImage(
                100, 100, originalImg.getType());
        Graphics2D g = resizedImg.createGraphics();
        g.drawImage(originalImg, 0, 0, 100, 100, null); // 100x100 px
        g.dispose();

        return resizedImg;
    }
}