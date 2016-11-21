//
//
//import com.google.zxing.BarcodeFormat;
//import com.google.zxing.EncodeHintType;
//import com.google.zxing.MultiFormatWriter;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
//import com.pay.common.util.PropertyUtil;
//import com.pay.common.util.StringUtil;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.Hashtable;
//
///**
// * 说明：二维码生成工具类
// * <p/>
// * Company：乐富
// *
// * @author guolong.qu
// * @date 2015年3月13日 下午3:14:36
// */
//public class QrCodeGenerateUtil {
//
//	private static final Log logger = LogFactory.getLog(QrCodeGenerateUtil.class);
//	private static final PropertyUtil propertyUtil = PropertyUtil.getInstance("system");
//
//	// 二维码服务器扫描地址
//	private static String baseUrl = null;
//	// 默认生成二维码图片的保存地址
//	private static String DEF_IMG_PATH = null;
//	// 默认图片格式
//	private static final String DEF_FORMAT = "bmp";
//	// 默认宽度
//	private static final Integer DEF_WIDTH = 217;
//	// 默认高度
//	private static final Integer DEF_HEIGHT = 217;
//
//	static {
//		baseUrl = getQrCodeBaseUrl();
//		DEF_IMG_PATH = propertyUtil.getProperty("lefubao.posActivity.img.path");
//	}
//
//	/**
//	 * 生成二维码
//	 *
//	 * @param activityId 活动ID
//	 * @param codeType   二维码类型：1:活动二维码、2:小票商户联二维码、3:小票持卡人二维码
//	 * @param customerNo 商户号
//	 * @param posSn      POS编号
//	 * @param posCati    POS终端号
//	 * @return
//	 */
//	public static String generateQrCode(String activityId, String codeType, String customerNo, String posSn, String posCati) {
//		return generateQrCode(activityId, codeType, customerNo, posSn, posCati, DEF_WIDTH, DEF_HEIGHT);
//	}
//
//	/**
//	 * 生成二维码
//	 *
//	 * @param activityId 活动ID
//	 * @param codeType   二维码类型：1:活动二维码、2:小票商户联二维码、3:小票持卡人二维码
//	 * @param customerNo 商户号
//	 * @param posSn      POS编号
//	 * @param posCati    POS终端号
//	 * @param width      宽度
//	 * @param height     高度
//	 * @return
//	 */
//	@SuppressWarnings("deprecation")
//	public static String generateQrCode(String activityId, String codeType, String customerNo, String posSn, String posCati,
//										Integer width, Integer height) {
//
//		if (StringUtil.isNull(baseUrl)) {
//			String msg = "qr.code.base.url is null in the system.properties";
//			logger.error(msg);
//			return null;
//		}
//		if (StringUtil.isNull(activityId) || StringUtil.isNull(customerNo) || StringUtil.isNull(posSn)
//				|| StringUtil.isNull(posCati)) {
//			String msg = "paramter is null in the [activityId:" + activityId + ",customerNo:" + customerNo + ",posSn:" + posSn
//					+ ",posCati:" + posCati + "]";
//			logger.error(msg);
//
//			return null;
//		}
//		if (StringUtil.isNull(width)) {
//			logger.warn("qr code width is null! use def_width 230!");
//		}
//		if (StringUtil.isNull(height)) {
//			logger.warn("qr code height is null! use def_height 230!");
//		}
//
//		String text = baseUrl + "?activityId=" + activityId + "&codeType=" + codeType + "&customerNo=" + customerNo + "&posSn="
//				+ posSn + "&posCati=" + posCati;
//		try {
//			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, DEF_WIDTH, DEF_HEIGHT, hints);
//
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String parDic = sdf.format(new Date());
//
//			String fileName = DEF_IMG_PATH + File.separator + parDic + File.separator + activityId + "_" + codeType + "_"
//					+ customerNo + "_" + posSn + "_" + posCati + "_" + System.currentTimeMillis() + "." + DEF_FORMAT;
//			File imgFile = new File(fileName);
//			if (!imgFile.getParentFile().exists()) {
//				imgFile.getParentFile().mkdirs();
//			}
//			MatrixToImageWriter.writeToFile(bitMatrix, DEF_FORMAT, imgFile);
//
//			return fileName;
//		} catch (Exception e) {
//			String msg = "generate qr code img error![activityId:" + activityId + ",customerNo:" + customerNo + ",posSn:" + posSn
//					+ "]";
//			logger.error(msg);
//
//			return null;
//		}
//	}
//
//	/**
//	 * 根据固定内容生成二维码
//	 *
//	 * @param text
//	 * @param adId
//	 * @return
//	 */
//	public static String generateQrCodeByText(String text, String adId) {
//		try {
//			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
//			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
//			BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, DEF_WIDTH, DEF_HEIGHT, hints);
//
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			String parDic = sdf.format(new Date());
//
//			String fileName = DEF_IMG_PATH + File.separator + parDic + "-ad" + File.separator + adId + "_" + System.currentTimeMillis() + "." + DEF_FORMAT;
//			File imgFile = new File(fileName);
//			if (!imgFile.getParentFile().exists()) {
//				imgFile.getParentFile().mkdirs();
//			}
//			MatrixToImageWriter.writeToFile(bitMatrix, DEF_FORMAT, imgFile);
//
//			return fileName;
//		} catch (Exception e) {
//			String msg = "generate qr code img error![adId:" + adId + ",text:" + text + "]";
//			logger.error(msg);
//
//			return null;
//		}
//	}
//
//	/**
//	 * 根据配置文件得到二维码服务器扫描跳转的地址
//	 *
//	 * @return
//	 */
//	private static String getQrCodeBaseUrl() {
//		String url = propertyUtil.getProperty("qr.code.base.url");
//		return url;
//	}
//}
