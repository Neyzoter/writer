package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;

/**
 * 文件0
 * @author scc
 */
public class File0 {
    private FileWriter writer;
    /**
     * 顺序
     */
    private String[] SEQ;
    /**
     * 下一个数据的指针
     */
    private int ptr;

    private int blockedNum;

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

    /**
     * 写入并刷新数据到文件
     * @param content 内容
     * @return 写入成功
     */
    public synchronized boolean writeAndFlush(String content) {
        try {
            if (write(content)) {
                writer.flush();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 只写不刷新
     * @param content 内容
     * @return 写入成功
     */
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
