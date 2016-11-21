package ip.demo;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class IPutil {
    /**
     * 将字符串表示的ip地址转换为long表示.
     *
     * @param ip
     *            ip地址
     * @return 以32位整数表示的ip地址
     */
    public static final long ip2Long(final String ip) {
        // if (!RegexpUtils.isExactlyMatches("(\\d{1,3}\\.){3}\\d{1,3}", ip)) {
        // throw new IllegalArgumentException("[" + ip + "]不是有效的ip地址");
        // }
        final String[] ipNums = ip.split("\\.");
        return (Long.parseLong(ipNums[0]) << 24) + (Long.parseLong(ipNums[1]) << 16) + (Long.parseLong(ipNums[2]) << 8)
                + (Long.parseLong(ipNums[3]));
    }

    public static long ip2num(String ip) {
        long ipNum = 0;
        try {
            if (ip != null) {
                String ips[] = ip.split("\\.");
                for (int i = 0; i < ips.length; i++) {
                    int k = Integer.parseInt(ips[i]);
                    ipNum = ipNum + k * (1L << ((3 - i) * 8));
                }
            }
        } catch (Exception e) {
        }
        return ipNum;
    }

    public static void main(String[] args) throws UnknownHostException {
//        long ip = IPutil.ip2Long("211.100.254.114");
        String ip = IPutil.num2ip(1034698751l);
        System.out.println(ip);
//        System.out.println(IPutil.ip2num("211.100.254.114"));
//        System.out.println(IPutil.num2ip(976547840));
//        System.out.println(
//                Inet4Address.getByName(String.valueOf(IPutil.ip2num("192.168.0.254"))).getHostAddress().toString());
    }

    public static String num2ip(long longIp) {
        StringBuffer sb = new StringBuffer("");
        // 直接右移24位
        sb.append(String.valueOf((longIp >>> 24)));
        sb.append(".");
        // 将高8位置0，然后右移16位
        sb.append(String.valueOf((longIp & 0x00FFFFFF) >>> 16));
        sb.append(".");
        // 将高16位置0，然后右移8位
        sb.append(String.valueOf((longIp & 0x0000FFFF) >>> 8));
        sb.append(".");
        // 将高24位置0
        sb.append(String.valueOf((longIp & 0x000000FF)));
        return sb.toString();
    }
}
