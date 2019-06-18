package com.luckyxs.conversion.vo;

/**
 * 用于储存解析的图片每个像素点的RGB值
 * @author luckyxs
 * @date 2019/6/17
 */
public class ImgVo {
    // red 值
    private int red;
    // green 值
    private int green;
    // blue 值
    private int blue;
    // 是否为最后一个像素点
    private boolean status;

    public ImgVo(int red, int green, int blue, boolean status) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.status = status;
    }

    public int getRed() {

        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }



    @Override
    public String toString() {
        // 格式
        return "("+red+","+green+","+blue+")";
    }

    public int getRgb() {
        // 计算出的RGB值
        return ((red*256)+green)*256+blue;
    }
}
