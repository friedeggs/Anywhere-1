package com.hackwestern.anywhere;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;
import android.net.wifi.p2p.WifiP2pDevice;

/**
 * Created by Frieda on 2015-03-28.
 */
public class Receiver extends BroadcastReceiver{
    MainActivity activity;
    WifiP2pManager manager;
    WifiP2pManager.Channel channel;

    public Receiver(WifiP2pManager manager, WifiP2pManager.Channel channel, MainActivity activity){
        super();
        this.activity = activity;
        this.channel = channel;
        this.manager = manager;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            // Determine if Wifi P2P mode is enabled or not, alert
            // the Activity.
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                activity.setIsWifiP2pEnabled(true);
            } else {
                activity.setIsWifiP2pEnabled(false);
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {

            Log.d("TAG", "receiver");
            Log.d("TAG", "ID " + R.id.my_name);
            manager.requestPeers(channel, (WifiP2pManager.PeerListListener) activity.getFragmentManager()
                    .findFragmentById(R.id.frag_list));

        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

        }
    }
}
