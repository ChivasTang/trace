package com.hitachi.trace.service;

import com.hitachi.trace.domain.TraceInstrumentGraphResDomain;

import java.util.List;

public interface TraceInstrumentService {
    List<TraceInstrumentGraphResDomain> selectGraphList();
}
