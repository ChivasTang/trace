package com.hitachi.trace.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="m_category")
@Getter
@Setter
@ToString(callSuper = true)
public class CategoryDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column(name = "RESC_KEY")
    private String resc_key;

    @Column(name = "CATEGORY_LEVEL")
    private Integer category_level;

    @Column(name = "ROOT_CATEGORY_ID")
    private Long root_category_id;
}
