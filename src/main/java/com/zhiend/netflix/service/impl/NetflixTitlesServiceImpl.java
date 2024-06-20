package com.zhiend.netflix.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhiend.netflix.dto.DirectorCountDTO;
import com.zhiend.netflix.entity.BackPage;
import com.zhiend.netflix.entity.NetflixTitles;
import com.zhiend.netflix.mapper.NetflixTitlesMapper;
import com.zhiend.netflix.service.INetflixTitlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhiend.netflix.vo.CountryCountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Zhiend
 * @since 2024-06-20
 */
@Service
public class NetflixTitlesServiceImpl extends ServiceImpl<NetflixTitlesMapper, NetflixTitles> implements INetflixTitlesService {

    @Autowired
    private NetflixTitlesMapper netflixTitlesMapper;

    @Override
    //根据页码和每页分页数给出分页查询，使用wrapper
    public BackPage<NetflixTitles> queryPage(Long pageNo, Long pageSize) {
        BackPage<NetflixTitles> NetflixTitlesBackPage = new BackPage<>();
        // 设置条件构造器
        QueryWrapper<NetflixTitles> wrapper = new QueryWrapper<>();
        // 构造分页信息，其中的Page<>(page, PAGE_RECORDS_NUM)的第一个参数是第几页，而第二个参数是每页的记录数
        Page<NetflixTitles> NetflixTitlesPage = new Page<>(pageNo, pageSize);
        // page(NetflixTitlesPage, wrapper)这里的第一个参数就是上面定义了的Page对象，第二个参数就是上面定义的条件构造器对象，通过调用这个方法就可以根据你的分页信息以及查询信息获取分页数据
        IPage<NetflixTitles> NetflixTitlesIPage = page(NetflixTitlesPage, wrapper);
        // 封装数据，其中getRecords()是获取记录数，getCurrent()获取当前页数，getPages()获取总页数，getTotal()获取记录总数，还要其他更多的方法，大家可以自行查看，在这里就不过多赘述了
        NetflixTitlesBackPage.setContentList(NetflixTitlesIPage.getRecords());
        NetflixTitlesBackPage.setCurrentPage(NetflixTitlesIPage.getCurrent());
        NetflixTitlesBackPage.setTotalPage(NetflixTitlesIPage.getPages());
        NetflixTitlesBackPage.setTotalNum(NetflixTitlesIPage.getTotal());
        return NetflixTitlesBackPage;
    }

    @Override
    public int countByType(String type) {
        return netflixTitlesMapper.selectCountByType(type);
    }

    @Override
    public List<DirectorCountDTO> countByDirector() {
        return netflixTitlesMapper.selectCountByDirector();
    }

    @Override
    public List<CountryCountVO> countByCountry() {
        return netflixTitlesMapper.countByCountry();
    }

}
