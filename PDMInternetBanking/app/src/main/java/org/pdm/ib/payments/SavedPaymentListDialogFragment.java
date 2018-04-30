package org.pdm.ib.payments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import org.pdm.ib.R;
import org.pdm.ib.model.Payment;
import org.pdm.ib.service.PaymentsService;
import org.pdm.ib.service.impl.PaymentsServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SavedPaymentListDialogFragment extends DialogFragment {

    public static final String SELECT_PAYMENT_KEY = "SELECTED_PAYMENT_KEY";
    public static final int SELECT_PAYMENT_OK = 1;
    public static final int SELECT_PAYMENT_NO_PAYMENT = -1;
    public static final int SELECT_PAYMENT_CANCEL = 0;

    private PaymentsService paymentsService = new PaymentsServiceImpl();

    private List<Payment> savedPayments;
    private CharSequence[] savedPaymentsNameList;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        getPayments();

        if (savedPayments.isEmpty()) {
            this.dismiss();

            getTargetFragment()
                    .onActivityResult(getTargetRequestCode(), SELECT_PAYMENT_NO_PAYMENT, null);
        } else {
            builder.setTitle(R.string.choose_saved_payment)
                    .setItems(savedPaymentsNameList, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent();
                            intent.putExtra(SELECT_PAYMENT_KEY, savedPayments.get(which));

                            getTargetFragment()
                                    .onActivityResult(getTargetRequestCode(), SELECT_PAYMENT_OK, intent);
                        }
                    })
                    .setCancelable(true);
        }

        return builder.create();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        getTargetFragment()
                .onActivityResult(getTargetRequestCode(), SELECT_PAYMENT_CANCEL, null);
    }

    private void getPayments() {
        savedPayments = paymentsService.getSavedPayments(getContext());
        List <String> nameList = new ArrayList<>();

        for (Payment payment : savedPayments) {
            nameList.add(payment.getReceiverName());
        }

        this.savedPaymentsNameList = nameList.toArray(new CharSequence[nameList.size()]);
    }
}
