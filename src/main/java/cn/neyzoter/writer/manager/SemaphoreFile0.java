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
    protected String[] SEQ;
    /**
     * 下一个数据的指针
     */
    protected int ptr;

    protected int blockedNum;

    private Semaphore semaphore;

    /**
     * 文件
     * @param path 路径
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
            boolean result;
            if (blockedNum >= checkNum) {
                result = writeAndFlush(content);
            } else {
                result = write(content);
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
            // 由于semaphore不能重入
            // 所以这里需要结合其他的锁
            synchronized (writer) {
                boolean result;
                if (result = write(content)) {
                    writer.flush();
                    // 清零
                    blockedNum = 0;
                }
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
            // semaphore只能acq一次，所以只有随后可以使用
            // 上层不可以调用
            if (semaphore.tryAcquire()) {
                boolean result;
                if (!SEQ[ptr].equals(content)) {
                    result = false;
                } else {
                    ptr = (ptr + 1) % SEQ.length;
                    blockedNum ++;
                    writer.append(content);
                    result = true;
                }
                semaphore.release();
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
