package org.pdm.ib.payments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.pdm.ib.R;
import org.pdm.ib.home.activity.HomeActivity;
import org.pdm.ib.model.Payment;
import org.pdm.ib.service.PaymentsService;
import org.pdm.ib.service.impl.PaymentsServiceImpl;

public class FragmentPayments extends Fragment {

    private Spinner spinnerChooseAccount;
    private EditText editTextAmount;
    private EditText editTextReceiverIban;
    private EditText editTextReceiverName;
    private CheckBox checkBoxSavePayment;

    private final PaymentsService paymentsService = new PaymentsServiceImpl();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_payments, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initFormItems(view);
        addButtonsEventListeners(view);
    }

    private void initFormItems(View view) {
        this.spinnerChooseAccount = view.findViewById(R.id.spinnerPaymentChooseAccount);
        this.editTextAmount = view.findViewById(R.id.editTextPaymentAmount);
        this.editTextReceiverIban = view.findViewById(R.id.editTextPaymentIban);
        this.editTextReceiverName = view.findViewById(R.id.editTextPaymentName);
        this.checkBoxSavePayment = view.findViewById(R.id.checkBoxPaymentSave);
    }

    private void addButtonsEventListeners(View view) {
        Button discardBtn = view.findViewById(R.id.btnPaymentDiscard);
        Button proceedBtn = view.findViewById(R.id.btnPaymentProceed);

        discardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), HomeActivity.class));
            }
        });

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidForm()) {
                    return;
                }

                paymentsService.makePayment(buildPayment(), getContext());

                Snackbar.make(v, R.string.payments_success_message, 2000).show();
                startActivity(new Intent(v.getContext(), HomeActivity.class));
            }
        });
    }

    private Payment buildPayment() {
        Payment payment = new Payment();

        payment.setAccountIban(spinnerChooseAccount.getSelectedItem().toString());
        payment.setAmount(Double.parseDouble(editTextAmount.getText().toString()));
        payment.setCurrency("RON");

        payment.setReceiverIban(editTextReceiverIban.getText().toString());
        payment.setReceiverName(editTextReceiverName.getText().toString());
        payment.setSavePayment(checkBoxSavePayment.isChecked());

        return payment;
    }

    private boolean isValidForm() {
        return true;
    }
}
