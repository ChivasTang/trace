package com.hitachi.trace.service;

import com.hitachi.trace.domain.CategoryDomain;

import java.util.List;

public interface CategoryService {
    List<CategoryDomain> findAll();
}
