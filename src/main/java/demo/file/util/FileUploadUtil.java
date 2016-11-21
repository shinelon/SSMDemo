package demo.file.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;

/**
 *
 *
 */
public class FileUploadUtil {
    static Logger logger = Logger.getLogger(FileUploadUtil.class);

    /**
     * 写文件到本地文件夹
     *
     * 返回生成的文件名
     */
    public static boolean copyStreamFile(InputStream inputStream, String filePath, String fileName) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.mkdirs();
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(filePath + File.separator + fileName);
            int readBytes = 0;
            byte[] buffer = new byte[10000];
            while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                outputStream.write(buffer, 0, readBytes);
            }
            return true;
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
            throw ioe;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }

    }

    public static String formatPath(String path) {
        path = path.replaceAll("\\\\", "/");
        if (!path.endsWith("/")) {
            path = path + "/";
        }
        return path;
    }

    /**
     * 运行环境绝对路径
     *
     */
    public static String getRealPath(HttpServletRequest request) {
        return request.getSession().getServletContext().getRealPath("/");
    }

    /**
     * 获取上传文件的文件流
     *
     */
    public static Map<String, Object> getUploadInputStreamAndName(HttpServletRequest request) throws Exception {
        if (ServletFileUpload.isMultipartContent(request)) {
            Map<String, Object> map = new HashMap<String, Object>();
            MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
            for (Iterator<String> iterator = req.getFileNames(); iterator.hasNext();) {
                String type = iterator.next();
                MultipartFile multipartFile = req.getFile(type);
                String fileName = multipartFile.getOriginalFilename();
                map.put("inputStream", multipartFile.getInputStream());
                map.put("fileName", fileName);
                map.put("postfix", fileName.substring(fileName.lastIndexOf("."), fileName.length()));
                return map;
            }
        }
        return null;
    }

    /**
     * 上传文件
     *
     */
    public static Map<String, Object> upload(String uploadPath, HttpServletRequest request) {
        try {
            Map<String, Object> map = getUploadInputStreamAndName(request);
            if (map == null || map.isEmpty()) {
                return null;
            }
            uploadPath = uploadPath.replaceAll("\\\\", "/");
            if (!uploadPath.endsWith("/")) {
                uploadPath = uploadPath + "/";
            }
            File savePath = new File(uploadPath);
            if (!savePath.exists() && !savePath.isDirectory()) {
                savePath.mkdirs();
            }
            String fileName = map.get("fileName").toString();

            logger.info(fileName);
            String postfix = map.get("postfix").toString();
            InputStream inputStream = (InputStream) map.get("inputStream");
            String file = uuid22() + postfix;
            logger.info("开始上传:\npath:" + uploadPath + "\nfile:" + fileName + "--->" + file);
            FileUploadUtil.copyStreamFile(inputStream, uploadPath, file);

            map.put("fileUrl", uploadPath + file);
            return map;
        } catch (Exception e) {
            logger.error("文件上传错误!", e);
            return null;
        }
    }

    /**
     * 通过base64编码压缩到22位
     *
     * @return
     */
    public synchronized static String uuid22() {
        return compressedUuid();
    }

    // 直接返回一个对象的JSON字符串，用于POST请求的应答消息
    public static void writeStringResponse(HttpServletResponse res, Object bean) {
        JSONObject obj = (JSONObject) (bean instanceof JSONObject ? bean : JSONObject.toJSON(bean));
        String json = obj.toString();
        try {
            // 设置输出文本格式为json
            res.setContentType("application/json");
            res.setCharacterEncoding("utf-8");
            // 获得输出流对象PrintWriter
            PrintWriter out = res.getWriter();
            // 输出json对象，这里可以封装用户提取出来的json字符串
            out.print(json);
            out.flush();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static String compressedUuid() {
        UUID uuid = UUID.randomUUID();
        return compressedUUID(uuid);
    }

    protected static String compressedUUID(UUID uuid) {
        byte[] byUuid = new byte[16];
        long least = uuid.getLeastSignificantBits();
        long most = uuid.getMostSignificantBits();
        long2bytes(most, byUuid, 0);
        long2bytes(least, byUuid, 8);
        String compressUUID = Base64.encodeBase64URLSafeString(byUuid);
        return compressUUID;
    }

    protected static void long2bytes(long value, byte[] bytes, int offset) {
        for (int i = 7; i > -1; i--) {
            bytes[offset++] = (byte) ((value >> 8 * i) & 0xFF);
        }
    }
}
