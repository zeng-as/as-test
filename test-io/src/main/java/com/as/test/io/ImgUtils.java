package com.as.test.io;

import org.bytedeco.opencv.opencv_core.Mat;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_core.*;
import static org.bytedeco.opencv.global.opencv_highgui.*;
import static org.bytedeco.opencv.global.opencv_imgcodecs.*;
import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class ImgUtils {

    public static void removeBackground(String imgUrl, String resUrl) {
        //定义一个临界阈值
        int threshold = 300;
        try {
            BufferedImage img = ImageIO.read(new File(imgUrl));
            int width = img.getWidth();
            int height = img.getHeight();
            for (int i = 1; i < width; i++) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));
//                        System.out.println("red:" + color.getRed() + " | green:" + color.getGreen() + " | blue:" + color.getBlue());
                        int num = color.getRed() + color.getGreen() + color.getBlue();
                        if (num >= threshold) {
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            for (int i = 1; i < width; i++) {
                Color color1 = new Color(img.getRGB(i, 1));
                int num1 = color1.getRed() + color1.getGreen() + color1.getBlue();
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Color color = new Color(img.getRGB(x, y));

                        int num = color.getRed() + color.getGreen() + color.getBlue();
                        if (num == num1) {
                            img.setRGB(x, y, Color.BLACK.getRGB());
                        } else {
                            img.setRGB(x, y, Color.WHITE.getRGB());
                        }
                    }
                }
            }
            File file = new File(resUrl);
            if (!file.exists()) {
                File dir = file.getParentFile();
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ImageIO.write(img, "png", file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cuttingImg(String imgUrl) {
        try {
            File newfile = new File(imgUrl);
            BufferedImage bufferedimage = ImageIO.read(newfile);
            int width = bufferedimage.getWidth();
            int height = bufferedimage.getHeight();
            if (width > 52) {
                bufferedimage = ImgUtils.cropImage(bufferedimage, (int) ((width - 52) / 2), 0, (int) (width - (width - 52) / 2), (int) (height));
                if (height > 16) {
                    bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((height - 16) / 2), 52, (int) (height - (height - 16) / 2));
                }
            } else {
                if (height > 16) {
                    bufferedimage = ImgUtils.cropImage(bufferedimage, 0, (int) ((height - 16) / 2), (int) (width), (int) (height - (height - 16) / 2));
                }
            }
            ImageIO.write(bufferedimage, "jpg", new File(imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage cropImage(BufferedImage bufferedImage, int startX, int startY, int endX, int endY) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        if (startX == -1) {
            startX = 0;
        }
        if (startY == -1) {
            startY = 0;
        }
        if (endX == -1) {
            endX = width - 1;
        }
        if (endY == -1) {
            endY = height - 1;
        }
        BufferedImage result = new BufferedImage(endX - startX, endY - startY, 4);
        for (int x = startX; x < endX; ++x) {
            for (int y = startY; y < endY; ++y) {
                int rgb = bufferedImage.getRGB(x, y);
                result.setRGB(x - startX, y - startY, rgb);
            }
        }
        return result;
    }

    //得到灰度图像
    public static void getHuidu(String imgUrl) {
        Mat image = imread(imgUrl, IMREAD_GRAYSCALE);
        //读入一个图像文件并转换为灰度图像（由无符号字节构成）
        Mat image1 = imread(imgUrl, IMREAD_COLOR);
        //读取图像，并转换为三通道彩色图像，这里创建的图像中每个像素有3字节
        //如果输入图像为灰度图像，这三个通道的值就是相同的
        System.out.println("image has " + image1.channels() + " channel(s)");
        //channels方法可用来检查图像的通道数
        flip(image, image, 1);//就地处理,参数1表示输入图像，参数2表示输出图像
        //在一窗口显示结果
//        namedWindow("输入图片显示窗口");//定义窗口
//        imshow("输入图片显示窗口", image);//显示窗口
        waitKey(0);//因为他是控制台窗口，会在mian函数结束时关闭;0表示永远的等待按键,正数表示等待指定的毫秒数
    }

    //得到二值化处理图像
    public static void getErzhihua(String imgUrl) {
        // TODO Auto-generated method stub
        Mat image = imread(imgUrl);    //加载图像
        if (image.empty()) {
            System.out.println("图像加载错误，请检查图片路径！");
            return;
        }
//        imshow("原始图像", image);
        Mat gray = new Mat();
        cvtColor(image, gray, COLOR_RGB2GRAY);        //彩色图像转为灰度图像
        imshow("灰度图像", gray);
        Mat bin = new Mat();
        threshold(gray, bin, 120, 255, THRESH_TOZERO);    //图像二值化
        imshow("二值图像", bin);
        waitKey(0);
    }
}
