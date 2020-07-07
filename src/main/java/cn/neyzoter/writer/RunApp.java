package cn.neyzoter.writer;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.service.WriterPlanIf;

/**
 * 主函数
 * @author scc
 */
public class RunApp {
    public static void main(String[] args) {
        updateProp(args);
        try {
            // 通过反射获取一个方案实例
            Class classType = Class.forName("cn.neyzoter.writer.service." + Contant.PLAN);
            WriterPlanIf writerPlan = (WriterPlanIf) classType.newInstance();
            // 开始运行，运行时间大约10秒钟，包括1秒钟的等待时间
            writerPlan.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新参数<br>
     * @param args 输入参数
     */
    public static void updateProp (String[] args) {
        int len = args.length > 2 ? 2 : args.length;
        switch (len) {
            case 2:
                Contant.setFileBasePath(args[--len]);
                System.out.println(Contant.FILE_BASE_PATH);
            case 1:
                Contant.setPlan(args[--len]);
                System.out.println(Contant.PLAN);
            default:
                break;
        }
    }
}
