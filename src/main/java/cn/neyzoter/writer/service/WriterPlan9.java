package cn.neyzoter.writer.service;

import cn.neyzoter.writer.constant.Constant;
import cn.neyzoter.writer.manager.File0If;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.task.WriterCheckFlushTask;
import cn.neyzoter.writer.task.WriterFlushTask;

/**
 * 无锁化写数据方案
 * @author scc
 */
public class WriterPlan9 implements WriterPlanIf{
    private Thread[] ts;
    private File0If[] files;
    public WriterPlan9() {
        int tn = Constant.THREAD_NUM;
        files = Files.createNonLockFiles(File0Utils.fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            ts[i] = new Thread(new WriterCheckFlushTask(String.valueOf(i + 1), files,  time + Constant.START_AFTER, time + Constant.END_AFTER));
        }
    }

    @Override
    public void start () {
        for (Thread t : ts) {
            t.start();
        }
    }
}
