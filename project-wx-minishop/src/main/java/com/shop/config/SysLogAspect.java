package com.shop.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Part;
import java.io.Serializable;
import java.util.List;

@Aspect
@Component
@Slf4j
public class SysLogAspect {


    @Pointcut("execution(* com.shop.controller..*.*(..))")
    public void controller() {
    }

    @Before("controller()")
    public void logTraceBefore(JoinPoint point) {
        try {
            //请求 类 + 方法名
            String methodName = point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()";

            Signature signature = point.getSignature();

            MethodSignature methodSignature = (MethodSignature) signature;

            // 方法参数名
            String[] parameterNames = methodSignature.getParameterNames();

            // 方法参数值
            Object[] args = point.getArgs();

            JSONObject param = new JSONObject();
            for (int i = 0; i < parameterNames.length; i++) {
                String paramName = parameterNames[i];
                Object value = args[i];
                if (!isSerializable(value)){
                    continue;
                }
                if (value instanceof Serializable) {
                    param.put(paramName, value);
                } else {
                    param.put(paramName, String.valueOf(value));
                }
            }
            log.info("Call {} , Input -- {}", methodName, JSON.toJSONString(param));
        } catch (Exception e) {
            log.error("日志打印实现类前置增强出现异常", e);
        }
    }

    /**
     * @title isSerializable
     * @description 判断当前对象是否可序列化
     * @author fanc
     * @param obj
     * @updateTime 2019/11/26 13:26
     * @return boolean
     * @throws
     **/
    private boolean isSerializable(Object obj) {
        //排除MultipartFile,Part
        if (obj instanceof ServletRequest || obj instanceof ServletResponse || obj instanceof MultipartFile || obj instanceof Part) {
            return false;
        }
        //排除List<MultipartFile>,List<Part>
        if (obj instanceof List) {
            @SuppressWarnings("unchecked")
            List<Object> list = (List<Object>) obj;
            if (list.size() > 0) {
                Object object = list.get(0);
                if (object instanceof MultipartFile || object instanceof Part) {
                    return false;
                }
            }
        }
        return true;
    }

    @AfterReturning(value = "controller()", returning = "returnValue")
    public void logTraceAfter(JoinPoint point, Object returnValue) {
        try {
            //请求 类 + 方法名
            String methodName = point.getTarget().getClass().getName() + "." + point.getSignature().getName() + "()";
            String out;
            if (returnValue instanceof Serializable){
                out = JSON.toJSONString(returnValue);
            }else {
                out = String.valueOf(returnValue);
            }
            log.info("Call {} Success, Output -- {}", methodName, out);
        } catch (Exception e) {
            log.error("日志打印实现类后置增强出现异常", e);
        }
    }
}
