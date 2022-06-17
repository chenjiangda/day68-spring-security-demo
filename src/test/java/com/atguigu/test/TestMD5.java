package com.atguigu.test;

import com.atguigu.util.MD5Utils;

public class TestMD5 {

    public static void main(String[] args) {

        /**
         * MD5算法的特点:
         *  1.明文加密成密文，生成固定长度16字节，可以将每一个字节分解出高4位和低4位
         *  2.原文不变，加密的密文是相同的
         *  4.不可逆，只能加密，不能解密。只能通过彩虹表暴力破解。
         *  3.已经被破解，直接使用不太安全，密文最好是连续加密15次以上，就不易被破解。或者采用加盐的方式进行加密，更安全
         */
        String md5 = MD5Utils.md5("123");
        System.out.println("md5 = " + md5); // md5 = 202cb962ac59075b964b07152d234b70 这个是16进制的数



    }

}
