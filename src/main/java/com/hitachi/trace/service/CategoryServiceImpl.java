package com.hitachi.trace.service;

import com.hitachi.trace.dao.CategoryDao;
import com.hitachi.trace.domain.CategoryDomain;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Resource
    private CategoryDao categoryDao;
    @Override
    public List<CategoryDomain> findAll() {
        return categoryDao.findAll();
    }
}
