package com.example.ecommerce.configs;

import java.lang.reflect.Method;

public final class Utils {
    private Utils(){}

    public static boolean nullChecker(Object param) {
        for (Method method : param.getClass().getMethods()) {
            if(!method.getName().startsWith("get")) {
                continue;
            }
            if(method.getName().equals("getId")) {
                continue;
            }

            try {
                final Object object = method.invoke(param);

                if (object == null) {
                    return true;
                }
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }

        return false;
    }

    public static boolean updateObjectValues(Object oldParam, Object newParam) {
        for (Method method : newParam.getClass().getMethods()) {
            if(!method.getName().startsWith("get")) {
                continue;
            }

            try {
                final Object object = method.invoke(newParam);

                if (object == null) {
                    continue;
                }

                String setterName = "s" + method.getName().substring(1);
                Method setter = oldParam.getClass().getMethod(setterName, method.getReturnType());
                setter.invoke(oldParam, object);

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                return false;
            }
        }

        return true;
    }
}
