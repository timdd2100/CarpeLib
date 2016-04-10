package com.test.work.library.Common;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathUtils {


    public MathUtils() {

    }

    /** */
    /**
     * 提供精確的加法運算。
     *
     * @param v1 被加數
     * @param v2 加數
     * @return 和
     */

    public static double add(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.add(b2).doubleValue();

    }

    public static String add(String v1, String v2) {

        BigDecimal b1 = new BigDecimal((v1));

        BigDecimal b2 = new BigDecimal((v2));

        return b1.add(b2).toPlainString();

    }
    /** */
    /**
     * 提供精確的減法運算
     *
     * @param v1 被減數
     * @param v2 減數
     * @return 差
     */

    public static double sub(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.subtract(b2).doubleValue();

    }

    public static String sub(String v1, String v2) {

        BigDecimal b1 = new BigDecimal((v1));

        BigDecimal b2 = new BigDecimal((v2));

        return b1.subtract(b2).toPlainString();

    }


    /** */
    /**
     * 提供精確的乘法運算。
     *
     * @param v1 被乘數
     * @param v2 乘數
     * @return 積
     */

    public static double mul(double v1, double v2) {

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.multiply(b2).doubleValue();

    }

    public static String mul(String v1, String v2) {

        BigDecimal b1 = new BigDecimal(v1);

        BigDecimal b2 = new BigDecimal(v2);

        return b1.multiply(b2).toPlainString();
    }


    /** */
    /**
     * 提供（相對）精確的除法運算，精確到
     * <p/>
     * 小數點以後"scale"位，以後的數字四捨五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 精確位數
     * @return 商
     */

    public static double div(double v1, double v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal(Double.toString(v1));

        BigDecimal b2 = new BigDecimal(Double.toString(v2));

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String div(String v1, String v2, int scale) {

        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }

        BigDecimal b1 = new BigDecimal((v1));

        BigDecimal b2 = new BigDecimal((v2));

        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /** */
    /**
     * 提供精確的小數位四捨五入處理。
     *
     * @param v     需要四捨五入的數字
     * @param scale 小數點 幾位
     * @return result
     */

    public static double round(double v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal(Double.toString(v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static String round(String v, int scale) {

        if (scale < 0) {

            throw new IllegalArgumentException(

                    "The scale must be a positive integer or zero");

        }

        BigDecimal b = new BigDecimal((v));

        BigDecimal one = new BigDecimal("1");

        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    /**
     * 預設格式成 三位一撇, 小數兩位
     *
     * @param num
     * @return
     */
    public static String getFormatStr(double num) {
        DecimalFormat formater = new DecimalFormat("#,###.##");
        return formater.format(num);
    }

    /**
     * 預設格式成 三位一撇, 小數兩位
     *
     * @param str
     * @return
     */
    public static String getFormatStr(String str) {
        Double num = Double.parseDouble(str);
        DecimalFormat formater = new DecimalFormat("#,###.##");
        return formater.format(num);
    }

    /**
     * 使用想調整格式
     *
     * @param num
     * @return
     */
    public static String getFormatStr(DecimalFormat formater, double num) {
        if (formater != null) {
            return formater.format(num);
        } else {
            return "";
        }
    }

    /**
     * 使用想調整格式
     *
     * @param str
     * @return
     */
    public static String getFormatStr(DecimalFormat formater, String str) {
        if (formater != null) {
            Double num = Double.parseDouble(str);
            return formater.format(num);
        } else {
            return "";
        }
    }
}
