package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTicket;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentTicket;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ActivityTicket;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_massage.FragmentTicketMessage;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class FragmentTicketList extends Fragment implements PresentTicket.OnPresentTicketResponseListener, AdapterTicketList.OnTicketItemClick {

    View view;
    RecyclerView list;
    ArrayList<StTicket> source = new ArrayList<>();
    AdapterTicketList adapterTicketList;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ticket_list, container, false);
        init();
        return view;
    }

    private void init() {
        pointer();
       getDataFromServer();
    }

    private void getDataFromServer() {
        (new PresentTicket(this)).getTicket();
    }

    private void pointer() {
        list = (RecyclerView) view.findViewById(R.id.RVTicketList);
    }

    @Override
    public void messageFromTicket(String message) {

    }

    @Override
    public void flagFromTicket(boolean flag) {

    }

    @Override
    public void dataFromTicket(ArrayList<StTicket> tickets) {

        source.clear();
        source.addAll(tickets);
        adapterTicketList = new AdapterTicketList(G.context, source, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterTicketList);
        adapterTicketList.notifyDataSetChanged();

    }

    @Override
    public void clickedTicket(int position) {
        ((ActivityTicket)(G.context)).replaceView(new FragmentTicketMessage(), source.get(position).subject);
    }
}
