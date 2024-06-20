package com.zhiend.netflix.controller;


import com.zhiend.netflix.dto.DirectorCountDTO;
import com.zhiend.netflix.dto.NetflixTitlesAddDTO;
import com.zhiend.netflix.dto.NetflixTitlesUpdateDTO;
import com.zhiend.netflix.entity.BackPage;
import com.zhiend.netflix.entity.NetflixTitles;
import com.zhiend.netflix.result.Result;
import com.zhiend.netflix.service.INetflixTitlesService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Zhiend
 * @since 2024-06-20
 */
@RestController
@RequestMapping("/netflix-titles")
public class NetflixTitlesController {
    @Autowired
    private INetflixTitlesService netflixTitlesService;

    @PostMapping("/add")
    public Result addNetflixTitle(@RequestBody @Valid NetflixTitlesAddDTO netflixTitlesDTO, BindingResult bindingResult) {
        // 校验参数
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        // 将DTO转换为实体对象
        NetflixTitles netflixTitles = new NetflixTitles();
        BeanUtils.copyProperties(netflixTitlesDTO, netflixTitles);

        // 保存到数据库
        boolean result = netflixTitlesService.save(netflixTitles);
        if (result) {
            return Result.success();
        } else {
            return Result.error("添加失败");
        }
    }


    @DeleteMapping("/delete/{showId}")
    public Result deleteNetflixTitle(@PathVariable Long showId) {
        boolean result = netflixTitlesService.removeById(showId);
        if (result) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }

    @PutMapping("/update")
    public Result updateNetflixTitle(@RequestBody @Valid NetflixTitlesUpdateDTO netflixTitlesDTO, BindingResult bindingResult) {
        // 校验参数
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        // 将DTO转换为实体对象
        NetflixTitles netflixTitles = new NetflixTitles();
        BeanUtils.copyProperties(netflixTitlesDTO, netflixTitles);

        // 执行更新操作
        boolean result = netflixTitlesService.updateById(netflixTitles);
        if (result) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }


    @GetMapping("/get/{showId}")
    public Result getNetflixTitle(@PathVariable Long showId) {
        NetflixTitles netflixTitles = netflixTitlesService.getById(showId);
        if (netflixTitles != null) {
            return Result.success(netflixTitles);
        } else {
            return Result.error("未找到对应记录");
        }
    }

    @GetMapping("/list")
    public Result listNetflixTitles() {
        List<NetflixTitles> netflixTitlesList = netflixTitlesService.list();
        return Result.success(netflixTitlesList);
    }

    /**
     * 获取所有用户信息
     * @return 所有用户信息
     */
    @GetMapping("/getAllPages")
    public BackPage<NetflixTitles> queryPage(@RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        return netflixTitlesService.queryPage(pageNo, pageSize);
    }


    @GetMapping("/count-by-type")
    public Result countByType(@RequestParam String type) {
        int count = netflixTitlesService.countByType(type);
        return Result.success(count);
    }

    //可以前端固定为10个导演显示
    @GetMapping("/count-by-director")
    public Result countByDirector() {
        List<DirectorCountDTO> directorCounts = netflixTitlesService.countByDirector();
        return Result.success(directorCounts);
    }


}
