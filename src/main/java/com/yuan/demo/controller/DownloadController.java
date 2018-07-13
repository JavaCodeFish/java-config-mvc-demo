package com.yuan.demo.controller;

import com.yuan.demo.model.dto.DownloadInfo;
import com.yuan.demo.model.dto.ResponseMsg;
import com.yuan.demo.utils.DownloadUtil;
import com.yuan.demo.utils.JsonUtil;
import com.yuan.demo.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/download")
public class DownloadController {

    @RequestMapping("")
    public String index(){
        return "download";
    }

    @RequestMapping(value = "/start", produces = "application/json;charset=utf-8")
    public @ResponseBody String start(String saveFloder, String url){
        ResponseMsg msg = new ResponseMsg();
        try {
            if (StringUtil.isEmpty(saveFloder) || StringUtil.isEmpty(url)){
                msg.setCode(400);
                msg.setMsg("参数异常");
                return JsonUtil.toJsonString(msg);
            }
            DownloadUtil.download(saveFloder, url);
            msg.setCode(200);
            msg.setMsg("已经开始下载");
        }catch (Exception e){
            msg.setCode(500);
            msg.setMsg("开始下载失败");
            e.printStackTrace();
        }
        return JsonUtil.toJsonString(msg);
    }

    @RequestMapping(value = "/query", produces = "application/json;charset=utf-8")
    public @ResponseBody String query(){
        List<DownloadInfo> list = new ArrayList<>();
        for (Map.Entry<String, Long> entry : DownloadUtil.DOWNLOAD_THREAD_LIST_MAP.entrySet()){
            DownloadInfo info = new DownloadInfo();
            info.setFileName(entry.getKey());
            info.setSize(entry.getValue());
            info.setSpent(DownloadUtil.DOWNLOAD_SPENT_MAP.get(entry.getKey()));
            list.add(info);
        }
        return JsonUtil.toJsonString(list);
    }

}
