package com.Listeners;

import java.awt.*;

/**
 * 选择监听器的接口，方便查找函数
 */
public interface ChoseListenerIml {

    /**
     *
     * @param tx 横坐标平移量
     * @param ty 总坐标平移量
     */
    void translateChoseContent(int tx, int ty);

    /**
     * 删除选中的图形
     */
    void deleteChoseContent();

    /**
     * 更新选中的图形颜色。
     *
     */
    void setChoseContentColor(Color newColor);

    /**
     * 道理同设置颜色
     */
    void setChoseContentLineWidth(float newLineWidth);

    /**
     * 将选中后的图形加入到图形栈中
     */
    void saveChoseContent();
}
