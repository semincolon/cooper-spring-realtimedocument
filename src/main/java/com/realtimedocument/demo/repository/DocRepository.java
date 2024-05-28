package com.realtimedocument.demo.repository;

import com.realtimedocument.demo.model.Doc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocRepository extends JpaRepository<Doc, String> {
    List<Doc> findAllByOrderByNameAsc();
}
