package com.vetc.manage.repository;

import com.vetc.manage.entity.AccountLink;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLinkRepository extends GenericRepository<AccountLink, Long>,
    JpaRepository<AccountLink, Long> {


}
