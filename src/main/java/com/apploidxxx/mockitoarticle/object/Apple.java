package com.apploidxxx.mockitoarticle.object;

import com.apploidxxx.mockitoarticle.ProxyExample;

/**
 * @author Arthur Kupriyanov on 24.01.2021
 */
public class Apple implements IApple {
    private String color = "red";
    public String getColor() {
        return color;
    }

    @Override
    public String message(String target) {
        return "Hello from " + color + " apple, " + target;
    }

}
