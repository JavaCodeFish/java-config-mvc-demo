package com.yuan.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.FileSystemException;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DownloadUtil {
    private final static Logger logger = LoggerFactory.getLogger("下载工具");
//    private static String SAVE_FLODER = "F:\\J.Video\\";
    // key: 文件名， value: 文件下载大小(字节)
    public static TreeMap<String, Long> DOWNLOAD_THREAD_LIST_MAP;
    // key: 文件名， value: 耗时（ms）
    public static TreeMap<String, Long> DOWNLOAD_SPENT_MAP;

    static {
        DOWNLOAD_THREAD_LIST_MAP = new TreeMap<>();
        DOWNLOAD_SPENT_MAP = new TreeMap<>();
    }

    public static void download(String saveFloder, String resourceUrl) throws Exception{
        File file = new File(saveFloder);
        if (file.exists()){
            if (!file.isDirectory()){
                throw new FileSystemException("文件已存在，请设置文件夹路径 | "+saveFloder);
            }
        }else{
            if (file.mkdirs()) {
                logger.info("创建文件夹成功 | "+saveFloder);
            }else{
                logger.error("已经创建文件夹失败 | "+saveFloder);
            }
        }

        String fileName = fileNameFromUrl(resourceUrl);
        if ("".equals(fileName.trim())){
            fileName = DateUtil.currentTimestampString();
        }
        logger.info(fileName + " 开始下载 >>>>>>");
        Long start = System.currentTimeMillis();

        DOWNLOAD_THREAD_LIST_MAP.put(fileName, 0L);
        DOWNLOAD_SPENT_MAP.put(fileName, 0L);

        InputStream is = null;
        BufferedInputStream bis = null;
        FileOutputStream fileOut = null;
        try {
            URL url = new URL(resourceUrl);
            URLConnection conn = url.openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is);

            if ("".equals(fileName)) {
                fileName = resourceUrl.substring(resourceUrl.lastIndexOf("/"));
            }

            fileOut = new FileOutputStream(new File(saveFloder+ "\\" + fileName));

            byte[] bytes = new byte[1024];

            int length;
            while ((length = bis.read(bytes)) != -1){
                fileOut.write(bytes, 0, length);
                DOWNLOAD_THREAD_LIST_MAP.put(fileName, DOWNLOAD_THREAD_LIST_MAP.get(fileName)+length);
                DOWNLOAD_SPENT_MAP.put(fileName, System.currentTimeMillis() - start);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (is != null)
                    is.close();
                if (bis != null)
                    bis.close();
                if (fileOut != null)
                    fileOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DOWNLOAD_SPENT_MAP.put(fileName, System.currentTimeMillis() - start);
            logger.info(fileName + " 结束下载 <<<<<<");
        }
    }

    public static String fileNameFromUrl(String url){
        url = url.replaceAll("(http://|https://)","");
        Pattern pattern = Pattern.compile("[-a-zA-z0-9_]+\\.(jpg|png|gif|avi|mp4|flv)");
        Matcher m = pattern.matcher(url);
        String fileName = "";
        if (m.find()){
            fileName = m.group();
        }
        if ("".equals(fileName.trim())){
            pattern = Pattern.compile("/[-a-zA-z0-9_]+$");
            m = pattern.matcher(url);
            if (m.find()){
                fileName = m.group();
            }
        }
        return fileName.replace("/","");
    }


}
