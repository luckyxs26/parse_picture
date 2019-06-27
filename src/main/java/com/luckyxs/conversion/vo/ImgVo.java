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

    private int rgb;

    public ImgVo(String rgb){
        // 是否符合RGB颜色值
        String s = "^[rR][gG][Bb][(](s*[1-2]?[0-9]?[0-9],)(s*[1-2]?[0-9]?[0-9],)(s*[1-2]?[0-9]?[0-9])[)]$";
        if(rgb.matches(s)){
          // 解析RGB值
            String[] split = rgb.split("\\(");
            split = split[1].split("\\)");
            split = split[0].split(",");
            red = isColorNum(Integer.parseInt(split[0]));
            green = isColorNum(Integer.parseInt(split[1]));
            blue = isColorNum(Integer.parseInt(split[2]));
            this.rgb = ((red*256)+green)*256+blue;
        }else
            return ;
    }


    public ImgVo(int red, int green, int blue, boolean status) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.status = status;
    }
    public ImgVo(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
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

    private int isColorNum(int num){
        if (num < 0)
            return 0;
        else if(num > 255)
            return 255;
        else
            return num;
    }


    @Override
    public String toString() {
        // 格式
        return "("+red+","+green+","+blue+")";
    }

    public int getRgb() {
        if(rgb != 0)
            return rgb;
        // 计算出的RGB值
        return ((red*256)+green)*256+blue;
    }

    public void setRgb(int rgb) {
        this.rgb = rgb;
    }
}
