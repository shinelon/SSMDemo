//
//package com.util;
//
//import java.awt.Color;
//import java.awt.Graphics2D;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//import javax.imageio.ImageIO;
//
//import com.swetake.util.Qrcode;
//
//import jp.sourceforge.qrcode.QRCodeDecoder;
//import jp.sourceforge.qrcode.data.QRCodeImage;
//import jp.sourceforge.qrcode.exception.DecodingFailedException;
//
//public class QRCodeUtils {
//
//    public static void main(String[] args) {
//        String FilePath = "D:\\二维码生成\\QRCode111.png";
//        File qrFile = new File(FilePath);
//
//        // 编码
//        String encodeddata = "http://blog.csdn.net/gao36951";
//        try {
//            long a = System.currentTimeMillis();
//            QRCodeUtils.qrCodeEncode(encodeddata, qrFile);
//            System.out.println("生成执行耗时-QRCode : " + (System.currentTimeMillis() - a) / 1000f + " 秒 ");
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        ;
//
//        // 解码
//        long b = System.currentTimeMillis();
//        String reText = QRCodeUtils.qrCodeDecode(qrFile);
//        System.out.println(reText);
//        System.out.println("解码执行耗时-QRCode : " + (System.currentTimeMillis() - b) / 1000f + " 秒 ");
//    }
//
//    /**
//     * 解析二维码，返回解析内容
//     *
//     * @param imageFile
//     * @return
//     */
//    public static String qrCodeDecode(File imageFile) {
//        String decodedData = null;
//        QRCodeDecoder decoder = new QRCodeDecoder();
//        BufferedImage image = null;
//        try {
//            image = ImageIO.read(imageFile);
//        } catch (IOException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//
//        try {
//            decodedData = new String(decoder.decode(new J2SEImage(image)), "GBK");
//            System.out.println("Output Decoded Data is：" + decodedData);
//        } catch (DecodingFailedException dfe) {
//            System.out.println("Error: " + dfe.getMessage());
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return decodedData;
//    }
//
//    /**
//     * 编码字符串内容到目标File对象中
//     *
//     * @param encodeddata
//     * @param destFile
//     * @throws IOException
//     */
//    public static void qrCodeEncode(String encodeddata, File destFile) throws IOException {
//        Qrcode qrcode = new Qrcode();
//        // 错误修正容量
//        // L水平 7%的字码可被修正
//        // M水平 15%的字码可被修正
//        // Q水平 25%的字码可被修正
//        // H水平 30%的字码可被修正
//        // QR码有容错能力，QR码图形如果有破损，仍然可以被机器读取内容，最高可以到7%~30%面积破损仍可被读取。
//        // 相对而言，容错率愈高，QR码图形面积愈大。所以一般折衷使用15%容错能力。
//        qrcode.setQrcodeErrorCorrect('M');
//        qrcode.setQrcodeEncodeMode('B');
//        qrcode.setQrcodeVersion(7);
//        byte[] d = encodeddata.getBytes("UTF-8");
//        BufferedImage bi = new BufferedImage(139, 139, BufferedImage.TYPE_INT_RGB);
//        // createGraphics
//        Graphics2D g = bi.createGraphics();
//        // set background
//        g.setBackground(Color.WHITE);
//        g.clearRect(0, 0, 139, 139);
//        // 设置二维码图片颜色
//        g.setColor(Color.BLACK);
//
//        if (d.length > 0 && d.length < 123) {
//            boolean[][] b = qrcode.calQrcode(d);
//            for (int i = 0; i < b.length; i++) {
//                for (int j = 0; j < b.length; j++) {
//                    if (b[j][i]) {
//                        g.fillRect(j * 3 + 2, i * 3 + 2, 3, 3);
//                    }
//                }
//            }
//        }
//
//        g.dispose();
//        bi.flush();
//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//
//        ImageIO.write(bi, "jpg", destFile);
//        // ImageIO.write(bi, "jpg", out);
//        System.out.println("Input Encoded data is：" + encodeddata);
//    }
//}
//
//class J2SEImage implements QRCodeImage {
//    BufferedImage image;
//
//    public J2SEImage(BufferedImage image) {
//        this.image = image;
//    }
//
//    public int getHeight() {
//        return image.getHeight();
//    }
//
//    public int getPixel(int x, int y) {
//        return image.getRGB(x, y);
//    }
//
//    public int getWidth() {
//        return image.getWidth();
//    }
//}
