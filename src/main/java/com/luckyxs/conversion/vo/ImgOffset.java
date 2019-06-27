package com.luckyxs.conversion.vo;

/**
 * RGB偏移量
 * @author luckyxs
 * @date 2019/6/27
 */
public class ImgOffset extends ImgVo{
    //r 偏移量
    private int rOffset;
    //g 偏移量
    private int gOffset;
    //b 偏移量
    private int bOffset;

    // r 偏移目标  true 正偏移 false 反偏移
    private RgbOffset rOffsetStatus;

    // g 偏移目标
    private RgbOffset gOffsetStatus;

    // b 偏移目标
    private RgbOffset bOffsetStatus;

    // r 偏移之后范围
    private int[] rScope;
    // g 偏移之后范围
    private int[] gScope;
    // b 偏移之后范围
    private int[] bScope;


    public int getrOffset() {
        return rOffset;
    }

    public void setrOffset(int rOffset) {
        this.rOffset = rOffset;
    }

    public int getgOffset() {
        return gOffset;
    }

    public void setgOffset(int gOffset) {
        this.gOffset = gOffset;
    }

    public int[] getrScope() {
        rScope = new int[2];
        int red = super.getRed();
        // 判断偏移状态
        switch (rOffsetStatus){
            case NOT_OFFSET:
                rScope[0] = red;
                rScope[1] = red;
            case OFFSET:
                if (red - rOffset < 0)
                    rScope[0] = 0;
                else
                    rScope[0] = red - rOffset;
                if (red + rOffset > 255)
                    rScope[1] = 255;
                else
                    rScope[1] = red + rOffset;
                break;
            case NEGATIVE_OFFSET:
                if (red - rOffset < 0)
                    rScope[0] = 0;
                else
                    rScope[0] = red - rOffset;
                rScope[1] = red;
                break;
            case POSITIVE_OFFSET:
                rScope[0] = red;
                if (red + rOffset > 255)
                    rScope[1] = 255;
                else
                    rScope[1] = red + rOffset;
                break;
        }
        return rScope;
    }

    public int[] getgScope() {
        gScope = new int[2];
        int green = super.getGreen();
        // 判断偏移状态
        switch (gOffsetStatus){
            case NOT_OFFSET:
                gScope[0] = green;
                gScope[1] = green;
            case OFFSET:
                if (green - gOffset < 0)
                    gScope[0] = 0;
                else
                    gScope[0] = green - gOffset;
                if (green + gOffset > 255)
                    gScope[1] = 255;
                else
                    gScope[1] = green + gOffset;
                break;
            case NEGATIVE_OFFSET:
                if (green - gOffset < 0)
                    gScope[0] = 0;
                else
                    gScope[0] = green - gOffset;
                gScope[1] = green;
                break;
            case POSITIVE_OFFSET:
                gScope[0] = green;
                if (green + gOffset > 255)
                    gScope[1] = 255;
                else
                    gScope[1] = green + gOffset;
                break;
        }
        return gScope;
    }

    public int[] getbScope() {
        bScope = new int[2];
        int blue = super.getBlue();
        // 判断偏移状态
        switch (bOffsetStatus){
            case NOT_OFFSET:
                bScope[0] = blue;
                bScope[1] = blue;
            case OFFSET:
                if (blue - bOffset < 0)
                    bScope[0] = 0;
                else
                    bScope[0] = blue - bOffset;
                if (blue + bOffset > 255)
                    bScope[1] = 255;
                else
                    bScope[1] = blue + bOffset;
                break;
            case NEGATIVE_OFFSET:
                if (blue - bOffset < 0)
                    bScope[0] = 0;
                else
                    bScope[0] = blue - bOffset;
                bScope[1] = blue;
                break;
            case POSITIVE_OFFSET:
                bScope[0] = blue;
                if (blue + bOffset > 255)
                    bScope[1] = 255;
                else
                    bScope[1] = blue + bOffset;
                break;
        }
        return bScope;
    }

    public int getbOffset() {
        return bOffset;
    }

    public void setbOffset(int bOffset) {
        this.bOffset = bOffset;
    }

    public RgbOffset getrOffsetStatus() {
        return rOffsetStatus;
    }

    public void setrOffsetStatus(RgbOffset rOffsetStatus) {
        this.rOffsetStatus = rOffsetStatus;
    }

    public RgbOffset getgOffsetStatus() {
        return gOffsetStatus;
    }

    public void setgOffsetStatus(RgbOffset gOffsetStatus) {
        this.gOffsetStatus = gOffsetStatus;
    }

    public RgbOffset getbOffsetStatus() {
        return bOffsetStatus;
    }

    public void setbOffsetStatus(RgbOffset bOffsetStatus) {
        this.bOffsetStatus = bOffsetStatus;
    }

    public ImgOffset(String rgb) {
        super(rgb);
    }

    public ImgOffset(int red, int green, int blue, boolean status) {
        super(red, green, blue, status);
    }

    public ImgOffset(int red, int green, int blue) {
        super(red, green, blue);
    }

    public ImgOffset(String rgb, int rOffset, int gOffset, int bOffset, RgbOffset rOffsetStatus, RgbOffset gOffsetStatus, RgbOffset bOffsetStatus) {
        super(rgb);
        this.rOffset = rOffset;
        this.gOffset = gOffset;
        this.bOffset = bOffset;
        this.rOffsetStatus = rOffsetStatus;
        this.gOffsetStatus = gOffsetStatus;
        this.bOffsetStatus = bOffsetStatus;
    }

    public ImgOffset(String rgb,int offset,RgbOffset rgbOffset){
        super(rgb);
        this.rOffset = offset;
        this.gOffset = offset;
        this.bOffset = offset;
        this.rOffsetStatus = rgbOffset;
        this.gOffsetStatus = rgbOffset;
        this.bOffsetStatus = rgbOffset;
    }
}
