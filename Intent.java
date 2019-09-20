//Explicit Intent
//Main2Activity
Button btn;
EditText edt1,edt2;

btn = findViewById(R.id.button3);
edt1 = findViewById(R.id.editText);
edt2 = findViewById(R.id.editText2);

btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
        intent.putExtra("username",edt1.getText().toString());
        intent.putExtra("password",edt2.getText().toString());
        startActivity(intent);
        Main2Activity.this.finish();//yes or no
    }
});

//Main3Activity
Intent intent = getIntent();
Toast.makeText(this, intent.getStringExtra("username"), Toast.LENGTH_SHORT).show();

//Implicit Intent
Button btn;
btn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
        startActivity(intent);
    }
});

//Intent : browser, sendEmail, Camera, Recorder, ...