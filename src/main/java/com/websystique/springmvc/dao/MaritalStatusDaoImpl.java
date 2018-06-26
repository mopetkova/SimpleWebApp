package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.MaritalStatus;
import com.websystique.springmvc.utils.Utils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;



@Repository("MaritalStatusDao")
public class MaritalStatusDaoImpl extends AbstractDao<Integer, MaritalStatus> implements MaritalStatusDao {


    public List<MaritalStatus> allMaritalStatus(){
        String u = new Utils("MaritalStatusDaoImpl", "allMaritalStatus").toString();
        Criteria criteria = createEntityCriteria();
        List<MaritalStatus> maritalStatusList = (List<MaritalStatus>) criteria.list();
        return  maritalStatusList;
    }



}