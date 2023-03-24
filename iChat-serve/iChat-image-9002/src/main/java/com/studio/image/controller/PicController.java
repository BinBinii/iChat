package com.studio.image.controller;

import com.studio.image.service.FileService;
import com.studio.image.vo.ResultBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author: BinBin
 * @Date: 2022/10/08/10:45
 * @Description:
 */

@Slf4j
@RestController
public class PicController {

    @Autowired
    FileService fileService;

    @PostMapping("/upload")
    public ResultBean upload(@RequestParam("media") MultipartFile file) {
        log.info("save file name {}", file.getOriginalFilename());
        String filePath = fileService.saveFile(file);
        return ResultBean.success(filePath);
    }

    @GetMapping("/download")
    public ResultBean downloadFile(HttpServletResponse response,
                                   @RequestParam(value = "fileName") String fileName) {

        Boolean result = fileService.downloadFile(response, fileName);
        return ResultBean.success(result);
    }

    @GetMapping("/list")
    public ResultBean list() {
        return ResultBean.success(fileService.getFiles());
    }

}