package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.MaritalStatusDao;
import com.websystique.springmvc.model.MaritalStatus;
import com.websystique.springmvc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("MaritalStatusService")
@Transactional
public class MaritalStatusServiceImpl implements MaritalStatusService {
    String u = new Utils("MaritalService Impl", "class").toString();

    @Autowired
    private MaritalStatusDao dao;

    public List<MaritalStatus> allMaritalStatus() {

        return dao.allMaritalStatus();
    }

}
