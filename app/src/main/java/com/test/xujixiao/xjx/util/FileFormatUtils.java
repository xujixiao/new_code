package com.test.xujixiao.xjx.util;

import java.io.FileInputStream;

/**
 * Created by xujixiao on 2015/12/29.
 */
public class FileFormatUtils {
    /**
     * 判断文件是否为图片文件(GIF,PNG,JPG)
     *
     * @param srcFileName
     * @return 1:gif图片2：png 3：gfif
     */
    public static int getImageFormat(String srcFileName) {
        FileInputStream imgFile = null;
        byte[] b = new byte[10];
        int l = -1;
        try {
            imgFile = new FileInputStream(srcFileName);
            l = imgFile.read(b);
            imgFile.close();
        } catch (Exception e) {
            return 0;
        }
        if (l == 10) {
            byte b0 = b[0];
            byte b1 = b[1];
            byte b2 = b[2];
            byte b3 = b[3];
            byte b6 = b[6];
            byte b7 = b[7];
            byte b8 = b[8];
            byte b9 = b[9];
            if (b0 == (byte) 'G' && b1 == (byte) 'I' && b2 == (byte) 'F') {
                TLog.log("图片类型gif");
                return 1;
            } else if (b1 == (byte) 'P' && b2 == (byte) 'N' && b3 == (byte) 'G') {
                TLog.log("图片类型png");
                return 2;
            } else if (b6 == (byte) 'J' && b7 == (byte) 'F' && b8 == (byte) 'I' && b9 == (byte) 'F') {
                TLog.log("图片类型jfif");
                return 3;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
