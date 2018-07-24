package cn.taroco.auth2.mapper;

import cn.taroco.auth2.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * UserRole
 *
 * @author liuht
 * @date 2018/7/23 12:42
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    List<UserRole> findByuid(int uid);

    @Query(value = "select r.role_name from user_role ur left join role r on ur.rid=r.id where ur.uid = ?1", nativeQuery = true)
    List<String> findRoleName(int uid);
}
