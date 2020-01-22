package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Recruit;
import com.kindblack.team10.POJO.Resume;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author black
 * @date 2019/12/30 - 0:04
 */
public interface ResumeInter extends JpaRepository<Resume, String> {
    @EntityGraph(value = "resume.all", type = EntityGraph.EntityGraphType.FETCH)
    List<Resume> findByDeliverId(String id);
}
