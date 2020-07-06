package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;

/**
 * 文件0
 * @author scc
 */
public class File0 implements File0If {
    protected FileWriter writer;
    /**
     * 顺序
     */
    protected String[] SEQ;
    /**
     * 下一个数据的指针
     */
    protected int ptr;

    protected int blockedNum;

    /**
     * 文件1
     * @param path 路径
     */
    public File0(String path, String[] s) {
        ptr = 0;
        blockedNum = 0;
        SEQ = s;
        try {
            writer = new FileWriter(new File(path), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public synchronized boolean writeCheckFlush(String content, int checkNum) {
        if (blockedNum >= checkNum) {
            return writeAndFlush(content);
        } else {
            return write(content);
        }
    }

    @Override
    public synchronized boolean writeAndFlush(String content) {
        try {
            if (write(content)) {
                writer.flush();
                // 清零
                blockedNum = 0;
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public synchronized boolean write(String content) {
        try {
            if (!SEQ[ptr].equals(content)) {
                return false;
            }
            ptr = (ptr + 1) % SEQ.length;
            blockedNum ++;
            writer.append(content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}