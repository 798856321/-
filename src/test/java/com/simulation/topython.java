package com.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class topython {
    public static void main(String[] args) {



        System.out.println("Start");
    // python脚本的绝对路径，在windows中用"\\"分隔，在Linux中用"/"分隔
    String pyPath = "E:\\Development\\Pycharm_project\\CVlearn\\demo2.py";
        double[] a = {11.1, 2.1};
    // 传入python脚本的参数为”111“
    String[] args1 = new String[]{"python", pyPath, Arrays.toString(a)};

        try

    {
        // 执行Python文件，并传入参数
        Process process = Runtime.getRuntime().exec(args1);
        // 获取Python输出字符串作为输入流被Java读取
        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String actionStr = in.readLine();
        if (actionStr != null) {
            System.out.println(actionStr);
        }

        in.close();
        process.waitFor();
    } catch(
    IOException e)

    {
        throw new RuntimeException(e);
    } catch(
    InterruptedException e)

    {
        throw new RuntimeException(e);
    }

        System.out.println("End");
}
}