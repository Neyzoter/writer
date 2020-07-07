package cn.neyzoter.writer.service;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.manager.File0If;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.task.WriterFlushTask;

/**
 * 写数据方案1的ReentrantLock版本<br>
 * 而且使用阻塞的lock方法
 * @author scc
 */
public class WriterPlan5 implements WriterPlanIf {
    private Thread[] ts;
    private File0If[] files;
    public WriterPlan5() {
        int tn = Contant.THREAD_NUM;
        files = Files.createReentrantLockFiles(File0Utils.fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            ts[i] = new Thread(new WriterFlushTask(String.valueOf(i + 1), files,  time + Contant.START_AFTER, time + Contant.END_AFTER));
        }
    }



    @Override
    public void start () {
        for (Thread t : ts) {
            t.start();
        }
    }
}
