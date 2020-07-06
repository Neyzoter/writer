package cn.neyzoter.writer.task;


import cn.neyzoter.writer.manager.File0If;

import java.util.HashMap;

/**
 * 线程任务1<br/>
 * 每次写入后马上flush
 * @author scc
 */
public class WriterFlushTask implements Runnable{

    private String content;
    private File0If[] files;
    private boolean run;
    private long startTime;
    private long endTime;
    /**
     * 任务构造器
     * @param c 写入内容
     * @param f 文件
     */
    public WriterFlushTask(String c, File0If[] f, long s, long e) {
        content = c;
        files = f;
        run = true;
        startTime = s;
        endTime = e;
    }

    @Override
    public void run () {
        // 等待开始
        while (System.currentTimeMillis() < startTime) {

        }
        while (System.currentTimeMillis() < endTime && run) {
            for (File0If f : files) {
                f.writeAndFlush(content);
            }
        }
    }

    /**
     * 停止此任务
     */
    public void stop () {
        run = false;
    }
}
