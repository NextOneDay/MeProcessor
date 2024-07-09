
package com.nextoneday.libProcesses;

import android.os.Parcel;
import android.os.Parcelable;

import com.nextoneday.libProcesses.wrapper.ParameterWrapper;
import com.nextoneday.libProcesses.wrapper.TypeWrapper;


public class Reply implements Parcelable {

    private final static TypeCenter TYPE_CENTER = TypeCenter.getInstance();

    private int mErrorCode;

    private String mErrorMessage;

    private TypeWrapper mClass;

    private Object mResult;

    public static final Creator<Reply> CREATOR
            = new Creator<Reply>() {
        public Reply createFromParcel(Parcel in) {
            Reply reply = new Reply();
            reply.readFromParcel(in);
            return reply;
        }

        public Reply[] newArray(int size) {
            return new Reply[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(mErrorCode);
        parcel.writeString(mErrorMessage);
        parcel.writeParcelable(mClass, flags);
        try {
            parcel.writeString(CodeUtils.encode(mResult));
        } catch (HermesException e) {
            e.printStackTrace();
        }
    }


    public void readFromParcel(Parcel in) {
        mErrorCode = in.readInt();
        ClassLoader classLoader = Reply.class.getClassLoader();
        mErrorMessage = in.readString();
        mClass = in.readParcelable(classLoader);
        try {
            Class<?> clazz = TYPE_CENTER.getClassType(mClass);
            mResult = CodeUtils.decode(in.readString(), clazz);
        } catch (Exception e) {

        }
    }

    private Reply() {

    }

    public Reply(ParameterWrapper parameterWrapper) {
        try {
            Class<?> clazz = TYPE_CENTER.getClassType(parameterWrapper);
            mResult = CodeUtils.decode(parameterWrapper.getData(), clazz);
            mErrorCode = ErrorCodes.SUCCESS;
            mErrorMessage = null;
            mClass = new TypeWrapper(clazz);
        } catch (HermesException e) {
            e.printStackTrace();
            mErrorCode = e.getErrorCode();
            mErrorMessage = e.getMessage();
            mResult = null;
            mClass = null;
        }
    }

    public Reply(int errorCode, String message) {
        mErrorCode = errorCode;
        mErrorMessage = message;
        mResult = null;
        mClass = null;
    }

    public int getErrorCode() {
        return mErrorCode;
    }

    public boolean success() {
        return mErrorCode == ErrorCodes.SUCCESS;
    }

    public String getMessage() {
        return mErrorMessage;
    }

    public Object getResult() {
        return mResult;
    }
}
