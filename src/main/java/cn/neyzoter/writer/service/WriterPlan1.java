package cn.neyzoter.writer.service;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.manager.File0;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.thread.WriterTask;
import java.util.*;

/**
 * 写数据方案1
 * @author scc
 * @date 2020-7-6
 */
public class WriterPlan1 {
    private Thread[] ts;
    private File0[] files;
    public WriterPlan1 () {
        int tn = Contant.THREAD_NUM;
        files = Files.createFiles(File0Utils.fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            ts[i] = new Thread(new WriterTask(String.valueOf(i + 1), files,  time + 1000, time + 1000 * 10));
        }
    }



    /**
     * 开始运行
     */
    public void start () {
        for (Thread t : ts) {
            t.start();
        }
    }
}
