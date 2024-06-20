package com.zhiend.netflix.mapper;

import com.zhiend.netflix.dto.DirectorCountDTO;
import com.zhiend.netflix.entity.NetflixTitles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhiend.netflix.vo.CountryCountVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT director, COUNT(*) as count FROM netflix_titles WHERE director IS NOT NULL GROUP BY director ORDER BY count DESC")
    List<DirectorCountDTO> selectCountByDirector();

    @Select("SELECT country, COUNT(*) as count FROM netflix_titles WHERE country IS NOT NULL GROUP BY country ORDER BY count DESC LIMIT 10")
    List<CountryCountVO> countByCountry();
}
