//package com.example.demo.doit;
//
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.InputEvent;
//import java.util.Timer;
//import java.util.TimerTask;
//
////@Component
//public class gobuk  {
//    static boolean ifRun = true;
//    static JButton startBt;
//    static JButton endBt;
//
//
////    public static void main(String[] args) {
////        // 显示主界面
////        showMain();
////
////    }
//
//    static Thread thread = new Thread(new Runnable() {
//
//        @Override
//        public void run() {
//            Robot robot = null;
//            try {
//                robot = new Robot();
//            } catch (AWTException e) {
//                e.printStackTrace();
//            }
//            while (ifRun) {
//                //挑战按钮
//                robot.delay(1000);
//
//                if(robot.getPixelColor(1410,750).getRGB()==-2108226){
//                    int q = (int)(Math.random()*30);
//                    int w = (int)(Math.random()*30);
//                    robot.mouseMove(1411+q, 784+w);
//                    click(robot);
//
//                }
//
//                if(robot.getPixelColor(1431,800).getRGB()==-2840981){
//                    int e = (int)(Math.random()*30);
//                    int r = (int)(Math.random()*30);
//                    robot.mouseMove(1433+e, 706+r);
//                    click(robot);
//                    robot.delay(25000);
//                }
//
//                if(robot.getPixelColor(838,821).getRGB()==-15722967){
//                    int t = (int)(Math.random()*40);
//                    int y = (int)(Math.random()*40);
//                    robot.mouseMove(1279+t, 674+y);
//                    click(robot);
//                }
//                if(robot.getPixelColor(999,685).getRGB()==-4506854){
//                    int t = (int)(Math.random()*40);
//                    int y = (int)(Math.random()*40);
//                    robot.mouseMove(1279+t, 674+y);
//                    click(robot);
//                }
//            }
////            while (ifRun) {
////                //挑战按钮
////                robot.delay(700);
////
////                if(robot.getPixelColor(1875,1016).getRGB()==-1845054){
////                    int q = (int)(Math.random()*10);
////                    int w = (int)(Math.random()*10);
////                    robot.mouseMove(1875+q, 1016+w);
////                    click(robot);
////                }
////
////                if(robot.getPixelColor(1878,1017).getRGB()==-1917567){
////                    int e = (int)(Math.random()*10);
////                    int r = (int)(Math.random()*10);
////                    robot.mouseMove(1878+e, 989+r);
////                    click(robot);
////                    robot.delay(25000);
////                }
////
////                if(robot.getPixelColor(1615,1024).getRGB()==-16249835){
////                    int t = (int)(Math.random()*10);
////                    int y = (int)(Math.random()*10);
////                    robot.mouseMove(1615+t, 1024+y);
////                    click(robot);
////                }
////                if(robot.getPixelColor(1792,979).getRGB()==-15722967){
////                    int t = (int)(Math.random()*10);
////                    int y = (int)(Math.random()*10);
////                    robot.mouseMove(1792+t, 979+y);
////                    robot.delay(1000);
////                    click(robot);
////                }
////            }
//        }
//    });
//
//    static public void click(Robot robot) {
//        // 模拟鼠标按下左键
//        robot.mousePress(InputEvent.BUTTON1_MASK);
//        // 模拟鼠标松开左键
//        robot.mouseRelease(InputEvent.BUTTON1_MASK);
//    }
//
//    static public void showMsg(String msg) {
//        JOptionPane.showMessageDialog(null, msg, "提示信息",
//                JOptionPane.PLAIN_MESSAGE);
//    }
//
//    static public void showMain() {
//        JDialog dialog = new JDialog();
//        // 设置大小
//        dialog.setSize(200, 100);
//        // 设置标题
//        dialog.setTitle("界面");
//
//        startBt = new JButton("开始");
//        endBt = new JButton("结束");
//        // 绑定监听
//        startBt.addActionListener(actionListener);
//        endBt.addActionListener(actionListener);
//        startBt.setBounds(35, 10, 60, 40);
//        endBt.setBounds(90, 10, 60, 40);
//        // 设置布局为空，使用坐标控制控件位置的时候，一定要设置布局为空
//        dialog.setLayout(null);
//        // 添加控件
//        dialog.add(startBt);
//        dialog.add(endBt);
//        // 设置dislog的相对位置，参数为null，即显示在屏幕中间
//        dialog.setLocationRelativeTo(null);
//        // 设置当用户在此对话框上启动 "close" 时默认执行的操作
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//        // 设置是否显示
//        dialog.setVisible(true);
//    }
//
//    static ActionListener actionListener = new ActionListener() {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//            if (e.getSource() == startBt) {
//                showMsg("准备开始——请在三秒内点击");
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        showMsg("已开始执行并已启动线程");
//                        thread.start();
//                    }
//                }, 3000);
//
//            }
//
//            if (e.getSource() == endBt) {
//                ifRun = false;
//                showMsg("结束");
//            }
//        }
//    };
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // 显示主界面
//        showMain();
//        System.out.println("==========程序已开始执行==============");
//    }
//}
