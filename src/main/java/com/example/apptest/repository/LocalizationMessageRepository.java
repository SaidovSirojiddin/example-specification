package com.example.apptest.repository;

import com.example.apptest.domain.LocalizationMessage;
import com.example.apptest.exceptions.ErrorTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface LocalizationMessageRepository extends JpaRepository<LocalizationMessage, UUID> {

    Optional<LocalizationMessage> findByErrorType(ErrorTypeEnum errorType);
}
