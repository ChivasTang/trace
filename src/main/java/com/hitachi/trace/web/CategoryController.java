package com.hitachi.trace.web;

import com.hitachi.trace.domain.CategoryDomain;
import com.hitachi.trace.service.CategoryService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping(value="/getCategoryList")
    @ResponseBody
    public List<CategoryDomain> getCategoryList(HttpServletRequest req, HttpServletResponse res, Model model){
        return categoryService.findAll();
    }
}
