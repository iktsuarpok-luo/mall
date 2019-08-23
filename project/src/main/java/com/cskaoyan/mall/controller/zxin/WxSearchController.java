package com.cskaoyan.mall.controller.zxin;

import com.cskaoyan.mall.bean.BaseRespModel;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.service.lxt.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("wx/search")
public class WxSearchController {
    @Autowired
    KeywordService keywordService;

    @RequestMapping("helper")
    public BaseRespModel SearchHelper(String keyword){
        BaseRespModel resp = new BaseRespModel();

        // 有问题啊，先留着
        List<Keyword> keywords = keywordService.selectHelper(keyword);
        ArrayList list = new ArrayList();
        for (Keyword keyword1 : keywords) {
            String keyword2 = keyword1.getKeyword();
            list.add(keyword2);
        }

        resp.setData(list);
        resp.setErrmsg("成功");
        resp.setErrno(0);

        return resp;
    }

    @RequestMapping("index")
    public BaseRespModel Search(){
        BaseRespModel resp = new BaseRespModel();

        try {
            HashMap map = new HashMap();
            map.put("defaultKeyword", keywordService.defaultKeyword());
            map.put("hotKeywordList", keywordService.hotKeywordList());
            map.put("historyKeywordList",new ArrayList<>());

            resp.setData(map);
            resp.setErrmsg("成功");
            resp.setErrno(0);
        } catch (Exception e) {
            resp.setErrno(-1);
            resp.setErrmsg("失败");
        }
        return resp;
    }


}
