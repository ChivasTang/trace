package com.hitachi.trace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name="lst_instrument")
@Getter
@Setter
@ToString(callSuper = true)
public class TraceInstrumentGraphDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "INST_ID")
    private int inst_id;

    @Column(name = "INST_NAME")
    private String inst_name;

    @Column(name = "SUBMODULE_NO")
    private int submodule_no;

    @Column(name = "INST_DISP_ID")
    private long inst_disp_id;

    @Column(name = "INST_DISP_NAME")
    private String inst_disp_name;

    @Column(name = "APPLICATION_CODE")
    private int application_code;

    @Column(name = "APPLICATION_NAME")
    private String application_name;

    @Column(name = "CREATE_DATE")
    private String create_date;

    @Column(name = "SUM_COUNT")
    private int sum_count;
}
