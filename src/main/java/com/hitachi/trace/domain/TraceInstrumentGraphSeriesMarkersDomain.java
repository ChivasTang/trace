package com.hitachi.trace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphSeriesMarkersDomain {
    private String type;
}
