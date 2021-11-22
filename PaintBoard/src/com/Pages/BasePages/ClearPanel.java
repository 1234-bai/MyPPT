package com.Pages.BasePages;

import com.Pages.CONSTANTS;

import javax.swing.*;

/**
 * 透明的干净的Panel
 */
public class ClearPanel extends JPanel {
    public ClearPanel() {
        setOpaque(false);   //变透明。透明的前提是有背景。
        setBackground(CONSTANTS.MY_COLOR.EMPTY_AREA_COLOR);
    }

    /**
     *
     * @param relativePathInResource 在资源文件夹中的位置
     * @return 编译成字节码后的位置
     */
    protected String getRightResourceFilePath(String relativePathInResource){
        return CONSTANTS.getRightResourceFilePath(relativePathInResource);
    }
}
