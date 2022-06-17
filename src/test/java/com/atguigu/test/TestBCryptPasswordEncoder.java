package com.atguigu.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCryptPasswordEncoder {
    public static void main(String[] args) {
        BCryptPasswordEncoder bpe = new BCryptPasswordEncoder();
        //同样明文每次生成出来的密文都不一样
        String encode = bpe.encode("123");
        System.out.println(encode);
        //60长度字符串
        System.out.println("$2a$10$IP3/o6kQ0DZxcPg87oCdw.k9CcFced9wHkvF8cB5xwwnH51UX5YY2".length());
        //$2a$10$IP3/o6kQ0DZxcPg87oCdw.k9CcFced9wHkvF8cB5xwwnH51UX5YY2
        //$2a$10$90obGBgo8YuF6GQyeveEhO/JI7ZQEplpDXocdhY4gG.y2dEXbGGHy
        //$2a$10$46eeqywAhGNBTbNZrAzAyO4Eo5XoM0sXVSxZZnrvm/7y7WGWv0V0i
    }
}
