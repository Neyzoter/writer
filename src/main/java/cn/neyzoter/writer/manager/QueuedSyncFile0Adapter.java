package cn.neyzoter.writer.manager;

/**
 * 队列化的File0
 * @author scc
 */
public class QueuedFile0Adapter extends File0 {

    private StringBuilder sb;

    public QueuedFile0Adapter(String path, String[] s) {
        super(path, s);
        sb = new StringBuilder();
    }

    @Override
    public synchronized boolean writeCheckFlush(String content, int checkNum) {
        if (blockedNum >= checkNum) {
            return writeAndFlush(content);
        } else {
            return write(content);
        }
    }

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
