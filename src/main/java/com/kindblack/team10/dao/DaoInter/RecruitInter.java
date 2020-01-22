package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Recruit;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;

/**
 * @author black
 * @date 2019/12/30 - 0:04
 */
public interface RecruitInter extends JpaRepository<Recruit, String> {
    @EntityGraph(value = "recruit.all", type = EntityGraph.EntityGraphType.FETCH)
    List<Recruit> findByPublisherId(String publisherId);

    @Override
    @EntityGraph(value = "recruit.all", type = EntityGraph.EntityGraphType.FETCH)
    List<Recruit> findAll();
}
