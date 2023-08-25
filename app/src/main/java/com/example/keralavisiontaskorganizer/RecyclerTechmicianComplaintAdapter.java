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

public class RecyclerTechmicianComplaintAdapter  extends RecyclerView.Adapter<TechnicianComplaintView> {

    public List<String> name;
    List<String> phoneNumber;
    List<String> address;
    List<String> priority;
    List<String> task;

    private Context context;

    public RecyclerTechmicianComplaintAdapter(Context context, List<String> name, List<String> address, List<String> priority, List<String> task) {
        this.context = context;
        this.name = name;

        this.address = address;
        this.priority = priority;
        this.task = task;
    }
    public void setData(List<String> data) {
        name = data;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TechnicianComplaintView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.technician_complaint_item, parent, false);
        return new TechnicianComplaintView(context,itemView,name);
    }

    @Override
    public void onBindViewHolder(@NonNull TechnicianComplaintView holder, int position) {

        String gname = name.get(position);

        String gaddress = address.get(position);
        String gpriority = priority.get(position);
        String gtask = task.get(position);

        holder.nametextview.setText(gname);
        holder.addresstextview.setText("New Complaint");
        holder.addresstextview.setTextColor(Color.parseColor("#009688"));

        holder.tasktextview.setText(gtask);


        ShapeDrawable shapeDrawable = new ShapeDrawable();
        shapeDrawable.setShape(new RoundRectShape(
                new float[]{45, 45, 45, 45, 45, 45, 45, 45}, // Corner radii
                null, null));

// Set the stroke and solid colors

        if(Objects.equals(gpriority, "High")){
            //Drawable background = new ColorDrawable(ContextCompat.getColor(context, R.color.black));

            // ViewCompat.setBackground(holder.priority, background);

            shapeDrawable.getPaint().setColor(Color.parseColor("#FF2222"));
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
            shapeDrawable.getPaint().setStrokeWidth(2);
            shapeDrawable.getPaint().setAntiAlias(true);

            holder.priority.setBackground(shapeDrawable);
            //   holder.priority.setBackground(gradientDrawable);

        } else if (Objects.equals(gpriority, "Medium")) {


            shapeDrawable.getPaint().setColor(Color.parseColor("#E68E0F"));
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
            shapeDrawable.getPaint().setStrokeWidth(2);
            shapeDrawable.getPaint().setAntiAlias(true);

            holder.priority.setBackground(shapeDrawable);
            // holder.priority.setBackground(gradientDrawable);
        }else{

            shapeDrawable.getPaint().setColor(Color.parseColor("#009D06"));
            shapeDrawable.getPaint().setStyle(Paint.Style.FILL);
            shapeDrawable.getPaint().setStrokeWidth(2);
            shapeDrawable.getPaint().setAntiAlias(true);

            holder.priority.setBackground(shapeDrawable);//  holder.priority.setBackground(gradientDrawable);
        }


    }

    @Override
    public int getItemCount() {
        return  name.size();
    }


}

