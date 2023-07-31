package com.example.ecommerce.configs;

import java.lang.reflect.Method;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

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
            boolean isGet  = method.getName().startsWith("get");
            boolean isBool = method.getName().startsWith("is");

            if (!isGet && !isBool) {
                continue;
            }

            try {
                final Object object = method.invoke(newParam);

                if (object == null) {
                    continue;
                }

                // only accept isGet and isBool for now
                String setterName = "";
                if (isGet) {
                    setterName = "s" + method.getName().substring(1);
                } else {
                    setterName = "set" + method.getName().substring(2);
                }


                Method setter = oldParam.getClass().getMethod(setterName, method.getReturnType());
                setter.invoke(oldParam, object);

            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                return false;
            }
        }

        return true;
    }

    public static String createTimeAt() {
        ZoneId zoneId = ZoneId.of("Asia/Jakarta");
        ZonedDateTime currentDateTime = ZonedDateTime.now(zoneId);
        String pattern = "yyyy-MM-dd HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        return currentDateTime.format(formatter);
    }
}
