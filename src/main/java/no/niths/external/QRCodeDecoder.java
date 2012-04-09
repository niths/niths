package no.niths.external;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;


/**
 * 
 * @author NITHs
 *
 */
public class QRCodeDecoder {

    public Long decodeFadderGroupQRCode(String data) throws Exception {
        System.out.println("------- " + data);
        byte[] b = new Base64().decode(data.getBytes("UTF-8"));
        
        Result result = new MultiFormatReader().decode(
                new BinaryBitmap(
                        new HybridBinarizer(
                                new BufferedImageLuminanceSource(
                                        ImageIO.read(
                                                new ByteArrayInputStream(b)
                                        )
                                )
                        )
                ),
                new Hashtable<DecodeHintType, String>() {{
                    put(DecodeHintType.CHARACTER_SET, "UTF-8");
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

    public BufferedImage convertToBufferedImage(byte[] data) throws WriterException {
        
        
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        return MatrixToImageWriter.toBufferedImage(
                new MultiFormatWriter().encode(
                        new String(data),
                        BarcodeFormat.QR_CODE,
                        100,
                        100));
    }
}