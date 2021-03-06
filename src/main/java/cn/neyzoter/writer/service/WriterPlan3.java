package cn.neyzoter.writer.service;

import cn.neyzoter.writer.constant.Constant;
import cn.neyzoter.writer.manager.File0If;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.task.WriterCheckFlushTask;
import cn.neyzoter.writer.task.WriterFlushTask;

/**
 * 基于Synchronized的写数据方案3<br>
 * 使用队列化File0，write写入到StringBuilder，到达阈值后flush
 * @author scc
 */
public class WriterPlan3 implements WriterPlanIf {
    private Thread[] ts;
    private File0If[] files;
    public WriterPlan3() {
        int tn = Constant.THREAD_NUM;
        // 使用队列化File0
        files = Files.createQuedFiles(File0Utils.fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            // 此处使用WriterCheckFlushTask
            // QueuedSyncFile0Adapter的WriterCheckFlushTask是通过把数据合并到StringBuilder
            // 而不是系统调用到flush
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
