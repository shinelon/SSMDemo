package demo.controller.fileupload;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import demo.file.util.FileMeta;
import demo.file.util.FileUploadUtil;

@Controller
@RequestMapping("/upload")
public class FileUploadController {

    @RequestMapping(value = "/basicUpload", method = RequestMethod.POST)
    public @ResponseBody LinkedList<FileMeta> basicUpload(MultipartHttpServletRequest request,
            HttpServletResponse response) {

        LinkedList<FileMeta> files = new LinkedList<FileMeta>();
        FileMeta fileMeta = null;
        System.out.println("upload");
        // 1. build an iterator
        Iterator<String> itr = request.getFileNames();
        MultipartFile mpf = null;

        // 2. get each file
        while (itr.hasNext()) {

            // 2.1 get next MultipartFile
            mpf = request.getFile(itr.next());
            System.out.println(mpf.getOriginalFilename() + " uploaded! " + files.size());

            // 2.2 if files > 10 remove the first from the list
            if (files.size() >= 10) {
                files.pop();
            }

            // 2.3 create new fileMeta
            fileMeta = new FileMeta();
            fileMeta.setFileName(mpf.getOriginalFilename());
            fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
            fileMeta.setFileType(mpf.getContentType());

            try {
                fileMeta.setBytes(mpf.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            // 2.4 add to files
            files.add(fileMeta);
        }
        // result will be like this
        // [{"fileName":"app_engine-85x77.png","fileSize":"8
        // Kb","fileType":"image/png"},...]
        return files;
    }

    @RequestMapping(value = "/basicUploadSave", method = RequestMethod.POST)
    public String basicUploadSave(MultipartHttpServletRequest request, HttpServletResponse response) {
        String path = request.getSession().getServletContext().getRealPath("/");
        System.out.println("path:" + path);
        Map<String, Object> filemap = FileUploadUtil.upload(path + "upload", request);
        System.out.println(filemap);
        request.setAttribute("filemap", filemap);
        return "/filedemo/uploadfile";
    }

    @RequestMapping(value = "/basicView")
    public String basicView() {
        return "/filedemo/basic";

    }
    
    @RequestMapping(value = "/page")
    public String page() {
        return "/filedemo/uploadfile";

    }
    /**
     * 文件下载
     * @Description: 
     * @param fileName
     * @param request
     * @param response
     * @return
     * @throws IOException 
     * @throws FileNotFoundException 
     */
    @RequestMapping("/downloadfile")
    public String downloadFile(@RequestParam("path") String fileName,
            HttpServletRequest request, HttpServletResponse response){
        if (fileName != null) {
            File file = new File(fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + file.getName());// 设置文件名
                byte[] buffer = new byte[1024];
                try(FileInputStream fis = new FileInputStream(file);
                		BufferedInputStream bis = new BufferedInputStream(fis)) {
                    
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch(FileNotFoundException e1){
                	e1.getStackTrace();
                } catch(IOException e2){
                	e2.getStackTrace();
                }
            }
        }
        return null;
    }
}
