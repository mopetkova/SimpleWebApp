package com.websystique.springmvc.converters;

import com.websystique.springmvc.model.MaritalStatus;
import com.websystique.springmvc.service.MaritalStatusService;
import com.websystique.springmvc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MaritalStatusConverter implements Converter<Object, MaritalStatus> {

    @Autowired
    MaritalStatusService maritalStatusService;


    public MaritalStatusConverter() {
    }

    public MaritalStatus convert(Object o) {
        Integer id = Integer.parseInt((String) o);
        MaritalStatus ms = maritalStatusService.findById(id);
        String u = new Utils("MaritalStatusConverter", "convert "+ms).toString();
        return ms;
    }
}