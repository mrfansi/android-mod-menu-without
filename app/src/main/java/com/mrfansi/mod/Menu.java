package com.mrfansi.mod;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.text.Html;
import android.util.Base64;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class Menu {
  protected int WIDTH, HEIGHT;
  protected boolean requirePermission;
  protected Context context;
  protected boolean isIconVisible;
  protected boolean isMenuVisible;
  protected ImageView iconView, closeView;
  protected FrameLayout parentBox;
  protected LinearLayout menuLayout;
  protected ScrollView scrollItems;

  private native String SliderString(int feature, int value);

  protected TextView cred;

  protected WindowManager wmManager;
  protected WindowManager.LayoutParams wmParams;
  protected LinearLayout headerLayout;

  protected LinearLayout childOfScroll;
  protected LinearLayout info;
  public static boolean game;

  //For Animations
  static int dur = 400;
  public static int one = 1;
  public static int zero = 0;

  public static Animation fadeIn() { //Closing
    AlphaAnimation alphaAnimation = new AlphaAnimation((float) one, (float) zero);
    alphaAnimation.setDuration(dur);
    return alphaAnimation;
  }

  public static Animation fadeOut() { //Opening
    AlphaAnimation alphaAnimation = new AlphaAnimation((float) zero, (float) one);
    alphaAnimation.setDuration(dur);
    return alphaAnimation;
  }


  public void setBackground(int RED) {
  }

  public void addText(String str) {
    TextView textView = new TextView(context);
    textView.setText(Html.fromHtml("<u><b>" + str + "</b></u>"));
    textView.setTextColor(Color.WHITE);
    textView.setTextSize(12.3f);
    textView.setGravity(3);
    textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    textView.setPadding(20, 0, 0, 0);
    getChildOfScroll().addView(textView);
  }

  @SuppressLint("SetTextI18n")
  public void SeekBar(final int featureNum, final String feature, final int prg, int max, final iit interInt) {
    LinearLayout linearLayout = new LinearLayout(context);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
    linearLayout.setPadding(10, 5, 0, 5);
    linearLayout.setOrientation(LinearLayout.VERTICAL);
    linearLayout.setGravity(17);
    linearLayout.setLayoutParams(layoutParams);

    //Textview
    final TextView textView = new TextView(context);
    String str = SliderString(featureNum, 0);

    if (str != null) //Show text progress instead number
      textView.setText(feature + " : " + str);
    else  //If string is null, show number instead
      textView.setText(feature + " : " + prg);
    textView.setTextSize(13.0f);
    textView.setGravity(3);
    textView.setTextColor(Color.WHITE);
    textView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
    textView.setPadding(5, 0, 0, 0);

    //Seekbar
    SeekBar seekBar = new SeekBar(context);
    seekBar.setMax(max);
    seekBar.getProgressDrawable().setColorFilter(Color.parseColor("#ffffffff"), PorterDuff.Mode.MULTIPLY);
    seekBar.getThumb().setColorFilter(Color.parseColor("#ffffffff"), PorterDuff.Mode.MULTIPLY);
    seekBar.setPadding(25, 10, 35, 10);
    seekBar.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
    layoutParams2.bottomMargin = 10;
    seekBar.setLayoutParams(layoutParams2);
    seekBar.setProgress(prg);

    seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      public void onStartTrackingTouch(SeekBar seekBar) {
      }

      public void onStopTrackingTouch(SeekBar seekBar) {
      }

      int l;

      public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        l = i;
        String str = SliderString(featureNum, i);

        interInt.OnWrite(i);

        if (str != null)
          textView.setText(feature + " : " + str);
        else
          textView.setText(feature + " : " + i);
      }
    });

    linearLayout.addView(textView);
    linearLayout.addView(seekBar);
    getChildOfScroll().addView(linearLayout);

  }

  public void ButtonOnOff(final int featNum, String feature, final ibt interfaceBtn) {
    final GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
    String str2 = "#ffffffff";
    gradientDrawable.setColor(Color.parseColor(str2));
    gradientDrawable.setStroke(3, Color.parseColor(str2));
    gradientDrawable.setCornerRadius(8.0f);
    final GradientDrawable gradientDrawable2 = new GradientDrawable();
    gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
    gradientDrawable2.setColor(0);
    gradientDrawable2.setStroke(3, Color.parseColor(str2));
    gradientDrawable2.setCornerRadius(8.0f);

    final Button button = new Button(context);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
    layoutParams.setMargins(7, 5, 7, 5);
    button.setText(feature);
    button.setTextColor(Color.WHITE);
    button.setTextSize(14.5f);
    button.setAllCaps(false);
    button.setBackgroundColor(Color.TRANSPARENT);
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dpi(40));
    button.setPadding(3, 3, 3, 3);
    layoutParams2.bottomMargin = 0;
    button.setLayoutParams(layoutParams2);
    final String gays2 = feature;
    button.setOnClickListener(new View.OnClickListener() {
      private boolean isActive = true;
      boolean gaa;

      public void onClick(View v) {

        if (featNum == -1) {
          return;
        }
        interfaceBtn.OnWrite();
        if (isActive) {
          button.setText(Html.fromHtml("<b>" + gays2 + "</b>"));
          button.setTextSize(15.5f);
          button.setBackgroundColor(Color.parseColor("#30FFFFFF"));
          isActive = false;
          return;
        }
        button.setText(gays2);
        button.setTextSize(15.0f);
        button.setBackgroundColor(Color.TRANSPARENT);
        isActive = true;

      }
    });
    getChildOfScroll().addView(button);

  }

  public void Button(final int featNum, String feature, final ibt interfaceBtn) {
    final GradientDrawable gradientDrawable = new GradientDrawable();
    gradientDrawable.setShape(GradientDrawable.RECTANGLE);
    String str2 = "#ffffffff";
    gradientDrawable.setColor(Color.parseColor(str2));
    gradientDrawable.setStroke(3, Color.parseColor(str2));
    gradientDrawable.setCornerRadius(8.0f);
    final GradientDrawable gradientDrawable2 = new GradientDrawable();
    gradientDrawable2.setShape(GradientDrawable.RECTANGLE);
    gradientDrawable2.setColor(0);
    gradientDrawable2.setStroke(3, Color.parseColor(str2));
    gradientDrawable2.setCornerRadius(8.0f);

    final Button button2 = new Button(context);
    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
    layoutParams.setMargins(7, 5, 7, 5);
    button2.setText(feature);
    button2.setTextColor(Color.WHITE);
    button2.setTextSize(14.5f);
    button2.setAllCaps(false);
    button2.setBackgroundColor(Color.TRANSPARENT);
    LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, dpi(40));
    button2.setPadding(3, 3, 3, 3);
    layoutParams2.bottomMargin = 0;
    button2.setLayoutParams(layoutParams2);
    final String gays2 = feature;
    // private boolean game;
    button2.setOnClickListener(v -> {
      if (featNum == -2) {
        return;
      }
      button2.setBackgroundColor(Color.parseColor("#30FFFFFF"));
      button2.setTextSize(15.5f);
      button2.setText(Html.fromHtml("<b>" + gays2 + "</b>"));
      final Handler handler = new Handler();
      handler.postDelayed(() -> {
        button2.setBackgroundColor(Color.TRANSPARENT);
        button2.setText(gays2);
        button2.setTextSize(14.5f);
      }, 75);
      interfaceBtn.OnWrite();
    });
    getChildOfScroll().addView(button2);
  }

  public LinearLayout getInfoLayout() {
    return info;
  }

  public LinearLayout getChildOfScroll() {
    return childOfScroll;
  }

  public LinearLayout getHeaderLayout() {
    return headerLayout;
  }

  public LinearLayout getMenuLayout() {
    return menuLayout;
  }

  public ScrollView getScrollView() {
    return scrollItems;
  }

  @SuppressLint({"ClickableViewAccessibility", "ObsoleteSdkInt", "RtlHardcoded"})
  protected void init(Context context) {

    this.context = context;
    isIconVisible = false;
    isMenuVisible = false;
    iconView = new ImageView(context);
    closeView = new ImageView(context);
    cred = new TextView(context);

    parentBox = new FrameLayout(context);

    parentBox.setOnTouchListener(handleMotionTouch);

    try {
      wmManager = ((Activity) context).getWindowManager();
    } catch (ClassCastException ex) {
      Toast.makeText(context.getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
      Toast.makeText(context.getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
    }

    int additionalFlags = 0;
    if (Build.VERSION.SDK_INT >= 11)
      additionalFlags = WindowManager.LayoutParams.FLAG_SPLIT_TOUCH;
    if (Build.VERSION.SDK_INT >= 3)
      additionalFlags = additionalFlags | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM;
    wmParams = new WindowManager.LayoutParams(
        WindowManager.LayoutParams.WRAP_CONTENT,
        WindowManager.LayoutParams.WRAP_CONTENT,
        100,//initialX
        100,//initialy
        WindowManager.LayoutParams.TYPE_APPLICATION,
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_OVERSCAN |
            WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN |
            additionalFlags,
        PixelFormat.TRANSPARENT
    );
    wmParams.gravity = Gravity.TOP | Gravity.LEFT;

  }

  public void setIconImage(String Icon) {

    byte[] decode = Base64.decode(Icon, 0);
    iconView.setImageBitmap(BitmapFactory.decodeByteArray(decode, 0, decode.length));
    iconView.setPadding(dpi(10), dpi(10), 0, 0);
    iconView.setImageAlpha(200);
  }

  public void setWidth(int px) {
    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) menuLayout.getLayoutParams();
    lp.width = px;
    menuLayout.setLayoutParams(lp);
    WIDTH = px;
  }

  public void setHeight(int px) {
    FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) menuLayout.getLayoutParams();
    lp.height = px;
    menuLayout.setLayoutParams(lp);
    HEIGHT = px;
  }

  public int getWidth(int px) {
    return WIDTH;
  }

  public int getHeight(int px) {
    return HEIGHT;
  }

  public void setTitle(String text) {
    ValueAnimator colorAnim = ObjectAnimator.ofInt(cred, "textColor", Color.rgb(210, 0, 0), Color.rgb(210, 105, 0), Color.rgb(210, 210, 0), Color.rgb(105, 210, 0), Color.rgb(0, 210, 0), Color.rgb(0, 210, 105), Color.rgb(0, 210, 210), Color.rgb(0, 105, 210), Color.rgb(0, 0, 210), Color.rgb(105, 0, 210), Color.rgb(210, 0, 210), Color.rgb(210, 0, 105), Color.rgb(100, 100, 100), Color.rgb(50, 50, 50), Color.rgb(1, 1, 1), Color.rgb(110, 0, 0), Color.rgb(150, 0, 0), Color.rgb(180, 0, 0), Color.rgb(200, 0, 0));
    colorAnim.setDuration(4000);
    colorAnim.setEvaluator(new ArgbEvaluator());
    colorAnim.setRepeatCount(ValueAnimator.INFINITE);
    colorAnim.setRepeatMode(ValueAnimator.RESTART);
    colorAnim.start();
    cred.setText(text);
    cred.setOnClickListener(p1 -> showIcon());
  }

  public TextView getTitleTextView() {
    return cred;
  }

  public void showIcon() {
    //iconView.setAnimation(fadeout());//The appearance of the icon
    if (Main.hide) {
      iconView.setVisibility(View.INVISIBLE);
    } else {
      iconView.setVisibility(View.VISIBLE);
    }
    if (!isIconVisible) {
      isMenuVisible = false;
      parentBox.removeAllViews();
      parentBox.addView(iconView, dpi(70), dpi(70));
      parentBox.addView(closeView, dpi(20), dpi(20));
      isIconVisible = true;
    }
  }

  public void showMenu() {

    if (!isMenuVisible) {
      isIconVisible = false;
      parentBox.removeAllViews();
      parentBox.addView(menuLayout, WIDTH, HEIGHT);
      isMenuVisible = true;
    }
  }

  public int dpi(float dp) {
    float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dp * scale + 0.5f);
  }

  @SuppressLint({"ObsoleteSdkInt", "RtlHardcoded"})
  public Menu(Context context) {
    init(context);

    WIDTH = dpi(100);
    HEIGHT = dpi(50);

    final GradientDrawable shit = new GradientDrawable();
    shit.setColor(Color.parseColor("#9A2D3133"));
    // shit.setCornerRadius(50.0f);

    ValueAnimator colorAnim = ObjectAnimator.ofInt(cred, "textColor", Color.rgb(210, 0, 0), Color.rgb(210, 105, 0), Color.rgb(210, 210, 0), Color.rgb(105, 210, 0), Color.rgb(0, 210, 0), Color.rgb(0, 210, 105), Color.rgb(0, 210, 210), Color.rgb(0, 105, 210), Color.rgb(0, 0, 210), Color.rgb(105, 0, 210), Color.rgb(210, 0, 210), Color.rgb(210, 0, 105), Color.rgb(100, 100, 100), Color.rgb(50, 50, 50), Color.rgb(1, 1, 1), Color.rgb(110, 0, 0), Color.rgb(150, 0, 0), Color.rgb(180, 0, 0), Color.rgb(200, 0, 0));
    colorAnim.setDuration(4000);
    colorAnim.setEvaluator(new ArgbEvaluator());
    colorAnim.setRepeatCount(ValueAnimator.INFINITE);
    colorAnim.setRepeatMode(ValueAnimator.RESTART);
    colorAnim.addUpdateListener(animator -> shit.setStroke(3, (int) animator.getAnimatedValue()));
    colorAnim.start();

    menuLayout = new LinearLayout(context);
    menuLayout.setOrientation(LinearLayout.VERTICAL);
    {//header
      headerLayout = new LinearLayout(context);

      menuLayout.addView(headerLayout, -1, -2);
      //MENU BG COLOR
      menuLayout.setBackground(shit);
      {
        ImageView minimize = new ImageView(context);
        InputStream isStr;
        Bitmap bitmap = null;
        AssetManager assetManager = context.getAssets();
        try {
          isStr = assetManager.open("null");
          bitmap = BitmapFactory.decodeStream(isStr);
        } catch (IOException e) {
          e.printStackTrace();
        } finally {
          minimize.setImageBitmap(bitmap);

        }
        cred.setTextColor(Color.RED);
        cred.setTextSize(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
          cred.getPaint().setElegantTextHeight(true);
        cred.setGravity(Gravity.CENTER_HORIZONTAL);

        {
          info = new LinearLayout(context);
          info.setOrientation(LinearLayout.VERTICAL);
          info.addView(cred, -1, -1);
          headerLayout.addView(info, -1, -2);
          LinearLayout.LayoutParams mnp = (LinearLayout.LayoutParams) info.getLayoutParams();
          mnp.weight = 10;
          mnp.gravity = Gravity.CENTER;
          info.setLayoutParams(mnp);
        }
        headerLayout.addView(minimize, dpi(25), dpi(25));
        {
          LinearLayout.LayoutParams mnp = (LinearLayout.LayoutParams) minimize.getLayoutParams();
          mnp.weight = 0;
          mnp.gravity = Gravity.RIGHT;
          minimize.setLayoutParams(mnp);
          minimize.setPadding(0, dpi(10), dpi(10), dpi(10));
          minimize.setOnClickListener(p1 -> showIcon());
        }
        {

          closeView.setOnClickListener(p1 -> {

          });
        }
      }
    }

    scrollItems = new ScrollView(context);

    childOfScroll = new LinearLayout(context);

    scrollItems.setVerticalScrollBarEnabled(false);
    scrollItems.setOverScrollMode(View.OVER_SCROLL_NEVER);
    scrollItems.addView(childOfScroll, -1, -1);
    childOfScroll.setOrientation(LinearLayout.VERTICAL);
    childOfScroll.setBackgroundColor(Color.TRANSPARENT);

    menuLayout.addView(scrollItems, -1, -1);

    try {
      wmManager.addView(parentBox, wmParams);
    } catch (NullPointerException ex) {
      //Toast.makeText(context.getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
    }

    showMenu();
    showIcon();
  }

  View.OnTouchListener handleMotionTouch = new View.OnTouchListener() {
    private float initX;
    private float initY;
    private float touchX;
    private float touchY;

    double clock = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouch(View vw, MotionEvent ev) {

      switch (ev.getAction()) {
        case MotionEvent.ACTION_DOWN:

          initX = wmParams.x;
          initY = wmParams.y;
          touchX = ev.getRawX();
          touchY = ev.getRawY();
          clock = System.currentTimeMillis();
          break;

        case MotionEvent.ACTION_MOVE:
          wmParams.x = (int) initX + (int) (ev.getRawX() - touchX);

          wmParams.y = (int) initY + (int) (ev.getRawY() - touchY);

          wmManager.updateViewLayout(vw, wmParams);
          break;

        case MotionEvent.ACTION_UP:
          if (isIconVisible && (System.currentTimeMillis() < (clock + 200))) {
            showMenu();
          }
          break;
      }
      return true;
    }
  };

  public static interface ibt {
    void OnWrite();
  }

  public static interface iit {
    void OnWrite(int i);
  }

  private int convertDipToPixels(int i) {
    return (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
  }
}
