package com.product.util;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class KeyBoardUtils {

    /**
     * 键盘上按下 Tab 键的方法
     */
    public static void pressTabKey(){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // 调用keyPress方法来实现按下Tab键
        robot.keyPress(KeyEvent.VK_TAB);
        // 调用keyRelease来释放Tab键
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    /**
     * 富文本框中对指定字符串内容的粘贴函数
     */
    public static void setAndCtrlVClipboardData(String string){
        // 申明 StringSelection 对象，并使用函数的String参数完成实例化
        StringSelection stringSelection = new StringSelection(string);
        // 使用Toolkit对象SetContents 方法将字符串放到剪切板中
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);

        // 申明Robot对象
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // 调用keyPress来按下Ctrl键
        robot.keyPress(KeyEvent.VK_CONTROL);
        // 调用keyPress来按下V键
        robot.keyPress(KeyEvent.VK_V);
        // 调用keyRelease来释放Ctrl键
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // 调用keyRelease来释放V键
        robot.keyRelease(KeyEvent.VK_V);
    }
}
