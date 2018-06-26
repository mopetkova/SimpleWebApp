package com.websystique.springmvc.service;

import com.websystique.springmvc.model.MaritalStatus;
import com.websystique.springmvc.utils.Utils;

import java.util.List;

public interface MaritalStatusService {
    String u = new Utils("MaritalService Interface", "class").toString();

    List<MaritalStatus> allMaritalStatus();
}
