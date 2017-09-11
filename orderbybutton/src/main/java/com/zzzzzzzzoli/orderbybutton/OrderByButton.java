package com.zzzzzzzzoli.orderbybutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

/**
 * Created by zoli on 07/09/17.
 */

public class OrderByButton extends android.support.v7.widget.AppCompatRadioButton {

    private static final int[] mStates = {R.attr.extra};
    private boolean firstCallConsumed = false;
    private boolean extraState;
    private boolean enableExtraState = true;
    private String extraStateTrueTag = "";
    private String extraStateFalseTag = "";
    private OnButtonStateChangeListener listener;
    private String tag;

    public void setListener(OnButtonStateChangeListener listener) {
        this.listener = listener;
    }

    public OrderByButton(Context context) {
        this(context, null);
    }

    public OrderByButton(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.orderByButtonStyle);
    }

    public OrderByButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(null);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.OrderByButton, 0, 0);
        try {
            tag = a.getString(R.styleable.OrderByButton_tag); // this tag is used to build query strings
            enableExtraState = a.getBoolean(R.styleable.OrderByButton_enableExtra, true); // by default extraState is enabled
            if (enableExtraState) {
                extraState = a.getBoolean(R.styleable.OrderByButton_extra, true); // starting value of extraState, default is true
                extraStateTrueTag = a.getString(R.styleable.OrderByButton_extra_true_tag) == null ? " asc" : a.getString(R.styleable.OrderByButton_extra_true_tag);
                extraStateFalseTag = a.getString(R.styleable.OrderByButton_extra_false_tag) == null ? " desc" : a.getString(R.styleable.OrderByButton_extra_false_tag);
            }
        } finally {
            a.recycle();
        }
    }

    public boolean isExtraState() {
        return extraState;
    }

    @Override
    public void toggle() {

            if (!isChecked()) {
                super.toggle();
            } else {
                extraState = !extraState;
                if (!enableExtraState) {return;} // if extraState is not allowed the state doesn't change, so we don't want to redraw or notify listener
            }
            firstCallConsumed=false;
            refreshDrawableState();
            listener.onButtonStateChange(this);

    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        final int[] drawableState = super.onCreateDrawableState(extraSpace + 1);
        if(extraState)
            mergeDrawableStates(drawableState,mStates);
        return drawableState;
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return OrderByButton.class.getName();
    }

    public interface OnButtonStateChangeListener {
        public void onButtonStateChange(OrderByButton button);
    }

    @Override
    public String getTag() {
        return tag;
    }

    public String getExtraStateTrueTag() {
        return extraStateTrueTag;
    }

    public String getExtraStateFalseTag() {
        return extraStateFalseTag;
    }

    public void setExtraState(boolean extraState) {
        this.extraState = extraState;
    }

    public void setEnableExtraState(boolean enableExtraState) {
        this.enableExtraState = enableExtraState;
    }

    public void setExtraStateTrueTag(String extraStateTrueTag) {
        this.extraStateTrueTag = extraStateTrueTag;
    }

    public void setExtraStateFalseTag(String extraStateFalseTag) {
        this.extraStateFalseTag = extraStateFalseTag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getExtraTag() {
        if (extraState) {
            return extraStateTrueTag;
        }
        return extraStateFalseTag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderByButton newButton = (OrderByButton) o;

        return getId() == newButton.getId();
    }

    @Override
    public int hashCode() {
        return getId();
    }
}
