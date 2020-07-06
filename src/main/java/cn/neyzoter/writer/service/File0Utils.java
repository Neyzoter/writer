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
}
