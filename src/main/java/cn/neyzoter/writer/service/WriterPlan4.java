package cn.neyzoter.writer.service;

import cn.neyzoter.writer.constant.Constant;
import cn.neyzoter.writer.manager.File0If;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.task.WriterFlushTask;

/**
 * 写数据方案1的ReentrantLock版本，并使用tryLock
 * @author scc
 */
public class WriterPlan4 implements WriterPlanIf {
    private Thread[] ts;
    private File0If[] files;
    public WriterPlan4() {
        int tn = Constant.THREAD_NUM;
        files = Files.createReentrantLockTryFiles(File0Utils.fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            ts[i] = new Thread(new WriterFlushTask(String.valueOf(i + 1), files,  time + Constant.START_AFTER, time + Constant.END_AFTER));
        }
    }

    @Override
    public void start () {
        for (Thread t : ts) {
            t.start();
        }
    }
}
