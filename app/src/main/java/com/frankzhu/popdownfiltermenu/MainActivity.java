package com.frankzhu.popdownfiltermenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.frankzhu.filtermenu.FilterMenu;
import com.frankzhu.filtermenu.adapter.DefaultFilterAdapter;
import com.frankzhu.filtermenu.adapter.FilterBaseAdapter;
import com.frankzhu.filtermenu.holder.FilterViewHolder;
import com.frankzhu.filtermenu.model.DefaultFilterModel;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private FilterMenu mFilterMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFilterMenu = (FilterMenu) findViewById(R.id.filter_menu);
        initMenuAdapters();
    }

    private void initMenuAdapters() {
        ArrayList<FilterBaseAdapter[]> mMenuAdapters = new ArrayList<>();
        ArrayList<String> titles = new ArrayList<>();

        final ArrayList<DefaultFilterModel> list1 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            DefaultFilterModel data = new DefaultFilterModel();
            data.id = String.valueOf(i);
            data.count = i;
            data.name = "Menu" + (i + 1);
            data.seconds = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                DefaultFilterModel sub = new DefaultFilterModel();
                sub.id = String.valueOf(j);
                sub.count = i;
                sub.name = "SubMenu" + (i + 1);
                data.seconds.add(sub);
            }
            list1.add(data);
        }
        final FilterBaseAdapter[] adapters = new FilterBaseAdapter[2];
        DefaultFilterAdapter firstFilterAdapter = new DefaultFilterAdapter(this, new FilterViewHolder.OnViewItemClickListener() {
            @Override
            public void onViewItemClick(int position) {
                if (mFilterMenu.getPopMenu() != null) {
                    adapters[0].setSelectIndex(position);
                    if (adapters.length == 2 && list1.get(position).seconds.size() > 0) {
                        adapters[1].clearAllItems();
                        adapters[1].addItems(list1.get(position).seconds);
                    }
                }
            }
        });
        firstFilterAdapter.addItems(list1);
        firstFilterAdapter.setIsShowFocusBg(true);
        firstFilterAdapter.setIsShowNumber(true);
        adapters[0] = firstFilterAdapter;

        final DefaultFilterAdapter secondFilterAdapter = new DefaultFilterAdapter(this, new FilterViewHolder.OnViewItemClickListener() {
            @Override
            public void onViewItemClick(int position) {
                if (mFilterMenu.getPopMenu() != null) {
                    adapters[1].setSelectIndex(position);
                }
            }
        });
        secondFilterAdapter.addItems(list1.get(0).seconds);
        adapters[1] = secondFilterAdapter;
        mMenuAdapters.add(adapters);
        mMenuAdapters.add(adapters);
        titles.add("Menu1");
        titles.add("Menu2");
        mFilterMenu.bindFilterMenuItems(mMenuAdapters, titles);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
