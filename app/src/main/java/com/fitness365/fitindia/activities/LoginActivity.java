package com.fitness365.fitindia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fitness365.fitindia.R;
import com.fitness365.fitindia.helper.AppConfig;
import com.fitness365.fitindia.helper.DatabaseManager;
import com.fitness365.fitindia.models.LoginModel;
import com.fitness365.fitindia.models.StateDistrictBlockModel;
import com.fitness365.fitindia.webservice.GetStateDistrictBlockRequest;
import com.fitness365.fitindia.webservice.LoginRequest;
import com.fitness365.fitindia.webservice.ResponseListener;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements ResponseListener, View.OnClickListener {

    SharedPreferences sharedPreferences;
    DatabaseManager databaseManager;
    TextView usernameLabel,passwordLabel,loginBtn,forgetPasswordBtn, registerBtn;
    //ImageView backBtn;
    ProgressDialog savingProgressDialog;
    LoginModel loginModel;
    EditText usernameEdText,passwordEdTxt;
    CheckBox rememberMeCb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //toolbarTitle = findViewById(R.id.toolbar_title);
        //backBtn = findViewById(R.id.back_btn);
        usernameLabel = findViewById(R.id.username_label);
        passwordLabel = findViewById(R.id.password_label);
        usernameEdText = findViewById(R.id.username);
        passwordEdTxt = findViewById(R.id.password);
        loginBtn = findViewById(R.id.login_btn);
        forgetPasswordBtn = findViewById(R.id.forget_password_btn);
        registerBtn = findViewById(R.id.register_btn);
        rememberMeCb = findViewById(R.id.remember_me_cb);

        init();


    }

    private void init(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        databaseManager = new DatabaseManager(this);


        Typeface fontRegular = Typeface.createFromAsset(getAssets(),
                "fonts/Barlow-Regular.ttf");
        Typeface font_semi_bold = Typeface.createFromAsset(getAssets(),
                "fonts/Barlow-BlackItalic.otf");

//        toolbarTitle.setText(getString(R.string.login));
//        toolbarTitle.setTypeface(font_semi_bold);
        usernameLabel.setTypeface(fontRegular);
        passwordLabel.setTypeface(fontRegular);
        usernameEdText.setTypeface(fontRegular);
        passwordEdTxt.setTypeface(fontRegular);
        loginBtn.setTypeface(fontRegular);
        forgetPasswordBtn.setTypeface(fontRegular);
        registerBtn.setTypeface(fontRegular);
        rememberMeCb.setTypeface(fontRegular);


        //backBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        forgetPasswordBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
        //backBtn.setVisibility(View.GONE);


    }

    @Override
    public void onResponse(Object obj) {
        if (obj instanceof LoginModel){
            loginModel = (LoginModel) obj;
            if (loginModel.isIsSuccess()){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(AppConfig.IS_LOGGED_IN,true);
                editor.putString(AppConfig.USER_ID,String.valueOf(loginModel.getUserid()));
                editor.putString(AppConfig.NAME,String.valueOf(loginModel.getName()));
                editor.putString(AppConfig.MOBILE,String.valueOf(loginModel.getMobile()));
                //editor.putString(AppConfig.EMAIL,String.valueOf(loginModel.getUserid()));
                editor.apply();

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                finish();
            }
            Toast.makeText(this, loginModel.getMessage(), Toast.LENGTH_SHORT).show();
    }
        else {
            Toast.makeText(this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
//            case R.id.back_btn:
//                super.onBackPressed();
//                break;
            case R.id.login_btn:
                String username = usernameEdText.getText().toString().trim();
                String password = passwordEdTxt.getText().toString().trim();

                if (username.length() < 1) {
                    usernameEdText.requestFocus();
                    usernameEdText.setError(getResources().getString(R.string.username));
                } else if (password.length() < 1) {
                    passwordEdTxt.requestFocus();
                    passwordEdTxt.setError(getResources().getString(R.string.enter_password));
                } else {
                    LoginRequest loginRequest = new LoginRequest(this, this,username,password);
                    loginRequest.hitUserRequest();
                }
                break;
            case R.id.forget_password_btn:
                break;
            case R.id.register_btn:
                startActivity(new Intent(this,CreateAccountActivity.class));
                break;
        }
    }




//    private void hitUserRequest() {
//        ApiRequest apiService =
//                ApiClient.getClient(this).create(ApiRequest.class);
//        Call<StateDistrictBlockModel> call = apiService.getStateDistrictBlock();
//        ProgressDialog progressDialog = new ProgressDialog(this);
//        progressDialog.dismiss();
//        call.enqueue(this);
//    }

}
