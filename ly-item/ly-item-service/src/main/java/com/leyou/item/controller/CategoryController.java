package com.leyou.item.controller;

import com.leyou.item.pojo.Category;
import com.leyou.item.service.CategorySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategorySerivce categorySerivce;

    @GetMapping("list")
    public ResponseEntity<List<Category>> queryByParentId(@RequestParam("pid") Long id){
        List<Category> categoryList=categorySerivce.queryByParentId(id);

        if(null!=categoryList&&categoryList.size()>0){
            return  ResponseEntity.ok(categoryList);
            //categoryList
            //200
        }
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();


    }
    //编辑
    @GetMapping("bid/{bid}")
    public ResponseEntity<List<Category>> queryByBrandId(@PathVariable("bid") Long id) {
        List<Category> categoryList = categorySerivce.queryByBrandId(id);
        if (categoryList!= null && categoryList.size()>0) {
            return  ResponseEntity.ok(categoryList);
        } else {
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }


    }

}
