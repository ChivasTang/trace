package com.hitachi.trace.web;

import com.hitachi.trace.domain.TraceInstrumentGraphResDomain;
import com.hitachi.trace.service.TraceInstrumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/")
public class TraceInstrumentController {
    @Resource
    private TraceInstrumentService traceInstrumentService;

    @GetMapping
    public ModelAndView main(HttpServletRequest req, HttpServletResponse res){
        return new ModelAndView("main");
    }

}
