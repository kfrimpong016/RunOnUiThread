package com.thelogbox.runonuithread;

import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Button btn = (Button) findViewById(R.id.button);
    final TextView tv = (TextView) findViewById(R.id.textView);

    assert btn != null;
    btn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        /*
        * todo Refactor the thread code
        *
        * Make it into a named inner class, so you can pass the context reference to it
        *
        * */
        Thread t = new Thread(new Runnable() {
          @Override
          public void run() {
            int counter = 0;
            synchronized (this) {
              while(counter++ < 10) {
                try {
                  wait(2000);
                  System.out.println("Counter = " +  counter);
                }
                catch(InterruptedException ioe) {
                  ioe.printStackTrace();
                }
              }
              final int returnCounter = counter;
              runOnUiThread(new Runnable(){
                public void run() {
                  System.out.println("I am done");
                }
              });
            }
          }
        });
        t.start();
      }
    });

  }
}
