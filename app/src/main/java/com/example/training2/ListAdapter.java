package com.example.training2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ListAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<HashMap<String,String>> data;
    private int fragment_position=0;
    private static LayoutInflater inflater=null;
    private String PACKAGE_NAME;

    public ListAdapter(Activity a, ArrayList<HashMap<String, String >>d, int fragment_pos){
        data =d;
        activity = a;
        fragment_position = fragment_pos;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        PACKAGE_NAME = activity.getPackageName();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup parent) {
//        View convertView;
        View vi = convertView;

        switch (fragment_position){

            case 1:
                if(convertView == null)
                    vi = inflater.inflate(R.layout.employee_lv, null);

                TextView employeeName = (TextView) vi.findViewById(R.id.namaKaryawan);
                TextView employeeNumber = (TextView) vi.findViewById(R.id.number);
                TextView employeeAddress = (TextView) vi.findViewById(R.id.address);
                TextView employeeGender = (TextView) vi.findViewById(R.id.gender);

                HashMap<String, String> empList = new HashMap<>();

                empList = data.get(position);

                employeeName.setText(empList.get("employee_name"));
                employeeName.setText(empList.get("nomor_induk_pegawai"));
                employeeName.setText(empList.get("address"));
                employeeName.setText(empList.get("gender"));

                break;

        }
        return null;
    }
}
