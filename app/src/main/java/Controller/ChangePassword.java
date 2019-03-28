package Controller;

import Model.AllUsers;
import Model.Customer;
import Model.Owner;

public class ChangePassword {
    private static AllUsers allUser;
    private static String userName,passWord;
    private static Owner owners = null;
    private static Customer customer = null;

    public boolean ChangePassord(String _userName,String _passWord, String _npassword, AllUsers _allUser){
        userName = _userName;
        passWord = _passWord;
        allUser = _allUser;

        owners = allUser.getOwnerBasedOnID(_userName);
        customer = allUser.getCustomerBasedOnID(_userName);

        if(owners != null && customer == null){
            if (owners.getID().equals(_userName) && owners.getPassword().equals(_passWord)){
                owners.setPassword(_npassword);
                return true;
            }
            else {
                // wrong password;
                return false;
            }
        }
        // then check the customer list
        if(owners == null && customer != null){
            if (customer.getID().equals(_userName) && customer.getPassword().equals(_passWord)){
                customer.setPassword(_npassword);
                return true;
            }
            else {
                // wrong password;
                return false;
            }
        }

        // return false if both of them are null
        return false;
    }
}
