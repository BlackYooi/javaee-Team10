package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Position;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author black
 * @date 2019/12/16 - 16:29
 */
public interface PositionDaoInter extends JpaRepository<Position, String> {
}
