package com.example.basic.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
// localhost:8080/html/exam 호출해보기
public class ControllerAspect {
    // PointCut
    @Before(value = "execution (* com.example.basic.controller.*.*(..))")
    // Advice
    public void onBeforeHandler(JoinPoint joinPoint) {
        log.debug("@Before run");
    }

    // // 너무 정신없으면 After 막아두기
    // @After(value = "execution (* com.example.basic.controller.*.*(..))")
    // public void onAfterHandler(JoinPoint joinPoint) {
    //     log.debug("@After run");
    // }

    // // 정상적으로 실행되고 난 후에 실행
    // @AfterReturning(value = "execution (* com.example.basic.controller.*.*(..))", returning = "data")
    // public void onAfterReturningHandler(JoinPoint joinPoint, Object data) {
    //     if (data != null) {
    //         log.debug(data.toString());
    //     }
    //     log.debug("@AfterReturning run");
    // }

    // Around
    @Pointcut(value = "execution(* com.example.basic.controller.*.*(..))")
    private void pointCut() {
    }
    @Around(value = "pointCut()")
    // public void aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
    public Object aroundAdvice(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("before around");
        Object returnData = null; // @
        try {
            // jp.proceed(); // 여기를 기준으로 before, after
            // return jp.proceed();
            returnData = jp.proceed(); // @

        // // DBController.java : /hos/list - 오류 발생했을때도 AOP 동작하도록
        // } catch (Exception e) { // ArithmeticException
        //     log.debug("오류 발생!");
        //     log.debug(e.getMessage());

        } finally {
            if (returnData != null) { // @
                log.debug(returnData.toString());
            }
        }
        // System.out.println("after around");
        // AfterReturning 사용하지않고 잘 동작했는지 알고싶으면 @ 코드 추가 및 변경
        return returnData; // @
    }
    
}
