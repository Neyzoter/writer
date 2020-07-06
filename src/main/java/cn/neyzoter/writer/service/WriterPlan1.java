package cn.neyzoter.writer.service;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.manager.File0;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.task.WriterTask4Plan1;

/**
 * 写数据方案1
 * @author scc
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
            ts[i] = new Thread(new WriterTask4Plan1(String.valueOf(i + 1), files,  time + Contant.START_AFTER, time + Contant.END_AFTER));
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
