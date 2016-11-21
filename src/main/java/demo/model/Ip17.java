package demo.model;

public class Ip17 {
    private Double id;

    private Double regionid;

    private String ipstart;

    private String ipend;

    private String country;

    private String province;

    private String city;

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Double getId() {
        return id;
    }

    public String getIpend() {
        return ipend;
    }

    public String getIpstart() {
        return ipstart;
    }

    public String getProvince() {
        return province;
    }

    public Double getRegionid() {
        return regionid;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public void setId(Double id) {
        this.id = id;
    }

    public void setIpend(String ipend) {
        this.ipend = ipend;
    }

    public void setIpstart(String ipstart) {
        this.ipstart = ipstart;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public void setRegionid(Double regionid) {
        this.regionid = regionid;
    }
}