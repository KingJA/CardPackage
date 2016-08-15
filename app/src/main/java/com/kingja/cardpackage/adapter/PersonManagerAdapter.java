package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.entiy.ChuZuWu_MenPaiAuthorizationList;
import com.kingja.cardpackage.ui.DrawHelperLayout;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonManagerAdapter extends BaseSimpleAdapter<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean>{
    public PersonManagerAdapter(Context context, List<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_person_info, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvname.setText(list.get(position).getNAME());
        viewHolder.tvcardId.setText("身份证号: "+list.get(position).getNAME());
        viewHolder.tvphone.setText("手机号: "+list.get(position).getNAME());

        return convertView;
    }


    public class ViewHolder {
        public final TextView tvname;
        public final TextView tvcardId;
        public final TextView tvphone;
        public final TextView tvdelete;
        public final DrawHelperLayout mDrawHelperLayout;
        public final View root;

        public ViewHolder(View root) {
            tvname = (TextView) root.findViewById(R.id.tv_name);
            tvcardId = (TextView) root.findViewById(R.id.tv_cardId);
            tvphone = (TextView) root.findViewById(R.id.tv_phone);
            tvdelete = (TextView) root.findViewById(R.id.tv_delete);
            mDrawHelperLayout = (DrawHelperLayout) root.findViewById(R.id.mDrawHelperLayout);
            this.root = root;
        }
    }
}
