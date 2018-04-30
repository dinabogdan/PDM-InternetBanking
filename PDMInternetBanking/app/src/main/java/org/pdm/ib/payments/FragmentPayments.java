package org.pdm.ib.payments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import org.pdm.ib.R;
import org.pdm.ib.home.activity.HomeActivity;
import org.pdm.ib.model.Payment;
import org.pdm.ib.service.PaymentsService;
import org.pdm.ib.service.impl.PaymentsServiceImpl;

import static org.pdm.ib.payments.SavedPaymentListDialogFragment.SELECT_PAYMENT_NO_PAYMENT;
import static org.pdm.ib.payments.SavedPaymentListDialogFragment.SELECT_PAYMENT_OK;

public class FragmentPayments extends Fragment {

    private static final int REQUEST_CODE = 1;

    private Spinner spinnerChooseAccount;
    private Spinner spinnerChoosePaymentType;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE) return;

        if (resultCode == SELECT_PAYMENT_NO_PAYMENT) {
            Snackbar.make(getActivity().findViewById(R.id.checkBoxPaymentSave), R.string.choose_saved_payment_no_payment_message, 2000).show();
        } else if (resultCode == SELECT_PAYMENT_OK) {
            Payment payment = (Payment) data.getSerializableExtra(SavedPaymentListDialogFragment.SELECT_PAYMENT_KEY);

            populateFormWithPayment(payment);
        } else {
            this.spinnerChoosePaymentType.setSelection(0);
            this.checkBoxSavePayment.setVisibility(View.VISIBLE);
        }
    }

    private void initFormItems(View view) {
        this.spinnerChooseAccount = view.findViewById(R.id.spinnerPaymentChooseAccount);
        this.spinnerChoosePaymentType = view.findViewById(R.id.spinnerChoosePaymentType);
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
                if (!isValidForm()) return;

                paymentsService.makePayment(buildPayment(), getContext());

                Snackbar.make(v, R.string.payments_success_message, 2000).show();
                startActivity(new Intent(v.getContext(), HomeActivity.class));
            }
        });

        spinnerChoosePaymentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 1) {
                    DialogFragment dialogFragment = new SavedPaymentListDialogFragment();
                    dialogFragment.setTargetFragment(FragmentPayments.this, REQUEST_CODE);

                    dialogFragment.show(getFragmentManager(), "some-tag");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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

    private void populateFormWithPayment(Payment payment) {
        this.editTextAmount.setText(payment.getAmount().toString());
        this.editTextReceiverIban.setText(payment.getReceiverIban());
        this.editTextReceiverName.setText(payment.getReceiverName());
        this.checkBoxSavePayment.setChecked(false);
        this.checkBoxSavePayment.setVisibility(View.INVISIBLE);
    }
}
