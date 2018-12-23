package com.hitachi.trace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphSeriesDomain {

    private long inst_disp_id;
    private int application_code;
    private String name;
    private List<TraceInstrumentGraphSeriesMarkersDomain> markers;
    private String type = "line";
    private String categoryField = "date";
    private List<TraceInstrumentGraphSeriesDataDomain> data;
}
