package com.hitachi.trace.dao;

import com.hitachi.trace.domain.CategoryDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<CategoryDomain,Long> {
}
