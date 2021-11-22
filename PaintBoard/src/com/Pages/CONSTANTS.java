package com.Pages;

import java.awt.*;

//为一个单例类
public class CONSTANTS {
    public static class MY_COLOR{
        public static final Color TITLE_BAR_COLOR = new Color(182, 70, 43);
        public static final Color EMPTY_AREA_COLOR = new Color(230,230,230);

        public static final Color SLIDE_BAR_IMAGE_BORDER_UNSELECTED = new Color(174, 176, 179, 255);
        public static final Color SLIDE_BAR_IMAGE_BORDER_SELECTED = TITLE_BAR_COLOR;
    }

    //获取类加载器，然后从中获取资源文件在编译后的位置，从而保证能正确在字节码中找到资源文件
    private final ClassLoader classLoader = getClass().getClassLoader();
    private static CONSTANTS myInstance;

    private CONSTANTS(){}

    private static CONSTANTS getInstance(){
        if(myInstance == null){
            myInstance = new CONSTANTS();
        }
        return myInstance;
    }
    /**
     *
     * @param relativePathInResource 在资源文件夹中的位置
     * @return 编译成字节码后的位置
     */
    public static String getRightResourceFilePath(String relativePathInResource){
        return getInstance().classLoader.getResource(relativePathInResource).getPath();
    }
}
