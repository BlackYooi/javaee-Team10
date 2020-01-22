package com.kindblack.team10.dao.DaoInter;

import com.kindblack.team10.POJO.Permission.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionInter extends JpaRepository<Permission, String> {
}