package cn.neyzoter.writer;

import cn.neyzoter.writer.service.WriterPlan2;
import cn.neyzoter.writer.service.WriterPlan3;

/**
 * 主函数
 * @author scc
 * @date 2020-7-6
 */
public class RunApp {
    public static void main(String[] args) {
//        WriterPlan1 writerPlan1 = new WriterPlan1();
//        writerPlan1.start();
//        WriterPlan2 writerPlan2 = new WriterPlan2();
//        writerPlan2.start();

        WriterPlan3 writerPlan3 = new WriterPlan3();
        writerPlan3.start();
    }
}
