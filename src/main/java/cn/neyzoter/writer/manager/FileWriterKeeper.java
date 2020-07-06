package cn.neyzoter.writer.manager;

import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 文件写入器管理
 * @author scc
 * @date 2020-7-6
 */
public class FileWriterKeeper {
    private LinkedBlockingQueue<FileWriter> writers;

    public FileWriterKeeper(List<String> path) {
        try {
            writers = new LinkedBlockingQueue<FileWriter>(path.size());
            for (String p : path) {
                putWriter(new FileWriter(new File(p), true));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 加入到Writer中
     * @param writer 写入器
     */
    public void putWriter(FileWriter writer) {
        try {
            writers.put(writer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /**
     * 获取FileWriter
     * @return FileWriter
     */
    public FileWriter pollOneWriter() {
        return writers.poll();
    }
}
