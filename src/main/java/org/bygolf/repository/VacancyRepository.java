package org.bygolf.repository;

import org.bygolf.model.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("SELECT v FROM Vacancy v WHERE " +
            "(:name IS NULL OR LOWER(v.name) = LOWER(:name)) AND " +
            "(:city IS NULL OR LOWER(v.city) = LOWER(:city)) AND " +
            "(:position IS NULL OR LOWER(v.position) = LOWER(:position))")
    List<Vacancy> findVacancyByFilter(@Param("name") String name,
                                      @Param("city") String city,
                                      @Param("position") String position);

}
