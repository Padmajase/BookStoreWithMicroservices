package com.example.CustomerServer.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {

    @Before("execution(* com.example.CustomerServer.service.UserService.updateUserById(..))")
    public String beforeUpdateUser(JoinPoint joinPoint){
        System.out.println("fetching User with given id ");
        return "User is fetched";
    }

    @After("execution(* com.example.CustomerServer.service.UserService.updateUserById(..))")
    public String afterBookUpdate(){
        System.out.println("updating User ");
        return "user updation ";
    }

    @AfterReturning("execution(* com.example.CustomerServer.service.UserService.updateUserById(..))")
    public String afterReturnBookUpdate(JoinPoint joinPoint){
        System.out.println("user updation");
        return "user has been updated";
    }
}
