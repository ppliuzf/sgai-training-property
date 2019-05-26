/**
 * Project Name:smart-common
 * File Name:DataAuthMap.java
 * Package Name:com.sgai.modules.login.annotation
 * Date:2018年1月25日下午2:43:06
 * Copyright (c) 2018, 首自信--智慧城市创新中心.
 *
*/

package com.sgai.property.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
* ClassName: DataAuthMap
* @author admin
* Date 2018年1月25日
* Company 首自信--智慧城市创新中心
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPermission {

	/**
	 * tableAlias:(默认是空  ).
	 * @return :String
	 * @since JDK 1.8
	 * @author admin
	 */
	String tableAlias() default  "";
}

