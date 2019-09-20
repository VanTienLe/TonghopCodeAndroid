//option menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option_menu,menu);//create file option_menu.xml on R.menu
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.mnuInsert:
                Toast.makeText(this, "Menu Insert", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuUpdate:
                Toast.makeText(this, "Menu Update", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuDelete:
                Toast.makeText(this, "Menu Delete", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

//context menu
//...

//PopupMenu
myViewHolder.img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        PopupMenu popupMenu = new PopupMenu(context,myViewHolder.img);
        popupMenu.getMenuInflater().inflate(R.menu.menu_02, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case R.id.mnu_detail:
                        //Toast.makeText(context, model.getDetail(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context,DetailActivity.class);
                        intent.putExtra("urldetail",model.getDetail());
                        context.startActivity(intent);

                        break;
                    case R.id.mnu_02:

                        break;
                    case R.id.mnu_03:

                        break;
                }

                return true;
            }
        });
        popupMenu.show();
    }
});

