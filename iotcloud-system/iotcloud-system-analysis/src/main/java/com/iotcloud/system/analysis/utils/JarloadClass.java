package com.iotcloud.system.analysis.utils;

import com.iotcloud.common.core.exception.ErrorType;
import com.iotcloud.common.core.exception.NettyServerException;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

@Slf4j
public class JarloadClass {

    //动态加载Jar
    public static void loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        //文件存在
        if (jarFile.exists() == false) {
            log.error("jar file not found.");
            throw new NettyServerException(ErrorType.SERVER_JAREMPTY_ERROR);
        }
        //从URLClassLoader类加载器中获取类的addURL方法
        Method method = null;
        try {
            method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        } catch (NoSuchMethodException | SecurityException e1) {
            //e1.printStackTrace();
            throw new NettyServerException(ErrorType.SERVER_JAR_ERROR);
        }
        // 获取方法的访问权限
        boolean accessible = method.isAccessible();
        try {
            //修改访问权限为可写
            if (accessible == false) {
                method.setAccessible(true);
            }
            // 获取系统类加载器
            URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
            //获取jar文件的url路径
            URL url = jarFile.toURI().toURL();
            //jar路径加入到系统url路径里
            method.invoke(classLoader, url);
        } catch (Exception e) {
            //e.printStackTrace();
            throw new NettyServerException(ErrorType.SERVER_JAR_ERROR);
        } finally {
            method.setAccessible(accessible);
        }
    }

}
