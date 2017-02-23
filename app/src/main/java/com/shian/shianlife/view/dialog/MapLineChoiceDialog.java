package com.shian.shianlife.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.baidu.mapapi.search.core.RouteLine;
import com.shian.shianlife.R;
import com.shian.shianlife.activity.map.RoutePlanActivity;
import com.shian.shianlife.mapapi.RouteLineAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */

public class MapLineChoiceDialog extends Dialog {


    public MapLineChoiceDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public MapLineChoiceDialog(Context context, List<? extends RouteLine> transitRouteLines, RouteLineAdapter.Type
            type) {
        this(context, 0);
        mtransitRouteLines = transitRouteLines;
        mTransitAdapter = new RouteLineAdapter(context, mtransitRouteLines, type);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }


    private List<? extends RouteLine> mtransitRouteLines;
    private ListView transitRouteList;
    private RouteLineAdapter mTransitAdapter;
    private MapLineChoiceCallBack mapLineChoiceCallBack;

    public void setMapLineChoiceCallBack(MapLineChoiceCallBack mapLineChoiceCallBack) {
        this.mapLineChoiceCallBack = mapLineChoiceCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_map_line_choice);

        transitRouteList = (ListView) findViewById(R.id.transitList);
        transitRouteList.setAdapter(mTransitAdapter);
        transitRouteList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mapLineChoiceCallBack.setMapLine(position);
                dismiss();
            }
        });
    }

    public interface MapLineChoiceCallBack{
        void setMapLine(int position);
    }

}
