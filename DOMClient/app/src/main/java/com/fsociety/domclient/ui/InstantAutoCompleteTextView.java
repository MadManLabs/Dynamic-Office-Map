package com.fsociety.domclient.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class InstantAutoCompleteTextView extends AutoCompleteTextView {
	private static final String TAG = "InstantAutoCompleteText";

	public InstantAutoCompleteTextView(Context context) {
		super(context);
	}

	public InstantAutoCompleteTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InstantAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public void setError(CharSequence error) {
		if (!isPartialMatch()) {
			super.setError(error);
		} else {
			super.setError(null);
		}
	}

	private Boolean isPartialMatch() {
		for (int i = 0; i < this.getAdapter().getCount(); i++) {
			if (this.getAdapter().getItem(i).toString().toUpperCase().contains(this.getText().toString().toUpperCase())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean enoughToFilter() {
		return true;
	}

	@Override
	protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
		super.onFocusChanged(focused, direction, previouslyFocusedRect);
		if (focused) {
			performFiltering(getText(), 0);
		}
	}
}
