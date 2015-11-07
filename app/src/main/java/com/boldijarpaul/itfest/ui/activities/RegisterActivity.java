package com.boldijarpaul.itfest.ui.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.presenter.presenters.RegisterPresenter;
import com.boldijarpaul.itfest.presenter.views.RegisterView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    @Bind(R.id.activity_register_age)
    EditText mAge;
    @Bind(R.id.activity_register_fullname)
    EditText mFullName;
    @Bind(R.id.activity_register_register)
    View mRegister;

    private RegisterPresenter mRegisterPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mRegisterPresenter = new RegisterPresenter(this, this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.activity_register_register)
    void tryToRegister() {
        try {
            int age = Integer.parseInt(mAge.getText().toString());
            String fullName = mFullName.getText().toString();
            mRegisterPresenter.addUser(fullName, age);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            Toast.makeText(this, R.string.msg_nput_invalid, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void onSuccess() {
        finish();
    }
}
