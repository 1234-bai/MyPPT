package com.Listeners;

import java.awt.*;

/**
 * 选择监听器的接口，方便查找函数
 */
public interface ChoseListenerIml {

    boolean choseContent(double x, double y);

    /**
     *
     * @param tx 横坐标平移量
     * @param ty 总坐标平移量
     */
    void translateChoseContent(int tx, int ty);

    /**
     * 删除选中的图形
     */
    void deleteChosenContent();

    /**
     * 更新选中的图形颜色。
     *
     */
    void setChosenContentColor(Color newColor);

    /**
     * 更新选中的图形线宽
     */
    void setChosenContentLineWidth(float newLineWidth);

//    /**
//     * 改变选中的文本内容
//     */
//    void setChosenText(String newText);

    /**
     * 将选中后的图形加入到图形栈中
     */
    void saveChoseContent();
}
