package com.kingja.cardpackage.ui;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import com.flyco.dialog.listener.OnOperItemClickL;
import com.flyco.dialog.widget.NormalListDialog;
import com.kingja.cardpackage.R;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/19 16:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DialogUtil {
    public void showDoubleDialog() {

    }
    public static NormalListDialog getListDialog(Context context, String title, BaseAdapter adapter) {
        final NormalListDialog dialog = new NormalListDialog(context, adapter);
        dialog.title(title).layoutAnimation(null) .heightScale(0.5f)
                .titleTextColor(ContextCompat.getColor(context, R.color.font_3))
                .titleBgColor(ContextCompat.getColor(context, R.color.bg_light))
                .dividerHeight(0.3f)
                .dividerColor(ContextCompat.getColor(context, R.color.bg_divider))
                .show();
        return dialog;
    }
}
