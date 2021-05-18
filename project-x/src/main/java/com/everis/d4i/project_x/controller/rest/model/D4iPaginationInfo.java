package com.everis.d4i.project_x.controller.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class D4iPaginationInfo implements Serializable {

    private static final long serialVersionUID = 6650681740249017918L;

    private int pageNumber;
    private int pageSize;
    private int totalPages;
}
