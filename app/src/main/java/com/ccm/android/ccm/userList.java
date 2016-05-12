package com.ccm.android.ccm;

import android.bluetooth.BluetoothDevice;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by KishanSubhashMiryala on 5/12/2016.
 */
public class userList implements Serializable {

        public userList(ArrayList<User> list){
            this.userList = list;
        }
        private static ArrayList<User> userList=null;// = new ArrayList<BluetoothDevice>();

        public ArrayList<User> getBTDeviceList() {
            return userList;
        }

        public void setBTDeviceList() {

        }
}
