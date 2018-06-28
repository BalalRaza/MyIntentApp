package com.example.figg_user.myapplication;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Intent;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class MyAccessibilityService extends AccessibilityService {
    private String TAG = "TESTING";

    private AccessibilityNodeInfo getListItemNodeInfo(AccessibilityNodeInfo source) {
        AccessibilityNodeInfo current = source;

        Log.d(TAG, "CURRENT");
        Log.d(TAG, source.toString());
        CharSequence text = source.getText();

        if (text != null) {
            Log.d(TAG, source.getText().toString());

            if (source.getText().toString().equals("CREATE NEW FACEBOOK ACCOUNT")) {
                Log.d(TAG, Boolean.toString(current.isClickable()) + " Clickcable");
                Log.d(TAG, "CLICKING NOW..................");
                boolean action = source.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                Log.d(TAG, Boolean.toString(action));
            }
        }
        while (true) {
            AccessibilityNodeInfo parent = current.getParent();
            if (parent == null) {
                Log.d(TAG, "parent is null");
                return null;
            }

            Log.d(TAG, parent.toString());
            CharSequence className = parent.getClassName();
            if (className != null) {
                Log.d(TAG, className.toString());
            }


            // if (TASK_LIST_VIEW_CLASS_NAME.equals(parent.getClassName())) {
            //     return current;
            //}

            // NOTE: Recycle the infos.
            AccessibilityNodeInfo oldCurrent = current;
            current = parent;
            oldCurrent.recycle();
        }
    }

    private void printChildren(AccessibilityNodeInfo source, int depth) {
        if (source == null) {
            return;
        }

        for (int i = 0; i < source.getChildCount(); i++) {
            AccessibilityNodeInfo child = source.getChild(i);

            if (child != null) {
                CharSequence text = child.getText();
                CharSequence className = child.getClassName();

                if (text == null) {
                    text = "";
                }

                if (className == null) {
                    className = "";
                }

                Log.d(TAG, className.toString() + " " + text.toString() + " " +
                        child.isClickable() + " " + depth);
                Log.d(TAG, child.toString());

                recursivelyPrintChildren(child, depth + 1);
            }
        }
    }

    private void recursivelyPrintChildren(AccessibilityNodeInfo source, int depth) {
        if (depth > 3) {
            return;
        }

        printChildren(source, depth);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, Integer.toString(event.getEventType()));

        CharSequence description = event.getContentDescription();
        if (description != null) {
            Log.d(TAG, event.getContentDescription().toString());
        }

        AccessibilityNodeInfo source = event.getSource();

        if (source == null) {
            Log.d(TAG, "source is null");
            return;
        }
        Log.d(TAG, "SOURCE");
        Log.d(TAG, source.getClassName().toString());

        recursivelyPrintChildren(source, 0);

        //getListItemNodeInfo(source);
    }

    @Override
    public void onInterrupt() {
        Log.d("TESTING", "onInterrupt");
    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        Log.d(TAG, this.getServiceInfo().toString());

        Log.d(TAG, "SERVICE CONNECTED");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "UNBIND");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "DESTROYED");
        super.onDestroy();
    }
}
