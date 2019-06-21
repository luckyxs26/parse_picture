package com.luckyxs.conversion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 改变图片颜色生成图片
 * @author luckyxs
 * @date 2019/6/18
 */
public class PictureColor {

    /**
     *  生成RGB三种颜色的图片
     * @param filePath  图片路径
     * @param newFilePath 生成的图片存储路径
     * @param type 图片类型 1->R 2->G 3->B
     */
    public static void getRGBPicture(String filePath, String newFilePath, int type){
        OutputStream output = null;
        try {
            BufferedImage img = ImageIO.read(new File(filePath));
            int imageType = img.getType();// 获取图片类型
            int width = img.getWidth();// 获取图片宽度
            int height = img.getHeight();// 获取图片高度
            int startX = 0;// 开始的横坐标
            int startY = 0;// 开始的纵坐标
            int offset = 0;// 偏移量
            int scansize = width;// 扫描间距
            int dd = width - startX;// 被遍历的宽度间距
            int hh = height - startY;// 被遍历的高度间距
            int x0 = width / 2;// 横向中间点
            int y0 = height / 2;// 纵向中间点
            System.out.println("dd:" + dd + " hh:" + hh);
            System.out.println("width:" + width + " height:" + height);
            System.out.println("imageType:" + imageType);
            System.out.println("size:"+(offset + hh * scansize + dd));
            // rgb的数组，保存像素，用一维数组表示二位图像像素数组
            int[] rgbArray = new int[offset + hh * scansize + dd];// 偏移量+纵向开始坐标*扫描间距+横向开始坐标
            //这里大家都感觉很奇怪为什么会是这样一个算法呢？为什么不知道用width*height就够用了，这里作者也搞不懂，你只要默认记住了这个规则，
            //然后取点的时候按这个规则去取就可以了
            // newArray 保存处理后的像素
            int[] newArray = new int[offset + hh * scansize + dd];// 偏移量+纵向开始坐标*扫描间距+横向开始坐标
            /**
             * getRGB public int[] getRGB(int startX, int startY, int w, int h,
             * int[] rgbArray, int offset, int scansize)从图像数据的某一部分返回默认 RGB 颜色模型
             * (TYPE_INT_ARGB) 和默认 sRGB 颜色空间中整数像素数组。如果该默认模型与该图像的 ColorModel
             * 不匹配，则发生颜色转换。在使用此方法所返回的数据中，每个颜色分量只有 8 位精度。通过图像中指定的坐标 (x, y)，ARGB
             * 像素可以按如下方式访问：
             *
             * pixel = rgbArray[offset + (y-startY)*scansize + (x-startX)];
             * 如果该区域不在边界内部，则抛出 ArrayOutOfBoundsException。但是，不保证进行显式的边界检查。
             *
             *
             * 参数：
             *  startX - 起始 X 坐标
             *  startY - 起始 Y 坐标
             *  w - 区域的宽度
             *  h - 区域的高度
             * rgbArray - 如果不为 null，则在此写入 rgb 像素
             * offset - rgbArray 中的偏移量
             * scansize - rgbArray 的扫描行间距
             * 返回： RGB 像素数组。
             */
            img.getRGB(startX, startY, width, height, rgbArray, offset,
                    scansize); // 把像素存到固定的数组里面去
            int count=0;
            for(int i : rgbArray){
                if(i!=0)
                    count=count+1;
            }
            System.out.println(count);
            int rgb = rgbArray[offset + (y0 - startY) * scansize
                    + (x0 - startX)]; // 位于图片正中央的rgb像素点
            Color c = new Color(rgb);
            System.out.println("中间像素点的rgb:"+c);
            for (int i = 0; i < height - startY; i++) {//遍历每一行
                for (int j = 0; j < width - startX; j++) {//遍历每一列
                    c = new Color(rgbArray[offset+i * scansize + j]);
                    switch (type) {
                        case 1://红色灰度图片
                            newArray[i*dd + j] = new Color(c.getRed(), 0, 0).getRGB();
                            break;
                        case 2://绿色灰度图片
                            newArray[i*dd + j] = new Color(0, c.getGreen(), 0).getRGB();
                            break;
                        case 3://蓝色灰度图片
                            newArray[i * dd + j] = new Color(0, 0, c.getBlue()).getRGB();
                            break;

                        default:
                            break;
                    }



                }
            }
            // 新建一个图像
            File out = new File(newFilePath);
            if (!out.exists())
                out.createNewFile();
            output = new FileOutputStream(out);
            BufferedImage imgOut = new BufferedImage(width, height,
                    BufferedImage.TYPE_3BYTE_BGR);
            imgOut.setRGB(startX, startY, width, height, newArray, offset,
                    scansize);
            ImageIO.write(imgOut, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
