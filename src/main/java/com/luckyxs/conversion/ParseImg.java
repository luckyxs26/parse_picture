package com.luckyxs.conversion;

import com.luckyxs.conversion.vo.ImgVo;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luckyxs
 * @date 2019/6/17
 */
public class ParseImg {

    /**
     * 获取图片文件的RGB数组
     * @param file
     */
    public static List<ImgVo> getImgRGB(File file){
        List<ImgVo> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        try {
            BufferedImage bufImg = ImageIO.read(file);
            int height = bufImg.getHeight();
            int width = bufImg.getWidth();
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int rgb = bufImg.getRGB(j, i);
                    System.out.println(rgb);
                    list.add(
                            new ImgVo(
                                    (rgb &0xff0000) >> 16,
                                    (rgb &0xff00) >> 8,
                                    rgb &0xff,
                                    false
                                    )
                            );
                    System.out.println(list.get((i+1)*j).toString());
                }
                // 读取完一行使用一个null来进行换行操作
                list.get(width*i).setStatus(true);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(list.get(list.size()-1).isStatus());
        return list;
    }


    /**
     * @Title: MakeHtml
     * @Description: 创建html
     * @param    filePath 设定模板文件
     * @param    disrPath  生成html的存放路径
     * @param    fileName  生成html名字
     * @return void    返回类型
     * @throws
     */
    public static void MakeHtml(List<ImgVo> list,String filePath,String disrPath,String fileName ){
        try {
            StringBuffer stringBuffer = new StringBuffer();
            for (ImgVo iv:list){
                // 判断是否是开头第一个元素
                if(iv.isStatus()){
                    stringBuffer.append("<div class=\"div\" style=\"clear:both;\" div-data=\""+iv.toString()+"\"></div>");
//                    stringBuffer.append("<div class=\"div\" style=\"clear:both;background-color: rgb"+iv.toString()+"\"></div>");
                }else{
                    stringBuffer.append("<div class=\"div\" div-data=\""+iv.toString()+"\"></div>");
//                    stringBuffer.append("<div class=\"div\" style=\"background-color: rgb"+iv.toString()+"\"></div>");
                }
            }
            String templateContent = "";
            FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();
            templateContent = new String(bytes);
            // 模板替换
            templateContent = templateContent.replaceAll("###div###", stringBuffer.toString());
            System.out.println(templateContent);
            // 生成的html名称
            String fileame = fileName + ".html";
            fileame = disrPath+"/" + fileame;// 生成的html文件保存路径。
            Files.write(Paths.get(fileame),templateContent.getBytes());
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }

    /**
     * 绘制一张图片 可用于测试像素颜色是否正确
     * @param list
     * @param bufImg
     */
    public static void createImg(List<ImgVo> list,BufferedImage bufImg,String picType,File file){
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                // (i*width)+j
                bufImg.setRGB(j,i,list.get((i*width)+j).getRgb());
            }
        }
        try {
            ImageIO.write(bufImg, picType, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     *  图片减少像素
     * @param file 图片文件
     * @param path 新生成的图片地址
     * @return
     * @throws IOException
     */
    public static File reducePicturePixel(File file,String path) throws IOException {
        //读取图片
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        //字节流转图片对象
        Image bi =ImageIO.read(in);
        //获取图像的高度，宽度
        int height=bi.getHeight(null);
        int width =bi.getWidth(null);
        // 长宽比例
        double hw = height/(width*1.0);
        //判断像素长宽是否高于500
        for (;;){
            if (width>200)
                width = width - 100;
            else{
                height =(int)(width * hw);
                break;
            }
        }
        //构建图片流
        BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //绘制改变尺寸后的图
        tag.getGraphics().drawImage(bi, 0, 0,width, height, null);
        //输出流
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(path));
        //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        //encoder.encode(tag);
        ImageIO.write(tag, "PNG",out);
        in.close();
        out.close();
        return new File(path);
    }
}
