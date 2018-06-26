package com.websystique.springmvc.service;

import com.websystique.springmvc.dao.SkillDao;
import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("SkillService")
@Transactional
public class SkillServiceImpl implements SkillService {
    String u = new Utils("SkillService Impl", "class").toString();

    @Autowired
    private SkillDao dao;

    public List<Skill> allSkills() {
        return dao.allSkills();
    }

}
