package com.ssafy.Domain.Repository;

import com.ssafy.Domain.Entity.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, String> {
}
