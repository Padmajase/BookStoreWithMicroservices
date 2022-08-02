package com.example.CartServer.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CartAspect {

    @Before("execution(* com.example.CartServer.service.CartService.updateBookQuantity(..))")
    public String beforeUpdate(JoinPoint joinPoint){
        System.out.println("fetching cart details ");
        return "cart data fetching";
    }



    @After("execution(* com.example.CartServer.service.CartService.updateBookQuantity(..))")
    public String afterUpdate(){
        System.out.println("updating cart ");
        return "cart updation ";
    }

    @AfterReturning("execution(* com.example.CartServer.service.CartService.updateBookQuantity(..))")
    public String afterReturnUpdate(JoinPoint joinPoint){
        System.out.println("cart updation ");
        return "cart has been updated";
    }
}
