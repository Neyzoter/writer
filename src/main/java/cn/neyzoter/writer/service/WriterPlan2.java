package cn.neyzoter.writer.service;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.manager.File0;
import cn.neyzoter.writer.manager.Files;
import cn.neyzoter.writer.thread.WriterTask;

import java.util.HashMap;
import java.util.Map;

/**
 * 写数据方案1
 * @author scc
 * @date 2020-7-6
 */
public class WriterPlan2 {
    private Thread[] ts;
    private File0[] files;
    public WriterPlan2() {
        int tn = Contant.THREAD_NUM;
        files = Files.createFiles(fileParam());
        ts = new Thread[tn];
        long time = System.currentTimeMillis();
        for (int i = 0; i < tn; i ++) {
            ts[i] = new Thread(new WriterTask(String.valueOf(i + 1), files,  time + 1000, time + 1000 * 10));
        }
    }

    /**
     * 获取文件参数
     * @return 文件参数
     */
    public Map<String, String[]> fileParam () {
        Map<String, String[]> fileParam = new HashMap<>();
        String basePath = "/home/scc/code/java/armee-proj/writer/files/";
        String[] seq1 = {"1", "2", "3", "4", "5"};
        String[] seq2 = {"5", "4", "3", "2", "1"};
        String[] seq3 = {"1", "3", "4", "2", "5"};
        fileParam.put(basePath + "f1", seq1);
        fileParam.put(basePath + "f2", seq2);
        fileParam.put(basePath + "f3", seq3);
        assert Contant.FILE_NUM == fileParam.size();
        return fileParam;
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
