package com.example.firstproject.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/* @Before: 대상 실행 이전에 수행
   @After: 대상 실행 후, 수행
   @AfterReturning: 대상 실행 후 수행(정상 수행 시)
   @AfterThrowing: 대상 실행 후, 수행(예외 발생 시)
   @Around: 대상 실행 전후로 수행
   @Pointcut:주입 대상 지정
*/
@Aspect // AOP 클래스 선언: 부가 기능을 주입하는 클래스
@Component // 스프링 컨테이너 선언(해당 객체를 IOC가 생성 및 관리)
@Slf4j
public class DebuggingAspect {
    // 대상 메소드 선택 : CommentService#create()
    @Pointcut("execution(* com.example.firstproject.service.CommentService.*(..))") // create => *
    private void cut() {}

    // 실행 시점을 설정(@Pointcut에 지정된 메소드 즉, cut()의 대상이 수행되기 이전)
    @Before("cut()")
    public void loggingArgs(JoinPoint joinPoint) { //JoinPoint : cut()의 대상 메소드
        // 입력값 가져오기
        Object[] args = joinPoint.getArgs();

        //클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        // 입력값 로깅하기
        for (Object obj : args) {
            log.info("{}#{}의 입력값 => {}", className, methodName, obj);
        }
    }

    // 실행 시점을 설정 : cut()에 지정된 대상 호출 성공 후
    @AfterReturning(value = "cut()", returning = "returnObj")
    public void loggingReturnValue(JoinPoint joinPoint, //cut()의 대상 메소드
                                    Object returnObj) { // 리턴값(user가 지정 returnobj)
         // 클래스명
        String className = joinPoint.getTarget()
                .getClass()
                .getSimpleName();

        //메소드명
        String methodName = joinPoint.getSignature()
                .getName();

        //반환값 로깅
            log.info("{}#{}의 반환값 => {}", className, methodName, returnObj);
        }

}
