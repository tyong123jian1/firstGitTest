package com.jiejue.netty.protocol;

/**
 * Created by jianbin on 2017-11-06.
 */
public interface TestMessage extends Message {

    public static int CONNECT = 0x6001;
    public static int CONNECT_RESP = 0x60000001;
    public static int PING = 0x6002;
    public static int PING_RESP = 0x60000002;
    public static int IMMESSAGE = 0x6003;
    public static int IMMESSAGE_RESP = 0x60000003;

    public final static short SUCCESS = 1;
    public final static short FAILED = 0;

    /**
     * 设置消息体长度
     *
     * @param
     */
    public void setBodyLength(int bodyLength);

    /**
     * 获取消息体长度
     *
     * @return
     */
    public int getBodyLength();

}
