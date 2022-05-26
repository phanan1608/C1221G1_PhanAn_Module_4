package com.codegym.furama_resort.repository;

import com.codegym.furama_resort.entity.Contract;
import com.codegym.furama_resort.service.ITotalMoney;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IContractRepository extends JpaRepository<Contract,Integer> {
        Page<Contract> findAll(Pageable pageable);

        @Query(value="select customer.customer_id as customerId,\n" +
                "       customer.customer_name as customerName,\n" +
                "       customer_type.customer_type_name as customerTypeName,\n" +
                "       service.service_name as serviceName,\n" +
                "       contract.contract_start_date as startDate,\n" +
                "       GROUP_CONCAT(attach_service.attach_service_name) as  attachServiceName,\n" +
                "       contract.contract_end_date as endDate,\n" +
                "       sum(service.service_cost + coalesce(contract_detail.quantity * attach_service.attach_service_cost, 0)) as total\n" +
                "from contract\n" +
                "         join customer on contract.customer_id = customer.customer_id\n" +
                "         left join customer_type on customer.customer_type_id = customer_type.customer_type_id\n" +
                "         left join contract_detail on contract.contract_id = contract_detail.contract_id\n" +
                "         left join service on contract.service_id = service.service_id\n" +
                "         left join attach_service on contract_detail.attach_service_id = attach_service.attach_service_id\n" +
                "group by contract.contract_id\n",
                countQuery="select customer.customer_id customerId,\n" +
                        "       customer.customer_name customerName,\n" +
                        "       customer_type.customer_type_name customerTypeName,\n" +
                        "       service.service_name serviceName,\n" +
                        "       contract.contract_start_date startDate,\n" +
                        "       GROUP_CONCAT(attach_service.attach_service_name) attachServiceName,\n" +
                        "       contract.contract_end_date endDate,\n" +
                        "       sum(service.service_cost + coalesce(contract_detail.quantity * attach_service.attach_service_cost, 0)) as total\n" +
                        "from contract\n" +
                        "         join customer on contract.customer_id = customer.customer_id\n" +
                        "         left join customer_type on customer.customer_type_id = customer_type.customer_type_id\n" +
                        "         left join contract_detail on contract.contract_id = contract_detail.contract_id\n" +
                        "         left join service on contract.service_id = service.service_id\n" +
                        "         left join attach_service on contract_detail.attach_service_id = attach_service.attach_service_id\n" +
                        "group by contract.contract_id"
                ,                nativeQuery=true)
        Page<ITotalMoney> findByTotal(Pageable pageable);

}
