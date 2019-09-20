
//Data Json
ListView listView;
ArrayList<Tivi> arrayList;
TiviAdapter tiviAdapter;

listView = findViewById(R.id.listView);
arrayList = new ArrayList<>();
tiviAdapter = new TiviAdapter(Main2Activity.this, R.layout.layout_item, arrayList);
listView.setAdapter(tiviAdapter);

ParserArrayJson parserArrayJson = new ParserArrayJson();
parserArrayJson.execute("http://192.168.41.56:3000/tiviarray.json");

//
class ParserArrayJson extends AsyncTask<String, Void, ArrayList<Tivi>> {

        @Override
        protected ArrayList<Tivi> doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openStream();

                //chuyen inputStream thanh String
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();
                StringBuilder sb = new StringBuilder();
                while (line != null) {
                    sb.append(line);
                    line = bufferedReader.readLine();
                }

                ArrayList<Tivi> arrayList1 = new ArrayList<>();

                // chuyen string sb thanh Json de phan tich
                JSONObject jsonObject = new JSONObject(sb.toString());
                JSONArray jsonArray = jsonObject.getJSONArray("danhsach");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(i);
                    String ten = jsonObject1.getString("ten");
                    double gia = jsonObject1.getDouble("giaban");
                    int sl = jsonObject1.getInt("soluong");
                    String hinh = jsonObject1.getString("hinh");

                    arrayList1.add(new Tivi(ten,gia,sl,hinh));
                }

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
        protected void onPostExecute(ArrayList<Tivi> tivis) {
            super.onPostExecute(tivis);
            arrayList.addAll(tivis);
            tiviAdapter.notifyDataSetChanged();
        }
    }
