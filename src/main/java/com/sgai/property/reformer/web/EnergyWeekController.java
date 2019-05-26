package com.sgai.property.reformer.web;

import com.sgai.property.reformer.service.EnergyWeekService;
import com.sgai.property.reformer.vo.EnergyWeekSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 周能源汇总.
 *
 * @author ppliu
 * created in 2019/3/26 17:30
 */
@RestController
@RequestMapping("/energy/week")
public class EnergyWeekController {
    @Autowired
    private EnergyWeekService energyWeekService;

    /**
     * 上周和这周的能源汇总数据.
     */
    @RequestMapping("/showNearlyTwoWeeksData")
    public EnergyWeekSummary energyWeekSummary(@RequestParam String position) {
        return energyWeekService.getWeekSummaryByPosition(position);
    }
}
