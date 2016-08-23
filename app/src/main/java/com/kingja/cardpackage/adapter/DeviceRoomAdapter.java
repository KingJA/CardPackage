package com.kingja.cardpackage.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.kingja.cardpackage.R;
import com.kingja.cardpackage.entiy.ChuZuWu_List;
import com.kingja.cardpackage.ui.FixedListView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description：TODO
 * Create Time：2016/8/5 14:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeviceRoomAdapter extends BaseLvAdapter<ChuZuWu_List.ContentBean.RoomListBean> {
    private OnExplandListener onExplandListener;
    private Map<Integer, DeviceInfoAdapter> adapterMap = new HashMap<>();
    public DeviceRoomAdapter(Context context, List<ChuZuWu_List.ContentBean.RoomListBean> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_device_room, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tvdeviceroom.setText("房间 "+list.get(position).getROOMNO());

        final boolean expland = list.get(position).isExpland();
        if (expland) {
            viewHolder.ivdevicearrow.setBackgroundResource(R.drawable.arrow_right);
            if (getAdapter(position) != null) {
                viewHolder.lvdevice.setAdapter(getAdapter(position));
            }
            viewHolder.lvdevice.setVisibility(View.VISIBLE);

        } else {
            viewHolder.ivdevicearrow.setBackgroundResource(R.drawable.arrow_down);
            viewHolder.lvdevice.setVisibility(View.GONE);

        }
        final ViewHolder finalViewHolder = viewHolder;

        viewHolder.llroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onExplandListener != null) {
                    if (expland) {//开=》关
                        finalViewHolder.ivdevicearrow.setBackgroundResource(R.drawable.arrow_down);
                        finalViewHolder.lvdevice.setVisibility(View.GONE);
                        setVisibility(!expland, position);
                    } else {//关=》开
                        if (getAdapter(position) != null) {
                            setVisibility(!expland, position);
                            return;
                        }
                        onExplandListener.onExpland(list.get(position).getROOMID() + "", list.get(position).getROOMNO() + "", finalViewHolder.lvdevice, finalViewHolder.ivdevicearrow, position, expland);
                    }
                }
            }
        });


        return convertView;
    }




    public interface OnExplandListener {
        void onExpland(String roomid, String roomno, ListView lv, ImageView iv, int position, boolean expland);
    }

    public void setOnExplandListener(OnExplandListener onExplandListener) {

        this.onExplandListener = onExplandListener;
    }
    public void saveAdapter(int position, DeviceInfoAdapter adapter) {
        this.adapterMap.put(position, adapter);
    }
    public DeviceInfoAdapter getAdapter(int position) {
        return this.adapterMap.get(position);
    }

    public void setVisibility(boolean expand, int position) {
        list.get(position).setExpland(expand);
        this.notifyDataSetChanged();
    }
    public class ViewHolder {
        public final ImageView ivdevicearrow;
        public final TextView tvdeviceroom;
        public final LinearLayout llroom;
        public final FixedListView lvdevice;
        public final LinearLayout lldevice;
        public final View root;

        public ViewHolder(View root) {
            ivdevicearrow = (ImageView) root.findViewById(R.id.iv_device_arrow);
            tvdeviceroom = (TextView) root.findViewById(R.id.tv_device_room);
            llroom = (LinearLayout) root.findViewById(R.id.ll_room);
            lvdevice = (FixedListView) root.findViewById(R.id.lv_device);
            lldevice = (LinearLayout) root.findViewById(R.id.ll_device);
            this.root = root;
        }
    }
}
