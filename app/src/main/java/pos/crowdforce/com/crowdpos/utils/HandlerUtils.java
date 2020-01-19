package pos.crowdforce.com.crowdpos.utils;


import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public final class HandlerUtils {

    private static Map<String, TaskDetails> tasks = new HashMap<>();
    private static String ANY_TAG = "ANY_TASK";
    private static String TAG = HandlerUtils.class.getSimpleName();

    public static void postDelayedTask(Runnable task, long delay) {
        postDelayedTask(task, ANY_TAG, delay);
    }

    private HandlerUtils() {
    }

    public static void postDelayedTask(Runnable task, String tag, long delay) {
        if (TextUtils.isEmpty(tag)) {
            return;
        }

        Handler handler = new Handler();
        tasks.put(tag, new TaskDetails(handler, task));

        handler.postDelayed(task, delay);
    }

    public static void cancelDelayedTask(String tag) {
        if (TextUtils.isEmpty(tag)) {
            return;
        }
        if (!tasks.containsKey(tag)) {
            //THIS SHOULD NOT HAPPEN!!
            Log.e(TAG, "Found no task details for tag " + tag);
            return;
        }
        TaskDetails taskDetails = tasks.get(tag);
        if (taskDetails.task == null) {
            return;
        }

        Log.e(TAG, "Removing callback with tag " + tag);
        taskDetails.handler.removeCallbacks(taskDetails.task);
    }

    public static void clean() {
        Log.e(TAG, "Cleaning up handler tasks store");
        tasks.clear();
    }

    private static class TaskDetails {
        Handler handler;
        Runnable task;

        public TaskDetails(Handler handler, Runnable task) {
            this.handler = handler;
            this.task = task;
        }
    }
}