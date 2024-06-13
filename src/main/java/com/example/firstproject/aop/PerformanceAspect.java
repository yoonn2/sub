package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Slf4j
public class PerformanceAspect {
    // 특정 어노테이션 지정
    @Pointcut("@annotation(com.example.firstproject.annotation.RunningTime)")
    private void enableRunningTime() {}

    // 기본 패키지의 모든 메소드
    @Pointcut("execution(* com.example.firstproject..*.*(..))")
    private void cut() {}

    //실행 시점을 설정: 두 조건을 만족하는 대상을 전후로 부가 기능을 삽입
    @Around("cut() && enableRunningTime()")
    public void loggingRunningTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 메소드 수행 전, 측정 시행
        StopWatch stopWatch = new StopWatch(); // 스프링에서 제공하는 도구 사용
        stopWatch.start();

        // 메소드 수행 후, 측정 종류 및 로깅
        Object returningObj = joinPoint.proceed();

        // 메소드 종료 후
        stopWatch.stop();
        String methodName = joinPoint.getSignature()
                .getName();
        log.info("{}의 총 수행 시간 => {} sec", methodName, stopWatch.getTotalTimeSeconds());
    }

}
