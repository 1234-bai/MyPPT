package com.Pages.SlideBar;

// Display an image for each object in the list.

import com.Pages.CONSTANTS;

import javax.swing.*;
import java.awt.*;

class ImageCellRenderer extends JLabel implements ListCellRenderer {

    // This is the only method defined by ListCellRenderer.
    // We just reconfigure the JLabel each time we're called.


    public ImageCellRenderer() {
        setFont(new Font("微软雅黑", Font.PLAIN, 15));
    }

    @Override
    public Component getListCellRendererComponent(
            JList list,              // the list
            Object value,            // value to display
            int index,               // cell index
            boolean isSelected,      // is the cell selected
            boolean cellHasFocus)    // does the cell have focus
    {

        setIcon((ImageIcon) value);
        if (isSelected) {   //选中时设置边框为红色
            setBorder(BorderFactory.createLineBorder(CONSTANTS.MY_COLOR.SLIDE_BAR_IMAGE_BORDER_SELECTED,4));
            setText(index+1+"");
        } else {    //未选中时为灰色
            setBorder(BorderFactory.createLineBorder(CONSTANTS.MY_COLOR.SLIDE_BAR_IMAGE_BORDER_UNSELECTED,2));
            setText("");
        }
        return this;
    }
}
