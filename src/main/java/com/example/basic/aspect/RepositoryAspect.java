package com.example.basic.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class RepositoryAspect {
    // PointCut : 모든 repository를 대상
    // repository가 반환하는 데이터 출력
    @Pointcut(value = "execution(* com.example.basic.repository.*.*(..))")
    private void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("before around");
        Object returnData = null;
        try {
            returnData = jp.proceed();
        } finally {
            if (returnData != null) {
                log.debug(returnData.toString());
            }
        }
        return returnData;
    }

}
