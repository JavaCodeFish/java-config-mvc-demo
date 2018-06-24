package com.yuan.demo.Thread;

import com.yuan.demo.utils.DownloadUtil;

public class DownloadRunnable implements Runnable{
    private String saveFloder;
    private String resourceUrl;

    public DownloadRunnable(String saveFloder, String resourceUrl) {
        this.saveFloder = saveFloder;
        this.resourceUrl = resourceUrl;
    }

    @Override
    public void run() {
        try {
            DownloadUtil.download(this.saveFloder, this.resourceUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(new DownloadRunnable("F:\\J.Video\\java","https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1529748089&di=f68764ca9abaa9d4e7d110ad6f850a68&src=http://imgsrc.baidu.com/imgad/pic/item/9d82d158ccbf6c8154bdd5ccb63eb13533fa4008.jpg")).start();
    }
}
