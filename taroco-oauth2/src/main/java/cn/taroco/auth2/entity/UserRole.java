package cn.taroco.auth2.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * 用户角色关联
 *
 * @author liuht
 * @date 2018/7/23 11:48
 */

@Entity
@Table(name = "ta_user_role")
@DynamicInsert
@DynamicUpdate
public class UserRole implements Serializable {

    private int id;
    private int uid;
    private int rid;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "rid", nullable = false)
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}
