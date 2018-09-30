package com.rk.controller;

import com.rk.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Created by Qin_Yikai on 2018-09-30.
 */
@RequestMapping("file")
@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping("uploadImage")
    @ResponseBody
    public HashMap<String, Object> UploadImage(HttpServletRequest request, MultipartFile imgFile) {
        HashMap<String, Object> hashMap = new HashMap<>();
        try {
            String fileUrl = FileUtil.SaveImage(request, imgFile);
            if (fileUrl != null) {
                hashMap.put("error", 0);
                hashMap.put("url", fileUrl);

                return hashMap;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.info("上传图片失败:" + ex.getMessage(), ex);
        }

        hashMap.put("error", 1);
        hashMap.put("message", "上传失败");

        return hashMap;
    }
}