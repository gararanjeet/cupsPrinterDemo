package com.cupsprinterdemo;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.util.Log;

public class CupsPrinterModule extends ReactContextBaseJavaModule {
    CupsPrinterModule(ReactApplicationContext context) {
        super(context);
    }
    @Override
    public String getName() {
        return "CupsPrinterModule";
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location) {
        Log.d("CalendarModule", "Create event called with name: " + name
                + " and location: " + location);
    }




}