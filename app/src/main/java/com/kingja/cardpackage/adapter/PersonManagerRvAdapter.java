package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.entiy.ChuZuWu_MenPaiAuthorizationList;
import com.kingja.cardpackage.util.ToastUtil;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/8/16 13:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PersonManagerRvAdapter extends BaseRvAdaper<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean> {
    public PersonManagerRvAdapter(Context context, List<ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean> list) {
        super(context, list);
    }

    @Override
    protected ViewHolder createViewHolder(View v) {
        return new PersonManagerViewHolder(v);
    }

    @Override
    protected int getItemView() {
        return R.layout.item_person_manager;
    }

    @Override
    protected void bindHolder(ViewHolder baseHolder, ChuZuWu_MenPaiAuthorizationList.ContentBean.PERSONNELINFOLISTBean bean, final int position) {
        PersonManagerViewHolder holder= (PersonManagerViewHolder) baseHolder;
        holder.tv_name.setText(bean.getNAME());
        holder.tv_cardId.setText(bean.getCARDID());
        holder.tv_phone.setText(bean.getPHONENUM());
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.showToast("删除");
                list.remove(position);
                notifyItemRemoved(position);
            }
        });
    }




    class PersonManagerViewHolder extends ViewHolder{
        public  TextView tv_cardId;
        public  TextView tv_phone;
        public  TextView tv_name;
        public  TextView tv_delete;
        public PersonManagerViewHolder(View itemView) {
            super(itemView);
            tv_cardId = (TextView) itemView.findViewById(R.id.tv_cardId);
            tv_phone = (TextView) itemView.findViewById(R.id.tv_phone);
            tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            tv_delete = (TextView) itemView.findViewById(R.id.tv_delete);
        }
    }
}