package com.vetc.manage.repository;

import com.vetc.manage.entity.AccountLinksAudit;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLinksAuditRepository extends GenericRepository<AccountLinksAudit, Long>,
    JpaRepository<AccountLinksAudit, Long> {

}
