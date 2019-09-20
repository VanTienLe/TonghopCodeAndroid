//MainActivity
Toolbar toolbar;
TabLayout tabLayout;

toolbar = findViewById(R.id.toolbar);
tabLayout = findViewById(R.id.tabs);
setSupportActionBar(toolbar);
tabLayout.addTab(tabLayout.newTab().setText("Home"));
tabLayout.addTab(tabLayout.newTab().setText("Camera"));
tabLayout.addTab(tabLayout.newTab().setText("Import"));

tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        display(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
});
display(0);


public void display(int id){
        Fragment fragment = null;

        if (id == 0) {
            fragment = new ImportFragment();
        } else if (id == 1) {
            fragment = new CameraFragment();
        } else if (id == 2) {
            fragment = new ImportFragment();
        }

        if(fragment != null) {
            FragmentTransaction fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content, fragment);
            fragmentTransaction.commit();
        }
    }

//create Fragment: ImportFragment and CameraFragment
public class ImportFragment extends Fragment {
    public ImportFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_import, container, false);
    }

}
