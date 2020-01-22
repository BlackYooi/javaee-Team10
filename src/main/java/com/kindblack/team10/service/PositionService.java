package com.kindblack.team10.service;


import com.kindblack.team10.POJO.Position;
import com.kindblack.team10.dao.DaoInter.EmployeeDaoInter;
import com.kindblack.team10.dao.PositonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author black
 * @date 2019/12/16 - 17:29
 */
@Service
public class PositionService {
    @Autowired
    PositonDao positonDao;
    @Autowired
    EmployeeDaoInter employeeDaoInter;

    public String addPosition(Position position) {
        return positonDao.addPosition(position);
    }

    public boolean deletePosition(String id) {
        employeeDaoInter.deleteByPositionId(id);
        return positonDao.deletePosition(id);
    }

    public boolean updatePosition(Position position) {
        return positonDao.updatePosition(position);
    }

    public Optional<Position> getById(String id) {
        return positonDao.getById(id);
    }

    public List<Position> findAll() {
        return positonDao.findAll();
    }
}
