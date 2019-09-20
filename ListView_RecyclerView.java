ListView listView;
RecyclerView recyclerView;
ArrayList<MyModel> arrayList;//data
ArrayAdapter arrayAdapter;//ListView default
MyAdapter myAdapter;//customListView
My1Adapter my1Adapter;//recycler


listView = findViewById(R.id.listView);
recyclerView = findViewById(R.id.recycleView);
arrayList = new ArrayList<>();
arrayList.add(new MyModel(1,"tien","24 tuoi"));
arrayList.add(new MyModel(2,"le","24 age"));

arrayAdapter = new ArrayAdapter(TonghopActivity.this,
                                android.R.layout.simple_list_item_1,
                                arrayList);
listView.setAdapter(arrayAdapter);

//custom listview
myAdapter = new MyAdapter(TonghopActivity.this,
                                    R.layout.item_layout,
                                    arrayList);
listView.setAdapter(myAdapter);

//recyclerView
my1Adapter = new MyAdapter(TonghopActivity.this,
                                    arrayList,
                                    R.layout.item_layout
                                    );
recyclerView.setLayoutManager(new LinearLayoutManager(TonghopActivity.this,
                                                    LinearLayoutManager.VERTICAL,
                                                    false));
recyclerView.setAdapter(my1Adapter);


//class MyModel.java
public class MyModel {
    int stt;
    String name;
    String age;

    public MyModel(int stt, String name, String age) {
        this.stt = stt;
        this.name = name;
        this.age = age;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyModel{" +
                "stt=" + stt +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}

//class MyAdapter.java
public class MyAdapter extends ArrayAdapter<MyModel> {

    Context context;
    int layout;
    ArrayList<MyModel> arrayList;

    public MyAdapter(@NonNull Context context, int resource, ArrayList<MyModel> arrayList) {
        super(context, resource, arrayList);
        this.context = context;
        this.layout = resource;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(layout, null);

       TextView txt1 = convertView.findViewById(R.id.textView); //id textView from layout.xml
       txt1.setText(arrayList.get(position).getStt());

        return convertView;
    }
}


//class My1Adapter.java
public class My1Adapter extends RecyclerView.Adapter<My1Adapter.MyViewHolder>{

    Context context;
    ArrayList<MyModel> arrayList;
    int layout;

    public My1Adapter(Context context, ArrayList<MyModel> arrayList, int layout) {
        this.context = context;
        this.arrayList = arrayList;
        this.layout = layout;
    }

    @NonNull
    @Override
    public My1Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(layout, viewGroup,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull My1Adapter.MyViewHolder myViewHolder, int i) {
        MyModel myModel = arrayList.get(i);
        myViewHolder.txt1.setText(myModel.getName());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txt1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt1 = itemView.findViewById(R.id.textView2); //from item_layout.xml
        }
    }
}
