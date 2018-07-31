package ir.mahoorsoft.app.stationsfanclub.presenter;

import java.util.ArrayList;

import ir.mahoorsoft.app.stationsfanclub.model.struct.RFServer;
import ir.mahoorsoft.app.stationsfanclub.model.struct.StComment;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Car;
import ir.mahoorsoft.app.stationsfanclub.model.tables.Comment;

/**
 * Created by RCC1 on 7/31/2018.
 */

public class PresentComment implements Comment.OnCommentResponseListener {


    public interface OnPresentCommentResponseListener {
        void messageFromComment(String message);

        void flagFromComment(boolean flag);

        void dataFromComment(ArrayList<StComment> stComments);
    }

    private OnPresentCommentResponseListener onPresentCommentResponseListener;

    public PresentComment(OnPresentCommentResponseListener onPresentCommentResponseListener) {
        this.onPresentCommentResponseListener = onPresentCommentResponseListener;
    }

    public void getComment() {
        (new Comment(this)).getComments();
    }

    @Override
    public void onReceiveFlag(ArrayList<RFServer> res) {

    }

    @Override
    public void onReceiveComment(ArrayList<StComment> comments) {
        if (comments == null || comments.size() == 0)
            sendMessage("error");
        else
            onPresentCommentResponseListener.dataFromComment(comments);
    }

    @Override
    public void sendMessage(String message) {
        onPresentCommentResponseListener.messageFromComment(message);
    }
}
