package ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_list;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.G;
import ir.mahoorsoft.app.stationsfanclub.R;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTicket;
import ir.mahoorsoft.app.stationsfanclub.presenter.PresentTicket;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ActivityTicket;
import ir.mahoorsoft.app.stationsfanclub.view.activity_ticket.ticket.fragment_ticket_massage.FragmentTicketMessage;
import ir.mahoorsoft.app.stationsfanclub.view.date.DateCreator;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class FragmentTicketList extends Fragment implements PresentTicket.OnPresentTicketResponseListener, AdapterTicketList.OnTicketItemClick {

    View view;
    RecyclerView list;
    ArrayList<StTicket> source = new ArrayList<>();
    AdapterTicketList adapterTicketList;
    FloatingActionButton fab;
    public static int position = 0;


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
        ((FloatingActionButton) view.findViewById(R.id.fabFragmentTicketList)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAddTicket();
            }
        });
        list = (RecyclerView) view.findViewById(R.id.RVTicketList);
    }

    private void showDialogAddTicket() {
        final Dialog dialog = new Dialog(G.context);
        LayoutInflater li = (LayoutInflater) G.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(R.layout.dialog_get_ticket_data, null, false);

        final TextView txtSubject = (TextView) view.findViewById(R.id.txtSubjectTicketDialog);
        final TextView txtQuestion = (TextView) view.findViewById(R.id.txtQuestioDialogTicket);


        Button btnConfirm = (Button) view.findViewById(R.id.btnConfirmDialogTicket);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancelDialogTicket);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subject = txtSubject.getText().toString().trim();
                String question = txtQuestion.getText().toString().trim();
                if (checkTicketData(subject, txtSubject, question, txtQuestion)) {
                    addTicket(subject, question);
                    dialog.cancel();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.setContentView(view);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private boolean checkTicketData(String subject, TextView txtSubject, String question, TextView txtquestion) {
        if (TextUtils.isEmpty(subject)) {
            txtSubject.setError("لطفا یک عنوان وارد کنید");
            return false;
        } else if (TextUtils.isEmpty(question)) {
            txtquestion.setError("لطفا متن سوال خودرا وارد کنید");
            return false;
        }
        return true;
    }

    private void addTicket(String subject, String question) {

        StTicket stTicket = new StTicket();
        stTicket.subject = subject;
        stTicket.tiDate = DateCreator.todayDate();
        source.add(stTicket);
        adapterTicketList.notifyItemInserted(source.size() - 1);
        adapterTicketList.notifyItemRangeChanged(source.size() - 1, adapterTicketList.getItemCount());
        list.scrollToPosition(source.size() - 1);
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
        list.scrollToPosition(position);

    }

    @Override
    public void clickedTicket(int position) {
        ((ActivityTicket) (G.context)).replaceView(new FragmentTicketMessage(), source.get(position).subject);
        FragmentTicketList.position = position;
    }
}
