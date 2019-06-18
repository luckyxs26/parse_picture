package com.luckyxs.main;

import com.luckyxs.conversion.ParseImg;
import com.luckyxs.conversion.vo.ImgVo;

import java.io.File;
import java.util.List;

/**
 * 程序主入口
 * @author luckyxs
 * @date 2019/6/18
 */
public class Main {
    /**
     * 主程序入口
     * @param args
     */
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();    //获取开始时间
        // 需要上传的图片地址
        File f = new File("C:\\Users\\fengm\\Desktop\\1.png");
        // 获取图片的RGB解析集合
        List<ImgVo> list = ParseImg.getImgRGB(f);
        // 最好使用文件的绝对路径
        ParseImg.MakeHtml(list,
                "D:\\java\\WorkSpace\\ideaSpace\\parse_picture\\src\\main\\resources\\static\\1.html",
                "D:\\java\\WorkSpace\\ideaSpace\\parse_picture\\src\\main\\resources\\static",
                "模板吗");
        System.out.println("生成ok");
        long endTime = System.currentTimeMillis();    //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时

    }
}
