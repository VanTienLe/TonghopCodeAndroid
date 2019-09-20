//Toast
Toast.makeText(MainActivity.this, "Day la toast", Toast.LENGTH_SHORT).show();

//AlertDialog
AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Tieu de Dialog");
        builder.setIcon(R.drawable.ic_launcher_background);
        builder.setMessage("Day la Alert Dialog");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


//CustomDialog
btn3.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        CustomDialog customDialog = new CustomDialog(MainActivity.this);
        customDialog.show();
    }
});
//class CustomDialog.java
public class CustomDialog extends Dialog {
    Button btn1, btn2;
    Context context;
    public CustomDialog( final Activity context) {
        super(context);
        setContentView(R.layout.dialog_custom);
        this.context = context;

        btn1 = findViewById(R.id.button4);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.finish();
            }
        });

        btn2 = findViewById(R.id.button5);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel(); //dismiss;
            }
        });

    }
}