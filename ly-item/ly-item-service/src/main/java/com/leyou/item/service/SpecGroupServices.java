package com.leyou.item.service;


import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecGroupServices {
    //规格组
    @Autowired
   private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> queryspec(Long id) {
        SpecGroup spe=new SpecGroup();
        spe.setCid(id);
        List<SpecGroup> listgroup=specGroupMapper.select(spe);
        listgroup.forEach(t->{
            Long id1=t.getId();
            SpecParam specparam=new SpecParam();
            specparam.setGroupId(id1);
            //通过规格组的id查询规格参数
            List<SpecParam> specParams=this.specParamMapper.select(specparam);
            t.setSpecParams(specParams);
        });
       return listgroup;
    }

//    public List<SpecParam> queryspecParam(Long gid) {
//        SpecParam specPa=new SpecParam();
//        specPa.setGroupId(gid);
//        return this.specParamMapper.select(specPa);
//    }

    public List<SpecParam> queryspecParam(Long gid, Long cid, Boolean searching, Boolean generic) {
        SpecParam specPa=new SpecParam();
        specPa.setGroupId(gid);
        specPa.setCid(cid);
        specPa.setGeneric(generic);
        specPa.setSearching(searching);
        return this.specParamMapper.select(specPa);
    }
}
