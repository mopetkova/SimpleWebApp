package com.websystique.springmvc.converters;

import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.service.SkillService;
import com.websystique.springmvc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SkillConverter implements Converter<Object, Skill>
{

    @Autowired
    SkillService skillService;

    public SkillConverter() { }

    public Skill convert(Object o)
    {
        Integer id = Integer.parseInt((String) o);
        Skill skill = skillService.findById(id);
        String u = new Utils("SkillConverter", "convert "+skill).toString();
        return skill;
    }
}
