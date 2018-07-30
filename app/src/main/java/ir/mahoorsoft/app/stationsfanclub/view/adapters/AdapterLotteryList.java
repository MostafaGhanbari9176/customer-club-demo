package ir.mahoorsoft.app.stationsfanclub.view.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StLottery;

/**
 * Created by M-gh on 30-Jul-18.
 */

public class AdapterLotteryList extends RecyclerView.Adapter<AdapterLotteryList.Holder> {

    private Context context;
    private ArrayList<StLottery> source = new ArrayList<>();

    public AdapterLotteryList(Context context, ArrayList<StLottery> source) {
        this.context = context;
        this.source = source;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lottery, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        StLottery stLottery = source.get(position);
        holder.txtSubject.setText(stLottery.lSubject);
        holder.txtDate.setText(stLottery.lDate);
        holder.txtAward.setText(stLottery.lAward);
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView txtSubject;
        TextView txtDate;
        TextView txtAward;

        public Holder(View itemView) {
            super(itemView);
            txtSubject  = (TextView) itemView.findViewById(R.id.txtSubjectItemLottery);
            txtDate = (TextView) itemView.findViewById(R.id.txtDateItemLottery);
            txtAward = (TextView) itemView.findViewById(R.id.txtAwardItemLottery);
        }
    }
}
