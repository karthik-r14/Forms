package com.userdetails.forms.view.more;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.userdetails.forms.R;
import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public static final int TYPE1 = 0;
    public static final int TYPE2 = 1;
    private ArrayList<String> myList;
    private Context context;
    //private ListItemClickListener itemClickListener;

//    public interface ListItemClickListener {
//        void onListItemClick(int clickedItemIndex);
//    }

    public MyAdapter(ArrayList<String> myList, Context context) {
        this.myList = myList;
        this.context = context;
        //itemClickListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case TYPE1:
                View v1 = inflater.inflate(R.layout.my_list_row, parent, false);
                viewHolder = new MyViewHolder(v1);
                break;

            case TYPE2:
                View v2 = inflater.inflate(R.layout.my_list_row2, parent, false);
                viewHolder = new MyViewHolder(v2);
                break;
        }
        return (MyViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0) {
            return TYPE1;
        } else {
            return TYPE2;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        TextView textView;
        TextView serialNumber;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.my_text);
            serialNumber = (TextView) itemView.findViewById(R.id.serial_no);
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            textView.setText(myList.get(listIndex));
            serialNumber.setText(String.valueOf(listIndex + 1));
        }

        @Override
        public void onClick(View v) {
            //Intent intent = new Intent(context, FormActivity.class);
            int clickedPosition = getAdapterPosition() + 1;
            Toast.makeText(context, "item" + clickedPosition + "was clicked", Toast.LENGTH_SHORT).show();

            //itemClickListener.onListItemClick(clickedPosition);
        }
    }
}
