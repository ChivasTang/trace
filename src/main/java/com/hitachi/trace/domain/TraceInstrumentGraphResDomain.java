package com.hitachi.trace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphResDomain {

    private long inst_disp_id;

    private String inst_disp_name;

    private List<TraceInstrumentGraphSeriesDomain> inst_disp_series;

}
