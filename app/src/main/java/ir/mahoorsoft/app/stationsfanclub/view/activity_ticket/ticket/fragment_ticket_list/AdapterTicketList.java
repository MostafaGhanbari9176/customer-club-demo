package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTicket;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class AdapterTicketList extends RecyclerView.Adapter<AdapterTicketList.Holder> {

    public interface OnTicketItemClick {
        void clickedTicket(int position);
    }

    private OnTicketItemClick onTicketItemClick;

    ArrayList<StTicket> source = new ArrayList<>();
    Context context;
    private int lastPosition;

    public AdapterTicketList(Context context, ArrayList<StTicket> source, OnTicketItemClick onTicketItemClick) {
        this.context = context;
        this.source = source;
        this.onTicketItemClick = onTicketItemClick;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_ticket, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        StTicket item = source.get(position);
        holder.txtSubject.setText(item.subject);
        holder.txtDate.setText(item.tiDate);
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onTicketItemClick.clickedTicket(position);
            }
        });

        lastPosition = G.setListItemsAnimation(null, new View[]{holder.txtDate, holder.txtSubject}, position, lastPosition);
    }

    @Override
    public int getItemCount() {
        return source.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView txtSubject;
        TextView txtDate;
        LinearLayout item;

        public Holder(View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.itemTicket);
            txtDate = (TextView) itemView.findViewById(R.id.txtDateItemTicket);
            txtSubject = (TextView) itemView.findViewById(R.id.txtSubjectItemTicket);
        }
    }
}
