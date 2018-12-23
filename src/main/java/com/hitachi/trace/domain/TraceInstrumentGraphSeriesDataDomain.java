package com.hitachi.trace.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphSeriesDataDomain {
    @JsonIgnore
    private long inst_disp_id;
    @JsonIgnore
    private int application_code;
    private String date;
    private int value;
}
