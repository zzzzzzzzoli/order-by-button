package com.zzzzzzzzoli.orderbybutton;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import java.util.ArrayList;

/**
 * Created by zoli on 08/09/17.
 */

public class OrderByGroup extends RadioGroup implements OrderByButton.OnButtonStateChangeListener {

    private ArrayList<OrderByButton> buttonsForQuery = new ArrayList<>();
    private OrderByButton.OnButtonStateChangeListener listener;

    public OrderByGroup(Context context) {
        super(context);
    }

    public OrderByGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setListener(OrderByButton.OnButtonStateChangeListener listener) {
        this.listener = listener;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        super.addView(child, index, params);
        if (child instanceof OrderByButton) {
            OrderByButton button = (OrderByButton) child;
            button.setListener(this);
        }
    }

    @Override
    public void onButtonStateChange(OrderByButton button) {
        if (buttonsForQuery.contains(button)) {
            buttonsForQuery.remove(button);
        }
        buttonsForQuery.add(0, button);
        listener.onButtonStateChange(button);
    }

    public String getOrderByString(String startTag) {
        String orderString = startTag + " ";
        int j=0;
        for (int i=0; i<buttonsForQuery.size()-1; i++) {
            orderString+=buttonsForQuery.get(i).getTag()+" "+buttonsForQuery.get(i).getExtraTag()+", ";
            j=i+1;
        }
        orderString+=buttonsForQuery.get(j).getTag()+" "+buttonsForQuery.get(j).getExtraTag();

        return orderString;
    }
}
