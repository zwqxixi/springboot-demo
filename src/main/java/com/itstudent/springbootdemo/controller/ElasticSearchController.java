/*
package com.itstudent.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.itstudent.springbootdemo.mapper.CloudDiskRepository;
import com.itstudent.springbootdemo.model.CloudDisk;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

*/
/**
 * @Project: SpringBootDemo
 * @Auther: wenqin.zhao
 * @CreateDate: 2019/11/5 11:54
 * @Description:
 *//*


@RestController
@RequestMapping
public class ElasticSearchController {


    private static Logger logger=Logger.getLogger(ElasticSearchController.class);

    @Autowired
    private CloudDiskRepository cloudDiskRepository;

    @GetMapping("/query/{id}")
    public Optional<CloudDisk> getById(@PathVariable("id")String id){
        logger.info("es查询入参"+id);
        Optional<CloudDisk> cloudDisk = cloudDiskRepository.findById(id);
        logger.info("查询结果:"+ JSONObject.toJSONString(cloudDisk));
        return cloudDisk;
    }

    @PostMapping("/save")
    public void save(@RequestBody CloudDisk cloudDisk){
        cloudDiskRepository.save(cloudDisk);
    }

    //浅分页查询
    @PostMapping("/findPage")
    public void fingPage( @PageableDefault(page = 0, value = 3) Pageable pageable,
                             String keyword){
        //bool和match联合使用
        //1.构建boolQueryBuilder查询器
        BoolQueryBuilder boolQueryBuilder= QueryBuilders.boolQuery();
        if(StringUtils.isNotEmpty(keyword)){
            //模糊查询，一定要IK中文
            //2.构建matchQueryBuilder
            MatchQueryBuilder matchQueryBuilder=QueryBuilders.matchQuery("name","keyword");
            boolQueryBuilder.must(matchQueryBuilder);
        }
        Page page=cloudDiskRepository.search(boolQueryBuilder,pageable);
        long total=page.getTotalElements();//计算查询总数
    }

}
*/
