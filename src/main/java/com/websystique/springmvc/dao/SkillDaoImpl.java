package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Skill;
import com.websystique.springmvc.utils.Utils;
import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.websystique.springmvc.model.Employee;


@Repository("SkillDao")
public class SkillDaoImpl extends AbstractDao<Integer, Skill> implements SkillDao {


    public List<Skill> allSkills(){
        String u = new Utils("SkillsDaoImpl", "allSkills").toString();
        Criteria criteria = createEntityCriteria();
        List<Skill> skills = (List<Skill>) criteria.list();
        return  skills;
    }



}