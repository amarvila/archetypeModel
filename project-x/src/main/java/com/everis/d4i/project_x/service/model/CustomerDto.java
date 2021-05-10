package com.everis.d4i.project_x.service.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto implements Serializable {

    private static final long serialVersionUID = 3098784982085101512L;

    private Long id;

    private String code;

    private String description;

}
