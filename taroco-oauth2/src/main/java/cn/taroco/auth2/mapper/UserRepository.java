package cn.taroco.auth2.mapper;

import cn.taroco.auth2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User
 *
 * @author liuht
 * @date 2018/7/23 12:40
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUsername(String name);
}
