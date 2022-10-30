package com.nicolo.onlineshop.validation.impl;

import com.nicolo.onlineshop.dto.AddressRequest;
import com.nicolo.onlineshop.entity.Address;
import com.nicolo.onlineshop.validation.ValidationService;

public class AddressValidation implements ValidationService <AddressRequest>  {


  @Override
  public boolean checkIfExist(AddressRequest arg) throws Exception {
    return false;
  }

  @Override
  public boolean checkValidParameters(AddressRequest arg) throws Exception {
    return false;
  }
}
