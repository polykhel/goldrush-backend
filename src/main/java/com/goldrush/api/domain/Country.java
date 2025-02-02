package com.goldrush.api.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "country")
@Data
@EqualsAndHashCode(callSuper = true)
public class Country extends BaseEntity{

    private String code;

    private String name;

}
