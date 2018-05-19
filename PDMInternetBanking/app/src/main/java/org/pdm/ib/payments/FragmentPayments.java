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
import android.widget.Toast;

import org.pdm.ib.R;
import org.pdm.ib.command.AccountCommand;
import org.pdm.ib.context.AccountContextHolder;
import org.pdm.ib.converter.impl.AccountConverter;
import org.pdm.ib.home.activity.HomeActivity;
import org.pdm.ib.model.Account;
import org.pdm.ib.model.Payment;
import org.pdm.ib.retrofit.RetrofitAPIService;
import org.pdm.ib.service.AccountService;
import org.pdm.ib.service.PaymentsService;
import org.pdm.ib.service.impl.AccountServiceImpl;
import org.pdm.ib.service.impl.PaymentsServiceImpl;
import org.pdm.ib.util.Validation;

import java.math.BigDecimal;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

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
    static AtomicBoolean atomicBoolean = new AtomicBoolean(true);


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
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(new Intent(v.getContext(), HomeActivity.class));
                            }
                        });
                    }
                }).start();

            }
        });

        proceedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidForm()) {
                    return;
                } else if (isValidForm() == true) {
                    paymentsService.makePayment(buildPayment(), getContext());
                    Snackbar.make(v, R.string.payments_success_message, 2000).show();
                    startActivity(new Intent(v.getContext(), HomeActivity.class));
                }
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
        AccountService accountService = new AccountServiceImpl();
        String amount = editTextAmount.getText().toString();
        Thread thread = null;
        if (spinnerChooseAccount.getSelectedItem().toString().equals(getResources().getString(R.string.current_account_title))) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Account currentAccount = accountService.getCurrentAccount();
                    boolean validateAmount = Validation.validateAmount(amount, BigDecimal.valueOf(currentAccount.getBalance().getAmount()));
                    if (!validateAmount) {
                        atomicBoolean.set(false);
                    } else {
                        AccountConverter accountConverter = AccountConverter.anAccountConverter();
                        AccountCommand accountCommand = accountConverter.convertToCommand(currentAccount);
                        BigDecimal transactionAmount = new BigDecimal(amount);
                        RetrofitAPIService retrofitAPIService = RetrofitAPIService.aRetrofitApiService();
                        retrofitAPIService.performTransaction(accountCommand, transactionAmount);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (validateAmount == false) {
                                Toast.makeText(getContext(), R.string.payments_incorrect_amount, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
            thread.start();
        } else if (spinnerChooseAccount.getSelectedItem().toString().equals(getResources().getString(R.string.savings_account_title))) {
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    Account savingsAccount = accountService.getSavingsAccount();
                    boolean validateAmount = Validation.validateAmount(amount, BigDecimal.valueOf(savingsAccount.getBalance().getAmount()));
                    if (!validateAmount) {
                        atomicBoolean.set(false);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (atomicBoolean.get() == false) {
                                Toast.makeText(getContext(), R.string.payments_incorrect_amount, Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
            thread.start();
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String receiverIban = editTextReceiverIban.getText().toString();
        if (!Validation.validateIban(receiverIban)) {
            Toast.makeText(getContext(), R.string.error_incorrect_iban, Toast.LENGTH_LONG).show();
            return false;
        }

        String receiverName = editTextReceiverName.getText().toString();
        if (receiverName == null || receiverName.isEmpty()) {
            Toast.makeText(getContext(), R.string.error_incorrect_receiver_name, Toast.LENGTH_LONG).show();
            return false;
        }
        return atomicBoolean.get();
    }

    private void populateFormWithPayment(Payment payment) {
        this.editTextAmount.setText(payment.getAmount().toString());
        this.editTextReceiverIban.setText(payment.getReceiverIban());
        this.editTextReceiverName.setText(payment.getReceiverName());
        this.checkBoxSavePayment.setChecked(false);
        this.checkBoxSavePayment.setVisibility(View.INVISIBLE);
    }
}
