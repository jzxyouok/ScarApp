package com.zero2ipo.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

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
       String mac2= getMAC();
        String mac1=getLocalMac();
        System.out.println(mac1+":"+mac2);
    }

    public static String getLocalMac() throws SocketException, UnknownHostException {
        // TODO Auto-generated method stub
        //获取网卡，获取地址
        InetAddress ia = InetAddress.getLocalHost();
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 按照"XX-XX-XX-XX-XX-XX"格式，获取本机MAC地址
     * @return
     * @throws Exception
     */
    public static String getMacAddress(){
        Enumeration<NetworkInterface> ni = null;
        try {
            ni = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        while(ni.hasMoreElements()){
            NetworkInterface netI = ni.nextElement();

            byte[] bytes = new byte[0];
            try {
                bytes = netI.getHardwareAddress();
            } catch (SocketException e) {
                e.printStackTrace();
            }
            try {
                if(netI.isUp() && netI != null && bytes != null && bytes.length == 6){
                    StringBuffer sb = new StringBuffer();
                    for(byte b:bytes){
                        //与11110000作按位与运算以便读取当前字节高4位
                        sb.append(Integer.toHexString((b&240)>>4));
                        //与00001111作按位与运算以便读取当前字节低4位
                        sb.append(Integer.toHexString(b&15));
                        sb.append("-");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    return sb.toString().toUpperCase();
                }
            } catch (SocketException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    public static String getMAC() {
        String mac = null;
        try {
            Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");

            InputStream is = pro.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String message = br.readLine();

            int index = -1;
            while (message != null) {
                if ((index = message.indexOf("Physical Address")) > 0) {
                    mac = message.substring(index + 36).trim();
                    break;
                }
                message = br.readLine();
            }
            System.out.println(mac);
            br.close();
            pro.destroy();
        } catch (IOException e) {
            System.out.println("Can't get mac address!");
            return null;
        }
        return mac;
    }

}



