package com.moelyon.study.cache.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author moelyon
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coffee implements Serializable {

    private Long id;
    private String name;
    private double price;

}
