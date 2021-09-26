package com.example.demo.doit;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

@Component
public class go implements ApplicationRunner {
    static boolean ifRun = true;
    static JButton startBt;
    static JButton endBt;

    static BufferedImage screenShotImage;    //屏幕截图
    static BufferedImage keyImage;           //查找目标图片

    static int scrShotImgWidth;              //屏幕截图宽度
    static int scrShotImgHeight;             //屏幕截图高度

    static int keyImgWidth;                  //查找目标图片宽度
    static int keyImgHeight;                 //查找目标图片高度

    static int[][] screenShotImageRGBData;   //屏幕截图RGB数据
    static int[][] keyImageRGBData;          //查找目标图片RGB数据

    static int[][][] findImgData;            //查找结果，目标图标位于屏幕截图上的坐标数据


    static Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {

            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }

            while (ifRun) {


                if(this.ImageFindDemo("/Users/yxh/Desktop/挑战.png")[0]!=0 && this.ImageFindDemo("/Users/yxh/Desktop/挑战.png")[1]!=0){
                    robot.mouseMove(this.ImageFindDemo("/Users/yxh/Desktop/挑战.png")[0], this.ImageFindDemo("/Users/yxh/Desktop/挑战.png")[1]);
                    click(robot);
                    System.out.println("挑战:"+this.ImageFindDemo("/Users/yxh/Desktop/挑战.png")[0]+","+this.ImageFindDemo("/Users/yxh/Desktop/挑战.png")[1]);
                    robot.delay(25000);
                }


                if(this.ImageFindDemo("/Users/yxh/Desktop/结算.png")[0]!=0 && this.ImageFindDemo("/Users/yxh/Desktop/结算.png")[1]!=0){
                    robot.mouseMove(this.ImageFindDemo("/Users/yxh/Desktop/结算.png")[0], this.ImageFindDemo("/Users/yxh/Desktop/结算.png")[1]);
                    click(robot);
//                    System.out.println("结算:"+this.ImageFindDemo("D:/结算.png")[0]+","+this.ImageFindDemo("D:/结算.png")[1]);
                }

                if(this.ImageFindDemo("/Users/yxh/Desktop/奖励.png")[0]!=0 && this.ImageFindDemo("/Users/yxh/Desktop/奖励.png")[1]!=0){
                    robot.mouseMove(this.ImageFindDemo("/Users/yxh/Desktop/奖励.png")[0], this.ImageFindDemo("/Users/yxh/Desktop/奖励.png")[1]);
                    click(robot);
//                    System.out.println("奖励:"+this.ImageFindDemo("D:/奖励.png")[0]+","+this.ImageFindDemo("D:/奖励.png")[1]);
                }

//                if(this.ImageFindDemo("D:/悬赏.png")[0]!=0 && this.ImageFindDemo("D:/悬赏.png")[1]!=0){
//                    robot.mouseMove(this.ImageFindDemo("D:/悬赏.png")[0], this.ImageFindDemo("D:/悬赏.png")[1]);
//                    click(robot);
//                }

            }
        }
        private int[] ImageFindDemo(String path) {

            screenShotImage = this.getFullScreenShot();
            keyImage = this.getBfImageFromPath(path);
            screenShotImageRGBData = this.getImageGRB(screenShotImage);
            keyImageRGBData = this.getImageGRB(keyImage);
            scrShotImgWidth = screenShotImage.getWidth();
            scrShotImgHeight = screenShotImage.getHeight();
            keyImgWidth = keyImage.getWidth();
            keyImgHeight = keyImage.getHeight();

            //开始查找
            this.findImage();

            //返回查询到的位置坐标
            return this.printFindData();
        }

        private int[] printFindData() {
            for(int y=0; y<keyImgHeight; y++) {
                for(int x=0; x<keyImgWidth; x++) {
//                System.out.print("("+this.findImgData[y][x][0]+", "+this.findImgData[y][x][1]+")");
                }
//            System.out.println();

            }
            Random random = new Random();
            int i = random.nextInt(findImgData[keyImgHeight-1][keyImgWidth-1][0]-findImgData[0][0][0]+1) + findImgData[0][0][0];
            int j = random.nextInt(findImgData[keyImgHeight-1][keyImgWidth-1][1]-findImgData[0][0][1]+1) + findImgData[0][0][1];

//            System.out.println("Y轴范围:"+findImgData[0][0][0]+"-"+findImgData[keyImgHeight-1][keyImgWidth-1][0]);
//            System.out.println("X轴范围:"+findImgData[0][0][1]+"-"+findImgData[keyImgHeight-1][keyImgWidth-1][1]);
//
//            System.out.println("Y随机点:"+i);
//            System.out.println("X随机点:"+j);
            int[] ints = {j,i};

            return ints;
        }

        private void findImage() {
            findImgData = new int[keyImgHeight][keyImgWidth][2];
            //遍历屏幕截图像素点数据
            for(int y=0; y<scrShotImgHeight-keyImgHeight; y++) {
                for(int x=0; x<scrShotImgWidth-keyImgWidth; x++) {
                    //根据目标图的尺寸，得到目标图四个角映射到屏幕截图上的四个点，
                    //判断截图上对应的四个点与图B的四个角像素点的值是否相同，
                    //如果相同就将屏幕截图上映射范围内的所有的点与目标图的所有的点进行比较。
                    if((keyImageRGBData[0][0]^screenShotImageRGBData[y][x])==0
                            && (keyImageRGBData[0][keyImgWidth-1]^screenShotImageRGBData[y][x+keyImgWidth-1])==0
                            && (keyImageRGBData[keyImgHeight-1][keyImgWidth-1]^screenShotImageRGBData[y+keyImgHeight-1][x+keyImgWidth-1])==0
                            && (keyImageRGBData[keyImgHeight-1][0]^screenShotImageRGBData[y+keyImgHeight-1][x])==0) {

                        boolean isFinded = this.isMatchAll(y, x);
                        //如果比较结果完全相同，则说明图片找到，填充查找到的位置坐标数据到查找结果数组。
                        if(isFinded) {
                            for(int h=0; h<keyImgHeight; h++) {
                                for(int w=0; w<keyImgWidth; w++) {
                                    findImgData[h][w][0] = y+h;
                                    findImgData[h][w][1] = x+w;
                                }
                            }
                            return;
                        }
                    }
                }
            }
        }

        private boolean isMatchAll(int y, int x) {
            int biggerY = 0;
            int biggerX = 0;
            int xor = 0;
            int xorTotal = 0;
            for(int smallerY=0; smallerY<keyImgHeight; smallerY++) {
                biggerY = y+smallerY;
                for(int smallerX=0; smallerX<keyImgWidth; smallerX++) {
                    biggerX = x+smallerX;
                    if(biggerY>=scrShotImgHeight || biggerX>=scrShotImgWidth) {
                        return false;
                    }
                    xor = keyImageRGBData[smallerY][smallerX]^screenShotImageRGBData[biggerY][biggerX];
//                    if(xor!=0) {
//                        return false;
//                    }
                    if(xor!=0){
                        xorTotal++;
                    }
                }
                biggerX = x;
            }
            if((keyImgHeight*keyImgWidth-xorTotal)/(keyImgHeight*keyImgWidth)<0.8){
                return false;
            }
            return true;
        }

        private int[][] getImageGRB(BufferedImage bfImage) {
            int width = bfImage.getWidth();
            int height = bfImage.getHeight();
            int[][] result = new int[height][width];
            for (int h = 0; h < height; h++) {
                for (int w = 0; w < width; w++) {
                    //使用getRGB(w, h)获取该点的颜色值是ARGB，而在实际应用中使用的是RGB，所以需要将ARGB转化成RGB，即bufImg.getRGB(w, h) & 0xFFFFFF。
                    result[h][w] = bfImage.getRGB(w, h) & 0xFFFFFF;
                }
            }
            return result;
        }

        /**
         * 从本地文件读取目标图片
         * @param keyImagePath - 图片绝对路径
         * @return 本地图片的BufferedImage对象
         */
        private BufferedImage getBfImageFromPath(String keyImagePath) {
            BufferedImage bfImage = null;
            try {
                bfImage = ImageIO.read(new File(keyImagePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bfImage;
        }

        /**
         * 全屏截图
         * @return 返回BufferedImage
         */
        private BufferedImage getFullScreenShot() {
            BufferedImage bfImage = null;
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
            Robot robot = null;
            try {
                robot = new Robot();
            } catch (AWTException e) {
                e.printStackTrace();
            }
            bfImage = robot.createScreenCapture(new Rectangle(0, 0, width, height));
            return bfImage;
        }
    });

    static public void click(Robot robot) {
        // 模拟鼠标按下左键
        robot.mousePress(InputEvent.BUTTON1_MASK);
        // 模拟鼠标松开左键
        robot.mouseRelease(InputEvent.BUTTON1_MASK);
    }

    static public void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg, "提示信息",
                JOptionPane.PLAIN_MESSAGE);
    }

    static public void showMain() {
        JDialog dialog = new JDialog();
        // 设置大小
        dialog.setSize(200, 100);
        // 设置标题
        dialog.setTitle("界面");

        startBt = new JButton("开始");
        endBt = new JButton("结束");
        // 绑定监听
        startBt.addActionListener(actionListener);
        endBt.addActionListener(actionListener);
        startBt.setBounds(35, 10, 60, 40);
        endBt.setBounds(90, 10, 60, 40);
        // 设置布局为空，使用坐标控制控件位置的时候，一定要设置布局为空
        dialog.setLayout(null);
        // 添加控件
        dialog.add(startBt);
        dialog.add(endBt);
        // 设置dislog的相对位置，参数为null，即显示在屏幕中间
        dialog.setLocationRelativeTo(null);
        // 设置当用户在此对话框上启动 "close" 时默认执行的操作
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // 设置是否显示
        dialog.setVisible(true);
    }

    static ActionListener actionListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == startBt) {
//                showMsg("准备开始——请在三秒内点击");
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        showMsg("已开始执行并已启动线程");
//                        thread.start();
//                    }
//                }, 3000);
                thread.start();
            }

            if (e.getSource() == endBt) {
                ifRun = false;
                showMsg("结束");
            }
        }
    };

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 显示主界面
        showMain();
        System.out.println("==========程序已开始执行==============");
    }
}
