package com.realtimedocument.demo.repository;

import com.realtimedocument.demo.model.TextBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TextBlockRepository extends JpaRepository<TextBlock, String> {

    @Query("SELECT tb FROM TextBlock tb WHERE tb.id IN :ids ORDER BY tb.position ASC")
    List<TextBlock> findByIdInOrderByPositionAsc(@Param("ids") List<String> ids);
}
