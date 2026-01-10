package com.company.config;

import java.lang.reflect.Field;

public class ValidateAnnotation {
    public static void main(String[] args) {
        SystemConfig systemConfig = new SystemConfig(10,120);

        try {
            for(Field field : systemConfig.getClass().getDeclaredFields()) {
                if(!field.isAnnotationPresent(RangeCheck.class)) {
                    throw new ConfigValidationException("Please put RangeCheck annotation over field: "+ field.getName());
                } else {
                    RangeCheck annotation = field.getAnnotation(RangeCheck.class);
                    int max = annotation.max(), min = annotation.min();

                    if(field.getName().equals("maxThreads") && (max > 16 || min < 1)) {
                        throw new ConfigValidationException("maxThreads should be less than 17 and minThreads should be greater than 0");
                    } else if(field.getName().equals("timeoutSeconds") && (max > 5000 || min < 100)) {
                        throw new ConfigValidationException("maxTimeoutSeconds should be less than 5001 and minTimeoutSeconds should be greater than 99");
                    }
                }
            }
        } catch (ConfigValidationException e) {
            System.out.println(e.getMessage());
        }
    }
}
