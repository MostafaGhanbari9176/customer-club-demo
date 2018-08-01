package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StMessage;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StNews;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Message;

/**
 * Created by M-gh on 01-Aug-18.
 */

public class PresentMessage implements Message.OnMessageResponseListener {


    public interface OnPresentMessageListener {
        void messageFromMessage(String message);

        void flagFromMessage(boolean flag);

        void dataFromMessage(ArrayList<StMessage> messages);
    }

    private OnPresentMessageListener onPresentMessageListener;

    public PresentMessage(OnPresentMessageListener onPresentMessageListener) {
        this.onPresentMessageListener = onPresentMessageListener;
    }

    public void getMessage() {
        (new Message(this)).getMessage();
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {

    }

    @Override
    public void onReceiveMessage(ArrayList<StMessage> messages) {

        if (messages == null || messages.size() == 0)
            onPresentMessageListener.messageFromMessage("error");
        else
            onPresentMessageListener.dataFromMessage(messages);
    }

    @Override
    public void sendMessage(String message) {
        onPresentMessageListener.messageFromMessage(message);
    }
}
