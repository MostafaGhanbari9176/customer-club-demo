package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.motedavel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTicket;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class AdapterMotedavelTicketList extends RecyclerView.Adapter<AdapterMotedavelTicketList.Holder> {

    public interface OnTicketItemClick {
        void clickedTicket(int position);
    }

    private OnTicketItemClick onTicketItemClick;

    ArrayList<StTicket> source = new ArrayList<>();
    Context context;
    private int lastPosition;

    public AdapterMotedavelTicketList(Context context, ArrayList<StTicket> source, OnTicketItemClick onTicketItemClick) {
        this.context = context;
        this.source = source;
        this.onTicketItemClick = onTicketItemClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_motadevel_message, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        StTicket item = source.get(position);
        holder.txtSubject.setText(item.subject);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTicketItemClick.clickedTicket(position);
            }
        });
        lastPosition = G.setListItemsAnimation(null, new View[]{holder.txtSubject}, position, lastPosition);
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView txtSubject;
        LinearLayout item ;

        public Holder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.itemMotedavel);
            txtSubject = (TextView) itemView.findViewById(R.id.txtQuestionItemMotedavel);
        }
    }
}
