package com.example.studentBackend.common.mybatis;

import com.example.studentBackend.common.util.StringEscapeEditor;
import com.example.studentBackend.common.vo.ObjectRestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public abstract class BaseController<Biz extends BaseBiz, Entity> {

    @Autowired
    protected HttpServletRequest request;

    @Autowired
    protected Biz baseBiz;

    public BaseController() {
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringEscapeEditor());
        binder.registerCustomEditor(String[].class, new StringEscapeEditor());
    }

    @ResponseBody
    @RequestMapping(value = {""}, method = {RequestMethod.POST})
    public ObjectRestResponse<Entity> add(@RequestBody Entity entity) {
        this.baseBiz.insertSelective(entity);
        return (new ObjectRestResponse()).data(entity);
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.GET})
    @ResponseBody
    public ObjectRestResponse<Entity> get(@PathVariable Object id) {
        ObjectRestResponse<Entity> entityObjectRestResponse = new ObjectRestResponse();
        Object o = this.baseBiz.selectById(id);
        entityObjectRestResponse.data((Entity) o);
        return entityObjectRestResponse;
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.PUT})
    @ResponseBody
    public ObjectRestResponse<Entity> update(@RequestBody Entity entity) {
        this.baseBiz.updateSelectiveById(entity);
        return (new ObjectRestResponse()).data(entity);
    }

    @RequestMapping(value = {"/{id}"}, method = {RequestMethod.DELETE})
    @ResponseBody
    public ObjectRestResponse<Entity> remove(@PathVariable Object id) {
        this.baseBiz.deleteById(id);
        return new ObjectRestResponse();
    }

    @RequestMapping(value = {"/all"}, method = {RequestMethod.GET})
    @ResponseBody
    public List<Entity> all() {
        return this.baseBiz.selectListAll();
    }

}
