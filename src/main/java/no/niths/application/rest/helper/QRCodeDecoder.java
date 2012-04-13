package no.niths.application.rest.helper;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import no.niths.application.rest.exception.QRCodeException;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


/**
 * 
 * @author NITHs
 *
 */
public class QRCodeDecoder {

    public Long decodeFadderGroupQRCode(byte[] data) throws QRCodeException {
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
            throw new QRCodeException(
                    "Could not read the contents of the QR code");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return groupNumber;
    }

    private BufferedImage resizeImage(BufferedImage originalImg) {
        BufferedImage resizedImg = new BufferedImage(
                100, 100, originalImg.getType());
        Graphics2D g = resizedImg.createGraphics();
        g.drawImage(originalImg, 0, 0, 100, 100, null); // 100x100 px
        g.dispose();

        return resizedImg;
    }

    private Long parseGroupNumber(String data) throws QRCodeException {
        String[] info = data.split(":");
        Long groupNumber = null;

        try {
            groupNumber = Long.parseLong(info[1]);
        } catch (NumberFormatException e) {
            throw new QRCodeException("Invalid QR code format");
        }

        return groupNumber;
    }
}