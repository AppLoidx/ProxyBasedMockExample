package com.apploidxxx.mockitoarticle;

import com.apploidxxx.mockitoarticle.object.IApple;

/**
 * @author Arthur Kupriyanov on 24.01.2021
 */
public class BourbonExample {
    public static void main(String[] args) {
        IApple apple = Bourbon.mock(IApple.class);
        Bourbon.when(apple.getColor()).thenReturn("Poisoned apple!");
        Bourbon.when(apple.message("Arthur")).thenReturn("Hey, arthur!");
        Bourbon.when(apple.message("Vlad")).thenReturn("Awm, sorry... Who are you?");

        System.out.println(apple.getColor());                        // Poisoned apple!
        System.out.println(apple.message("Arthur"));        // Hey, arthur!
        System.out.println(apple.message("Vlad"));          // Awm, sorry... Who are you?
    }
}
