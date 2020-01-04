package com.leyou.item.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.po.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.mapper.SupDetailMapper;
import com.leyou.item.mapper.SupMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Spu;
import com.netflix.discovery.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpuServices {
    @Autowired
    private SupDetailMapper supDetailMapper;
    @Autowired
    private SupMapper supMapper;
    @Autowired
    private CategorySerivce categorySerivce;
    @Autowired
    private BrandMapper brandMapper;

    public PageResult<SpuBo> queryBo(String key, String saleble, Integer page, Integer rows) {
        //开启分页
        PageHelper.startPage(page,rows);
        //查询对象
        Example exam=new Example(Spu.class);
        //获取cirteria
        Example.Criteria criteria=exam.createCriteria();
        if (StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"5");
        }
        if (null!=saleble){
            criteria.andEqualTo("saleble",saleble);
        }
        //查询结果
         Page<Spu> supage=(Page<Spu>)this.supMapper.selectByExample(exam);
        //
        List<Spu> result=supage.getResult();
        List<SpuBo> spuBos=new ArrayList<SpuBo>();
        for(Spu spu: result){

            SpuBo spuBo=new SpuBo();

            BeanUtils.copyProperties(spu,spuBo);
            //通过分类名称获取分类id
            List<String> names=this.categorySerivce.queryNameByIds(Arrays.asList(spu.getCid1(),spu.getCid2(),spu.getCid3()));
            String join= StringUtils.join(names, "/");
            //分类名称
            spuBo.setCname(join);
            //根据品牌id查品牌
            Brand brand=brandMapper.selectByPrimaryKey(spu.getBrandId());
            //()
            spuBo.setBname(brand.getName());
            //

            spuBos.add(spuBo);
        }

        return new PageResult<>(supage.getTotal(),new Long(supage.getPages()),spuBos);
    }
}
