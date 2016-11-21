import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import demo.dao.Ip17Mapper;
import demo.dao.IpcnMapper;
import demo.model.Ip17;
import demo.model.Ipcn;
import ip.demo.IPDataHandler;
import ip.demo.IPutil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "classpath:spring-mybatis.xml" })
public class TestUserService {

    private static final Logger LOGGER = Logger.getLogger(TestUserService.class);

    // @Autowired
    // private StudentServices studentServices;
    //
    // @Autowired
    // private StipinfoMapper stipinfoMapper;

    @Autowired
    private IpcnMapper ipcnMapper;

    @Autowired
    private Ip17Mapper ip17Mapper;

    // @Test
    // public void testQueryById1() {
    // // Student userInfo = studentServices.getStudentById(1);
    // // Stipinfo ipinfo = stipinfoMapper.selectByPrimaryKey(1);
    // List<Stipinfo> ipall = stipinfoMapper.selectAll();
    // System.out.println("ipall count " + ipall.size());
    // Map<Integer, String[]> cityMap = new HashMap<Integer, String[]>();
    // for (Stipinfo ip : ipall) {
    // String geography =
    // IPDataHandler.findGeography(ip.getIpstart().toString());
    // String[] geoArray = StringUtils.split(geography, "\t");
    // if (IPDataHandler.findGeography(ip.getIpstart().toString())
    // .equals(IPDataHandler.findGeography(ip.getIpend().toString()))) {
    // if (geoArray.length > 2) {
    // ip.setCountry(geoArray[0]);
    // ip.setProvince(geoArray[1]);
    // ip.setCity(geoArray[2]);
    // }
    //
    // }
    //
    // }
    // stipinfoMapper.updateIp(ipall);
    // // IPDataHandler.findGeography(address);
    // // LOGGER.info(JSON.toJSON(ipinfo));
    // }
    // @Test
    // public void inset() {
    // List<Ip17> ip17List = new ArrayList<Ip17>(1000);
    // Ip17 ip17 = new Ip17();
    // ip17.setIpstart("1");
    // ip17.setIpend("1");
    // ip17.setCountry("1");
    // ip17.setProvince("1");
    // ip17.setCity("1");
    // ip17List.add(ip17);
    // System.out.println("记录" + ip17List.size());
    // ip17Mapper.insertBatch(ip17List);
    //
    // }
    @Test
    public void testQuery() {
        List<Ipcn> iplist = ipcnMapper.selectAll();
        int countIp = 0;
        System.out.println("IP " + countIp++ + iplist.size());
        for (Ipcn ipcn : iplist) {
            Long ipstart = IPutil.ip2Long(ipcn.getIpstart());
            // Long.parseLong(ipcn.getIpstart());
            Long ipend = IPutil.ip2Long(ipcn.getIpend());
            // Long.parseLong(ipcn.getIpend());
            List<Ip17> ip17List = new LinkedList<Ip17>();
            String[] tmp = StringUtils.split(IPDataHandler.findGeography(String.valueOf(ipstart)), "\t");
            Long iptmp = ipstart;
            for (Long l = ipstart + 10; l < ipend; l = l + 100) {
                String[] ret = StringUtils.split(IPDataHandler.findGeography(String.valueOf(l)), "\t");
                if (Arrays.deepEquals(ret, tmp)) {
                    continue;
                } else {
                    if (tmp.length >= 3) {
                        Ip17 ip17 = new Ip17();
                        ip17.setIpstart(String.valueOf(iptmp));
                        ip17.setIpend(String.valueOf(l));
                        ip17.setCountry(tmp[0]);
                        ip17.setProvince(tmp[1]);
                        ip17.setCity(tmp[2]);
                        ip17List.add(ip17);
                    } else if (tmp.length == 2) {
                        Ip17 ip17 = new Ip17();
                        ip17.setIpstart(String.valueOf(iptmp));
                        ip17.setIpend(String.valueOf(l));
                        ip17.setCountry(tmp[0]);
                        ip17.setProvince(tmp[1]);
                        ip17List.add(ip17);
                    }
                    l++;
                    iptmp = l;
                    tmp = StringUtils.split(IPDataHandler.findGeography(String.valueOf(l)), "\t");

                }
            }
            if (ip17List.size() == 0) {
                if (tmp.length >= 3) {
                    Ip17 ip17 = new Ip17();
                    ip17.setIpstart(String.valueOf(ipstart));
                    ip17.setIpend(String.valueOf(ipend));
                    ip17.setCountry(tmp[0]);
                    ip17.setProvince(tmp[1]);
                    ip17.setProvince(tmp[2]);
                    ip17List.add(ip17);
                } else {
                    Ip17 ip17 = new Ip17();
                    ip17.setIpstart(String.valueOf(ipstart));
                    ip17.setIpend(String.valueOf(ipend));
                    ip17.setCountry(tmp[0]);
                    ip17.setProvince(tmp[1]);
                    ip17List.add(ip17);
                }

            }
            System.out.println("记录" + ip17List.size());
            ip17Mapper.insertBatch(ip17List);
        }
    }

    // @Test
    // public void testQueryAll() throws UnknownHostException {
    // List<Ipcn> iplist = ipcnMapper.selectAll();
    // System.out.println(iplist.size());
    // for (Ipcn ipcn : iplist) {
    // List<String> ips = IPSerach.GET_IP_ARR(ipcn.getIpstart(),
    // ipcn.getIpend());
    // System.out.println("IP num:" + ips.size());
    // List<Ip17> ip17List = new LinkedList<Ip17>();
    // String[] tmp = StringUtils.split(IPDataHandler.findGeography(ips.get(0)),
    // "\t");
    // String iptmp = ips.get(0);
    // for (int i = 1; i < ips.size(); i++) {
    // String[] ret = StringUtils.split(IPDataHandler.findGeography(ips.get(i)),
    // "\t");
    // if (Arrays.deepEquals(ret, tmp)) {
    // continue;
    // } else {
    // // 记录数据
    // if (tmp.length >= 3) {
    // Ip17 ip17 = new Ip17();
    // ip17.setIpstart(String.valueOf(IPDataHandler.ip2long(iptmp)));
    // ip17.setIpend(String.valueOf(IPDataHandler.ip2long(ips.get(i))));
    // ip17.setCountry(tmp[0]);
    // ip17.setProvince(tmp[1]);
    // ip17.setCity(tmp[2]);
    // ip17List.add(ip17);
    // }
    // i++;
    // iptmp = ips.get(i);
    // tmp = StringUtils.split(IPDataHandler.findGeography(ips.get(i)), "\t");
    // }
    //
    // }
    // if (tmp.length >= 3) {
    // Ip17 ip17 = new Ip17();
    // ip17.setIpstart(String.valueOf(IPDataHandler.ip2long(iptmp)));
    // ip17.setIpend(String.valueOf(IPDataHandler.ip2long(ips.get(ips.size() -
    // 1))));
    // ip17.setCountry(tmp[0]);
    // ip17.setProvince(tmp[1]);
    // ip17.setCity(tmp[2]);
    // ip17List.add(ip17);
    // }
    // Ip17 ip17 = new Ip17();
    // ip17.setIpstart("1");
    // ip17.setIpend("1");
    // ip17.setCountry("1");
    // ip17.setProvince("1");
    // ip17.setCity("1");
    // ip17List.add(ip17);
    // System.out.println("记录" + ip17List.size());
    // ip17Mapper.insertBatch(ip17List);
    // }
    // }

}
