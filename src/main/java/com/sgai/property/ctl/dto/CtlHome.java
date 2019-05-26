package com.sgai.property.ctl.dto;

import com.sgai.common.persistence.Page;
import com.sgai.modules.login.jwt.bean.LoginUser;

/**  
* @ClassName: CtlHome  
* @Description: home页面 事件展示
* @author admin  
* @date 2018年1月11日  
* @Company 首自信--智慧城市创新中心  
*/
public class CtlHome<T> {

	
	private  Page<T> page;
	private  LoginUser  user;
	
	public Page<T> getPage() {
        if (page == null) {
            page = new Page<T>();
        }
        return page;
	 }
	
    public Page<T> setPage(Page<T> page) {
        this.page = page;
        return page;
    }

    
	public LoginUser getUser() {
		return user;
	}

	public void setUser(LoginUser user) {
		this.user = user;
	}
    
    
    
	

	
	
	
}
