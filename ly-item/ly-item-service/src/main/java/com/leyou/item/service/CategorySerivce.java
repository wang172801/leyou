package com.leyou.item.service;

import com.leyou.item.mapper.CategoryMapper;
import com.leyou.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategorySerivce {
    @Autowired
    private CategoryMapper categoryMapper;


    public List<Category> queryByParentId(Long id) {
        Category category = new Category();
        category.setParentId(id);
        return categoryMapper.select(category);
        //select * from tb_category where parent_id=0
    }

    //编辑
    public List<Category> queryByBrandId(Long id){

        return categoryMapper.queryByBrandId(id);
    }

    public List<String> queryNameByIds(List<Long> asList) {
        //
        List<String> strings=new ArrayList<String>();
        List<Category> categoryList=this.categoryMapper.selectByIdList(asList);
        categoryList.forEach(t->{
            strings.add(t.getName());
        });
        return strings;
    }
}
