package com.taha.deneme;

/**
 * Created by tahabayi on 22/06/2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class FacebookLogin extends Activity {


    CallbackManager callbackManager;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(AccessToken.getCurrentAccessToken()!=null){
            Log.d("token",AccessToken.getCurrentAccessToken().getToken());
            Intent intent = new Intent(FacebookLogin.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facebooklogin);

        callbackManager = CallbackManager.Factory.create();



        final LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                loginButton.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(FacebookLogin.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

}
