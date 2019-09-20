ImageView img;
Button btn, btn2;
Handler myHandler;
String link = "https://fossbytes.com/wp-content/uploads/2018/01/Best-Android-Apps-List-fossbytes.jpg";
String link = "/storage/emulated/0/DCIM/Camera/IMG_20181231_225751.jpg";

//Thread
btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(link);
                            final Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                            img.post(new Runnable() {
                                @Override
                                public void run() {
                                    img.setImageBitmap(bitmap);
                                }
                            });

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

//Handler
btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myHandler = new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);

                        Bundle bundle = msg.getData();
                        byte[] bytes = bundle.getByteArray("image");
                        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                        img.setImageBitmap(bitmap);
                    }
                };

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(link);
                            Bitmap bitmap = BitmapFactory.decodeStream(url.openStream());
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);

                            Bundle bundle = new Bundle();
                            bundle.putByteArray("image",byteArrayOutputStream.toByteArray());

                            Message message = new Message();
                            message.setData(bundle); // tham so truyen vao Message la 1 bundle

                            myHandler.sendMessage(message);

                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();
            }
        });

//Add library Picasso
ImageView img1,img2,img3,img4,img5,img6;
String link = "/system/media/lockscreen/lockscreen_01.jpg";
String link2 = "/storage/emulated/0/DCIM/Camera/IMG_20181231_225751.jpg";

img1 = findViewById(R.id.imageView21);
Picasso.get()
        .load("https://cms-assets.tutsplus.com/uploads/users/1499/posts/30966/preview_image/fe.png")
        .into(img1);

img2 = findViewById(R.id.imageView32);
Picasso.get().load(new File(link2)).resize(410,310)
        .into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                img2.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

img3 = findViewById(R.id.imageView43);
Picasso.get().load("file://"+link).into(img3);

img4 = findViewById(R.id.imageView74);
Picasso.get().load(new File(link)).into(img4);

img5 = findViewById(R.id.imageView65);
Picasso.get().load(new File(link2)).resize(410,310).into(img5);

img6 = findViewById(R.id.imageView86);