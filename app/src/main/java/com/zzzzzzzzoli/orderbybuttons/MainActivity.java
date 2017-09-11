package com.zzzzzzzzoli.orderbybuttons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.zzzzzzzzoli.orderbybutton.OrderByButton;
import com.zzzzzzzzoli.orderbybutton.OrderByGroup;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private OrderByGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /*textView = (TextView) findViewById(R.id.textview);

        group = (OrderByGroup) findViewById(R.id.group);
        group.setListener(new OrderByButton.OnButtonStateChangeListener() {
            @Override
            public void onButtonStateChange(OrderByButton button) {
                textView.setText(group.getOrderByString("ORDER BY "));
            }
        });
    */}
}
