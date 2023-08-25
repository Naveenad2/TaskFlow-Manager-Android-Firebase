package com.example.keralavisiontaskorganizer;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

public class RecyclerCollectionCollectionAgentAdapter extends RecyclerView.Adapter<CollectionCollectionAgentViewitem> {
    public List<String> name;
    List<String> phoneNumber;

    List<String> priority;
    List<String> allreadyDone;


    private Context context;
    public RecyclerCollectionCollectionAgentAdapter(Context context, List<String> name, List<String> phoneNumber, List<String> priority,List<String> allreadyDone) {
        this.context = context;
        this.name = name;
        this.priority = priority;
        this.phoneNumber = phoneNumber;
        this.allreadyDone = allreadyDone;
    }
    public void setData(List<String> data) {
        name = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CollectionCollectionAgentViewitem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_collection_agent_item, parent, false);
        return new CollectionCollectionAgentViewitem(context,itemView,name);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionCollectionAgentViewitem holder, int position) {

        String gname = name.get(position);

        String gphoneNumber = phoneNumber.get(position);
        String gpriority = priority.get(position);

        String gallreadyDone = allreadyDone.get(position);




        holder.nametextview.setText(gname);

        // here we are displaying that is this is a complaint message
        holder.phoneNumber.setText("+91 "+gphoneNumber);

        ShapeDrawable shapeDrawable = new ShapeDrawable();
        ShapeDrawable shapeDrawableMainBox = new ShapeDrawable();

        shapeDrawable.setShape(new RoundRectShape(
                new float[]{45, 45, 45, 45, 45, 45, 45, 45}, // Corner radii
                null, null));

        shapeDrawableMainBox.setShape(new RoundRectShape(
                new float[]{45, 45, 45, 45, 45, 45, 45, 45}, // Corner radii
                null, null));

// Set the stroke and solid colors

        if (gallreadyDone.equals("null")) {

            if (Objects.equals(gpriority, "High")) {


                // ViewCompat.setBackground(holder.priority, background);

                shapeDrawable.getPaint().setColor(Color.parseColor("#FF2222"));
                shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawable.getPaint().setStrokeWidth(2);
                shapeDrawable.getPaint().setAntiAlias(true);

                shapeDrawableMainBox.getPaint().setColor(Color.parseColor("#FFFFFF"));
                shapeDrawableMainBox.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawableMainBox.getPaint().setStrokeWidth(2);
                shapeDrawableMainBox.getPaint().setAntiAlias(true);

                holder.mainBox.setBackground(shapeDrawableMainBox);

                holder.priority.setBackground(shapeDrawable);
                //   holder.priority.setBackground(gradientDrawable);

            } else if (Objects.equals(gpriority, "Medium")) {


                shapeDrawable.getPaint().setColor(Color.parseColor("#E68E0F"));
                shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawable.getPaint().setStrokeWidth(2);
                shapeDrawable.getPaint().setAntiAlias(true);

                shapeDrawableMainBox.getPaint().setColor(Color.parseColor("#FFFFFF"));
                shapeDrawableMainBox.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawableMainBox.getPaint().setStrokeWidth(2);
                shapeDrawableMainBox.getPaint().setAntiAlias(true);

                holder.mainBox.setBackground(shapeDrawableMainBox);

                holder.priority.setBackground(shapeDrawable);
                //  holder.mainBox.setBackground(shapeDrawable);
                // holder.priority.setBackground(gradientDrawable);
            } else {

                shapeDrawable.getPaint().setColor(Color.parseColor("#009D06"));
                shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawable.getPaint().setStrokeWidth(2);
                shapeDrawable.getPaint().setAntiAlias(true);

                shapeDrawableMainBox.getPaint().setColor(Color.parseColor("#FFFFFF"));
                shapeDrawableMainBox.getPaint().setStyle(Paint.Style.FILL);
                shapeDrawableMainBox.getPaint().setStrokeWidth(2);
                shapeDrawableMainBox.getPaint().setAntiAlias(true);

                holder.mainBox.setBackground(shapeDrawableMainBox);

                holder.priority.setBackground(shapeDrawable);//  holder.priority.setBackground(gradientDrawable);
            }

        }else {

            shapeDrawable.getPaint().setColor(Color.parseColor("#009D06"));
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
            shapeDrawable.getPaint().setStrokeWidth(2);
            shapeDrawable.getPaint().setAntiAlias(true);

            holder.goButton.setVisibility(View.GONE);


            // Hide the Go button


            holder.priority.setBackground(shapeDrawable);
            holder.mainBox.setBackground(shapeDrawable);

        }

    }

    @Override
    public int getItemCount() {
        return  name.size();
    }

}

