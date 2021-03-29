package com.product.util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 获取图片验证码
 */
public class TessUtil {

    // 得到验证码图片分为两步：
    //
    //1. 将验证码页面截图保存
    public static byte[] takeScreenshot(WebDriver webDriver){
        byte[] screenshot = null;
        screenshot = ((TakesScreenshot)webDriver).getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }


    /**
     * 2.得到的图片是整个屏幕的截图，我们可以处理一下，对图片进行截取，只保留验证码那一部分
     * @param webDriver
     * @param webElement
     * @param x 指定矩形区域左上角的 X 坐标
     * @param y 指定矩形区域左上角的 Y 坐标
     * @param width 指定矩形区域的宽度
     * @param height 指定矩形区域的高度
     * @return
     * @throws IOException
     */
    public static BufferedImage createElementImage(WebDriver webDriver, WebElement webElement,int x,int y,int width,int height) throws IOException {

//        Dimension size = webElement.getSize();
        BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(takeScreenshot(webDriver)));
//        BufferedImage croppedImage = originalImage.getSubimage(x, y,size.getWidth() + width, size.getHeight() + height);
        BufferedImage croppedImage = originalImage.getSubimage(x, y,width,height);
        return croppedImage;
    }

    // 3.tesseract读取图片，获得验证码，默认是英文，如果要使用中文包，加上instance.setLanguage("chi_sim");
    public static String getVerificationCode(String path,BufferedImage image) throws IOException, TesseractException {
        File imageFile = new File(path);
        imageFile.createNewFile();

        ImageIO.write(image,"png",imageFile);//进行保存

        ITesseract instance = new Tesseract();
        instance.setTessVariable("user_defined_dpi", "300");
        URL url = ClassLoader.getSystemResource("tessdata");//获得Tesseract的文字库
        String tessPath = url.getPath().substring(1);
        instance.setDatapath(tessPath);//进行读取，默认是英文，如果要使用中文包，加上instance.setLanguage("chi_sim");
        String result = instance.doOCR(imageFile);
        result = result.replaceAll("[^a-z^A-Z^0-9]", "");//替换大小写及数字
        return result;
    }
}
