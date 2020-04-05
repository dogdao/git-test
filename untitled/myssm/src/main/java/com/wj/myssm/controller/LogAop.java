package com.wj.myssm.controller;

import com.wj.myssm.entity.Syslog;
import com.wj.myssm.service.ISyslogService;
import com.wj.myssm.utils.CreateUUIDUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISyslogService syslogService;

    private Date visitTime;
    private Class clazz;
    private Method method;


    @Before(value = "execution(* com.wj.myssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        clazz = jp.getTarget().getClass();//具体要访问的类
        System.out.println("<<<<"+clazz);
        String methodName = jp.getSignature().getName();//获取访问类的名称
        Object[] args = jp.getArgs();//获取访问方法的参数

        if(args==null || args.length==0){
            method = clazz.getMethod(methodName);
        } else {
            Class[] classArgs = new Class[args.length];
            for(int i=0;i<args.length;i++) {
                classArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,classArgs);
        }
        System.out.println("method is :"+method);
    }

    @After(value = "execution(* com.wj.myssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {
        long time = new Date().getTime()-visitTime.getTime();

        //通过反射获取URL
        String url = "";
        if(clazz!=null && method!=null && clazz != LogAop.class) {
            //1.获取类上的RequestMapping
            RequestMapping classAnnotation =
                    (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null) {
                String[] classValue = classAnnotation.value();
                //2.获取方法上的RequestMapping
                RequestMapping methodAnnotation =
                        (RequestMapping) method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null) {
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0]+methodValue[0];
                    System.out.println("url is:"+url);

                    //获取ip
                    String ip = request.getRemoteAddr();
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获取当前登入对象
                    User user = (User)context.getAuthentication().getPrincipal();
                    String username = user.getUsername();

                    //将信息封装到Syslog
                    Syslog syslog = new Syslog();
                    syslog.setId(CreateUUIDUtils.createID());
                    syslog.setExecutTime(time);
                    syslog.setUrl(url);
                    syslog.setUsername(username);
                    syslog.setIp(ip);
                    syslog.setMethod("[类名]"+clazz.getName()+"[方法名]"+method.getName());
                    syslog.setVisitTime(visitTime);

                    syslogService.save(syslog);
                }
            }


        }


    }
}
