package be.hapa.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by hpacquee on 05/07/2017.
 */
@RepositoryRestResource
public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
