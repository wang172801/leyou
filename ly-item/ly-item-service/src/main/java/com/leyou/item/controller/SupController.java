package com.leyou.item.controller;

import com.leyou.common.po.PageResult;
import com.leyou.item.bo.SpuBo;
import com.leyou.item.service.SpuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SupController {
    @Autowired
    private SpuServices spuServices;
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> queryBo(@RequestParam(value = "key",required = false) String key,
                                                     @RequestParam(value = "saleble",required = false) String saleble,
                                                     @RequestParam(value = "page",required = false) Integer page,
                                                     @RequestParam(value = "rows",required = false) Integer rows){

        PageResult<SpuBo> pageRes=spuServices.queryBo(key,saleble,page,rows);
        if (null!=pageRes && pageRes.getItems().size()>0){
            return ResponseEntity.ok(pageRes);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
