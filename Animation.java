//create folder anim on main/res, create file animation .xml
ImageView imageView;
Button btn, btn2, btn3, btn4, btn5, btn6;

imageView = findViewById(R.id.imageView);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(MainActivity.this,
                                                                                R.anim.blinking);
                imageView.startAnimation(alphaAnimation);
            }
        });

        btn2 = findViewById(R.id.button2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.scale);
                imageView.startAnimation(animation);
            }
        });

        btn3 = findViewById(R.id.button3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.translate);
                imageView.startAnimation(animation);
            }
        });

        btn4 = findViewById(R.id.button4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.rotate);
                imageView.startAnimation(animation);
            }
        });

        btn5 = findViewById(R.id.button5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.mix);
                imageView.startAnimation(animation);
            }
        });

        btn6 = findViewById(R.id.button6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.sequence);
                imageView.startAnimation(animation);
            }
        });

//blinking.xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <alpha android:fromAlpha="1.0"
        android:toAlpha="0.1"
        android:duration="600"
        android:repeatMode="reverse"
        android:repeatCount="infinite">
    </alpha>

</set>
//mix.xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <alpha android:fromAlpha="1.0"
        android:toAlpha="0.1"
        android:duration="1000"
        android:repeatMode="reverse"
        android:repeatCount="100">
    </alpha>
    <rotate android:toDegrees="0"
        android:fromDegrees="360"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="1000"
        android:repeatCount="100"
        android:repeatMode="reverse">

    </rotate>
    <scale android:toXScale="0.1"
        android:fromXScale="1.0"
        android:toYScale="0.1"
        android:fromYScale="1.0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:duration="1000"
        android:repeatCount="100"
        android:repeatMode="reverse">

    </scale>
    <translate android:toXDelta="-100%p"
        android:fromXDelta="50%p"
        android:duration="1000"
        android:repeatCount="100"
        android:repeatMode="reverse">

    </translate>
</set>
//translate.xml
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">
    <translate android:toXDelta="-100%p"
        android:fromXDelta="50%p"
        android:duration="1000"
        android:repeatCount="10"
        android:repeatMode="reverse">

    </translate>
</set>