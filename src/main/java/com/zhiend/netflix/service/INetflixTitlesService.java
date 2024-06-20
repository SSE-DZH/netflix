package com.zhiend.netflix.service;

import com.zhiend.netflix.entity.BackPage;
import com.zhiend.netflix.entity.NetflixTitles;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Zhiend
 * @since 2024-06-20
 */
public interface INetflixTitlesService extends IService<NetflixTitles> {

    BackPage<NetflixTitles> queryPage(Long pageNo, Long pageSize);

    int countByType(String type);
}
