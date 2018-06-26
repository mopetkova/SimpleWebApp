package com.websystique.springmvc.service;

import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.utils.Utils;

import java.util.List;

public interface SkillService {
    String u = new Utils("SkillService Interface", "class").toString();

    List<Skill> allSkills();
}
