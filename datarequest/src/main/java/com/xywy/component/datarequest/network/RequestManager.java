package com.xywy.component.datarequest.network;

import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.xywy.component.datarequest.tool.AssertDebug;

public class RequestManager {

	private static final int DEFAULT_TIMEOUT_TIMER = 10000;

	private static RequestQueue mRequestQueue;

	private RequestManager() {
	}

	public static void init(Context context) {
		if(mRequestQueue == null){
			mRequestQueue = Volley.newRequestQueue(context);
		}
	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue != null) {
			return mRequestQueue;
		} else {
			throw new IllegalStateException("RequestQueue not initialized");
		}
	}
	
	public static void addRequest(Request<?> request, Object tag) {
		if (mRequestQueue == null) {
			throw new IllegalStateException("RequestQueue not initialized");
		}

		AssertDebug.Assert(request);

		if (tag != null) {
            request.setTag(tag);
        }

		if(request.getRetryPolicy() == null) {
			request.setRetryPolicy(new DefaultRetryPolicy(
					DEFAULT_TIMEOUT_TIMER,
					DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
					DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		}

        mRequestQueue.add(request);
    }
	
	public static void cancelAll(Object tag) {
		if (mRequestQueue == null) {
			throw new IllegalStateException("RequestQueue not initialized");
		}
        mRequestQueue.cancelAll(tag);
    }
}
