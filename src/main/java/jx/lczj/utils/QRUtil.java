package jx.lczj.utils;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Hashtable;

public class QRUtil
{


    /**
     * 生成二维码
     * @param text  生成的url文本
     * @param dst_url  保存二维码的路径
     */
    public static void CreatQRCode(String text,String dst_url){
        int width = 200;
        int height = 200;
        String format = "png";
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        hints.put(EncodeHintType.MARGIN, 2);
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
            Path file = new File(dst_url).toPath();
            MatrixToImageWriter.writeToPath(bitMatrix, format, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析二维码
     * @param src_url  源二维码图片
     * @throws NotFoundException
     */
    public static void AnalyzeQRCode(String src_url) throws NotFoundException {
        MultiFormatReader formatReader=new MultiFormatReader();
        File file =new File(src_url);
        BufferedImage image=null;
        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BinaryBitmap binaryBitmap =new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
        Hashtable hints=new Hashtable();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        Result result=formatReader.decode(binaryBitmap,hints);
        System.err.println("解析结果："+result.toString());
        System.out.println(result.getBarcodeFormat());
        System.out.println(result.getText());
    }
}