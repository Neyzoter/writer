package cn.neyzoter.writer.task;


import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.manager.File0If;

/**
 * 线程任务2<br/>
 * 每次写入后等待到一个阈值后flush
 * @author scc
 */
public class WriterTask2 implements Runnable{

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
    public WriterTask2(String c, File0If[] f, long s, long e) {
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
        int checkNum = Contant.PAGE_SIZE;
        while (System.currentTimeMillis() < endTime && run) {
            for (File0If f : files) {
                f.writeCheckFlush(content, checkNum);
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
