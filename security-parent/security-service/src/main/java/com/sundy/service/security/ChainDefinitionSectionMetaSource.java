package com.sundy.service.security;

import java.text.MessageFormat;
import java.util.List;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.sundy.core.Menu;
import com.sundy.core.Role;
import com.sundy.service.inter.IMenuService;
import com.sundy.service.inter.IRoleService;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {
	@Autowired
	private IMenuService menuService;
	@Autowired
	private IRoleService roleService;
	 //注入默认的授权定义
    private String filterChainDefinitions;
  //格式化数据，符合shiro的格式，如：perms["admin"]
    public static final String PREMISSION_FORMAT = "perms[\"{0}\"]";

	public Section getObject() throws Exception {
		Ini ini = new Ini();
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //由注入的资源管理对象获取所有资源数据，并且资源的authorities的属性是EAGER的fetch类型
        for(Menu permission : menuService.findAllMenu()) {
            if(!StringUtils.hasText(permission.getUrl())) {
                continue;
            }
            
            List<Role> roleList=this.roleService.findRoleByMenuId(permission.getId());
           
            for(Role role : roleList){
            	 //如果资源路径的值为逗号分隔，则循环构造元数据。
                putDefinitionSection(section, permission.getUrl(),role.getRolename());
            }
           
        }
        return section;
	}
	
	private void putDefinitionSection(Section section, String key, String value) {
        System.out.println("加载数据库权限：【key=" + key + "\tvalue=" + value + "】");
        section.put(key, MessageFormat.format(PREMISSION_FORMAT, value));
    }

    public void setFilterChainDefinitions(String filterChainDefinitions) {
        this.filterChainDefinitions = filterChainDefinitions;
    }


	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return this.getClass();
	}

	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}

}
