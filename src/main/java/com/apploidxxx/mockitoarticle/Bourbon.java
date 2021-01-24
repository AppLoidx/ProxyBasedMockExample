package com.apploidxxx.mockitoarticle;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Arthur Kupriyanov on 24.01.2021
 */
public class Bourbon {

    private static MockInvocationHandler lastMockInvocationHandler;

    @SuppressWarnings("unchecked")
    public static <T> T mock(Class<T> clazz) {
        MockInvocationHandler invocationHandler = new MockInvocationHandler();
        T proxy = (T) Proxy.newProxyInstance(Bourbon.class.getClassLoader(), new Class[]{clazz}, invocationHandler);
        return proxy;
    }

    public static <T> When<T> when(T obj) {
        return new When<>();
    }

    public static class When<T> {
        public void thenReturn(T retObj) {
            lastMockInvocationHandler.setRetObj(retObj);
        }
    }

    private static class MockInvocationHandler implements InvocationHandler {

        private Method lastMethod;
        private Object[] lastArgs;
        private final List<StoredData> storedData = new ArrayList<>();

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) {
            lastMockInvocationHandler = this;
            lastMethod = method;
            lastArgs = args;

            // проверяем в сохраненных данных
            for (StoredData storedData : this.storedData) {
                if (storedData.getMethod().equals(method) && Arrays.deepEquals(storedData.getArgs(), args)) {
                    // если данные есть, то возвращаем сохраненный
                    return storedData.getRetObj();
                }
            }

            // иначе возвращаем null,
            // в случае с Mockito дефолтное значение для примитивов
            return null;
        }

        public void setRetObj(Object retObj) {
            storedData.add(new StoredData(lastMethod, lastArgs, retObj));
        }

        private static class StoredData {
            private final Object[] args;
            private final Method method;
            private final Object retObj;

            private StoredData(Method method, Object[] args, Object retObj) {
                this.args = args;
                this.method = method;
                this.retObj = retObj;
            }

            private Object[] getArgs() {
                return args;
            }

            private Method getMethod() {
                return method;
            }

            private Object getRetObj() {
                return retObj;
            }
        }

    }


}
