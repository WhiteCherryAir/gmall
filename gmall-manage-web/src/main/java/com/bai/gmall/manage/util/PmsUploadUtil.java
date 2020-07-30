package com.bai.gmall.manage.util;


import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.web.multipart.MultipartFile;

public class PmsUploadUtil {

    public static String uploadImage(MultipartFile multipartFile) {
        String imgUrl = "http://192.168.188.14";
        //上传图片服务器
        // 配置fdfs的全局链接地址
        String tracker = PmsUploadUtil.class.getResource("/tracker.conf").getPath();// 获得配置文件的路径

        try {
            ClientGlobal.init(tracker);
        } catch (Exception e) {
            e.printStackTrace();
        }

        TrackerClient trackerClient = new TrackerClient();

        // 获得一个trackerServer的实例
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //通过tracker获得一个StorageClient客户端
        StorageClient storageClient = new StorageClient(trackerServer, null);
        try {
            //获得上传的二进制对象
            byte[] bytes = multipartFile.getBytes();
            //获得文件后缀名   //a.jpg  originalFilename文件全名
            String originalFilename = multipartFile.getOriginalFilename();
            System.out.println(originalFilename);
            int i = originalFilename.lastIndexOf(".");
            String extName = originalFilename.substring(i + 1);
            String[] uploadInfos = storageClient.upload_appender_file(bytes, extName, null);
            for (String uploadInfo : uploadInfos) {
                imgUrl += "/" + uploadInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return imgUrl;
    }
}
