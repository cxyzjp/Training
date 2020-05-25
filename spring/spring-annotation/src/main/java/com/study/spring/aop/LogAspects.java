package com.study.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

@Aspect
public class LogAspects {

    // 公共的切入点表达式。
    @Pointcut("execution(public int com.study.spring.aop.MathCalculator.*(..))")
    public void pointCut() {
    }

    // 目标方法执行之前
    // JoinPoint一定要放在参数第一位，否则报错
    @Before("execution(public int com.study.spring.aop.MathCalculator.div(int,int))")
    public void logStart(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature().getName() + "之前。。。参数:" + Arrays.toString(joinPoint.getArgs()));
    }

    // 目标方法之后，无论正常还是异常执行
    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束。。。");
    }

    // 目标方法成功执行之后
    @AfterReturning(value = "pointCut()", returning = "result")
    public void logReturn(Object result) {
        System.out.println("除法返回。。。结果：" + result);
    }

    // 目标方法异常执行之后
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        System.out.println(joinPoint.getSignature().getName() + "异常。。。异常：" + ex.toString());
    }
}
