

package com.nextoneday.libProcesses.wrapper;

import android.os.Parcel;

/**
 * Created by Xiaofei on 16/4/7.
 */
public class BaseWrapper {

    private boolean mIsName;

    private String mName;

    protected void setName(boolean isName, String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        mIsName = isName;
        mName = name;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mIsName ? 1 : 0);
        parcel.writeString(mName);
    }

    public void readFromParcel(Parcel in) {
        mIsName = in.readInt() == 1;
        mName = in.readString();
    }

    public boolean isName() {
        return mIsName;
    }

    public String getName() {
        return mName;
    }
}
