package com.sundy.core;

public class RoleResource {
    private Integer id;

    private Integer roleid;

    private Integer menuid;
    
    private Role roleBean;
    private Menu menuBean;
    
    
    

    public Role getRoleBean() {
		return roleBean;
	}

	public void setRoleBean(Role roleBean) {
		this.roleBean = roleBean;
	}

	public Menu getMenuBean() {
		return menuBean;
	}

	public void setMenuBean(Menu menuBean) {
		this.menuBean = menuBean;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }
}