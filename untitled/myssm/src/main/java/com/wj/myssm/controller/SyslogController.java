package com.wj.myssm.controller;

import com.wj.myssm.entity.Syslog;
import com.wj.myssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SyslogController {
    @Autowired
    private ISyslogService syslogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView m = new ModelAndView();
        List<Syslog> syslogList = syslogService.findAll();
        m.addObject("sysLogs",syslogList);
        m.setViewName("syslog-list");
        return m;
    }


}
