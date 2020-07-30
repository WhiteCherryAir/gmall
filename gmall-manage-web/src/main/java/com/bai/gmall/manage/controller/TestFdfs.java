package com.bai.gmall.manage.controller;

public class TestFdfs {
    public static void main(String[] args) {
        String originalFilename = "123.231.32.1.21.31.jpg";
        int i = originalFilename.lastIndexOf(".");
        String extName = originalFilename.substring(i+1);
        System.out.println(extName);
    }
}
