package cn.withstars.domain;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: withstars
 * Date: 2018-05-13
 * Time: 8:28
 * Mail: withstars@126.com
 */
public class RolePermission extends BaseDomain {

    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
}
