/*
The MIT License (MIT)

Copyright (c) 2014

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package net.hoodalabs.cordova.plugins.touchevent;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

import android.os.SystemClock;
import android.view.InputDevice;
import android.view.MotionEvent;

public class HoodalabsTouchEvent extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {

        if ("fireAt".equals(action)) {

            final float x = args.getInt(0);
            final float y = args.getInt(1);

            // List of meta states found here: developer.android.com/reference/android/view/KeyEvent.html#getMetaState()
            final int metaState = 0;

            final long timestamp = SystemClock.uptimeMillis();

            MotionEvent touchStart = MotionEvent.obtain(
                    timestamp,
                    timestamp + 50,
                    MotionEvent.ACTION_DOWN,
                    x,
                    y,
                    metaState
            );
            touchStart.setSource(InputDevice.SOURCE_TOUCHSCREEN);

            webView.dispatchTouchEvent(touchStart);

            MotionEvent touchEnd = MotionEvent.obtain(
                    timestamp+50,
                    timestamp+100,
                    MotionEvent.ACTION_UP,
                    x,
                    y,
                    metaState
            );
            touchEnd.setSource(InputDevice.SOURCE_TOUCHSCREEN);

            webView.dispatchTouchEvent(touchEnd);


            callbackContext.success(); // Thread-safe.
            return true;
        }

        return false;
    }

}
