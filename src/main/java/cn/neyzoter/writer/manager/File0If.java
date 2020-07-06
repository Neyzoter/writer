package cn.neyzoter.writer.manager;

/**
 * File0接口
 * @author scc
 */
public interface File0If {
    /**
     * 只写不刷新
     * @param content 内容
     * @return 写入成功
     */
    boolean write(String content);
    /**
     * 写完后检查是否需要刷新
     * @param content 内容
     * @return 写入成功
     */
    boolean writeCheckFlush(String content, int checkNum);
    /**
     * 写入并刷新数据到文件
     * @param content 内容
     * @return 写入成功
     */
    boolean writeAndFlush(String content);
}
