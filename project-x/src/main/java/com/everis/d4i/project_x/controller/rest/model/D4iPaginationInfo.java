package com.everis.d4i.project_x.controller.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class D4iPaginationInfo {
    private int pageNumber;
    private int pageSize;
    private int totalPages;
}
