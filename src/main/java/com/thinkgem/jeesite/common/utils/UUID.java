/**
 * 
 */
package com.thinkgem.jeesite.common.utils;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author LiaoXueWei
 *
 */

//生成有顺序的UUID
//IP+sep+StartUpTime+sep+HiTime+sep+LoTime+sep+Seq(<Short.MAX_VALUE)
//或
//IP+sep+left(StartUpTime,4)+sep+right(StartUpTime,4)+sep+HiTime+sep+LoTime+Seq(<Short.MAX_VALUE)
//sep可以定义
public class UUID {
    private static final int IP;
    public static int IptoInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }
    static {
        int ipadd;
        try {
            ipadd = IptoInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
        //System.out.println(IP);
    }
    private static short counter = (short) 0;
    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);
    private boolean GuidFormat = true;
    private boolean toUpperCase = true;

    public UUID() {
    }
    /**
     * Unique across JVMs on this machine (unless they load this class in the
     * same quater second - very unlikely)
     */
    protected int getJVM() {
        return JVM;
    }
    /**
     * Unique in a millisecond for this JVM instance (unless there are >
     * Short.MAX_VALUE instances created in a millisecond)
     */
    protected short getCount() {
        synchronized (UUID.class) {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }
    /**
     * Unique in a local network
     */
    protected int getIP() {
        return IP;
    }
    /**
     * Unique down to millisecond
     */
    protected short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }
    protected int getLoTime() {
        return (int) System.currentTimeMillis();
    }
    private String sep = "";
    protected String format(int intval) {
        String formatted = Integer.toHexString(intval);
        StringBuffer buf = new StringBuffer("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }
    protected String format(short shortval) {
        String formatted = Integer.toHexString(shortval);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }
    /**
     * @param guidFormat
     *            the guidFormat to set
     */
    public void setGuidFormat(boolean guidFormat) {
        this.GuidFormat = guidFormat;
    }
    /**
     * @return the guidFormat
     */
    public boolean getGuidFormat() {
        return GuidFormat;
    }
    /**
     * @param toUpperCase
     *            the toUpperCase to set
     */
    public void setToUpperCase(boolean toUpperCase) {
        this.toUpperCase = toUpperCase;
    }
    /**
     * @return the toUpperCase
     */
    public boolean getToUpperCase() {
        return toUpperCase;
    }
    
    /**
	 * @return the sep
	 */
	public String getSep() {
		return sep;
	}
	/**
	 * @param sep the sep to set
	 */
	public void setSep(String sep) {
		this.sep = sep;
	}
	
	public Serializable generate() {
        String s = null;
        if (GuidFormat)
            s = new StringBuffer(36).append(format(getIP())).append(sep)
                    .append(format(getJVM()).substring(0, 4)).append(sep).append(
                            format(getJVM()).substring(4, 8)).append(sep).append(
                            format(getHiTime())).append(sep).append(
                            format(getLoTime())).append(format(getCount()))
                    .toString();
        else
            s = new StringBuffer(36).append(format(getIP())).append(sep)
                    .append(format(getJVM())).append(sep).append(
                            format(getHiTime())).append(sep).append(
                            format(getLoTime())).append(sep).append(
                            format(getCount())).toString();
        if (toUpperCase)
            s = s.toUpperCase();
        return s;
    }

    public static java.util.UUID getUuid() {
        UUID p = new UUID();
        p.setSep("-");
        return java.util.UUID.fromString(p.generate().toString());
    }

    public static String getUuidString(String sep) {
        UUID UUID = new UUID();
        UUID.setSep(sep);
        return UUID.generate().toString();
    }

    public static String getUuidString() {
        return getUuidString("");
    }

    public static void main(String[] args) {
        UUID UUID = new UUID();
        for (int i = 0; i < 10000; i++)
            System.out.println(UUID.generate().toString());
        for (int i = 0; i < 10; i++)
            System.out.println(UUID.getUuid());
    }
}
