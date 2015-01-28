package com.samenea.banking.simia.service.customer;

import com.samenea.banking.customer.ICustomer;
import com.samenea.banking.customer.ICustomerService;
import com.samenea.banking.simia.model.SimiaUtils;
import com.samenea.banking.simia.model.repository.CustomerRepository;
import com.samenea.banking.simia.service.AbstractBankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Soroosh Sarabadani
 * Date: 1/19/13
 * Time: 3:34 PM
 */

@Service
@Transactional(value = SimiaUtils.SIMIA_TRANSACTION_MANAGER, readOnly = true)
public class CustomerService extends AbstractBankingService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ICustomer findCustomer(String customerCode, String username, String password) {
        return customerRepository.findCustomer(customerCode, username, password);
    }

    @Override
    public List<ICustomer> findCustomersOfDeposit(String depositNumber) {
        return customerRepository.findCustomersOfDeposit(depositNumber);
    }

}
