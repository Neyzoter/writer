package cn.neyzoter.writer.manager;

import java.util.Map;
import java.util.Set;

/**
 * 文件方法
 * @author scc
 */
public class Files {
    /**
     * 创建Sync文件读写器
     * @param param 参数
     *              key：地址
     *              val：数字序列
     * @return 文件数组
     */
    public static SyncFile0[] createSyncFiles(Map<String, String[]> param) {
        SyncFile0[] files = new SyncFile0[param.size()];
        Set<String> iter = param.keySet();
        int num = 0;
        for (String k : iter) {
            String[] seq = param.get(k);
            files[num] = new SyncFile0(k, seq);
            num++;
        }
        return files;
    }

    /**
     * 创建Queued文件读写器
     * @param param 参数
     *              key：地址
     *              val：数字序列
     * @return 文件数组
     */
    public static QueuedSyncFile0Adapter[] createQuedFiles (Map<String, String[]> param) {
        QueuedSyncFile0Adapter[] files = new QueuedSyncFile0Adapter[param.size()];
        Set<String> iter = param.keySet();
        int num = 0;
        for (String k : iter) {
            String[] seq = param.get(k);
            files[num] = new QueuedSyncFile0Adapter(k, seq);
            num++;
        }
        return files;
    }

    /**
     * 创建ReentrantLock文件读写器
     * @param param 参数
     *              key：地址
     *              val：数字序列
     * @return 文件数组
     */
    public static LockFile0[] createLockFiles(Map<String, String[]> param) {
        LockFile0[] files = new LockFile0[param.size()];
        Set<String> iter = param.keySet();
        int num = 0;
        for (String k : iter) {
            String[] seq = param.get(k);
            files[num] = new LockFile0(k, seq);
            num++;
        }
        return files;
    }

    /**
     * 创建Semapphore文件读写器
     * @param param 参数
     *              key：地址
     *              val：数字序列
     * @return 文件数组
     */
    public static SemaphoreFile0[] createSemaporeFiles(Map<String, String[]> param) {
        SemaphoreFile0[] files = new SemaphoreFile0[param.size()];
        Set<String> iter = param.keySet();
        int num = 0;
        for (String k : iter) {
            String[] seq = param.get(k);
            files[num] = new SemaphoreFile0(k, seq);
            num++;
        }
        return files;
    }
}
