package com.andreock.plugins.esptouch;

import android.content.Context;
import android.util.Log;

import com.espressif.iot.esptouch2.provision.EspProvisioner;
import com.espressif.iot.esptouch2.provision.EspProvisioningListener;
import com.espressif.iot.esptouch2.provision.EspProvisioningRequest;
import com.espressif.iot.esptouch2.provision.EspProvisioningResult;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "EsptouchCapacitorPlugin")
public class CapacitorEsptouchPlugin extends Plugin {

    private final CapacitorEsptouch implementation = new CapacitorEsptouch();
    private final String TAG = "CapacitorEsptouch";

    @PluginMethod
    public void EsptouchV2(PluginCall call) throws InterruptedException {
        Context context = getContext();
        EspProvisioner provisioner = new EspProvisioner(context);
        implementation.syncPacket(provisioner);

        String ssid = call.getString("ssid");
        String bssid = call.getString("bssid");
        String password = call.getString("password");
        String customData = call.getString("custom_data");

        assert bssid != null;

        EspProvisioningRequest.Builder requestBuilder = new EspProvisioningRequest.Builder(context);

        requestBuilder.setBSSID(implementation.convertBssid2Bytes(bssid)); // AP's BSSID, nonnull

        if(ssid != null) {
            requestBuilder.setSSID(ssid.getBytes()); // AP's SSID, nullable
        }

        if(password != null){
            requestBuilder.setPassword(password.getBytes()); // AP's password, nullable if the AP is open
        }

        if(customData != null) {
            requestBuilder.setReservedData(customData.getBytes()); // User's custom data, nullable. If not null, the max length is 64
        }

        EspProvisioningRequest request = requestBuilder.build();

        EspProvisioningListener listener = new EspProvisioningListener() {
            @Override
            public void onStart() {
                Log.i(TAG, "Starting provisioning");
            }

            @Override
            public void onResponse(EspProvisioningResult result) {
                Log.i(TAG, "Response from esp");
                JSObject ret = new JSObject();
                ret.put("result", result);
                ret.put("error", "{}"); // No error occurred
                call.resolve(ret);
                provisioner.stopProvisioning();
            }

            @Override
            public void onStop() {
                Log.i(TAG, "Stopping provisioning");
            }

            @Override
            public void onError(Exception e) {
                Log.e(TAG, "Error during provisioning: ");
                e.printStackTrace();
                JSObject ret = new JSObject();
                ret.put("result", "{}");
                ret.put("error", e.getMessage());   // Error occurred
                call.resolve(ret);
                provisioner.stopProvisioning();
            }
        };
        provisioner.startProvisioning(request, listener); // request is nonnull, listener is nullable
    }
}
