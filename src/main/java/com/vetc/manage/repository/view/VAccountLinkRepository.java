package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VAccountLink;
import com.vetc.manage.entity.view.VAccountLinkDetail;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VAccountLinkRepository extends GenericRepository<VAccountLink, Long>,
    JpaRepository<VAccountLink, Long> {


}
