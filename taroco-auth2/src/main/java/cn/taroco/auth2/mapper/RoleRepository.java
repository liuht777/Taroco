package cn.taroco.auth2.mapper;

import cn.taroco.auth2.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * role
 *
 * @author liuht
 * @date 2018/7/23 12:40
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
