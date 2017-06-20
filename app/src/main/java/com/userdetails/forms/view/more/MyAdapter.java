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

class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
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

        View view = inflater.inflate(R.layout.my_list_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return myList.size();
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
