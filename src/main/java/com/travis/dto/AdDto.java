package com.travis.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.travis.bean.Ad;
import org.springframework.web.multipart.MultipartFile;

@JsonInclude(Include.NON_NULL)
public class AdDto extends Ad {

    /*接收文件*/
    private MultipartFile imgFile;

    private String img;

    public MultipartFile getImgFile() {
        return imgFile;
    }

    public void setImgFile(MultipartFile imgFile) {
        this.imgFile = imgFile;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
