package com.digitox.authapplicationv3.recyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.recyclerview.widget.RecyclerView;

import com.digitox.authapplicationv3.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> implements Filterable {
    private ArrayList<User> userList;
    private ArrayList<User> filteredUserList;
    private Context context;
    private CustomItemClickListener customItemClickListener;

    public CustomAdapter2(Context context, ArrayList<User> userArrayList, CustomItemClickListener customItemClickListener) {
        this.context = context;
        this.userList = userArrayList;
        this.filteredUserList = userArrayList;
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    public CustomAdapter2.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_titles, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for click item listener
                customItemClickListener.onItemClick(filteredUserList.get(myViewHolder.getAdapterPosition()),myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CustomAdapter2.MyViewHolder viewHolder, int position) {

//        viewHolder.userId.setText(filteredUserList.get(position).getUserId());
//        viewHolder.id.setText(filteredUserList.get(position).getId());
        viewHolder.userTitle.setText(filteredUserList.get(position).getTitle());
        //viewHolder.userBody.setText(filteredUserList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    filteredUserList = userList;

                } else {

                    ArrayList<User> tempFilteredList = new ArrayList<>();

                    for (User user : userList) {

                        // search for user title
                        if (user.getTitle().toLowerCase().contains(searchString)) {

                            tempFilteredList.add(user);
                        }
                    }

                    filteredUserList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredUserList = (ArrayList<User>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView userId;
        private MaterialTextView id;
        private MaterialTextView userBody;
        private MaterialTextView userTitle;
        public MyViewHolder(View view) {
            super(view);
//            userId = (MaterialTextView)view.findViewById(R.id.userId);
//            id = (MaterialTextView)view.findViewById(R.id.Id);
            userTitle = (MaterialTextView)view.findViewById(R.id.userTitle);
//            userBody = (MaterialTextView)view.findViewById(R.id.userBody);

        }
    }
}
