package com.lms.app.repository;

import com.lms.app.model.entities.Blob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlobRepository extends JpaRepository<Blob, Long> { }
