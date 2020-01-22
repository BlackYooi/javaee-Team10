package com.kindblack.team10.controller;


import com.kindblack.team10.POJO.Position;
import com.kindblack.team10.Utils.Msg;
import com.kindblack.team10.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author black
 * @date 2019/12/16 - 17:34
 */

@RestController
@RequestMapping("/position")
public class PositonController {
    @Autowired
    PositionService positionService;


    /*增加职位*/
    @PostMapping
    public Msg addPosition(@RequestBody Position position) {
        String uuid = positionService.addPosition(position);
        if (0 < uuid.length()) {
            return Msg.success().add("uuid", uuid);
        }
        return Msg.fail().add("msg", "0");
    }

    /*删除职位*/
    @DeleteMapping
    public Msg deletePosition(@RequestParam String id) {
        if (positionService.deletePosition(id)) {
            return Msg.success().add("msg", "1");
        }
        return Msg.fail().add("msg", "0");
    }

    /*修改职位*/
    @PutMapping
    public Msg updatePosition(@RequestBody Position position) {
        if (positionService.updatePosition(position)) {
            return Msg.success().add("msg", "1");
        }
        return Msg.fail().add("msg", "0");
    }

    /*查询所有职位*/
    @GetMapping
    public Msg findAll() {
        return Msg.success().add("posits", positionService.findAll());
    }
}
