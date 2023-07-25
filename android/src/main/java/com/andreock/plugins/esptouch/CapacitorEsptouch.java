package com.andreock.plugins.esptouch;

import com.espressif.iot.esptouch2.provision.EspProvisioner;
import com.espressif.iot.esptouch2.provision.EspSyncListener;

public class CapacitorEsptouch {
    void syncPacket(EspProvisioner provisioner) throws InterruptedException {
        EspSyncListener listener = new EspSyncListener() {
            @Override
            public void onStart() {
            }

            @Override
            public void onStop() {
            }

            @Override
            public void onError(Exception e) {
            }
        };
        provisioner.startSync(listener); // listener is nullable
        Thread.sleep(1000); // Sync for 1 sec
        provisioner.stopSync();
    }

    /**
     * From original ESPTouch app: https://github.com/EspressifApp/EsptouchForAndroid/blob/master/esptouch-v2/src/main/java/com/espressif/iot/esptouch2/provision/TouchNetUtil.java#L133C1-L148C1
     * @param bssid the bssid like aa:bb:cc:dd:ee:ff
     * @return byte array converted from bssid
     */
    public byte[] convertBssid2Bytes(String bssid) {
        String[] bssidSplits = bssid.split(":");
        if (bssidSplits.length != 6) {
            throw new IllegalArgumentException("Invalid bssid format");
        }
        byte[] result = new byte[bssidSplits.length];
        for (int i = 0; i < bssidSplits.length; i++) {
            result[i] = (byte) Integer.parseInt(bssidSplits[i], 16);
        }
        return result;
    }

}
