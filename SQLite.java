//database.db create for "DB Browser for SQLite"
//..create folder "assets" on "app" and cho file database.db vao do
public class DBHelper {
    String DATABASE_NAME = "qlsvDB.db";
    private static final String DB_PATH_SUFFIX = "/databases/";
    SQLiteDatabase db = null;

    Context context;

    public DBHelper(Context context) {
        this.context = context;

        processSQLite();
    }

    private void processSQLite() {
        File dbFile = context.getDatabasePath(DATABASE_NAME);
        if(!dbFile.exists()){
            try{
                CopyDatabaseFromAsset();
                Toast.makeText(context, "Copy successful !!!", Toast.LENGTH_SHORT).show();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try{
            InputStream databaseInputStream = context.getAssets().open(DATABASE_NAME);

            String outputStream = getPathDatabaseSystem();

            File file = new File(context.getApplicationInfo().dataDir + DB_PATH_SUFFIX);
            if(!file.exists()){
                file.mkdir();
            }

            OutputStream databaseOutputStream = new FileOutputStream(outputStream);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = databaseInputStream.read(buffer)) > 0){
                databaseOutputStream.write(buffer,0,length);
            }


            databaseOutputStream.flush();
            databaseOutputStream.close();
            databaseInputStream.close();

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private String getPathDatabaseSystem() {
        return context.getApplicationInfo().dataDir + DB_PATH_SUFFIX + DATABASE_NAME;
    }

    public ArrayList<Student> getAllStudent(){
        ArrayList<Student> arrayList = new ArrayList<>();

        db = context.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null);

        Cursor cursor = db.query("SinhVien",
                null,null,null,null,null,null);
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String mobile = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            Student student = new Student(id,name,mobile,image);
            arrayList.add(student);
        }

        return arrayList;
    }

    public void insertStudent(Student student){
        db = context.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",student.getName());
        contentValues.put("Mobile",student.getMobile());
        db.insert("SinhVien",null,contentValues);
    }

    public void deleteStudent(int studentId){
        db = context.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null);
        db.delete("SinhVien","ID="+ studentId,null);

    }

    public void updateStudent(int idStudentOld, Student newStudent){
        db = context.openOrCreateDatabase(DATABASE_NAME,Context.MODE_PRIVATE,null);

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name",newStudent.getName());
        contentValues.put("Mobile",newStudent.getMobile());
        db.update("SinhVien",contentValues,"ID=" + idStudentOld,null);
    }

}
//MainActivity
DBHelper db;
ArrayList<Student> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DBHelper(MainActivity.this);
        arrayList = db.getAllStudent();
    }