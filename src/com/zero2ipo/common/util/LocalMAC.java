package com.zero2ipo.common.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * 物理地址是48位，别和ipv6搞错了
 */
public class LocalMAC {
    /**
     * @param args
     * @throws UnknownHostException
     * @throws SocketException
     */
    public static void main(String[] args) throws UnknownHostException, SocketException {
        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DAY_OF_MONTH);
        int n = cal.get(Calendar.DAY_OF_WEEK);
        if (n == 1) {
            n = 7;
        } else {
            n = n - 1;
        }
        System.out.println("当天为本周第" + n + "天");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 1; i <= 7; i++) {
            cal.set(Calendar.DAY_OF_MONTH, date + i - n);
            System.out.println("本周第" + i + "天：" + sdf.format(cal.getTime()));
        }
    }

    public  static String getLocalMac() throws SocketException, UnknownHostException {
        // TODO Auto-generated method stub
        //获取网卡，获取地址
        InetAddress ia = InetAddress.getLocalHost();
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }
}
