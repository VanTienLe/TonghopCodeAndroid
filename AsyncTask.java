ImageView img;
Button btn;
String link = "https://www.extremetech.com/wp-content/uploads/2017/04/business-android-640x353.jpg";
String link2 = "https://shost001.tenten.vn:2083/cpsess2090433053/viewer/home%2fcdinhvi47of/abcd.png";

btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadImage loadImage = new LoadImage(MainActivity.this);
                loadImage.execute(link2); //loadImage.execute(link, link2, link3);
            }
        });

class LoadImage extends AsyncTask<String, Void, Bitmap>{
        private ProgressDialog dialog;

        public LoadImage(MainActivity activity){
            dialog = new ProgressDialog(activity);
        }

        @Override
        protected void onPreExecute() {
            dialog.setMessage("please wait...");
            dialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {

            try {
                URL url = new URL(strings[0]);
                Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                return bitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
            if (dialog.isShowing()){dialog.dismiss();}
        }
    }