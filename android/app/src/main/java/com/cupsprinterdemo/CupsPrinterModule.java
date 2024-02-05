package com.cupsprinterdemo;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.util.Log;

import org.cups4j.CupsClient;
import org.cups4j.CupsPrinter;

import java.net.SocketTimeoutException;
import java.net.URL;

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


    @ReactMethod
    public void connectPrinter(String address, String url, Promise promise) {
        CupsPrinter cupsPrinter = null;
         try{
            CupsClient cupsClient = new CupsClient(address, 631);
            URL printerURL = new URL(url);
            cupsPrinter = cupsClient.getPrinter(printerURL);
        } catch (SocketTimeoutException e) {
              promise.reject("socket time out" + e);
        } catch (Exception e) {
             promise.reject( "error" + e);
        }
        promise.resolve( cupsPrinter + "hello");
    }
}