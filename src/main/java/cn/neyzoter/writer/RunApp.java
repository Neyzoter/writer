package cn.neyzoter.writer;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.service.WriterPlanIf;

/**
 * 主函数
 * @author scc
 * @date 2020-7-6
 */
public class RunApp {
    public static void main(String[] args) {
        updateProp();
        try {
            // 初始化为方案1
            String plan = "WriterPlan1";
            // 如果有参数输入，则更新方案
            if (args.length > 0) {
                plan = args[0];
            }
            // 通过反射获取一个方案实例
            Class classType = Class.forName("cn.neyzoter.writer.service." + plan);
            WriterPlanIf writerPlan = (WriterPlanIf) classType.newInstance();
            // 开始运行，运行时间大约10秒钟，包括1秒钟的等待时间
            writerPlan.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新参数<br/>
     * 参数设置方法-Dfile.basepath=...
     */
    public static void updateProp () {
        String path = (String) System.getProperties().get(Contant.FILE_BASE_PATH_VM_OPTION);
        System.out.println(path);
        if (path != null) {
            Contant.setFileBasePath(path);
        }
    }
}
