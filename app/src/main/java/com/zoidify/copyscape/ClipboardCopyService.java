package com.zoidify.copyscape;

/**
 * Created by gaurav on 04-02-2017.
 */

import android.app.Service;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class ClipboardCopyService extends Service {

    private final String tag = "[[ClipboardWatcherService]] ";
    private OnPrimaryClipChangedListener listener = new OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {
            performClipboardCheck();
        }
    };

    @Override
    public void onCreate() {
        ((ClipboardManager) getSystemService(CLIPBOARD_SERVICE)).addPrimaryClipChangedListener(listener);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        File folder = new File(ClipboardCacheFolderPath);
        // ClipboardCacheFolderPath is a predefined constant with the path
        // where the clipboard contents will be written

//        if (!folder.exists()) { folder.mkdir(); }
        ClipboardManager cb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void performClipboardCheck() {
        ClipboardManager cb = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        if (cb.hasPrimaryClip()) {
            ClipData cd = cb.getPrimaryClip();
            try {
//                    File folder = new File(ClipboardCacheFolderPath);
//                    if (!folder.exists()) { folder.mkdir(); }
//                    Calendar cal = Calendar.getInstance();
//                    String newCachedClip =
//                            cal.get(Calendar.YEAR) + "-" +
//                                    cal.get(Calendar.MONTH) + "-" +
//                                    cal.get(Calendar.DAY_OF_MONTH) + "-" +
//                                    cal.get(Calendar.HOUR_OF_DAY) + "-" +
//                                    cal.get(Calendar.MINUTE) + "-" +
//                                    cal.get(Calendar.SECOND);
//
//                    // The name of the file acts as the timestamp (ingenious, uh?)
//                    File file = new File(ClipboardCacheFolderPath + newCachedClip);
//                    file.createNewFile();
//                    BufferedWriter bWriter = new BufferedWriter(new FileWriter(file));
//                    bWriter.write((cd.getItemAt(0).getText()).toString());
//                    bWriter.close();
                String clippedString = cd.getItemAt(0).getText().toString();
                String regex = "[0-9]+";
                String category = "Generic";
                if(clippedString.matches(regex)) {
                    category = "Contact";
                }
//                cd.getDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN) For future segregation
                if(clippedString.startsWith("http")) {
                    category = "URL";
                }
                CopyDBHelper dbHelper = new CopyDBHelper(this);
                dbHelper.insertItem(category, clippedString, false);
                Log.d("COPY SERVICE", "Clipped Item: " + clippedString);

            }
                catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }
}
