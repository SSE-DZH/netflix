package com.zhiend.netflix.mapper;

import com.zhiend.netflix.entity.NetflixTitles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Zhiend
 * @since 2024-06-20
 */
public interface NetflixTitlesMapper extends BaseMapper<NetflixTitles> {

    @Select("SELECT COUNT(*) FROM netflix_titles WHERE type = #{type}")
    int selectCountByType(String type);
}
