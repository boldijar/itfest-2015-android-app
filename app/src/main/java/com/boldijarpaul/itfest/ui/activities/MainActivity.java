package com.boldijarpaul.itfest.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.boldijarpaul.itfest.R;
import com.boldijarpaul.itfest.data.models.Movie;
import com.boldijarpaul.itfest.data.models.Person;
import com.boldijarpaul.itfest.data.models.PersonResponse;
import com.boldijarpaul.itfest.presenter.presenters.PeoplePresenter;
import com.boldijarpaul.itfest.presenter.views.PeopleView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements PeopleView {

    @Bind(R.id.text)
    TextView mTextView;
    PeoplePresenter mPeoplePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPeoplePresenter = new PeoplePresenter(this, this);
        mPeoplePresenter.getMovies();
    }

    @OnClick(R.id.text)
    void onClickText() {
        mTextView.setText("loading");
        mPeoplePresenter.getMovies();
    }

    @Override
    public void showPeople(PersonResponse personResponse) {
//        StringBuilder output = new StringBuilder();
//        for (Person person : personResponse.people) {
//            output.append("Person id: ").append(person.id).append(" Person name: ").append(person.name).append(" favorite class: ").append(person.favoriteClasses.get(0).name);
//            output.append("\n\n\n");
//        }
//        mTextView.setText(output.toString());


    }

    @Override
    public void showMovies(List<Movie> movies) {
        String result = "";
        for (Movie movie : movies) {
            result += movie.name + " " + movie.releaseYear + " " + movie.genres.get(0).name+"\n\n";
        }
        mTextView.setText(result);

    }
}
