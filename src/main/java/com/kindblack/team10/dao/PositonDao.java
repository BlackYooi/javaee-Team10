package com.kindblack.team10.dao;


import com.kindblack.team10.POJO.Position;
import com.kindblack.team10.dao.DaoInter.PositionDaoInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author black
 * @date 2019/12/16 - 16:29
 */
@Component
public class PositonDao {
    @Autowired
    PositionDaoInter posionDaoInter;

    /*增加某个职位*/
    public String addPosition(Position position) {
        position.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        if (posionDaoInter.save(position) != null) {
            return position.getId();
        }
        return position.getId();
    }

    /*删除某个址位*/
    public boolean deletePosition(String id) {
        posionDaoInter.deleteById(id);
        return true;
    }

    /*修改某个职位*/
    public boolean updatePosition(Position position) {
        posionDaoInter.save(position);
        return true;
    }

    /*查找某个职位*/
    public Optional<Position> getById(String id) {
        Optional<Position> optional = posionDaoInter.findById(id);
        return optional;
    }

    public List<Position> findAll() {
        List<Position> list = posionDaoInter.findAll();
        return list;
    }
}
