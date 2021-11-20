package com.Paint;

import java.io.File;
import java.util.ArrayList;

public abstract class MyDrawPPTIml {

    ArrayList<DrawJPanel> myPPT = new ArrayList<>();

    /**
     * 参数如果有其他需要，老洪可以自己调整。
     * 从一个.myppt文件里，读取获得多个DrawJPanel实例。存入myPPT。
     * 可调用DrawJPanelFileUtil类choseFile函数选择文件，获得文件对象
     * @param file 要加载的MyPPT文件的文件实例
     * @return 是否加载成功
     */
    public abstract boolean loadPPT(File file);

    /**
     * 参数如果有其他需要，老洪可以自己调整。
     * 非空myPPT形成.myppt文件。存到电脑里。
     * 可在函数中调用DrawJPanelFileUtil类choseFile函数选择文件，获得储存的文件对象
     * @return
     */
    public abstract boolean savePPT();

    public ArrayList<DrawJPanel> getMyPPT() {
        return myPPT;
    }
}
