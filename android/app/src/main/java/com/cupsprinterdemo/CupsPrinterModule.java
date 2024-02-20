package com.cupsprinterdemo;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.google.gson.Gson;

import android.util.Log;

import org.cups4j.CupsClient;
import org.cups4j.CupsPrinter;
import org.cups4j.PrintJob;
import org.cups4j.PrintRequestResult;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

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
    public void connectPrinter(String url, String host, Promise promise) {
        CupsPrinter cupsPrinter = null;
        PrintRequestResult  result = null;
         try{
             CupsClient cupsClient = new CupsClient(host, 631);

            URL printerURL = new URL(url);
            cupsPrinter = cupsClient.getPrinter(printerURL);
            String zpl = "^XA" +
                    "^FO50,60^A0,40^FDWorld's Best Griddle^FS" +
                    "^FO60,120^BY3^BCN,60,,,,A^FD1234ABC^FS" +
                    "^FO25,25^GB380,200,2^FS" +
                    "^XZ";
             InputStream inputStream = new ByteArrayInputStream(zpl.getBytes(StandardCharsets.UTF_8));
             PrintJob printJob = new PrintJob.Builder(inputStream).build();
             result = cupsPrinter.print(printJob);

         } catch (SocketTimeoutException e) {
              promise.reject("socket time out" + e);
        } catch (Exception e) {
             promise.reject("error" + e);
         }

        promise.resolve(new Gson().toJson(result) );
    }
}