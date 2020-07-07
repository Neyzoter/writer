package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 基于ReentrantLock的文件<br>
 * 并使用tryLock
 * @author scc
 */
public class ReentrantLockFile0 implements File0If {
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
     * 可重入锁
     */
    private ReentrantLock lock;

    /**
     * 文件
     * @param path 路径
     * @param s 字符顺序
     */
    public ReentrantLockFile0(String path, String[] s) {
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
        lock.lock();
        try {
            if (blockedNum >= checkNum) {
                return writeAndFlush(content);
            } else {
                return write(content);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public boolean writeAndFlush(String content) {
        lock.lock();
        try {
            boolean result;
            if (result = write(content)) {
                writer.flush();
                // 清零
                blockedNum = 0;
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public boolean write(String content) {
        lock.lock();
        try {
            if (!SEQ[ptr].equals(content)) {
                return false;
            } else {
                ptr = (ptr + 1) % SEQ.length;
                blockedNum ++;
                writer.append(content);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return false;
    }
}
