package com.example.BookServer.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class BookAspect {

    @Before("execution(* com.example.BookServer.service.BookService.updateBookById(..))")
    public String beforeUpdateBook(JoinPoint joinPoint){
        System.out.println("fetching book with given id ");
        return "book is fetched";
    }

    @After("execution(* com.example.BookServer.service.BookService.updateBookById(..))")
    public String afterBookUpdate(){
        System.out.println("updating book ");
        return "book updated ";
    }

    @AfterReturning("execution(* com.example.BookServer.service.BookService.updateBookById(..))")
    public String afterReturnBookUpdate(JoinPoint joinPoint){
        System.out.println("book updation");
        return "book has been updated";
    }
}
