package demo.model;

public class Stipinfo {
    private Integer id;

    private Integer regionid;

    private Long ipstart;

    private Long ipend;

    private String country = "null";

    private String province = "null";

    private String city = "null";

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public Integer getId() {
        return id;
    }

    public Long getIpend() {
        return ipend;
    }

    public Long getIpstart() {
        return ipstart;
    }

    public String getProvince() {
        return province;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIpend(Long ipend) {
        this.ipend = ipend;
    }

    public void setIpstart(Long ipstart) {
        this.ipstart = ipstart;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }
}