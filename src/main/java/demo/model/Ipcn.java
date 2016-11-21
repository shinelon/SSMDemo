package demo.model;

public class Ipcn {
    private String ipstart;

    private String ipend;

    private String mask;

    private String num;

    public String getIpstart() {
        return ipstart;
    }

    public void setIpstart(String ipstart) {
        this.ipstart = ipstart == null ? null : ipstart.trim();
    }

    public String getIpend() {
        return ipend;
    }

    public void setIpend(String ipend) {
        this.ipend = ipend == null ? null : ipend.trim();
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask == null ? null : mask.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }
}