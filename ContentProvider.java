
<uses-permission android:name="android.permission.READ_CONTACTS"></uses-permission>

ListView listView;
ArrayList<DanhBa> arrayList;
ArrayAdapter arrayAdapter;

listView = findViewById(R.id.listView);
arrayList = new ArrayList<>();
arrayAdapter = new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,arrayList);
listView.setAdapter(arrayAdapter);
checkPermissions();

public void checkPermissions(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {ReadContactContentProvider();}
    }

@Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                checkPermissions();
            }else {
                Toast.makeText(this, "Huong dan cai dat: Setting -> ...", Toast.LENGTH_SHORT).show();
            }
        }
    }

public void ReadContactContentProvider(){
        Uri uri = ContactsContract.Contacts.CONTENT_URI;

        ContentResolver cr = getContentResolver();

        Cursor cursor = cr.query(uri,null,null,null,null);

        while (cursor.moveToNext()){
            DanhBa danhBa = new DanhBa();

            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            danhBa.setName(name);
            String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            Uri uri1 = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
            Cursor cursor1 = getContentResolver().query(uri1,null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=" + id,
                    null,
                    null);
            ArrayList<String> numberPhone = new ArrayList<>();
            while (cursor1.moveToNext()){
                String phone = cursor1.getString(cursor1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                numberPhone.add(phone);
            }
            danhBa.setSdt(numberPhone);
            arrayList.add(danhBa);
        }
        arrayAdapter.notifyDataSetChanged();
    }


//Provider Media Image
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>

public ArrayList<Model> ReadImageContentProvder(){
        Uri uri = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(uri,null,null,null,null);
        ArrayList<Model> arrayList1 = new ArrayList<>();
        while (cursor.moveToNext()){
            String duongDanHinhAnh = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            String tenHinhAnh = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME));
            Model imageModel = new Model(tenHinhAnh,duongDanHinhAnh);
            arrayList1.add(imageModel);
        }
        return arrayList1;

    }

public ArrayList<Model> ReadImageSD() {
            ArrayList<Model> arrayList1 = new ArrayList<>();
            ContentResolver contentResolver = getContentResolver();
            Cursor cursor = contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    new String[]{MediaStore.Images.Media.DATA}, // Which columns to return
                    null,       // Return all rows
                    null,
                    null);

                while (cursor.moveToNext()) {

                    int file_ColumnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    String path = cursor.getString(file_ColumnIndex);
                    String fileName = path.substring(path.lastIndexOf("/") + 1, path.length());
                    Model imageModel = new Model(fileName,path);
                    arrayList1.add(imageModel);

                }
                return  arrayList1;
    }

//Provider Lat/Lon from GPS and Network
//GPS
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
<uses-permission android:name="android.permission.INTERNET"/>

//open GPS (->fix)
import android.location.Location;
import android.location.LocationManager;

public void GetLocationGPS() {
        LocationManager lm = (LocationManager) getSystemService(MainActivity.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        double lat = location.getLatitude();
        double lon = location.getLongitude();
        txt1.setText(lat+"" + "  " + lon+"");
    }
//Network ....{}