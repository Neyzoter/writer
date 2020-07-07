package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;

/**
 * 无锁化写文件
 * @author scc
 */
public class NonLockFile0 implements File0If {
    protected FileWriter writer;
    /**
     * 顺序
     */
    protected String[] SEQ;
    /**
     * 下一个数据的指针
     */
    protected volatile int ptr;
    /**
     * 被阻塞的字符个数
     */
    protected int blockedNum;

    /**
     * 文件
     * @param path 路径
     * @param s 字符顺序
     */
    public NonLockFile0(String path, String[] s) {
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
    public boolean writeCheckFlush(String content, int checkNum) {
        if (blockedNum >= checkNum) {
            return writeAndFlush(content);
        } else {
            return write(content);
        }
    }

    @Override
    public boolean writeAndFlush(String content) {
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
    public boolean write(String content) {
        try {
            if (!SEQ[ptr].equals(content)) {
                return false;
            }
            writer.append(content);
            blockedNum ++;
            ptr = (ptr + 1) % SEQ.length;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


}
