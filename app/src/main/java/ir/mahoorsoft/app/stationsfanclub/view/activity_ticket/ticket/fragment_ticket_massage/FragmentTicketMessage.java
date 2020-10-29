package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_massage;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StMessage;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Message;
import ir.mahoorsoft.app.stationsfanclub.view.date.DateCreator;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class FragmentTicketMessage extends Fragment implements Message.OnMessageResponseListener {

    View view;
    RecyclerView list;
    ArrayList<StMessage> source = new ArrayList<>();
    AdapterTicketMessage adapterTicketMessage;
    TextView txtMEssage;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ticket_message, container, false);
        init();
        return view;
    }

    private void init() {
        pointer();
        getDataFromServer();
    }

    private void pointer() {
        txtMEssage = (TextView) view.findViewById(R.id.txtTicketMessage);
        ((ImageView) view.findViewById(R.id.imgBtnSendTicket)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkMessageData();
            }
        });
        list = (RecyclerView) view.findViewById(R.id.RVTicketMessage);
    }

    private void checkMessageData(){
        if(TextUtils.isEmpty(txtMEssage.getText().toString().trim()))
        {
            txtMEssage.setError("هیچ متنی وارد نشده");
        }
        else
            sendDataForServer();
    }

    private void sendDataForServer()
    {
        StMessage message = new StMessage();
        message.qustionText = txtMEssage.getText().toString().trim();
        message.qustionDate = DateCreator.todayDate();
        source.add(source.size() , message);
        adapterTicketMessage.notifyItemInserted(source.size()-1);
        adapterTicketMessage.notifyItemRangeChanged(source.size()-1, adapterTicketMessage.getItemCount());
        list.scrollToPosition(source.size()-1);
        txtMEssage.setText("");
    }

    private void getDataFromServer() {
        (new Message(this)).getMessage();
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {

    }

    @Override
    public void onReceiveMessage(ArrayList<StMessage> messages) {
        source.clear();
        source.addAll(messages);
        adapterTicketMessage = new AdapterTicketMessage(G.context, source);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(G.context, LinearLayout.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        list.setAdapter(adapterTicketMessage);
        list.setItemAnimator(new DefaultItemAnimator());
        adapterTicketMessage.notifyDataSetChanged();
        list.scrollToPosition(source.size()-1);
    }

    @Override
    public void sendMessage(String message) {

    }
}
