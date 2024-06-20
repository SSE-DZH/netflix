package com.zhiend.netflix.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class NetflixTitlesUpdateDTO {
    
    @NotBlank(message = "类型不能为空")
    private String type;

    @NotBlank(message = "标题不能为空")
    private String title;

    private String director;

    private String cast;

    private String country;

    private LocalDate dateAdded;

    @NotNull(message = "发行年份不能为空")
    private Integer releaseYear;

    @NotBlank(message = "评级不能为空")
    private String rating;

    @NotBlank(message = "持续时间不能为空")
    private String duration;

    @NotBlank(message = "列出分类不能为空")
    private String listedIn;

    @NotBlank(message = "描述不能为空")
    private String description;

}
