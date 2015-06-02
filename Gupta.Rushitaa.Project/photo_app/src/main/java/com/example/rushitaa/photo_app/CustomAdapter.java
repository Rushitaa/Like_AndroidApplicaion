package com.example.rushitaa.photo_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rushitaa on 2/22/2015.
 */
public class CustomAdapter extends ArrayAdapter<Hobbies> {

public List<Hobbies> hobby;

public CustomAdapter(Context context, int resource, List<Hobbies> hobby) {
        super(context, resource, hobby);
        this.hobby = hobby;
        }
private class ViewHolder {
    TextView code;
    CheckBox name;
}
    @Override
    public View getView( int position, View convertView, ViewGroup parent) {


        ViewHolder holder = null;

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_mylikes, null);
            holder = new ViewHolder();
            holder.code = (TextView) convertView.findViewById(R.id.item_name);
            holder.name = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

            holder.name.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    Hobbies hobby = (Hobbies) cb.getTag();
//                    Toast.makeText(getApplicationContext(),"Clicked on Checkbox: " + cb.getText() +" is " + cb.isChecked(),
//                            Toast.LENGTH_LONG).show();
                    hobby.setSelected(cb.isChecked());
                }

            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        Hobbies hob = hobby.get(position);
        holder.name.setText(hob.getName());
        holder.name.setChecked(hob.isSelected());
        holder.name.setTag(hob);

        return convertView;

    }

}













