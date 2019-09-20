
    <uses-permission android:name="android.permission.CHANGE_WIFI_STATE"/>
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>

Switch wifiSwitch;
WifiManager wifiManager;
WifiReciver wifiReciver;
String CHANNEL_ID = "com.example.vtl.bt_broadcast";

createNotificationChannel();

wifiSwitch = findViewById(R.id.switch1);
wifiManager = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);

wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    wifiSwitch.setText("Wifi is ON");
                    wifiManager.setWifiEnabled(true);
                    CreateNotification("Wifi ON");
                } else {
                    wifiSwitch.setText("Wifi is OFF");
                    wifiManager.setWifiEnabled(false);
                    CreateNotification("Wifi OFF");
                }
            }
});

//
class WifiReciver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE,
                                                WifiManager.WIFI_STATE_UNKNOWN);
            switch (wifiState){
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setText("Wifi ON");
                    wifiSwitch.setChecked(true);
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setText("Wifi OFF");
                    wifiSwitch.setChecked(false);
                    break;
            }
        }
    }

@Override
protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION);
        wifiReciver = new WifiReciver();
        registerReceiver(wifiReciver,intentFilter);
}

@Override
protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiReciver);
}

public void CreateNotification(String content){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_network_wifi_black_24dp)
                    .setContentTitle("Check Wifi")
                    .setContentText(content)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
            notificationManager.notify(20, builder.build());

        } else {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.drawable.ic_network_wifi_black_24dp)
                    .setContentTitle("Check Wifi")
                    .setContentText(content)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);

// notificationId is a unique int for each notification that you must define
            notificationManager.notify(10, builder.build());

        }
}

//android >= 8
private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Notification";
            String description = "Create Notification";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
}