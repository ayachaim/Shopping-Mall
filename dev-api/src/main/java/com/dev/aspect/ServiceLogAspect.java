package com.dev.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceLogAspect {

    public static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    /**
     * @Desc 日志
     * @param joinPoint
     * @return result
     */
    @Around("execution(* com.dev.service.impl..*.*(..))")
    public Object recordTimeLog(ProceedingJoinPoint joinPoint) throws Throwable{
        logger.info("=======开始执行{}.{}=======",
                joinPoint.getTarget().getClass(),
                joinPoint.getSignature().getName());
        //记录开始时间
        Long begin = System.currentTimeMillis();
        //执行目标service
        Object result = joinPoint.proceed();
        //记录结束时间
        Long end = System.currentTimeMillis();
        Long takeTime = end-begin;
        if(takeTime > 3000){
            logger.error("=======执行结束,耗时：{}毫秒=======",takeTime);
        }else if (takeTime > 2000){
            logger.warn("=======执行结束,耗时：{}毫秒=======",takeTime);
        }else {
            logger.info("=======执行结束,耗时：{}毫秒=======",takeTime);
        }
        return result;
    }

}
