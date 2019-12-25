package com.unlim.pincode;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.unlim.pincode.Adapter.MyAdapter;

public class MainActivity extends AppCompatActivity {

    private MyPresenter myPresenter;
    private LinearLayout linearLayout;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPresenter = new MyPresenter(this);
        myAdapter = new MyAdapter(this, MyPresenter.SYMBOLS_AMN);
        fillCodeCircles();
        fillKeyboard();
    }

    public void btnClickDelete(View view) {
        myPresenter.onDeleteCodeSymbol();
    }

    public void switchOffCodeSymbol(int id) {
        myAdapter.switchOffCircle(linearLayout, id - 1);
    }

    public void switchOnCodeSymbol(int id) {
        myAdapter.switchOnCircle(linearLayout, id);
    }

    private void clearCode() {
        myAdapter.clearCodeLine(linearLayout);
        myPresenter.clearCode();
    }

    private void fillKeyboard() {
        TableLayout tableLayout = findViewById(R.id.table);
        tableLayout.setStretchAllColumns(true);
        tableLayout.setShrinkAllColumns(true);
        myAdapter.fillKeyboard(tableLayout);
    }

    public void getClickedNumber(int number) {
        myPresenter.getClickedNumber(number);
    }

    private void fillCodeCircles() {
        linearLayout = findViewById(R.id.linear_layout);
        myAdapter.fillCodeLine(linearLayout);
    }

    public void errAnimation() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.move);
        linearLayout.startAnimation(animation);
        clearCode();
    }
}
