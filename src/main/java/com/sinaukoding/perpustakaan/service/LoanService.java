package com.sinaukoding.perpustakaan.service;

import com.sinaukoding.perpustakaan.dao.BaseDAO;
import com.sinaukoding.perpustakaan.dao.LoadDAO;
import com.sinaukoding.perpustakaan.entity.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoanService extends BaseService<Loan> {

    @Autowired
    private LoadDAO dao;

    @Override
    protected BaseDAO<Loan> getDAO() {
        return dao;
    }
}
