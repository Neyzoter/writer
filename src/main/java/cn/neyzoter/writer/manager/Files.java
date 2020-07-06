package cn.neyzoter.writer.manager;

import java.util.*;

/**
 * 文件方法
 * @author scc
 */
public class Files {
    /**
     * 创建文件读写器
     * @param param 参数
     *              key：地址
     *              val：数字序列
     * @return 文件数组
     */
    public static File0[] createFiles (Map<String, String[]> param) {
        File0[] files = new File0[param.size()];
        Set<String> iter = param.keySet();
        int num = 0;
        for (String k : iter) {
            String[] seq = param.get(k);
            files[num] = new File0(k, seq);
            num++;
        }
        return files;
    }
}
