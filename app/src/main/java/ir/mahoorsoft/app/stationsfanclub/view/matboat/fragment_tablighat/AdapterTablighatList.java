package ir.mahoorsoft.app.stationsfanclub.view.matboat.fragment_tablighat;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTablighat;

/**
 * Created by M-gh on 30-Jul-18.
 */

public class AdapterTablighatList extends RecyclerView.Adapter<AdapterTablighatList.Holder> {

    private Context context;
    private ArrayList<StTablighat> source = new ArrayList<>();

    public AdapterTablighatList(Context context, ArrayList<StTablighat> source) {
        this.context = context;
        this.source = source;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_tablighat, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        StTablighat stLottery = source.get(position);
        holder.txtSubject.setText(stLottery.tSubject);
        Glide.with(context)
                .load("https://anetwork.ir/content/uploads/2018/03/app-for-advertising.png")
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txtSubject;
        ImageView img;

        public Holder(View itemView) {
            super(itemView);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSubjectTablighat);
            img = (ImageView) itemView.findViewById(R.id.imgItemTablighat);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            G.activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            img.getLayoutParams().height = (int) (width / 2);
        }
    }
}
