package com.example.vroch.hubuece.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.vroch.hubuece.R;


public class RateDialog extends DialogFragment implements TextWatcher{

    private OnRateDialogFinishedListener mListener;

    private String comment;

    public void setOnRateDialogFinishedListener(OnRateDialogFinishedListener l) {
        mListener = l;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final View customView = getActivity().getLayoutInflater().inflate(R.layout.dlg_rate, null, false);

        final EditText edtComment = (EditText)customView.findViewById(R.id.dlg_rate_EdtComment);
        //Must be done on code.
        edtComment.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
        edtComment.setText(comment);
        edtComment.setSingleLine(true);
        edtComment.setLines(7);
        edtComment.setHorizontallyScrolling(false);
        edtComment.setImeOptions(EditorInfo.IME_ACTION_DONE);
        edtComment.addTextChangedListener(this);
        edtComment.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService
                        (Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
        });

        // Rating
        final RatingBar customRatingBar = (RatingBar)customView.findViewById(R.id.dlg_rate_RatingBar);
        customRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                if ( edtComment.length() == 0 ) {
                    if ( ratingBar.getRating() == 5 ) enablePositiveButton();
                    else disablePositiveButton();
                }


            }
        });

        builder.setTitle("Avaliar cardápio");
        builder.setView(customView);
        builder.setNegativeButton("Cancelar", null);
        builder.setPositiveButton("Salvar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mListener != null) {
                    String comment = ((EditText)customView.findViewById(R.id.dlg_rate_EdtComment)).
                            getEditableText().toString();

                    float rating = customRatingBar.getRating();

                    mListener.onRateDialogSave(comment, rating);
                }
            }
        });


        return builder.create();
    }

    @Override
    public void onResume() {
        super.onResume();
        afterTextChanged(((EditText)getDialog().findViewById(R.id.dlg_rate_EdtComment)).getEditableText());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.length() > 0) {
            enablePositiveButton();
        }
        else {
            disablePositiveButton();
        }
    }

    private void enablePositiveButton() {
        ((AlertDialog)getDialog()).getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(true);
    }

    private void disablePositiveButton() {
        ((AlertDialog)getDialog()).getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(false);
    }


    public interface OnRateDialogFinishedListener {
        void onRateDialogSave(String comment, float rating);
    }
}
