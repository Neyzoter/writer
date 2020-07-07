package cn.neyzoter.writer.service;

import cn.neyzoter.writer.constant.Constant;

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
        String basePath = Constant.FILE_BASE_PATH;
        String[] seq1 = Constant.FILE1_SEQ;
        String[] seq2 = Constant.FILE2_SEQ;
        String[] seq3 = Constant.FILE3_SEQ;
        fileParam.put(basePath + Constant.FILE1_NAME, seq1);
        fileParam.put(basePath + Constant.FILE2_NAME, seq2);
        fileParam.put(basePath + Constant.FILE3_NAME, seq3);
        assert Constant.FILE_NUM == fileParam.size();
        return fileParam;
    }
}
