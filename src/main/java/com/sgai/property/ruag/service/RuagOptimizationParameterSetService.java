package com.sgai.property.ruag.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sgai.common.persistence.Page;
import com.sgai.common.service.CrudServiceExt;
import com.sgai.common.utils.StringUtils;
import com.sgai.modules.login.jwt.bean.LoginUser;
import com.sgai.property.ruag.dao.RuagOptimizationParameterSetDao;
import com.sgai.property.ruag.entity.RuagModelDeviceParamerSet;
import com.sgai.property.ruag.entity.RuagOptimizationParameterSet;
import com.sgai.property.ruag.entity.RuagWorkModelDatail;

/**
 * 优化参数配置Service
 *
 * @author admin
 * @version 2018-08-17
 */
@Service
@Transactional
public class RuagOptimizationParameterSetService
		extends CrudServiceExt<RuagOptimizationParameterSetDao, RuagOptimizationParameterSet> {

	@Autowired
	private RuagWorkModelDatailService ruagWorkModelDatailService;
	@Autowired
	private RuagOptimizationParameterSetService ruagOptimizationParameterSetService;
	@Autowired
	private RuagModelDeviceParamerSetService ruagModelDeviceParamerSetService;

	public void updateParamter(String str1, String str2) {
		if (StringUtils.isNotBlank(str1)) {
			String str1s[] = str1.split(","); // ee6404228f2147558fb4c1f8b3923e36,8b5b02f64dcb475688e6a1d8be0b4817,
			String str2s[] = str2.split(","); // DEVICE-PROF-2018-00007*min,DEVICE-PROF-2018-00010*min
			List<Map<String, String>> strList = new ArrayList<>();
			List<String> workModelIdList = new ArrayList<>();
			// 将DEVICE-PROF-2018-00007*min,DEVICE-PROF-2018-00010*min 以list<map>存到strList
			for (int i = 0; i < str2s.length; i++) {
				Map<String, String> map = new HashMap<>();
				String[] profList = str2s[i].split("\\*");
				// 判断是目标优化勾选 或是 一键优化勾选
				if (profList.length > 1) {
					map.put(profList[0], profList[1]);
				} else {
					map.put(profList[0], "avg");
				}
				strList.add(map);
			}
			// 处理数据 获得workModelId 和 target
			for (String id : str1s) {
				List<RuagWorkModelDatail> list = ruagWorkModelDatailService.findDevicesById(id);
				// 判断prof_code 与 页面勾选 PROF_CODE
				for (int i = 0; i < list.size(); i++) {
					for (Map<String, String> map : strList) {
						for (Map.Entry<String, String> m : map.entrySet()) {
							System.out.println(m.getKey());
							System.out.println(list.get(i).getProfCode());

							if (m.getKey().equals(list.get(i).getProfCode())) {
								System.out.println(m.getValue());
								List<RuagModelDeviceParamerSet> workModelIds = ruagModelDeviceParamerSetService
										.findDevicesById(list.get(i).getId());
								workModelIdList.add(workModelIds.get(i).getWorkModelId() + "*" + m.getValue());
							}
						}
					}
				}

			}
			for (int k = 0; k < workModelIdList.size(); k++) {
				String workModelIdLists[] = workModelIdList.get(k).split("\\*");
				RuagOptimizationParameterSet ruagOptimizationParameterSet = new RuagOptimizationParameterSet();
				ruagOptimizationParameterSet.setWorkModelId(workModelIdLists[0]);
				ruagOptimizationParameterSet.setTargets(workModelIdLists[1]);
				ruagOptimizationParameterSetService.updateRuagWorkModelDetail(ruagOptimizationParameterSet);

			}

		}

	}

	public void saveParameter(LoginUser user) {

		// 查出status为1状态下的策略
		RuagOptimizationParameterSet ruagOptimizationParameterSet = new RuagOptimizationParameterSet();
		ruagOptimizationParameterSet.setComCode(user.getComCode());
		List<RuagOptimizationParameterSet> list = ruagOptimizationParameterSetService
				.findParameter(ruagOptimizationParameterSet);

		// 遍历list 将参数进行处理 insert到新参数表

		for (int i = 0; i < list.size(); i++) {
			RuagOptimizationParameterSet ruagOptPar = new RuagOptimizationParameterSet();
			RuagOptimizationParameterSet ruagOptPar1 = new RuagOptimizationParameterSet();
			RuagOptimizationParameterSet r;
			r = list.get(i);
			// min
			ruagOptPar.setWorkModelId(r.getWorkModelId());
			ruagOptPar.setDeviceCode(r.getDeviceCode());
			ruagOptPar.setParameterId(r.getParameterId());
			ruagOptPar.setEnabledFlag("Y");
			ruagOptPar.setVersion(1);
			ruagOptPar.setCreatedDt(r.getCreatedDt());
			ruagOptPar.setParameterName(r.getParameterName());
			ruagOptPar.setComCode(r.getComCode());
			ruagOptPar.setProfCode(r.getProfCode());
			ruagOptPar.setOldId(r.getId());
			ruagOptPar.setTimePoint(r.getTimePoint());
			ruagOptPar.setParameterValue(r.getParameterValue());
			ruagOptPar.setTargets("min");

			/*int time = Integer.valueOf(r.getTimePoint().substring(0, 2));*/
			/*int time=10;
			if(r.getTimePoint()!=null) {
				time=Integer.parseInt(r.getTimePoint().substring(0, r.getTimePoint().indexOf(":")));
			}*/
			int time= 0;
			// 日期模式 修改开启时间和参数
			if ("date".equals(r.getControlCode())) {
				 time=Integer.parseInt(r.getTimePoint().substring(0, r.getTimePoint().indexOf(":")));
				if ("SDWD".equals(r.getParameterId())) {
					ruagOptPar.setParameterValue("28");
				} else if ("FS".equals(r.getParameterId())) {
					ruagOptPar.setParameterValue("20");
				} else if ("1".equals(r.getParameterValue())) {

					if(time>10) {
						ruagOptPar.setTimePoint(time + ":30");
					}else {
						ruagOptPar.setTimePoint("0"+time + ":30");
					}


				} else if ("0".equals(r.getParameterValue())) {

					if(time>10) {
						ruagOptPar.setTimePoint(time - 1 + ":30");
					}else {
						ruagOptPar.setTimePoint("0"+(time-1) + ":30");
					}



				} else {
					ruagOptPar.setParameterValue(r.getParameterValue());// 需要判断
					ruagOptPar.setTimePoint(r.getTimePoint());
				}
			}

			else {
				if ("ModeControl".equals(r.getParameterId())) {
					ruagOptPar.setParameterValue("26");
				} else if ("FanSpeedControl".equals(r.getParameterId())) {
					ruagOptPar.setParameterValue("26");
				} else if ("SetTemp".equals(r.getParameterId())) {
					ruagOptPar.setParameterValue("26");
				} else {
					ruagOptPar.setParameterValue(r.getParameterValue());// 需要判断
					ruagOptPar.setTimePoint(r.getTimePoint());
				}
			}
			ruagOptimizationParameterSetService.save(ruagOptPar);

			// avg
			ruagOptPar1.setWorkModelId(r.getWorkModelId());
			ruagOptPar1.setDeviceCode(r.getDeviceCode());
			ruagOptPar1.setParameterId(r.getParameterId());
			ruagOptPar1.setEnabledFlag("Y");
			ruagOptPar1.setVersion(1);
			ruagOptPar1.setCreatedDt(r.getCreatedDt());
			ruagOptPar1.setParameterName(r.getParameterName());
			ruagOptPar1.setComCode(r.getComCode());
			ruagOptPar1.setProfCode(r.getProfCode());
			ruagOptPar1.setOldId(r.getId());
			ruagOptPar1.setTimePoint(r.getTimePoint());
			ruagOptPar1.setParameterValue(r.getParameterValue());
			ruagOptPar1.setTargets("avg");

			if ("date".equals(r.getControlCode())) {
				time=Integer.parseInt(r.getTimePoint().substring(0, r.getTimePoint().indexOf(":")));
				ruagOptPar1.setTimePoint(r.getTimePoint());
				if ("1".equals(r.getParameterValue())) {

					if(time>10) {
						ruagOptPar1.setTimePoint(time + ":00");
					}else {
						ruagOptPar1.setTimePoint("0"+time + ":00");
					}

				} else if ("0".equals(r.getParameterValue())) {

					if(time>10) {
						ruagOptPar1.setTimePoint(time - 1 + ":30");
					}else {
						ruagOptPar1.setTimePoint("0"+(time - 1) + ":30");
					}

				} else {
					ruagOptPar1.setTimePoint(r.getTimePoint());//
					ruagOptPar1.setParameterValue(r.getParameterValue());
				}
			} else {
				if ("SetTemp".equals(r.getParameterId())) {
					ruagOptPar1.setParameterValue("20");
				} else if ("ModeControl".equals(r.getParameterId())) {
					ruagOptPar1.setParameterValue("20");
				} else if ("FanSpeedControl".equals(r.getParameterId())) {
					ruagOptPar1.setParameterValue("20");
				}else {
					ruagOptPar1.setTimePoint(r.getTimePoint());
					ruagOptPar1.setParameterValue(r.getParameterValue());// 需要判断

				}
			}
			ruagOptimizationParameterSetService.save(ruagOptPar1);

		}
	}

	public List<RuagOptimizationParameterSet> findParameter(RuagOptimizationParameterSet ruagOptimizationParameterSet) {
		return dao.findParameter(ruagOptimizationParameterSet);
	}

	public void updateRuagWorkModelDetail(RuagOptimizationParameterSet ruagOptimizationParameterSet) {
		dao.updateRuagWorkModelDetail(ruagOptimizationParameterSet);
	}

	public RuagOptimizationParameterSet get(String id) {
		return super.get(id);
	}

	public List<RuagOptimizationParameterSet> findList(RuagOptimizationParameterSet ruagOptimizationParameterSet) {
		return super.findList(ruagOptimizationParameterSet);
	}

	public Page<RuagOptimizationParameterSet> findPage(Page<RuagOptimizationParameterSet> page,
			RuagOptimizationParameterSet ruagOptimizationParameterSet) {
		return super.findPage(page, ruagOptimizationParameterSet);
	}

	@Transactional(readOnly = false)
	public void save(RuagOptimizationParameterSet ruagOptimizationParameterSet) {
		super.save(ruagOptimizationParameterSet);
	}

	@Transactional(readOnly = false)
	public void delete(RuagOptimizationParameterSet ruagOptimizationParameterSet) {
		super.delete(ruagOptimizationParameterSet);
	}

	@Transactional(readOnly = false)
	public void deleteAll() {
		dao.deleteAll();
	}

}
