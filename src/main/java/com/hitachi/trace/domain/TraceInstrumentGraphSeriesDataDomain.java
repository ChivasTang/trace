package com.hitachi.trace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphSeriesDataDomain {
    private long inst_disp_id;
    private int application_code;
    private String date;
    private int value;
}
