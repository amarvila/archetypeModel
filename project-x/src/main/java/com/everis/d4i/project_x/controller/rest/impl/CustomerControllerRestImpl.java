
package com.everis.d4i.project_x.controller.rest.impl;

import com.everis.d4i.project_x.controller.rest.model.D4iPageRest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.everis.d4i.project_x.controller.rest.CustomerControllerRest;
import com.everis.d4i.project_x.controller.rest.model.SalesResponse;
import com.everis.d4i.project_x.controller.rest.model.CustomerRest;
import com.everis.d4i.project_x.exception.SalesException;
import com.everis.d4i.project_x.service.CustomerService;
import com.everis.d4i.project_x.util.constant.CommonConstantsUtils;
import com.everis.d4i.project_x.util.constant.RestConstantsUtils;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@Tag(name = "Customer", description = "Customer rest")
public class CustomerControllerRestImpl implements CustomerControllerRest {

    @Autowired
    private CustomerService customerService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = RestConstantsUtils.RESOURCE_CUSTOMER, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "getAllCustomers", description = "Get all Customers paginated")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SalesResponse<D4iPageRest<CustomerRest>> getAllCustomers(
      @RequestParam(defaultValue = CommonConstantsUtils.ZERO) final int page,
	    @RequestParam(defaultValue = CommonConstantsUtils.TWENTY) final int size, 
      @Parameter(hidden = true) final Pageable pageable,
	    @Parameter(hidden = true) final PagedResourcesAssembler<CustomerRest> assembler) throws SalesException {
	return new SalesResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
		CommonConstantsUtils.OK,customerService.getAllCustomers(pageable, assembler));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "getCustomerById", description = "Get one Customer by given id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @GetMapping(value = RestConstantsUtils.RESOURCE_CUSTOMER + RestConstantsUtils.RESOURCE_CUSTOMERID)
    public SalesResponse<CustomerRest> getCustomerById(@PathVariable(value = RestConstantsUtils.CUSTOMERID) final Long id)
	    throws SalesException {
	return new SalesResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
		CommonConstantsUtils.OK,customerService.getCustomerById(id));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = RestConstantsUtils.RESOURCE_CUSTOMER)
    @Operation(summary = "CustomerClient", description = "Create a new Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public SalesResponse<CustomerRest> createCustomer(@RequestBody final CustomerRest customer) throws SalesException {
	return new SalesResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
		CommonConstantsUtils.OK,customerService.createCustomer(customer));
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "updateCustomerStatus", description = "Update Customer status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content)
    })
    @PatchMapping(value = RestConstantsUtils.RESOURCE_CUSTOMER)
    public SalesResponse<CustomerRest> updateCustomer(@RequestBody final CustomerRest customerRest) throws SalesException {
	return new SalesResponse<>(HttpStatus.OK.toString(), String.valueOf(HttpStatus.OK.value()),
		CommonConstantsUtils.OK,customerService.updateCustomer(customerRest));
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "deleteCustomer", description = "Delete Customer by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @DeleteMapping(value = RestConstantsUtils.RESOURCE_CUSTOMER + RestConstantsUtils.RESOURCE_CUSTOMERID)
    public void deleteCustomer(@PathVariable(value = RestConstantsUtils.CUSTOMERID) final Long id)
	    throws SalesException {
	      customerService.deleteCustomer(id);
    }
}
