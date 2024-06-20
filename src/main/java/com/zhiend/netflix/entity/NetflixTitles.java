package com.zhiend.netflix.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Zhiend
 * @since 2024-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("netflix_titles")
public class NetflixTitles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "show_id")
    private Long showId;

    private String type;

    private String title;

    private String director;

    private String cast;

    private String country;

    private LocalDate dateAdded;

    private Integer releaseYear;

    private String rating;

    private String duration;

    private String listedIn;

    private String description;


}
