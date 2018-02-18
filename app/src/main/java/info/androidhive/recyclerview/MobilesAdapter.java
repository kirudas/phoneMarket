package info.androidhive.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MobilesAdapter extends RecyclerView.Adapter<MobilesAdapter.MyViewHolder> {

    private List<Mobile> mobilesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, subtitle;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            subtitle = (TextView) view.findViewById(R.id.subtitle);
        }
    }


    public MobilesAdapter(List<Mobile> mobilesList) {
        this.mobilesList = mobilesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mobile_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Mobile mobile = mobilesList.get(position);
        holder.title.setText(mobile.getModel());
        holder.subtitle.setText(mobile.getMarca());
    }

    @Override
    public int getItemCount() {
        return mobilesList.size();
    }
}
