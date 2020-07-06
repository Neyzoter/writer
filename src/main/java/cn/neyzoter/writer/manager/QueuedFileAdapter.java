package cn.neyzoter.writer.manager;

/**
 * 队列化的File0
 * @author scc
 */
public class QueuedFileAdapter extends File0 {

    private StringBuilder sb;

    public QueuedFileAdapter (String path, String[] s) {
        super(path, s);
        sb = new StringBuilder();
    }

    /**
     * 只写不刷新，并且检查是否需要刷新
     * @param content 内容
     * @return 写入成功
     */
    @Override
    public synchronized boolean writeCheckFlush(String content, int checkNum) {
        if (blockedNum >= checkNum) {
            return writeAndFlush(content);
        } else {
            return write(content);
        }
    }
    /**
     * 写入并刷新数据到文件
     * @param content 内容
     * @return 写入成功
     */
    @Override
    public synchronized boolean writeAndFlush(String content) {
        try {
            if (write(content)) {
                writer.append(sb.toString());
                writer.flush();
                // 清零
                blockedNum = 0;
                sb = new StringBuilder();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 只写不刷新
     * @param content 内容
     * @return 写入成功
     */
    @Override
    public synchronized boolean write(String content) {
        try {
            if (!SEQ[ptr].equals(content)) {
                return false;
            }
            ptr = (ptr + 1) % SEQ.length;
            blockedNum ++;
            sb.append(content);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
