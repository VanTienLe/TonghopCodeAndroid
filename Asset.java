//SharedPreferences, Asset Folder, 

//Asset
private void loadData(){

        try {
            AssetManager assetManager = getAssets(); // chi toi file assets
            InputStream inputStream = assetManager.open("danhba.txt"); // open file danhba.txt
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream); // doc du lieu theo dong
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  //doc du lieu theo dong
            String line = bufferedReader.readLine();

            while (line != null){
                String[] s = line.split(";"); // ki tu dat biet trong file txt de phan tich du lieu

                DanhBa danhBa = new DanhBa(s[0],s[1],s[2]);
                arrayList.add(danhBa);

                line = bufferedReader.readLine();
            }
            arrayAdapter.notifyDataSetChanged();

            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//file danhba.txt on FolderAsset
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC
Le Van Tien;0944777888;ABC