package com.jiejue.netty.protocol;

import java.io.*;

/**
 * Created by jianbin on 2017-10-13.
 */
public class Header {
    protected int commandID; //4
    protected int sequence; //4
    protected int length;  //4

    public Header() {
    }

    public Header(byte[] bytes) throws IOException {
        this.parse(bytes);
    }

    public Header(int commandID, int sequence, int length) {
        this.commandID = commandID;
        this.sequence = sequence;
        this.length = length;
    }

    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeInt(commandID);
            dos.writeInt(sequence);
            dos.writeInt(length);
        } finally {
            if (bos != null) {
                bos.close();
            }
            if (dos != null) {
                dos.close();
            }
        }
        return bos.toByteArray();
    }

    public void parse(byte[] bytes) throws IOException {
        DataInputStream dis = null;
        ByteArrayInputStream bis = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            dis = new DataInputStream(bis);
            this.commandID = dis.readInt();
            this.sequence = dis.readInt();
            this.length = dis.readInt();
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (dis != null) {
                dis.close();
            }
        }
    }

    public int getCommandID() {
        return commandID;
    }

    public void setCommandID(int commandID) {
        this.commandID = commandID;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Header [commandID=" + commandID + ", sequence=" + sequence
                + ", length=" + length + "]";
    }
}
