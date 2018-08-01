package ir.mahoorsoft.app.stationsfanclub.model.tables;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StComment;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StTicket;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class Ticket {

    public interface OnTicketResponseListener {
        void onReceiveFlag(ArrayList<RFServer> res);

        void onReceiveTicket(ArrayList<StTicket> tickets);

        void sendMessage(String message);
    }

    private OnTicketResponseListener onTicketResponseListener;

    public Ticket(OnTicketResponseListener onTicketResponseListener) {
        this.onTicketResponseListener = onTicketResponseListener;
    }

    public void getTicket() {

        ArrayList<StTicket> stTickets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            StTicket stTicket = new StTicket();
            stTicket.subject = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ " + i;
            stTicket.tiDate = "1365-04-0" + i;
            stTickets.add(stTicket);
        }

        onTicketResponseListener.onReceiveTicket(stTickets);
    }
}
