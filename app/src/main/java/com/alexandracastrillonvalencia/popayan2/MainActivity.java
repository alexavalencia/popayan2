package com.alexandracastrillonvalencia.popayan2;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static com.alexandracastrillonvalencia.popayan2.R.array;
import static com.alexandracastrillonvalencia.popayan2.R.drawable;
import static com.alexandracastrillonvalencia.popayan2.R.id;
import static com.alexandracastrillonvalencia.popayan2.R.layout;
import static com.alexandracastrillonvalencia.popayan2.R.string;

public class MainActivity extends AppCompatActivity {
    private ImageView icon;
    private TextView nombre;
    private String[] menutitles;
    private DrawerLayout drawer_layout;
    private ListView lst;
    private ActionBarDrawerToggle drawerToggle;
    private CharSequence activityTitle = "Papaya";
    private CharSequence itemTitle;
    private int flag;
    android.support.v4.app.Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        itemTitle = activityTitle = getTitle();
        menutitles = getResources().getStringArray(array.menu);
        drawer_layout = (DrawerLayout) findViewById(id.drawer_layout);
        lst = (ListView) findViewById(id.left_drawer);


        ArrayList<DrawerItem> items = new ArrayList<DrawerItem>();
        items.add(new DrawerItem(menutitles[0], drawable.ic_hotel));
        items.add(new DrawerItem(menutitles[1], drawable.bar));
        items.add(new DrawerItem(menutitles[2], drawable.turis));
        items.add(new DrawerItem(menutitles[3], drawable.ic_demografia));


        lst.setAdapter(new drawerAdapter(this, items));
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                fragment = null;
                int op;
                op = position;
                switch (op) {
                    case 0:
                        fragment = new Fragment1();
                        flag = 1;
                        break;
                    case 1:
                        fragment = new Fragment2();
                        flag = 2;
                        break;
                    case 2:
                        fragment = new Fragment3();
                        flag = 3;
                        break;
                    case 3:
                        fragment = new Fragment4();
                        flag = 4;
                        break;
                    default:
                        fragment=new Fragment_ini();
                        flag = 5;
                        break;

                }

                getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragm, fragment).commit();
                lst.setItemChecked(op, true);
                getSupportActionBar().setTitle(menutitles[op]);

                drawer_layout.closeDrawer(lst);


            }
        });
        drawerToggle = new ActionBarDrawerToggle(this, drawer_layout, string.drawer_open, string.drawer_close) {
            public void onDrawerClosed(View view) {

            }

            public void onDrawerOpened(View drawerView) {


            }
        };
        drawer_layout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
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
        if (id == R.id.action_acerca) {
            Intent i = new Intent(this, acerca.class);
            startActivity(i);

        }
        if (drawerToggle.onOptionsItemSelected(item)) {
            // Toma los eventos de selecciÃ³n del toggle aquÃ­
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    public class drawerAdapter extends ArrayAdapter {
        public drawerAdapter(Context context, List objects) {
            super(context, 0, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(layout.listopcion, null);
            }

            icon = (ImageView) convertView.findViewById(id.icono);
            nombre = (TextView) convertView.findViewById(id.name);

            DrawerItem item = (DrawerItem) getItem(position);
            icon.setImageResource(item.getIconId());
            nombre.setText(item.getName());
            return convertView;

        }
    }

    @Override
    public void onBackPressed() {
        fragment= null;
        if(flag==1 || flag==2 || flag==3 || flag==4 ){
            fragment=new Fragment_ini();
            getSupportFragmentManager().beginTransaction().replace(R.id.cont_fragm, fragment).commit();
            getSupportActionBar().setTitle(menutitles[4]);
            flag=0;
        }else {
            super.finish();
        }


    }


       //




}


