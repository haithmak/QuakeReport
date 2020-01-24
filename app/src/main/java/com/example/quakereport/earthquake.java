package com.example.quakereport;

public class earthquake {


    // public variables 
        private double mMagnitude ;
        private String mLocation ,murl ;
        private long  mDate ;



        public earthquake (double magnitude ,String location , long  date , String  url)
        {
            mMagnitude= magnitude;

            mLocation = location ;

            mDate= date;

            murl = url ;
        }


        public double getMagnitude ()
        {

            return  mMagnitude;
        }

        public String getLocation ()
        {

            return  mLocation;
        }

        public long  getTimeInMilliseconds ()
        {

            return  mDate;
        }


        public String getUrl ()
        {

            return  murl;
        }





}
