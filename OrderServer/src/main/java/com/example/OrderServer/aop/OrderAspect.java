package com.example.OrderServer.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OrderAspect {

    @Before("execution(* com.example.OrderServer.service.OrderService.updateOrder(..))")
    public String beforeUpdatoOrder(JoinPoint joinPoint){
        System.out.println("fetching order ");
        return "order is fetched";
    }

    @After("execution(* com.example.OrderServer.service.OrderService.updateOrder(..))")
    public String afterBookUpdate(){
        System.out.println("updating order ");
        return "order updation ";
    }

    @AfterReturning("execution(* com.example.OrderServer.service.OrderService.updateOrder(..))")
    public String afterReturnBookUpdate(JoinPoint joinPoint){
        System.out.println("order updation");
        return "order has been updated";
    }
}
