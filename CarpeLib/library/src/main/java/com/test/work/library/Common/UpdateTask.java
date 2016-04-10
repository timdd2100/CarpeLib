package com.test.work.library.Common;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.ContextThemeWrapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * 自動下載.apk作業 於下載成功後自動開啟安裝畫面
 * 於.excute時傳入Url參數
 */
public class UpdateTask extends AsyncTask<String, Integer, Void> {

    private Context context;

    private String PATH;

    private int dataReadCount = 0;

    private int iFileSize = 0;

    private ProgressDialog progressDialog;


    private String update_FileName = "update.apk";

    public UpdateTask(Context m_context) {
        context = m_context;
    }

    public void setContext(Context contextf) {
        context = contextf;
    }

    public void setUpdate_FileName(String update_FileName) {
        this.update_FileName = update_FileName;
    }

    @Override
    protected void onPreExecute() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            progressDialog = new ProgressDialog(new ContextThemeWrapper(context,
                    android.R.style.Theme_Holo_Light_Dialog));
        } else {
            progressDialog = new ProgressDialog(context);
        }

        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("下載更新中...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(false);
        progressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cancel(true);
            }
        });
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            URL url = new URL(params[0]);
            HttpsURLConnection c = (HttpsURLConnection) url.openConnection();
            c.setDoInput(true);
            c.connect();
            if (c.getResponseCode() == 200) {
                PATH = Environment.getExternalStorageDirectory() + "/download/";
                File file = new File(PATH);
                file.mkdirs();
                File outputFile = new File(file, update_FileName);
                if (outputFile.exists()) {
                    outputFile.delete();
                }
                FileOutputStream fos = new FileOutputStream(outputFile);

                InputStream is = c.getInputStream();
                iFileSize = c.getContentLength();    //取得檔案大小

                byte[] buffer = new byte[1024];
                int len1 = 0;
                while ((len1 = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len1);
                    dataReadCount += len1;
                    publishProgress((int) (dataReadCount * 100) / iFileSize);
                }
                fos.close();
                is.close();

                c.disconnect();
            }
        } catch (Exception e) {
            Log.e("UpdateAPP", "Update error! " + e.getMessage());
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void aVoid) {
        progressDialog.dismiss();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(PATH + update_FileName)), "application/vnd.android.package-archive");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // without this flag android returned a intent error!
        context.startActivity(intent);

        Activity activity = (Activity) context;
        activity.finish();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressDialog.setProgress(values[0]);
    }
}
