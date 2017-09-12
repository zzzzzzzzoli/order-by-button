# Multistate RadioButtons for ordering lists
Custom RadioButtons with RadioGroup, allowing you to declare two extra states for each button. 

[](https:/https://raw.githubusercontent.com/zzzzzzzzoli/order-by-button/master/orderByButtons.gif)

Basically:

 * Extends RadioButton and RadioGroup
 * Comes with a method to create an ORDER BY tag for use in SQLite query
 * To use the getOrderByString method pass it a starting string like "ORDER BY"
 * Add ````app:tag="some tag"```` in your xml layout file to the OrderByButton to build the query string
 * Same place add ````app:enableExtra="false"```` to disable extra state
 * With ````app:extra_false_tag```` and ````app:extra_true_tag```` you can define how to append the query string. Default is asc/desc 


## How to use
In your layout:
```xml

    <com.zzzzzzzzoli.orderbybutton.OrderByGroup
        <!--Don't forget to add id or state might reset on screen orientation change etc.-->
        android:id="@+id/group"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_marginTop="100dp"
        android:gravity="center"
        android:orientation="horizontal">

        <com.zzzzzzzzoli.orderbybutton.OrderByButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            <!-Below two lines are for enabling ripple effect -->
            android:background="?attr/selectableItemBackgroundBorderless"
            android:clickable="true"
            <!--Set a selector as your drawable-->
            android:drawableEnd="@drawable/price_button"           
            <!--You can set the initial extra state of your button like this-->
            app:extraState="false"
            <!--Tag is used for constructing queries-->
            app:tag="price"
            <!--Don't forget to add id or state might reset on screen orientation change etc.-->
            android:id="@+id/price"/>
            
        <com.zzzzzzzzoli.orderbybutton.OrderByButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:background="?attr/selectableItemBackgroundBorderless"
            android:clickable="true"
            android:drawableEnd="@drawable/applaud_button"
            <!--Disable extra state like this-->
            app:enableExtra="false"
            app:tag="applaud"
            android:id="@+id/applaud"/>
            <!--Don't forget to add id or state might reset on screen orientation change etc.-->
    </com.zzzzzzzzoli.orderbybutton.OrderByGroup>

```

Make a selector xml drawable like this:
```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android" xmlns:app="http://schemas.android.com/apk/res/com.zzzzzzzzoli.orderbybuttons">
    <item android:drawable="@drawable/price_asc_fill" android:state_checked="true" app:extraState="true" />
    <item android:drawable="@drawable/price_desc_fill" android:state_checked="true" app:extraState="false"  />
    <item android:drawable="@drawable/price_asc" android:state_checked="false" app:extraState="true"  />
    <item android:drawable="@drawable/price_desc" android:state_checked="false" app:extraState="false" />
</selector>
```


In your activity:
````java

        group = (OrderByGroup) findViewById(R.id.group);
        group.setListener(new OrderByButton.OnButtonStateChangeListener() {
            @Override
            public void onButtonStateChange(OrderByButton button) { //here you get the changed button
                // you can disregard it and go straight for the string
                queryOrderByTag = group.getOrderByString("ORDER BY ");
            }
        });
        
````

## Get it here
Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add the dependency

	dependencies {
	        compile 'com.github.zzzzzzzzoli:order-by-button:v0.9.1'
	}



## The MIT License (MIT)

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

