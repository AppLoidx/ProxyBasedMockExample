package com.apploidxxx.mockitoarticle;

import com.apploidxxx.mockitoarticle.object.Apple;
import com.apploidxxx.mockitoarticle.object.IApple;

import java.lang.reflect.Proxy;

/**
 * @author Arthur Kupriyanov on 24.01.2021
 */
public class ProxyExample {

    public static void main(String[] args) {
        Object proxyInstance = Proxy.newProxyInstance(
                ProxyExample.class.getClassLoader(),
                Apple.class.getInterfaces(), (proxy, method, args1) -> {
                    System.out.println("Called getColor() method on Apple");
                    return method.invoke(new Apple(), args1);
                });

        IApple appleProxy = (IApple) proxyInstance;
        System.out.println(appleProxy.getColor());
    }
}
