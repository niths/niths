package no.niths.external;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import no.niths.common.config.HibernateConfig;
import no.niths.common.config.TestAppConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.HybridBinarizer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestAppConfig.class, HibernateConfig.class })
public class QRCodeTest {

    private final String CHARSET   = "UTF-8",
                         FILE_NAME = "foobar.jpg",
                         FILE_PATH = System.getProperty("user.home") + '/'
                             + FILE_NAME,
                         DATA      = "Lorem ipsum";

    @Test
    @SuppressWarnings("serial")
    public void testWriteQRCode()  throws IOException, WriterException {
        MatrixToImageWriter.writeToFile(
                new MultiFormatWriter().encode(

                        // The data which is to be stored
                        new String(DATA.getBytes(CHARSET)),
                        BarcodeFormat.QR_CODE,
                        100, // Width
                        100, // Height
                        new Hashtable<EncodeHintType, String>() {{
                            put(EncodeHintType.CHARACTER_SET, CHARSET);
                        }}
                ),

                // File type based on file extension
                FILE_NAME.replaceFirst("\\w+\\.(\\w+)", "$1".toUpperCase()),
                new File(FILE_PATH)
        );
    }

    @Test
    @SuppressWarnings("serial")
    public void testReadQRCode()
            throws NotFoundException, FileNotFoundException, IOException {

        assertEquals(
                DATA,

                // Very simple way to get the QR contents
                new MultiFormatReader().decode(
                        new BinaryBitmap(
                                new HybridBinarizer(
                                        new BufferedImageLuminanceSource(
                                                ImageIO.read(
                                                        new FileInputStream(
                                                            FILE_PATH
                                                        )
                                                )
                                        )
                                )
                        ),
                        new Hashtable<DecodeHintType, String>() {{
                            put(DecodeHintType.CHARACTER_SET, CHARSET);
                        }}
                ).getText());
    }
}