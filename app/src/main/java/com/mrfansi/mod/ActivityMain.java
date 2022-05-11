package com.mrfansi.mod;

import android.app.Activity;
import android.os.Bundle;

public class ActivityMain extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Main.Start(this);
  }
}
