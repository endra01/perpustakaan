package com.sinaukoding.perpustakaan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sinaukoding.perpustakaan.common.RestResult;
import com.sinaukoding.perpustakaan.common.StatusCode;
import com.sinaukoding.perpustakaan.entity.Loan;
import com.sinaukoding.perpustakaan.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("loans")
public class LoanController extends BaseController {
    @Autowired
    private LoanService service;

    @PreAuthorize("permitAll()")
    @GetMapping
    public RestResult find(@RequestParam(value = "param", required = false) String param,
                           @RequestParam(value = "offset") int offset,
                           @RequestParam(value = "limit") int limit) throws JsonProcessingException {

        /*parsing json dati entity loan*/
        Loan loan = param != null ? new ObjectMapper().readValue(param, Loan.class) : new Loan();

        Long rows = service.count(loan);
        return new RestResult(rows > 0 ? service.find(loan, offset, limit) : new ArrayList<>(), rows);
    }

    @PreAuthorize("permitAll()")
    @PostMapping
    public RestResult save(@RequestBody Loan entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);

        if (entity != null){
            result.setData(service.save(entity));
            result.setStatus(StatusCode.SAVE_SUCCESS);
        }
        return result;
    }

    @PreAuthorize("permitAll()")
    @PutMapping
    public RestResult update(Loan entity){
        RestResult result = new RestResult(StatusCode.OPERATION_FAILED);
        if (entity != null){

        }
        return result;
    }

}