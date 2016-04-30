package com.smartYummy.repository;

/**
 * Created by chenglongwei on 4/28/16.
 */

import com.smartYummy.model.Customer;
import org.springframework.data.repository.CrudRepository;

/**
 * A DAO for the entity Customer is simply created by extending the CrudRepository
 * interface provided by spring. The following methods are some of the ones
 * available from such interface: save, delete, deleteAll, findOne and findAll.
 * The magic is that such methods must not be implemented, and moreover it is
 * possible create new query methods working only by defining their signature!
 *
 * @author chenglongwei
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
