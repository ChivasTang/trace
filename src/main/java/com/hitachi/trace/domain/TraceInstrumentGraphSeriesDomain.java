package com.hitachi.trace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphSeriesDomain {
    @JsonIgnore
    private long inst_disp_id;
    @JsonIgnore
    private int application_code;
    private String name;
    private List<TraceInstrumentGraphSeriesMarkersDomain> markers;
    private String type = "line";
    private String categoryField = "date";
    private List<TraceInstrumentGraphSeriesDataDomain> data;
}
