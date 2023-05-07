package com.tencent.wxcloudrun.modules.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tencent.wxcloudrun.common.Res;
import com.tencent.wxcloudrun.modules.dto.QueryDTO;
import com.tencent.wxcloudrun.modules.entity.Feedback;
import com.tencent.wxcloudrun.modules.service.FeedbackService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (feedback)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("feedback")
public class FeedbackController {
    /**
     * 服务对象
     */
    @Resource
    private FeedbackService feedbackService;

    /**
     * 查询列表数据
     */
    @GetMapping("list")
    public Res<IPage<Feedback>> selectList(QueryDTO dto) {
        return Res.success(feedbackService.selectPage(dto));
    }

    /**
     * 新增或修改操作
     */
    @PostMapping("saveOrUpdate")
    public Res<String> saveOrUpdate(@RequestBody Feedback entity) {

        boolean result = feedbackService.saveOrUpdate(entity);
        if (result) {
            return Res.successMsg("新增或修改成功");
        }
        return Res.errorMsg("新增或修改失败");
    }

/*    *//**
     * 删除操作
     *//*
    @DeleteMapping("delete")
    public Res<String> delete(String ids) {
        if (StrUtil.isBlank(ids)) {
            return Res.errorMsg("id不能为空！");
        }
        List<Feedback> list = new ArrayList<>();
        Arrays.stream(ids.split(",")).collect(Collectors.toList()).forEach(e ->
                list.add(Feedback.builder().id(Integer.valueOf(e.trim())).delFlag(true).build())
        );
        boolean result = feedbackService.updateBatchById(list);
        if (result) {
            return Res.successMsg("删除成功！");
        }
        return Res.errorMsg("删除失败！");
    }*/
}
