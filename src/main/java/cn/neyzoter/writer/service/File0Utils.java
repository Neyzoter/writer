package cn.neyzoter.writer.service;

import cn.neyzoter.writer.contant.Contant;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件工具
 * @author scc
 */
public class File0Utils {
    /**
     * 获取文件参数
     * @return 文件参数
     */
    public static Map<String, String[]> fileParam () {
        Map<String, String[]> fileParam = new HashMap<>();
        String basePath = Contant.FILE_BASE_PATH;
        String[] seq1 = Contant.FILE1_SEQ;
        String[] seq2 = Contant.FILE2_SEQ;
        String[] seq3 = Contant.FILE3_SEQ;
        fileParam.put(basePath + Contant.FILE1_NAME, seq1);
        fileParam.put(basePath + Contant.FILE2_NAME, seq2);
        fileParam.put(basePath + Contant.FILE3_NAME, seq3);
        assert Contant.FILE_NUM == fileParam.size();
        return fileParam;
    }
}
