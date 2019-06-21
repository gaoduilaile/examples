package com.anyu.armmodule.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.anyu.armmodule.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static File cameraFile;

    /**
     * 获取屏幕高度和宽带
     *
     * @param mContext
     * @return int[高，宽]
     *///TODO
    public static int[] getScreen(Context mContext) {
        DisplayMetrics dm = new DisplayMetrics();
        // 取得窗口属性
        // getWindowManager().getDefaultDisplay().getMetrics(dm);
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(dm);

        // 窗口的宽度
        int screenWidth = dm.widthPixels;

        // 窗口高度
        int screenHeight = dm.heightPixels;
        int screen[] = {screenHeight, screenWidth};
        return screen;
    }

    /**
     * @param context 获取宽度
     * @return
     *///TODO
    public static int getSecreenW(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        dm = null;
        return width;
    }

    /**
     * 像素转换
     *
     * @param context
     * @param dp
     * @return
     *///TODO
    public static int Dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 获取手机型号
     *
     * @return 返回值
     *///TODO
    public static String getPhoneModel() {
        return Build.MANUFACTURER;
    }

    /**
     * 获取手机android版本
     *
     * @return
     *///TODO
    public static String getPhoneAndroidVersion() {
        return Build.VERSION.RELEASE;
    }
    /**
     * 获取手机android SDK版本
     * @return
     *///TODO
    /*public static String getPhoneSDKVersion(){
        return android.os.Build.VERSION.SDK;
	}*/

    /**
     * 获取手机android SDK版本
     *
     * @return
     */
    public static int getPhoneSDKVersion() {
        String SDK = Build.VERSION.SDK;
        int SDKVersion = Integer.parseInt(SDK);
        return SDKVersion;
    }

    // 返回是否有SD卡//TODO
    public static boolean GetSDState() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    /**
     * @Title: deleteFile @Description: TODO(删除文件) @param @param file
     * 设定文件 @return void 返回类型 @throws
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {

        }
    }

    /**
     * 显示toast当前activity
     *
     * @param content 显示的内容
     *///TODO
    public static void showToast(Context mContext, String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Log
     * context 承接上下文
     *
     * @param content 显示的内容
     *///TODO
    public static void showLog(String output, String content) {
        Log.d(output, content);
    }

    /**
     * actionbar高度
     *
     * @param context
     * @return
     *///TODO
    public static int getBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 38;// 默认为38，貌似大部分是这样的

        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }


    /**
     * 获取时间戳
     *
     * @return
     *///TODO
    public static long getSeq() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前日期时间
     *///TODO
    public static String CurrentTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取当前日期
     *///TODO
    public static String CurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss ");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        return str;
    }

    /**
     * 获取时间
     */
    public static String getTimeString() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        return year + "年" + month + "月" + day + "日";
    }

    /**
     * 检查网络状态
     *///TODO
    public static boolean isNetUseable(Context context) {
        boolean have = false;
        ConnectivityManager cwjManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cwjManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            have = true;
        } else {
            have = false;
        }
        return have;
    }

    /**
     * 检查ip是否合法
     *///TODO
    public static boolean isIP(String addr) {
        if (addr.length() < 7 || addr.length() > 15 || "".equals(addr)) {
            return false;
        }
        /**
         * 判断IP格式和范围
         */
        String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pat = Pattern.compile(rexp);
        Matcher mat = pat.matcher(addr);
        boolean ipAddress = mat.find();
        //============对之前的ip判断的bug在进行判断
        if (ipAddress == true) {
            String ips[] = addr.split("\\.");
            if (ips.length == 4) {
                try {
                    for (String ip : ips) {
                        if (Integer.parseInt(ip) < 0 || Integer.parseInt(ip) > 255) {
                            return false;
                        }
                    }
                } catch (Exception e) {
                    return false;
                }
                return true;
            } else {
                return false;
            }
        }
        return ipAddress;
    }

    /**
     * 验证端口号
     *///TODO
    public static boolean pa_chkport(int port) {
        if (port < 0 && port > 65536) {
            return false;
        } else {
            return true;
        }
    }



    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     *///TODO
    public static boolean isDate2Bigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    /**
     * 倒计时
     *///TODO
    public static CountDownTimer startNormalCountDownTime(final long time, final TextView time_tv) {
        /**
         * 最简单的倒计时类，实现了官方的CountDownTimer类（没有特殊要求的话可以使用）
         * 即使退出activity，倒计时还能进行，因为是创建了后台的线程。
         * 有onTick，onFinsh、cancel和start方法
         */
        final CountDownTimer timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time_tv.setText("0" + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                time_tv.setText("00");
                /*if (time_tv.getText().toString().equals("00")){
                    startNormalCountDownTime(10 ,time_tv);
				}*/
            }
        };
        //timer.start();
        return timer;
    }

    /**
     * 强制关闭软键盘
     *
     * @param context 承接上下文
     * @param view    控件
     *///TODO
    public static final void CloseSoftKey(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
    }

    /**
     * 验证手机格式
     *///TODO
    public static final boolean isMobile(String number) {//TODO
        /*
		移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		联通：130、131、132、152、155、156、185、186
		电信：133、153、180、189、（1349卫通）
		总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		*/
        String num = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    /***
     * 获取控件高度
     *///TODO
    public static int getViewHeight(LinearLayout linearLayout) {
        int w = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0,
                View.MeasureSpec.UNSPECIFIED);
        linearLayout.measure(w, h);
        int height = linearLayout.getMeasuredHeight();
        return height;
    }

    /***
     * 获取控件宽度
     *///TODO
    public static int getViewWidth(View view) {
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(w, h);
        int width = view.getMeasuredWidth();
        return width;
    }

    /**
     * 图片质量压缩
     */
    public static Bitmap compressImage(Bitmap image) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 100) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    /**
     * bitmap转化为Base64 ---->通过Base32将Bitmap转换成Base64字符串
     *
     * @return
     *///TODO
    public static String Bitmap2StrByBase64(Context context, Uri imgUri) throws IOException {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), imgUri);
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                int options = 100;
                while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
                    baos.reset();//重置baos即清空baos
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
                    options -= 10;//每次都减少10
                }
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = "data:image/jpeg;base64," + Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * byte[]转化为Base64 ---->通过Base32将Bitmap转换成Base64字符串
     *
     * @return
     */
    public static String ByteArr2StrByBase64(byte[] bitmapBytes) throws IOException {

        String result = "data:image/jpeg;base64," + Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
        return result;
    }

    /**
     * str 已出价多少次
     * s  总出价次数
     */

    public static final int StringInt(String str, String s) {
        int num = 0;
        int zoongNum = Integer.valueOf(s);
        num = (100 / zoongNum) * Integer.valueOf(str);
        return num;
    }

    /**
     * String型转化为int 并判断数量
     *///TODO
    public static final String StringToInt(String str) {
        int num = Integer.valueOf(str);
        if (num == 0) {
            return "0";
        }
        if (num > 99) {
            return "99";
        }
        return str;
    }

    /**
     * 数量加减控制
     *///TODO
    public static void AddSub(Context context, Button view, String strNum, EditText editText) {
        if (strNum.equals("")) {
            strNum = "0";
        }
        int intNum = Integer.valueOf(strNum);
        if (view.getText().toString().equals("+")) {
            if (++intNum < 1) {  //先加，再判断
                intNum--;
                Util.showToast(context, "请输入一个大于等1的数字");
            } else {
                editText.setText(String.valueOf(intNum));
            }
        } else if (view.getText().toString().equals("-")) {
            if (--intNum < 1) {//先减，再判断
                intNum++;
                Util.showToast(context, "亲，不能再小了！");
            } else {
                editText.setText(String.valueOf(intNum));
            }
        }
    }

    /**
     * 日期比较
     */
    public static int compare_date(String DATE1, String DATE2) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = df.parse(DATE1);
        Date dt2 = df.parse(DATE2);
        if (dt1.getTime() > dt2.getTime()) {
            return 1;
        }
        return 0;
    }

    /**
     * 分割字符串
     */
    public static String DivisionString(String sourceStr) {
        String ereaName = "";
        String[] sourceStrArray = sourceStr.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            ereaName = sourceStrArray[i];
        }
        return ereaName;
    }

    /**
     * 分割字符串
     */
    public static List<String> ImgDivisionString(String sourceStr) {
        List<String> ereaName = new ArrayList<>();
        String[] sourceStrArray = sourceStr.split("&&");
        for (int i = 0; i < sourceStrArray.length; i++) {
            ereaName.add(sourceStrArray[i]);
        }
        return ereaName;
    }

    /**
     * 手机号验证
     */
    public static boolean isPhoneNumberValid(String phoneNumber) {
        boolean isValid = false;
        CharSequence inputStr = phoneNumber;
        //正则表达式
        String phone = "^1[34578]\\d{9}$";
        Pattern pattern = Pattern.compile(phone);
        Matcher matcher = pattern.matcher(inputStr);
        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     * 倒计时
     * millisInFuture 倒计时时长
     * countDownInterval 倒计时时间间隔
     * btnGetcode Button按钮
     * context 承接上下文
     */
    public static class TimeCount extends CountDownTimer {
        Button btnGetcode;
        Context context;

        public TimeCount(long millisInFuture, long countDownInterval, Button btnGetcode, Context context) {
            super(millisInFuture, countDownInterval);
            this.btnGetcode = btnGetcode;
            this.context = context;
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onTick(long millisUntilFinished) {
            btnGetcode.setBackgroundColor(context.getResources().getColor(R.color.aa_color));
            btnGetcode.setClickable(false);
            btnGetcode.setText(millisUntilFinished / 1000 + "秒");
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onFinish() {
            btnGetcode.setText("重新获取验证码");
            btnGetcode.setClickable(true);
            btnGetcode.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));

        }
    }

    public static String getLocalIpAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException ex) {
            LogUtils.e("WifiPreference IpAddress", ex.toString());
        }
        return null;
    }


    /*
     * 方法一：判断网络连接是否已开
     *true 已打开  false 未打开
     * */
    public static boolean isConn(Context context) {
        boolean bisConnFlag = false;
        ConnectivityManager conManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo network = conManager.getActiveNetworkInfo();
        if (network != null) {
            bisConnFlag = conManager.getActiveNetworkInfo().isAvailable();
        }
        return bisConnFlag;
    }

    /**
     * 判断连接的是否为无线网
     *
     * @param mContext
     * @return
     */
    public static boolean isWifi(Context mContext) {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }



    public static <T> T checkNotNull(T obj) {
        if (obj == null) {
            throw new NullPointerException();
        }
        return obj;
    }


}

