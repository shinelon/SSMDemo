package demo.file.util;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

//ignore "bytes" when return json format
@JsonIgnoreProperties({ "bytes" })
public class FileMeta {

    private String fileName;
    private String fileSize;
    private String fileType;

    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    // setters & getters

}