package com.zzzzzzzzoli.orderbybutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

/**
 * Created by zoli on 07/09/17.
 */

public class OrderByButton extends android.support.v7.widget.AppCompatRadioButton {

    private static final int[] mStates = {R.attr.extraState};
    private boolean extraState;
    private boolean enableExtraState = true;
    private String extraStateTrueTag = "";
    private String extraStateFalseTag = "";
    private OnButtonStateChangeListener listener;

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
            setTag(a.getString(R.styleable.OrderByButton_tag)); // this tag is used to build query strings
            enableExtraState = a.getBoolean(R.styleable.OrderByButton_enableExtra, true); // by default extraState is enabled
            if (enableExtraState) {
                extraState = a.getBoolean(R.styleable.OrderByButton_extraState, true); // starting value of extraState, default is true
                extraStateTrueTag = a.getString(R.styleable.OrderByButton_extra_true_tag) == null ? " asc" : a.getString(R.styleable.OrderByButton_extra_true_tag);
                extraStateFalseTag = a.getString(R.styleable.OrderByButton_extra_false_tag) == null ? " desc" : a.getString(R.styleable.OrderByButton_extra_false_tag);
            }
        } finally {
            a.recycle();
        }
        setSaveEnabled(true);
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

            refreshDrawableState();
            if (listener!=null) {listener.onButtonStateChange(this);}

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

    static class SavedState extends BaseSavedState {
        boolean checked;
        boolean extraState;

        SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            checked = (Boolean)in.readValue(null);
            extraState = (Boolean)in.readValue(getClass().getClassLoader());
        }

        @Override
        public void writeToParcel(Parcel out, int flags) {
            super.writeToParcel(out, flags);
            out.writeValue(checked);
            out.writeValue(extraState);
        }

        @Override
        public String toString() {
            return "OrderByButton.SavedState{"
                    + Integer.toHexString(System.identityHashCode(this))
                    + " checked=" + checked + " extraState=" + extraState +" }";
        }

        public static final Parcelable.Creator<SavedState> CREATOR
                = new Parcelable.Creator<SavedState>() {
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        SavedState ss = new SavedState(superState);

        ss.checked = isChecked();
        ss.extraState = extraState;
        return ss;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState ss = (SavedState) state;

        super.onRestoreInstanceState(ss.getSuperState());
        setChecked(ss.checked);
        setExtraState(ss.extraState);
        requestLayout();
    }


}
