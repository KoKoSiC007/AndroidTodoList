package com.example.todolist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoricalAdapter extends BaseExpandableListAdapter {

    private ArrayList<Category> mGroups;
    private Context mContext;

    public CategoricalAdapter(Context context, ArrayList<Category> groups){
        mContext = context;
        mGroups = groups;
    }

    @Override
    public int getGroupCount() {
//        Log.d("РАЗМЕР", ""+mGroups.size());
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mGroups.get(groupPosition).getTodos().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mGroups.get(groupPosition).getTodo(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category, null);
        }

        if (isExpanded){

        }else {

        }

        TextView textGroup = (TextView) convertView.findViewById(R.id.categoryName);
        textGroup.setText(mGroups.get(groupPosition).getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.todo_item, null);
        }
        CheckBox checkBox = (CheckBox) convertView.findViewById(R.id.todo);
        checkBox.setText(mGroups.get(groupPosition).getTodo(childPosition).getText());
        checkBox.setChecked(mGroups.get(groupPosition).getTodo(childPosition).isCompleted());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
