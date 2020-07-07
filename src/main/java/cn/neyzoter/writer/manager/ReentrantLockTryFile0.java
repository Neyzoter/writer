package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于ReentrantLock的文件<br>
 * 并使用tryLock
 * @author scc
 */
public class ReentrantLockTryFile0 implements File0If {
    protected FileWriter writer;
    /**
     * 顺序
     */
    private String[] SEQ;
    /**
     * 下一个数据的指针
     */
    private volatile int ptr;
    /**
     * 被阻塞的字符个数
     */
    private int blockedNum;

    /**
     * 可重入锁
     */
    private ReentrantLock lock;

    /**
     * 文件
     * @param path 路径
     * @param s 字符顺序
     */
    public ReentrantLockTryFile0(String path, String[] s) {
        lock = new ReentrantLock();
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
            if (lock.tryLock()) {
                boolean result;
                if (blockedNum >= checkNum) {
                    result = writeAndFlush(content);
                } else {
                    result = write(content);
                }
                lock.unlock();
                return result;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean writeAndFlush(String content) {
        try {
            if (lock.tryLock()) {
                boolean result;
                if (result = write(content)) {
                    writer.flush();
                    // 清零
                    blockedNum = 0;
                }
                lock.unlock();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean write(String content) {
        try {
            if (lock.tryLock()) {
                boolean result;
                if (!SEQ[ptr].equals(content)) {
                    result = false;
                } else {
                    writer.append(content);
                    blockedNum ++;
                    ptr = (ptr + 1) % SEQ.length;
                    result = true;
                }
                lock.unlock();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
