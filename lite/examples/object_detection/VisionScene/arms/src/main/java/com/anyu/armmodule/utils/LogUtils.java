package com.anyu.armmodule.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 创建日期：2018/11/18
 * 描述: 日志工具类
 * 作者: gaoqiong
 */
public class LogUtils {
    private static boolean DEBUG= true;

    //信息太长,分段打印
    public static void e(String tag, String msg) {
        if (DEBUG){
            if (msg.length() > 4000) {
                for (int i = 0; i < msg.length(); i += 4000) {
                    if (i + 4000 < msg.length())
                        Log.e(tag, msg.substring(i, i + 4000));
                    else Log.e(tag, msg.substring(i, msg.length()));
                }
            } else {
                Log.e(tag, msg);
            }
        }
    }

    public static void v(String tag, String message) {
        if(DEBUG) {
            Log.v(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if(DEBUG) {
            Log.d(tag, message);
        }
    }

    public static void i(String tag, String message) {
        if(DEBUG) {
            Log.i(tag, message);
        }
    }

    public static void w(String tag, String message) {
        if(DEBUG) {
            Log.w(tag, message);
        }
    }

    public static void e(String tag, String message, Exception e) {
        if(DEBUG) {
            Log.e(tag, message, e);
        }
    }
    public static void System(String message) {
        if(DEBUG) {
            System.out.println(message);
        }
    }


    private static File file = null;

    public static void intAppLog(String appLogFileName) {
        File parentFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "/anyu/appLog");
        if (!parentFile.exists()) {
            try {
                parentFile.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        file = new File(parentFile, appLogFileName + ".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 程序运行时的日志保存在本地
     *
     * @param data
     */
    public static void saveAppLog(String data) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            //光标移到原始文件最后，再执行写入
            raf.seek(file.length());
            raf.write(data.getBytes());
            raf.writeBytes("\r\n");
            raf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}