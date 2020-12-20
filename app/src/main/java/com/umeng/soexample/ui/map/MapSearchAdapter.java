package com.umeng.soexample.ui.map;

import android.content.Context;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;
import com.umeng.soexample.R;
import com.umeng.soexample.base.BaseAdapter;

import java.util.ArrayList;

public class MapSearchAdapter extends BaseAdapter<PoiInfo> {

    public MapSearchAdapter(ArrayList<PoiInfo> List, Context context) {
        super( List, context );
    }

    @Override
    protected int getLayout() {
        return R.layout.mapsearch_item;
    }

    @Override
    protected void bindData(PoiInfo data, VH vh) {
        TextView desc = (TextView) vh.getViewById( R.id.tv_desc );
        desc.setText( data.name+"   "+data.address );
    }


}
