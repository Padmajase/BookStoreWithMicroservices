//package com.example.OrderServer.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.After;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class OrderAspect {
//
//    @Before("execution(* com.example.OrderServer.service.OrderService.updateOrder(..))")
//    public String beforeUpdatoOrder(JoinPoint joinPoint){
//        System.out.println("fetching order  given details ");
//        return "fetching order  given details";
//    }
//
//    @After("execution(* com.example.OrderServer.service.OrderService.updateOrder(..))")
//    public String afterBookUpdate(){
//        System.out.println("updating order ");
//        return "updating orde ";
//    }
//
//    @AfterReturning("execution(* com.example.OrderServer.service.OrderService.updateOrder(..))")
//    public String afterReturnBookUpdate(JoinPoint joinPoint){
//        System.out.println("update method executed");
//        return "update method executed";
//    }
//}
