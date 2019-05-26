package com.sgai.property.ctl.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.IdGen;
import com.sgai.property.ctl.dao.CtlBusinessDefineDao;
import com.sgai.property.ctl.entity.CtlBusinessDefine;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.modules.login.support.UserServletContext;

/**
 *
    * ClassName: CtlBusinessDefineService
    * Description: (子系统service层)
    * @author ASUS
    * Date 2017年11月18日
    * Company 首自信--智慧城市创新中心
 */
@Service
@Transactional
public class CtlBusinessDefineService extends CrudServiceExt<CtlBusinessDefineDao, CtlBusinessDefine>{
	public CtlBusinessDefine get(String id) {
		return super.get(id);
	}

	public List<CtlBusinessDefine> findList(CtlBusinessDefine ctlBusinessDefine) {
		return super.findList(ctlBusinessDefine);
	}

	public Page<CtlBusinessDefine> findPage(Page<CtlBusinessDefine> page, CtlBusinessDefine ctlBusinessDefine) {
		return super.findPage(page, ctlBusinessDefine);
	}
	@Transactional(readOnly = false)
	public void delete(CtlBusinessDefine ctlBusinessDefine) {
		super.delete(ctlBusinessDefine);
	}

	@Transactional(readOnly = false)
	public void save(CtlBusinessDefine ctlBusinessDefine) {
		if(ctlBusinessDefine.getId()==null||ctlBusinessDefine.getId().equals("")) {
			ctlBusinessDefine.setId(IdGen.uuid());
			LoginUser user = UserServletContext.getUserInfo();
			ctlBusinessDefine.setUpdatedBy(user.getUserId());
			ctlBusinessDefine.setCreatedBy(user.getUserId());
			ctlBusinessDefine.setUpdatedDt(new Date());
			ctlBusinessDefine.setCreatedDt(ctlBusinessDefine.getUpdatedDt());
			dao.insert(ctlBusinessDefine);
		}else {
			LoginUser user = UserServletContext.getUserInfo();
			ctlBusinessDefine.setUpdatedBy(user.getUserId());
			ctlBusinessDefine.setUpdatedDt(new Date());
			dao.update(ctlBusinessDefine);
		}

	}
	/**
	 *
	 * addSaveBD:(新增或是修改子系统).
	 * @param ctlBusinessDefine
	 * @param busiCode 子系统代码
	 * @param map 存放结果状态的集合
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public Map<String,String> saveBD(CtlBusinessDefine ctlBusinessDefine,Long orderOld,Map<String,String> map){
		// 通过busiCode判断系统代码是否重复
		CtlBusinessDefine ctlBusinessDefineNew = new CtlBusinessDefine();
		//通过判断id是否为空来决定添加还是修改
		if(ctlBusinessDefine.getId().equals("")) {
			ctlBusinessDefineNew.setBusiCode(ctlBusinessDefine.getBusiCode());
			List<CtlBusinessDefine> resultList = findList(ctlBusinessDefineNew);
			// 如果resultList的长度等于0说明该系统代码没有在用，可以添加，否则返回repeat，提示系统代码重复
			if (resultList.size() == 0) {
				long displayOrder = 0;
				CtlBusinessDefine ctlBusinessDefineNew2 = new CtlBusinessDefine();
				List<CtlBusinessDefine> resultList2 = findList(ctlBusinessDefineNew2);
				// 显示顺序递增
				if (resultList2.size() != 0) {
					displayOrder = resultList2.get(resultList2.size() - 1).getDisplayOrder() + 1;
				} else {
					displayOrder = 1;
				}
				ctlBusinessDefine.setDisplayOrder(displayOrder);
				save(ctlBusinessDefine);
				map.put("msg", "success");
			} else {
				map.put("msg", "repeat");
			}
		}else {
			//判断显示顺序是否有重复，如果有，交换位置，如果没有直接增加
			if(orderOld==ctlBusinessDefine.getDisplayOrder()) {
				 save(ctlBusinessDefine);
				 map.put("msg", "success");
			}else {
				ctlBusinessDefineNew.setDisplayOrder(ctlBusinessDefine.getDisplayOrder());
				List<CtlBusinessDefine> List = findList(ctlBusinessDefineNew);
				if(List.size()!=0) {
					  save(ctlBusinessDefine);
					  CtlBusinessDefine ctlBusinessDefine2 = List.get(0);
					  ctlBusinessDefine2.setDisplayOrder(orderOld);
					  ctlBusinessDefine2.setEnabledFlag('Y');
					  save(ctlBusinessDefine2);
					  map.put("msg", "success");
				}else {
					  save(ctlBusinessDefine);
					  map.put("msg", "success");
				}
			}

		}

		return map;
	}
	/**
	 *
	 * delete:(删除子系统).
	 * @param ids 子系统id集合拼成的字符串
	 * @return :Map<String,String>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional(readOnly = false)
	public void delete(String ids){
		//将得到的ids拆分成数组
		String[] ctlBusinessDefineIds = ids.split(",");
		//遍历数组逐个删除
		for(String id:ctlBusinessDefineIds){
			if(id!=null&&!id.equals("")){
				CtlBusinessDefine ctlBusinessDefine = get(id);
				delete(ctlBusinessDefine);
			}
		}
	}
	/**
	 *
	 * getList:(获取子系统列表，带分页).
	 * @param ctlBusinessDefine
	 * @param busiName
	 * @param page
	 * @return :Page<CtlBusinessDefine>
	 * @since JDK 1.8
	 * @author 王天尧
	 */
	@Transactional
	public Page<CtlBusinessDefine> getList(CtlBusinessDefine ctlBusinessDefine,Page<CtlBusinessDefine> page) {
		page.setOrderBy("DISPLAY_ORDER");
		//设置查询条件
		ctlBusinessDefine.setBusiName(ctlBusinessDefine.getBusiName());
		ctlBusinessDefine.setEnabledFlag('Y');
		Page<CtlBusinessDefine> pageList = findPage(page, ctlBusinessDefine);
		return pageList;
	}

	/**
	 * 机构指定子系统时 选择没有被指定过的子系统
	 * findBusiList:(这里用一句话描述这个方法的作用).
	 * @param ctlBusinessDefine
	 * @return :List<CtlBusinessDefine>
	 * @since JDK 1.8
	 * @author admin
	 */
	List<CtlBusinessDefine> findBusiList(CtlBusinessDefine ctlBusinessDefine){
		return dao.findBusiList(ctlBusinessDefine);
	}


}
