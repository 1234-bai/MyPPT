package com.Paint;

import java.util.ArrayList;

public abstract class MyDrawPPTIml {

    ArrayList<DrawJPanel> myPPT = new ArrayList<>();

    /**
     * 从一个.myppt文件里，读取获得多个DrawJPanel实例。存入myPPT。
     * 可调用DrawJPanelFileUtil类choseFile函数选择文件，获得文件对象
     *
     * @return 是否加载成功
     */
    public abstract boolean loadPPT();

    /**
     * 非空myPPT形成.myppt文件。存到电脑里。
     * 可在函数中调用DrawJPanelFileUtil类choseFile函数选择文件，获得储存的文件对象
     *
     * @return 是否保存成功
     */
    public abstract boolean savePPT();

    public ArrayList<DrawJPanel> getMyPPT() {
        return myPPT;
    }
}
