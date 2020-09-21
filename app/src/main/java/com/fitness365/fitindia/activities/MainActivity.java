package com.fitness365.fitindia.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.adapters.CategoriesRecyclerAdapter;
import com.fitness365.fitindia.fragments.HomeFragment;
import com.fitness365.fitindia.helper.AppConfig;
import com.fitness365.fitindia.models.Category;
import com.fitness365.fitindia.webservice.EventCategoryRequest;
import com.fitness365.fitindia.webservice.ResponseListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener, ResponseListener {

    WebView webView;
    Toolbar toolbar;
    TextView toolbarTitle;
    DrawerLayout drawer;
    //BottomNavigationView bottomNavigationView;
    SharedPreferences sharedPreferences;
    RecyclerView categoriesRecyclerView;
    CategoriesRecyclerAdapter categoriesRecyclerAdapter;
    String userId;
    CardView takeTestCard,knowTestsCard,getActiveCard,registerFitIndiaChampionCard,fitnessBlogCard,nutritionBlogCard,shareStoryCard;
    ArrayList<Category> categoriesArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbar_title);
        takeTestCard  = findViewById(R.id.take_test);
        knowTestsCard  = findViewById(R.id.know_your_tests);
        getActiveCard  = findViewById(R.id.get_active);
        registerFitIndiaChampionCard  = findViewById(R.id.register_fit_champion);
        fitnessBlogCard  = findViewById(R.id.fitness_blog);
        nutritionBlogCard  = findViewById(R.id.nutrition_blog);
        shareStoryCard  = findViewById(R.id.share_story);

        // set navigation drawer
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.side_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        TextView userNameTxt = headerView.findViewById(R.id.user_name);
        TextView userImageTxt = headerView.findViewById(R.id.user_image);

//        bottomNavigationView = findViewById(R.id.navigation_menu);
//        bottomNavigationView.setOnNavigationItemSelectedListener(this);
//        bottomNavigationView.setSelectedItemId(R.id.nav_get_fit);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userName = sharedPreferences.getString(AppConfig.NAME,"");
        userId = PreferenceManager.getDefaultSharedPreferences(this).getString(AppConfig.USER_ID,"");
        userNameTxt.setText(userName);
        if (!userName.equals(""))
            userImageTxt.setText(String.valueOf(userName.toUpperCase().charAt(0)));

        //webView = findViewById(R.id.home_webview);
        //webView.getSettings().setJavaScriptEnabled(true);
        //webView.loadUrl(AppConfig.HOME_PAGE_URL);

        Typeface font_semi_bold = Typeface.createFromAsset(getAssets(),
                "fonts/Barlow-BlackItalic.otf");
        toolbarTitle.setTypeface(font_semi_bold);
        //toolbarTitle.setText(getString(R.string.home));

        takeTestCard.setOnClickListener(this);
        knowTestsCard.setOnClickListener(this);
        getActiveCard.setOnClickListener(this);
        registerFitIndiaChampionCard.setOnClickListener(this);
        fitnessBlogCard.setOnClickListener(this);
        nutritionBlogCard.setOnClickListener(this);
        shareStoryCard.setOnClickListener(this);

        setCategoryRecyclerView();



    }

    private void setCategoryRecyclerView(){
        EventCategoryRequest eventCategoryRequest = new EventCategoryRequest(this,this);
        eventCategoryRequest.hitUserRequest();
//        categoriesArrayList.clear();
//        for (int i=1;i<5;i++){
//            categoriesArrayList.add(new Category(i,"http://fitindia.gov.in/wp-content/uploads/2020/03/Strong-Women_inner.jpg"));
//        }
        categoriesRecyclerView = findViewById(R.id.categories_recycler_view);
        categoriesRecyclerAdapter = new CategoriesRecyclerAdapter(this,categoriesArrayList);

        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoriesRecyclerView.setLayoutManager(horizontalLayoutManagaer);
        categoriesRecyclerView.setNestedScrollingEnabled(false);

        categoriesRecyclerView.setAdapter(categoriesRecyclerAdapter);
        //loadCategories();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = new HomeFragment();
        switch (menuItem.getItemId()){
//            case R.id.nav_home:
//                loadFragment(fragment,getString(R.string.app_name),AppConfig.HOME_URL);
//                break;
            case R.id.nav_get_fit:
                loadFragment(fragment,getString(R.string.get_fit),AppConfig.GET_FIT_URL);
                break;
            case R.id.nav_get_active:
                loadFragment(fragment,getString(R.string.get_active),AppConfig.GET_ACTIVE_URL);
                break;
            case R.id.nav_events:
                loadFragment(fragment,getString(R.string.events),AppConfig.EVENTS_URL);
                break;
            case R.id.nav_social_media:
                loadFragment(fragment,getString(R.string.media),AppConfig.MEDIA_URL);
                break;
            case R.id.nav_your_story:
                loadFragment(fragment,getString(R.string.your_story),AppConfig.YOUR_STORY_URL);
                break;
            // side navigation drawer

//            case R.id.side_nav_home:
//                startWebViewActivity(getString(R.string.app_name),AppConfig.HOME_URL);
//                break;
//            case R.id.side_nav_aboutus:
//                startWebViewActivity(getString(R.string.about_us),AppConfig.ABOUT_US_URL);
//                break;
//            case R.id.side_nav_get_fit:
//                startWebViewActivity(getString(R.string.get_fit),AppConfig.GET_FIT_URL+userId);
//                break;
            case R.id.side_nav_get_active:
                startWebViewActivity(getString(R.string.get_active),AppConfig.GET_ACTIVE_URL);
                break;
            case R.id.side_nav_events:
                startWebViewActivity(getString(R.string.events),AppConfig.EVENTS_URL);
                break;
            case R.id.side_nav_social_media:
                startWebViewActivity(getString(R.string.media),AppConfig.MEDIA_URL);
                break;
            case R.id.side_nav_fitindia_school:
                startWebViewActivity(getString(R.string.fit_india_school),AppConfig.FITINDIA_SCHOOL_URL);
                break;
            case R.id.side_nav_your_story:
                startWebViewActivity(getString(R.string.your_story),AppConfig.YOUR_STORY_URL);
                break;
            case R.id.side_nav_become_partner:
                startWebViewActivity(getString(R.string.become_partner),AppConfig.BECOME_PARTNER_URL);
                break;
            case R.id.side_nav_logout:
                showLogoutDialog();
                break;
//            case R.id.side_nav_login:
//                //startWebViewActivity(getString(R.string.login_register),AppConfig.LOGIN_URL);
//                startActivity(new Intent(this,LoginActivity.class));
//                break;
            case R.id.side_nav_register_fit_champion:
                //startWebViewActivity(getString(R.string.login_register),AppConfig.LOGIN_URL);
                Intent intent = new Intent(this,CreateAccountActivity.class);
                intent.putExtra(AppConfig.FOR_FIT_INDIA_CHAMPION,true);
                startActivity(intent);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadFragment(Fragment fragment,String label,String url) {
        // load fragment
        toolbarTitle.setText(label);
        Bundle bundle = new Bundle();
        if (userId==null){
            userId = PreferenceManager.getDefaultSharedPreferences(this).getString(AppConfig.USER_ID,"");
        }
        bundle.putString(AppConfig.INTENT_STRING_EXTRA,label);
        bundle.putString(AppConfig.INTENT_URL_EXTRA,url);
        bundle.putString(AppConfig.USER_ID,userId);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void startWebViewActivity(String title, String url){
        Intent intent = new Intent(this,WebViewActivity.class);
        intent.putExtra(AppConfig.INTENT_STRING_EXTRA,title);
        intent.putExtra(AppConfig.INTENT_URL_EXTRA,url);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

        alertDialog.setMessage(getResources().getString(R.string.exit_from_app));

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.alarm);

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                dialog.cancel();
                finish();
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    public void showLogoutDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setCancelable(false);
        alertDialog.setMessage(getString(R.string.logout_confirm_msg));

        // Setting Icon to Dialog
        //alertDialog.setIcon(R.drawable.alarm);

        alertDialog.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).edit();
                editor.remove(AppConfig.USER_ID);
                editor.remove(AppConfig.IS_LOGGED_IN);
                editor.apply();
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
                dialog.cancel();

            }
        });

        alertDialog.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.take_test:
                startWebViewActivity(getString(R.string.take_your_fitness_test),AppConfig.GET_FIT_URL+userId+AppConfig.TARGET+"A");
                break;
            case R.id.know_your_tests:
                startWebViewActivity(getString(R.string.know_your_tests),AppConfig.GET_FIT_URL+userId+AppConfig.TARGET+"B");
                break;
            case R.id.get_active:
                startWebViewActivity(getString(R.string.get_active),AppConfig.GET_ACTIVE_URL);
                break;
            case R.id.register_fit_champion:
                Intent intent = new Intent(this,CreateAccountActivity.class);
                intent.putExtra(AppConfig.FOR_FIT_INDIA_CHAMPION,true);
                startActivity(intent);
                break;
            case R.id.fitness_blog:
                startWebViewActivity(getString(R.string.fitness),AppConfig.FITNESS_BLOG_URL);
                break;
            case R.id.nutrition_blog:
                startWebViewActivity(getString(R.string.nutrition),AppConfig.NUTRITION_BLOG_URL);
                break;
            case R.id.share_story:
                startWebViewActivity(getString(R.string.your_story),AppConfig.YOUR_STORY_URL);
                break;

        }
    }

    @Override
    public void onResponse(Object obj) {
        ArrayList<Category> tempList = new ArrayList<>();
//        if (obj instanceof ArrayList){
//            tempList = (ArrayList<Category>) obj;
//            categoriesArrayList.addAll(tempList);
//            categoriesRecyclerAdapter.notifyDataSetChanged();
//        }
        tempList = (ArrayList<Category>) obj;
        for (int i=0;i<tempList.size();i++) {
            Category category = tempList.get(i);
            if (!category.getBannerurl().equals("")){
                categoriesArrayList.add(category);
            }
        }
//        categoriesArrayList.addAll(tempList);
        categoriesRecyclerAdapter.notifyDataSetChanged();
    }
}
