package com.hitachi.trace.dao;

import com.hitachi.trace.domain.TraceInstrumentGraphDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceInstrumentDao extends JpaRepository<TraceInstrumentGraphDomain,Long> {

}
