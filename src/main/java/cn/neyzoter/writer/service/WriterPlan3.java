package cn.neyzoter.writer.service;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.manager.File0If;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.task.WriterFlushTask;

/**
 * 基于Synchronized的写数据方案3<br/>
 * 使用队列化File0，write写入到StringBuilder，到达阈值后flush
 * @author scc
 */
public class WriterPlan3 implements WriterPlanIf {
    private Thread[] ts;
    private File0If[] files;
    public WriterPlan3() {
        int tn = Contant.THREAD_NUM;
        // 使用队列化File0
        files = Files.createQuedFiles(File0Utils.fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            // 必须使用WriterTask4Plan2才可以体现出效果
            // 因为WriterTask4Plan会不停地flush
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
