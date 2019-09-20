TextView txt1,txt2;
RecyclerView recyclerView;
ArrayList<Model> arrayList;
MyAdapter myAdapter;
String link = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

txt1 = findViewById(R.id.textView);
txt2 = findViewById(R.id.textView2);
recyclerView = findViewById(R.id.recyclerView);
arrayList = new ArrayList<>();
myAdapter = new MyAdapter(MainActivity.this,arrayList,R.layout.layout_item);
recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL,false));
recyclerView.setAdapter(myAdapter);

getJson getJson = new getJson(MainActivity.this);
getJson.execute(link);

//
class getJson extends AsyncTask<String,Void,ArrayList<Model>> {
        private ProgressDialog dialog;

        public getJson(MainActivity activity) {
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("get data from internet...");
            dialog.show();
        }

        @Override
        protected ArrayList<Model> doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openStream();

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                StringBuilder stringBuilder = new StringBuilder();

                while (line != null){
                    stringBuilder.append(line);
                    line = bufferedReader.readLine();
                }

                ArrayList<Model> arrayList1 = new ArrayList<>();
                JSONObject jsonObject = new JSONObject(stringBuilder.toString());
                JSONObject jsonObjectMetadata = jsonObject.getJSONObject("metadata");
                String urlString = jsonObjectMetadata.getString("url");
                String titleString = jsonObjectMetadata.getString("title");

                JSONArray jsonArrayFeatures = jsonObject.getJSONArray("features");
                for (int i = 0; i < jsonArrayFeatures.length(); i++){
                    JSONObject jsonObjectFeatures = (JSONObject) jsonArrayFeatures.get(i);
                    JSONObject properties = jsonObjectFeatures.getJSONObject("properties");
                    String cuongdo = properties.getString("mag");
                    String diadiem = properties.getString("place");
                    String thoigian = properties.getString("time");
                    arrayList1.add(new Model(cuongdo,diadiem,thoigian));
                }
                arrayList1.add(new Model(titleString,urlString));

                return arrayList1;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Model> arrayList2) {
            super.onPostExecute(arrayList2);
            arrayList.addAll(arrayList2);
            myAdapter.notifyDataSetChanged();
            txt1.setText(arrayList.get(arrayList.size() - 1).getTitle());
            txt2.setText(arrayList.get(arrayList.size() - 1).getUrl());
            if (dialog.isShowing()){dialog.dismiss();}
        }
    }