package com.test.work.library.Common;

import android.animation.Animator;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;


public class CommonFunction {

    private static Context context;

    public static ProgressDialog loadingDialog;


    public static void Log(Object objname, String msg) {
        Class currentClass = objname.getClass();
        Log.v("Carpe", "[" + currentClass.getSimpleName() + "]:" + msg);
    }


    /**
     * 電子公文 ErrorLog
     *
     * @param msg
     */
    public static void ErrorLog(Object objname, String msg) {
        Class currentClass = objname.getClass();
        Log.v("CarpeError", "[" + currentClass.getSimpleName() + "]:" + msg);
    }


    /*
    --------------Function------------------

     */

    /**
     * show AlertMessage
     *
     * @param
     * @param result
     */
    public static void showMsgConfirm(Context context, String result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提醒").
                setMessage(result)
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * get Device Name
     *
     * @return
     */
    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }


    /**
     * 取得系統當前時間
     * yyyy-MM-dd hh:mm:ss
     *
     * @return
     */
    public static String getCurrentSysDate() {
        String date = "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        date = simpleDateFormat.format(new java.util.Date());
        return date;
    }


    /**
     * 取得App版本資訊
     *
     * @param context
     * @return
     */
    public static String getAppVersion(Context context) {
        String versionName = "";
        try {
            if (context != null) {
                versionName = context.getPackageManager().getPackageInfo
                        (context.getPackageName(), 0).versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.v("SKLlibrary", e.toString());
        }
        return versionName;
    }

    private static String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }
    }

    /**
     * 取得當前系統得HomePackage Name
     *
     * @param context
     * @return
     */
    public static String getOSHomePackage(Context context) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);

        ResolveInfo resolveInfo = context.getPackageManager().resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo != null && resolveInfo.activityInfo != null && resolveInfo.activityInfo.packageName != null) {
            return resolveInfo.activityInfo.packageName;
        }
        return context.getPackageName();
    }

    public enum AnimationType {
        Slide_Bottom, Slide_Left, Slide_Right, Slide_Top
    }

//    /**
//     * 切換Activity動畫
//     *
//     * @param srcActivity
//     * @param cls
//     * @param type
//     */
//    public static void startNewActivity(Activity srcActivity, Class<?> cls, AnimationType type) {
//        ActivityOptionsCompat options = null;
//        //context.getResources().getIdentifier("settings", "id", "com.skl.appteam.skllibary");
//        switch (type) {
//            case Slide_Left:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_left_in, R.anim.slide_left_out);
//                break;
//            case Slide_Right:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_right_in, R.anim.slide_right_out);
//                break;
//            case Slide_Bottom:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_bottom_in, R.anim.slide_bottom_out);
//                break;
//            case Slide_Top:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_top_in, R.anim.slide_top_out);
//                break;
//            default:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_bottom_in, R.anim.slide_bottom_out);
//                break;
//        }
//
//        if (options != null) {
//            Intent intent = new Intent(srcActivity, cls);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            ActivityCompat.startActivity(srcActivity, intent, options.toBundle());
//        }
//    }

    /**
     * 切換Activity動畫
     *
     * @param srcActivity
     * @param cls
     * @param type
     */
//    public static void startNewActivity(Activity srcActivity, Class<?> cls, AnimationType type, Bundle bundle) {
//        ActivityOptionsCompat options = null;
//
//        switch (type) {
//            case Slide_Left:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_left_in, R.anim.slide_left_out);
//                break;
//            case Slide_Right:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_right_in, R.anim.slide_right_out);
//                break;
//            case Slide_Bottom:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_bottom_in, R.anim.slide_bottom_out);
//                break;
//            case Slide_Top:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_top_in, R.anim.slide_top_out);
//                break;
//            default:
//                options =
//                        ActivityOptionsCompat.makeCustomAnimation(srcActivity,
//                                R.anim.slide_bottom_in, R.anim.slide_bottom_out);
//                break;
//        }
//
//        if (options != null) {
//            Intent intent = new Intent(srcActivity, cls);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            if (bundle != null) {
//                intent.putExtras(bundle);
//            }
//            ActivityCompat.startActivity(srcActivity, intent, options.toBundle());
//        }
//    }

//    /**
//     * 20141119 add by timkao
//     * <p/>
//     * 在畫面顯示進度條視窗
//     *
//     * @return void
//     */
//    public static void showLoadingScene(Context context, String msg) {
//        if (loadingDialog != null) {
//            loadingDialog.dismiss();
//            loadingDialog = null;
//        }
//        loadingDialog = new ProgressDialog(context, msg);
//        loadingDialog.show();
//    }

    /**
     *
     * <p/>
     * 在畫面顯示載入視窗
     *
     * @return void
     */

    public static void showLoadingScene(Context context) {
        if (context != null) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }

            loadingDialog = new ProgressDialog(context);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setMessage("載入中....");
            loadingDialog.setIndeterminate(true);
            loadingDialog.show();
        }
    }

    /**
     * 在畫面顯示載入視窗
     *
     * @param context
     * @param msg
     */
    public static void showLoadingScene(Context context, String msg) {
        if (context != null && msg != null) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }

            loadingDialog = new ProgressDialog(context);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setMessage(msg);
            loadingDialog.setIndeterminate(true);
            loadingDialog.show();
        }
    }

    /**
     * 在畫面顯示載入視窗
     *
     * @param context
     * @param msg
     */
    public static void showLoadingScene(Context context, String title, String msg) {
        if (context != null && msg != null && title != null) {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
                loadingDialog = null;
            }

            loadingDialog = new ProgressDialog(context);
            loadingDialog.setCancelable(true);
            loadingDialog.setCanceledOnTouchOutside(false);
            loadingDialog.setTitle(title);
            loadingDialog.setMessage(msg);
            loadingDialog.setIndeterminate(true);
            loadingDialog.show();
        }
    }


    /**
     *
     * <p/>
     * 關閉畫面的載入視窗
     *
     * @return a string type ArrayList.
     */

    public static void closeLoadingScene(Context context) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }

    /**
     * 2015.04.17 timkadd
     * value null 檢查 是的話回傳空值
     *
     * @param
     * @return
     */
    public static String isNullCheck(String value) {
        return value == null ? "" : value;
    }


    /**
     * 根據回傳header parmMap回傳 一層的xml
     *
     * @param parmMap
     * @return
     */
    public static String composeSingleXMLData(String header, HashMap<String, String> parmMap) {
        StringBuilder sb = new StringBuilder();
        // header
        sb.append("<" + header + ">");
        Iterator iterator = parmMap.entrySet().iterator();
        while (iterator.hasNext()) {
            HashMap.Entry entry = (HashMap.Entry) iterator.next();
            String key = entry.getKey().toString();
            String value = entry.getValue().toString();
            sb.append("<" + key + ">");
            sb.append(value);
            sb.append("</" + key + ">");
        }
        // header
        sb.append("</" + header + ">");
        return sb.toString();
    }

    private void getHttpImg() {
        //        new AsyncTask<Void, Void, Void>() {
//            @Override
//            protected Void doInBackground(Void... voids) {
//                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
//                CommonFunction.showLoadingScene(loginPage);
//                try {
//                    URL imageURL = new URL(Configuration.VerifyImgURL);
//                    HttpURLConnection connection = (HttpURLConnection) imageURL
//                            .openConnection();
//                    connection.setDoInput(true);
//                    connection.connect();
//
//                    int statusCode = connection.getResponseCode();
//
//                    if (statusCode == 200) {
//                        InputStream inputStream = connection.getInputStream();
//                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);// Convert to bitmap
//                        verifyImg.setImageBitmap(bitmap);
//                    }
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(Void aVoid) {
//                CommonFunction.closeLoadingScene(loginPage);
//            }
//        }.execute();
    }

    /**
     * 造訪HashMap<String,String>
     *
     * @param map
     * @return
     */
    public static String iteratorHashMap(HashMap<String, String> map) {
        String result = "";
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            result += (key + "=" + value + "\n");
        }
        return result;
    }

    /**
     * 日期的比較
     * 回傳差異天數
     *
     * @param dateStart
     * @param dateStop
     * @return
     */
    public static long compareDateByDay(String dateStart, String dateStop) {
        Date d1;
        Date d2;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            //in milliseconds
            long diff = d2.getTime() - d1.getTime();
            long diffDays = diff / (24 * 60 * 60 * 1000);

            return diffDays;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 日期的比較
     * 回傳差異秒數
     *
     * @param dateStart
     * @param dateStop
     * @return
     */
    public static long compareDateBySecond(String dateStart, String dateStop) {
        Date d1;
        Date d2;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        try {
            d1 = format.parse(dateStart);
            d2 = format.parse(dateStop);
            //in milliseconds
            long diff = d2.getTime() - d1.getTime();
            //long diffDays = diff / (24 * 60 * 60 * 1000);
            return diff;
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * 檢查網路狀態
     *
     * @param context
     * @return
     */
    public static boolean checkNetWork(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null) {
            return false;
        }
        if (!netInfo.isAvailable()) {
            return false;
        }
        if (!netInfo.isConnected()) {
            return false;
        }
        if (!netInfo.isConnectedOrConnecting()) {
            return false;
        }
        return true;
    }

    /**
     * set View 高寬
     *
     * @param view
     * @param height
     * @param width
     */
    public static void setViewSize(View view, int height, int width) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        lp.width = width;
        view.setLayoutParams(lp);
    }

    /**
     * 讓View出現點擊特效
     *
     * @param view
     */
    public static void setViewCircularReveal(View view) {
        // get the center for the clipping circle
        int cx = view.getWidth() / 2;
        int cy = view.getHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(view.getWidth(), view.getHeight());

//        // create the animator for this view (the start radius is zero)
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius);
            // make the view visible and start the animation
            view.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            //Animation anim = AnimationUtils.loadAnimation(mainPage, R.anim.click_blink);
            //view.startAnimation(anim);
        }
    }

    /**
     * 變色的動畫 from > to
     *
     * @param view
     * @param fromColor
     * @param toColor
     * @param duration
     */
    public static void ColorTransitionAnim(View view, int fromColor, int toColor, int duration) {
        ColorDrawable[] color = {new ColorDrawable(fromColor), new ColorDrawable(toColor)};
        TransitionDrawable trans = new TransitionDrawable(color);
        view.setBackgroundDrawable(trans);
        trans.startTransition(duration);
    }

    /**
     * 讓鍵盤收下method
     *
     * @param context
     * @param view
     */
    public static void keyBoardDisappear(Context context, View view) {
        // 鍵盤會收下
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
