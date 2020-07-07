package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/**
 * 基于ReentrantLock的文件
 * @author scc
 */
public class SemaphoreFile0 implements File0If {
    protected FileWriter writer;
    /**
     * 顺序
     */
    private String[] SEQ;
    /**
     * 下一个数据的指针
     */
    private int ptr;

    /**
     * 被阻塞的字符个数
     */
    private int blockedNum;

    /**
     * 信号量
     */
    private Semaphore semaphore;

    /**
     * 文件
     * @param path 路径
     * @param s 字符顺序
     */
    public SemaphoreFile0(String path, String[] s) {
        semaphore = new Semaphore(1);
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
        try {
            boolean result = false;
            if (semaphore.tryAcquire()) {
                if (!SEQ[ptr].equals(content)) {
                    result = false;
                } else {
                    ptr = (ptr + 1) % SEQ.length;
                    blockedNum ++;
                    writer.append(content);
                    result = true;
                }
                if (blockedNum >= checkNum) {
                    writer.flush();
                    blockedNum = 0;
                }
                semaphore.release();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean writeAndFlush(String content) {
        try {
            boolean result = false;
            if (semaphore.tryAcquire()) {
                if (!SEQ[ptr].equals(content)) {
                    result = false;
                } else {
                    ptr = (ptr + 1) % SEQ.length;
                    writer.append(content);
                    writer.flush();
                    blockedNum = 0;
                    result = true;
                }
                semaphore.release();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean write(String content) {
        try {
            // semaphore只能acq一次，所以只有随后可以使用
            // 上层不可以调用acq
            boolean result = false;
            if (semaphore.tryAcquire()) {
                if (!SEQ[ptr].equals(content)) {
                    result = false;
                } else {
                    ptr = (ptr + 1) % SEQ.length;
                    blockedNum ++;
                    writer.append(content);
                    result = true;
                }
                semaphore.release();
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
