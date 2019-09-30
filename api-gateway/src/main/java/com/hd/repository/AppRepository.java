package com.hd.repository;

import com.hd.common.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App, Long> {
    App findByAppKey(String AppKey);

}
