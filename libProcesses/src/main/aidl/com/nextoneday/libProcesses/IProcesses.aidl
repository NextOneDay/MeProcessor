// IProcesses.aidl
package com.nextoneday.libProcesses;

// Declare any non-default types here with import statements
import com.nextoneday.libProcesses.Mail;
import com.nextoneday.libProcesses.Reply;


interface IProcesses {

     Reply send(in Mail request);
}
