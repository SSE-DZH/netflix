package com.zhiend.netflix.controller;


import com.zhiend.netflix.dto.DirectorCountDTO;
import com.zhiend.netflix.dto.NetflixTitlesAddDTO;
import com.zhiend.netflix.dto.NetflixTitlesUpdateDTO;
import com.zhiend.netflix.entity.BackPage;
import com.zhiend.netflix.entity.NetflixTitles;
import com.zhiend.netflix.result.Result;
import com.zhiend.netflix.service.INetflixTitlesService;
import com.zhiend.netflix.vo.AddDateCountVO;
import com.zhiend.netflix.vo.CountryCountVO;
import com.zhiend.netflix.vo.ReleaseYearCountVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
@CrossOrigin("*")
@Api(tags = "奈飞数据统计")
@Slf4j
public class NetflixTitlesController {
    @Autowired
    private INetflixTitlesService netflixTitlesService;

    /**
     * 通过POST请求添加Netflix标题。
     *
     * @param netflixTitlesDTO 添加请求的数据传输对象，包含要添加的Netflix标题的信息。
     * @param bindingResult 用于存储验证netflixTitlesDTO过程中产生的错误信息。
     * @return 如果添加成功，返回一个成功的Result对象；如果添加失败或验证失败，返回一个包含错误信息的Result对象。
     */
    @ApiOperation("添加Netflix影视")
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

    @ApiOperation("删除影视信息")
    @DeleteMapping("/delete/{showId}")
    public Result deleteNetflixTitle(@PathVariable Long showId) {
        boolean result = netflixTitlesService.removeById(showId);
        if (result) {
            return Result.success();
        } else {
            return Result.error("删除失败");
        }
    }

    @ApiOperation("更新Netflix影视")
    @PutMapping("/update")
    public Result updateNetflixTitle(@RequestBody @Valid NetflixTitles netflixTitles, BindingResult bindingResult) {
        // 校验参数
        if (bindingResult.hasErrors()) {
            return Result.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        // 执行更新操作
        boolean result = netflixTitlesService.updateById(netflixTitles);
        if (result) {
            return Result.success();
        } else {
            return Result.error("更新失败");
        }
    }



    @ApiOperation("根据ID查询Netflix影视")
    @GetMapping("/get/{showId}")
    public Result getNetflixTitle(@PathVariable Long showId) {
        NetflixTitles netflixTitles = netflixTitlesService.getById(showId);
        if (netflixTitles != null) {
            return Result.success(netflixTitles);
        } else {
            return Result.error("未找到对应记录");
        }
    }

    @ApiOperation("查询所有Netflix影视（这个不写了）")
    @GetMapping("/list")
    public Result listNetflixTitles() {
        List<NetflixTitles> netflixTitlesList = netflixTitlesService.list();
        return Result.success(netflixTitlesList);
    }

    /**
     * 获取所有分页影视信息
     * @return 所有分页影视信息
     */
    @ApiOperation("获取所有分页影视信息")
    @GetMapping("/getAllPages")
    public Result queryPage(@RequestParam("pageNo") Long pageNo, @RequestParam("pageSize") Long pageSize) {
        return Result.success(netflixTitlesService.queryPage(pageNo, pageSize));
    }

    //返回所有影视的类型
    @ApiOperation("获取所有影视类型")
    @GetMapping("/get-all-types")
    public Result getAllTypes() {
        List<String> types = netflixTitlesService.getAllTypes();
        return Result.success(types);
    }

    @ApiOperation("根据类型统计影视数量")
    @GetMapping("/count-by-type")
    public Result countByType(@RequestParam String type) {
        int count = netflixTitlesService.countByType(type);
        return Result.success(count);
    }

    //可以前端固定为10个导演显示
    @ApiOperation("根据导演统计影视数量")
    @GetMapping("/count-by-director")
    public Result countByDirector() {
        List<DirectorCountDTO> directorCounts = netflixTitlesService.countByDirector();
        return Result.success(directorCounts);
    }

    @ApiOperation("根据国家/地区统计影视数量")
    @GetMapping("/count-by-country")
    public Result<List<CountryCountVO>> countByCountry() {
        List<CountryCountVO> countryCounts = netflixTitlesService.countByCountry();
        return Result.success(countryCounts);
    }

    @ApiOperation("获取前10名添加日期统计数据")
    @GetMapping("/top-add-dates")
    public Result<List<AddDateCountVO>> getTopAddDates() {
        List<AddDateCountVO> topAddDates = netflixTitlesService.getTopAddDates();
        return Result.success(topAddDates);
    }


    @ApiOperation("获取前10名发行年份统计数据")
    @GetMapping("/top-release-years")
    public Result<List<ReleaseYearCountVO>> getTopReleaseYears() {
        List<ReleaseYearCountVO> topReleaseYears = netflixTitlesService.getTopReleaseYears();
        return Result.success(topReleaseYears);
    }
}
