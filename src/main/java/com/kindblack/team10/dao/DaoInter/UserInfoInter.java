package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Permission.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoInter extends JpaRepository<UserInfo, String> {
}
