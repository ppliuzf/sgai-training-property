package com.sgai.property.reformer.service.impl;

import com.sgai.property.reformer.entity.DayEnergyConsumption;
import com.sgai.property.reformer.service.DayEnergyConsumptionService;
import com.sgai.property.reformer.service.EnergyWeekService;
import com.sgai.property.reformer.vo.EnergyWeekDetail;
import com.sgai.property.reformer.vo.EnergyWeekSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

/**
 * List<DayEnergyConsumption> ---> EnergyWeekSummary 转换封装.
 *
 * @author ppliu
 * created in 2019/3/25 12:08
 */
@Service
public class EnergyWeekServiceImpl implements EnergyWeekService {

    @Autowired
    DayEnergyConsumptionService dayEnergyConsumptionService;

    @Override
    public EnergyWeekSummary getWeekSummaryByPosition(String position) {
        List<DayEnergyConsumption> lastWeek = dayEnergyConsumptionService.getLastWeekDataList();
        List<DayEnergyConsumption> thisWeek = dayEnergyConsumptionService.getThisWeekDataList();
        return getPositionWeekSummary(lastWeek, thisWeek, position);
    }

    private EnergyWeekSummary getPositionWeekSummary(List<DayEnergyConsumption> lastWeek, List<DayEnergyConsumption> thisWeek, String position) {
        EnergyWeekSummary energyWeekSummary = new EnergyWeekSummary();
        energyWeekSummary.setEnergyPosition(position);

        EnergyWeekDetail thisWeekDetail = new EnergyWeekDetail();
        EnergyWeekDetail lastWeekDetail = new EnergyWeekDetail();
        setDetailValue(thisWeek, thisWeekDetail, position);
        setDetailValue(lastWeek, lastWeekDetail, position);

        energyWeekSummary.setThisWeek(thisWeekDetail);
        energyWeekSummary.setLasTWeek(lastWeekDetail);
        return energyWeekSummary;
    }

    private void setDetailValue(List<DayEnergyConsumption> dayList, EnergyWeekDetail weekDetail, String position) {
        Calendar calendar = Calendar.getInstance();
        dayList.forEach(dayEnergy -> {
            calendar.setTime(dayEnergy.getRecordTime());
            int recordDayInWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if (recordDayInWeek == Calendar.MONDAY) {
                weekDetail.setMonday(getMethodValue(dayEnergy, position));
            } else if (recordDayInWeek == Calendar.TUESDAY) {
                weekDetail.setThursday(getMethodValue(dayEnergy, position));
            } else if (recordDayInWeek == Calendar.WEDNESDAY) {
                weekDetail.setWednesday(getMethodValue(dayEnergy, position));
            } else if (recordDayInWeek == Calendar.THURSDAY) {
                weekDetail.setThursday(getMethodValue(dayEnergy, position));
            } else if (recordDayInWeek == Calendar.FRIDAY) {
                weekDetail.setFriday(getMethodValue(dayEnergy, position));
            } else if (recordDayInWeek == Calendar.SATURDAY) {
                weekDetail.setSaturday(getMethodValue(dayEnergy, position));
            } else if (recordDayInWeek == Calendar.SUNDAY) {
                weekDetail.setSunday(getMethodValue(dayEnergy, position));
            }
        });
    }

    /**
     * 反射获取属性
     */
    private BigDecimal getMethodValue(Object ob, String name) {
        Method[] m = ob.getClass().getMethods();
        for (int i = 0; i < m.length; i++) {
            if (("get" + name).toLowerCase().equals(m[i].getName().toLowerCase())) {
                try {
                    return (BigDecimal) m[i].invoke(ob);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return new BigDecimal(0);
    }


}
