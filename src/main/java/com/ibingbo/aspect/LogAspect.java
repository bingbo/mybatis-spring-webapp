package com.ibingbo.aspect;

import com.ibingbo.annotation.Log;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * Created by bing on 2016/12/29.
 */
@Aspect
@Component
public class LogAspect {


    @Pointcut("@annotation(com.ibingbo.annotation.Log)")
    public void log() {
        System.out.println("log this controller ....");
    }

    @Before("log()")
    public void doLog() {

        System.out.println("this is aspect before dolog ....");
    }

    @After("@annotation(com.ibingbo.annotation.Log)")
    public void afterDoLog(JoinPoint joinPoint) throws ClassNotFoundException {
        System.out.println("this is aspect after dolog ....");
        System.out.println(joinPoint.getTarget());
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        System.out.println(args);
        Class targetClass = Class.forName(targetName);
        java.lang.reflect.Method[] methods = targetClass.getMethods();
        String value = "";
        for (java.lang.reflect.Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] classes = method.getParameterTypes();
                if (classes.length == args.length) {
                    value = method.getAnnotation(Log.class).remark();
                }
            }
        }
        System.out.println("annotation parameter is " + value);
    }

    @Around("log()")
    public Object aroundDoLog(ProceedingJoinPoint point) {
        System.out.println("this is aspect around dolog ...");
        System.out.println(point.getArgs().toString());
        Object o = null;
        try {
             o = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(o);
        System.out.println("this is aspect around dolog ...");
        return o;
    }

    @AfterThrowing("log()")
    public void afterThrowingDoLog() {
        System.out.println("this is aspect after throwing dolog ...");
    }
}

