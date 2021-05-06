package com.everis.d4i.project_x.controller.rest.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerRest implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("code")
    private String code;
    
    @JsonProperty("description")
    private String description;


}
