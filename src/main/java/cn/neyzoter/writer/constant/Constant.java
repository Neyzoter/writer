package cn.neyzoter.writer.constant;

/**
 * 常量
 * @author scc
 */
public class Constant {
    /**
     * 文件存放位置
     */
    public static String FILE_BASE_PATH = "/files/";

    /**
     * 写入器方案
     */
    public static String PLAN = "WriterPlan1";

    /**
     * 文件1名称
     */
    public static final String FILE1_NAME = "f1";
    /**
     * 文件2名称
     */
    public static final String FILE2_NAME = "f2";
    /**
     * 文件3名称
     */
    public static final String FILE3_NAME = "f3";


    /**
     * 文件1字符顺序
     */
    public static final String[] FILE1_SEQ = {"1", "2", "3", "4", "5"};
    /**
     * 文件2字符顺序
     */
    public static final String[] FILE2_SEQ = {"5", "4", "3", "2", "1"};
    /**
     * 文件3字符顺序
     */
    public static final String[] FILE3_SEQ = {"1", "3", "4", "2", "5"};
    /**
     * 线程个数
     */
    public static final int THREAD_NUM = 5;
    /**
     * 文件个数
     */
    public static final int FILE_NUM = 3;

    /**
     * 物理页的字节大小
     */
    public static final int PAGE_SIZE = 4096;

    /**
     * 开始
     */
    public static final int START_AFTER = 1000;

    /**
     * 结束
     */
    public static final int END_AFTER = 10 * 1000;


    public static void setFileBasePath(String p) {
        FILE_BASE_PATH = p;
    }
    public static void setPlan(String p) {
        PLAN = p;
    }

}
