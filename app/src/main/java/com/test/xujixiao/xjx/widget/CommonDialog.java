package com.test.xujixiao.xjx.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.test.xujixiao.xjx.R;

/**
 * 通用dialog
 */
public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context);
    }

    public CommonDialog(Context context, int theme) {
        super(context, theme);
    }

    protected CommonDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCanceledOnTouchOutside(false);
    }

    public static class Builder {

        private Context context;
        private String title;
        private CharSequence message;
        private String positiveButtonText;
        private String negativeButtonText;
        private float mAdd = 0;
        private float mMult = 0;
        private View.OnClickListener mContentOnClickListener;
        private View contentView;
        private DialogInterface.OnClickListener positiveButtonClickListener;
        private DialogInterface.OnClickListener negativeButtonClickListener;
        private boolean mCancelable = true;
        private boolean mCanceledOnTouchOutside;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        /**
         * Set the Dialog message from resource
         *
         * @param message
         * @return
         */
        public Builder setMessage(int message) {
            this.message = (String) context.getText(message);
            return this;
        }

        /**
         * Set the Dialog title from resource
         *
         * @param title
         * @return
         */
        public Builder setTitle(int title) {
            this.title = (String) context.getText(title);
            return this;
        }

        /**
         * Set the Dialog title from String
         *
         * @param title
         * @return
         */

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setContentView(View v) {
            this.contentView = v;
            return this;
        }


        /**
         * Sets whether the dialog is cancelable or not.  Default is true.
         *
         * @return This Builder object to allow for chaining of calls to set methods
         */
        public Builder setCancelable(boolean cancelable) {
            this.mCancelable = cancelable;
            return this;
        }

        public Builder setLineSpacing(float add, float mult) {
            this.mAdd = add;
            this.mMult = mult;
            return this;
        }

        public Builder setContentOnClickListener(View.OnClickListener contentOnClickListener) {
            this.mContentOnClickListener = contentOnClickListener;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean canceledOnTouchOutside) {
            this.mCanceledOnTouchOutside = canceledOnTouchOutside;
            return this;
        }

        /**
         * Set the positive button resource and it's listener
         *
         * @param positiveButtonText
         * @return
         */
        public Builder setPositiveButton(int positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = (String) context.getText(positiveButtonText);
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener listener) {
            this.positiveButtonText = positiveButtonText;
            this.positiveButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(int negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = (String) context.getText(negativeButtonText);
            this.negativeButtonClickListener = listener;
            return this;
        }

        public Builder setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener listener) {
            this.negativeButtonText = negativeButtonText;
            this.negativeButtonClickListener = listener;
            return this;
        }

        public CommonDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final CommonDialog commonDialog = new CommonDialog(context, R.style.PayDialog1);
            commonDialog.getWindow().setBackgroundDrawableResource(R.color.white);
            View layout = inflater.inflate(R.layout.tailorx_dialog, null);
//            commonDialog.addContentView(layout, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            commonDialog.setContentView(layout);
            MaterialRippleLayout.on(layout).rippleColor(Color.BLACK).create();
            // set the dialog title
            if (!TextUtils.isEmpty(title)) {
                ((TextView) layout.findViewById(R.id.tv_hint)).setText(title);
            }
            // set the confirm button
            if (positiveButtonText != null) {
                ((Button) layout.findViewById(R.id.positiveButton)).setText(positiveButtonText);
                (layout.findViewById(R.id.ripple2)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (positiveButtonClickListener != null) {
                            positiveButtonClickListener.onClick(commonDialog, DialogInterface.BUTTON_POSITIVE);
                        } else {
                            if (commonDialog.isShowing()) {
                                commonDialog.dismiss();
                            }
                        }
                    }
                });
            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.positiveButton).setVisibility(View.GONE);
            }
            // set the cancel button
            if (negativeButtonText != null) {
                ((Button) layout.findViewById(R.id.negativeButton)).setText(negativeButtonText);

                (layout.findViewById(R.id.ripple)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (negativeButtonClickListener != null) {
                            negativeButtonClickListener.onClick(commonDialog, DialogInterface.BUTTON_NEGATIVE);
                        } else {
                            if (commonDialog.isShowing()) {
                                commonDialog.dismiss();
                            }
                        }
                    }
                });

            } else {
                // if no confirm button just set the visibility to GONE
                layout.findViewById(R.id.negativeButton).setVisibility(View.GONE);
            }
            // set the content message
            if (message != null) {
                TextView textView = (TextView) layout.findViewById(R.id.contentTextView);
                if (null != textView) {
                    textView.setText(message);
                    if (mAdd != 0 || mMult != 0) {
                        textView.setLineSpacing(mAdd, mMult);
                    }
                    if (null != mContentOnClickListener) {
                        textView.setOnClickListener(mContentOnClickListener);
                    }
                }
            } else if (contentView != null) {
                // if no message set
                // add the contentView to the dialog body
                ((RelativeLayout) layout.findViewById(R.id.contentLinearLayout)).removeAllViews();
                ((RelativeLayout) layout.findViewById(R.id.contentLinearLayout)).addView(contentView, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            }
//            commonDialog.setContentView(layout);
            commonDialog.setCancelable(mCancelable);
            commonDialog.setCanceledOnTouchOutside(mCanceledOnTouchOutside);
            return commonDialog;
        }

        public CommonDialog show() {
            CommonDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
