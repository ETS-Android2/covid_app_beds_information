package com.example.covid_beds_information_app;

public class Hospital {
    private int Reg_No;
    private String Hospital_Name;
    private String Hospital_Address;
    private int Hospital_No_Of_Beds;
    private int Hospital_No_Of_Ventilators;
    private long Hospital_Phone;

    public Hospital() {
    }

    public Hospital(int reg_No, String hospital_Name, String hospital_Address, int hospital_No_Of_Beds, int hospital_No_Of_Ventilators, long hospital_Phone) {
        Reg_No = reg_No;
        Hospital_Name = hospital_Name;
        Hospital_Address = hospital_Address;
        Hospital_No_Of_Beds = hospital_No_Of_Beds;
        Hospital_No_Of_Ventilators = hospital_No_Of_Ventilators;
        Hospital_Phone = hospital_Phone;
    }

    public int getReg_No() {
        return Reg_No;
    }

    public String getHospital_Name() {
        return Hospital_Name;
    }

    public String getHospital_Address() {
        return Hospital_Address;
    }

    public int getHospital_No_Of_Beds() {
        return Hospital_No_Of_Beds;
    }

    public int getHospital_No_Of_Ventilators() {
        return Hospital_No_Of_Ventilators;
    }

    public long getHospital_Phone() {
        return Hospital_Phone;
    }

    public void setReg_No(int reg_No) {
        Reg_No = reg_No;
    }

    public void setHospital_Name(String hospital_Name) {
        Hospital_Name = hospital_Name;
    }

    public void setHospital_Address(String hospital_Address) {
        Hospital_Address = hospital_Address;
    }

    public void setHospital_No_Of_Beds(int hospital_No_Of_Beds) {
        Hospital_No_Of_Beds = hospital_No_Of_Beds;
    }

    public void setHospital_No_Of_Ventilators(int hospital_No_Of_Ventilators) {
        Hospital_No_Of_Ventilators = hospital_No_Of_Ventilators;
    }

    public void setHospital_Phone(long hospital_Phone) {
        Hospital_Phone = hospital_Phone;
    }

    @Override
    public String toString() {
        return
                " Reg_No= " + Reg_No +"\n"+
                " Hospital_Name= " + Hospital_Name + "\n" +
                " Hospital_Address= " + Hospital_Address + "\n" +
                " Hospital_No_Of_Beds= " + Hospital_No_Of_Beds +"\n"+
                " Hospital_No_Of_Ventilators= " + Hospital_No_Of_Ventilators +"\n"+
                " Hospital_Phone= " + Hospital_Phone ;
    }
}
