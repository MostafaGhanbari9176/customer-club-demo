package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StComment;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTicket;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Ticket;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class PresentTicket implements Ticket.OnTicketResponseListener {

    public interface OnPresentTicketResponseListener {
        void messageFromTicket(String message);

        void flagFromTicket(boolean flag);

        void dataFromTicket(ArrayList<StTicket> tickets);
    }

    private OnPresentTicketResponseListener onPresentTicketResponseListener;

    public PresentTicket(OnPresentTicketResponseListener onPresentTicketResponseListener) {
        this.onPresentTicketResponseListener = onPresentTicketResponseListener;
    }

    public void getTicket() {
        (new Ticket(this)).getTicket();
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {

    }

    @Override
    public void onReceiveTicket(ArrayList<StTicket> tickets) {
        if (tickets == null || tickets.size() == 0)
            onPresentTicketResponseListener.messageFromTicket("error");
        else
            onPresentTicketResponseListener.dataFromTicket(tickets);
    }

    @Override
    public void sendMessage(String message) {
        onPresentTicketResponseListener.messageFromTicket(message);
    }
}
