package cn.neyzoter.writer;

import cn.neyzoter.writer.contant.Contant;
import cn.neyzoter.writer.service.WriterPlan2;
import cn.neyzoter.writer.service.WriterPlan3;
import cn.neyzoter.writer.service.WriterPlan4;
import cn.neyzoter.writer.service.WriterPlan5;

/**
 * 主函数
 * @author scc
 * @date 2020-7-6
 */
public class RunApp {
    public static void main(String[] args) {
        updateProp();
//        WriterPlan1 writerPlan1 = new WriterPlan1();
//        writerPlan1.start();
//        WriterPlan2 writerPlan2 = new WriterPlan2();
//        writerPlan2.start();

//        WriterPlan3 writerPlan3 = new WriterPlan3();
//        writerPlan3.start();

//        WriterPlan4 writerPlan4 = new WriterPlan4();
//        writerPlan4.start();

        WriterPlan5 writerPlan5 = new WriterPlan5();
        writerPlan5.start();

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
